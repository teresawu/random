package app.co.uk.tensorflow.util

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.os.SystemClock
import android.os.Trace
import app.co.uk.tensorflow.util.Keys.DIM_BATCH_SIZE
import app.co.uk.tensorflow.util.Keys.DIM_IMG_SIZE_X
import app.co.uk.tensorflow.util.Keys.DIM_IMG_SIZE_Y
import app.co.uk.tensorflow.util.Keys.DIM_PIXEL_SIZE
import app.co.uk.tensorflow.util.Keys.MAX_RESULTS
import org.tensorflow.lite.Interpreter
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import java.util.*

class ImageClassifier constructor() {

    private var tfLite: Interpreter? = null
    private lateinit var labelProb: Array<ByteArray>

    // Pre-allocated buffers.
    private val labels = Vector<String>()
    private var intValues: IntArray? = null
    private lateinit var imgData: ByteBuffer

    /** Writes Image data into a `ByteBuffer`.  */
    private fun convertBitmapToByteBuffer(bitmap: Bitmap) {
        if (imgData == null) return
        imgData!!.rewind()
        bitmap.getPixels(intValues, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
        // Convert the image to floating point.
        var pixel = 0
        val startTime = SystemClock.uptimeMillis()
        for (i in 0 until DIM_IMG_SIZE_X) {
            for (j in 0 until DIM_IMG_SIZE_Y) {
                val `val` = intValues!![pixel++]
                imgData!!.put((`val` shr 16 and 0xFF).toByte())
                imgData!!.put((`val` shr 8 and 0xFF).toByte())
                imgData!!.put((`val` and 0xFF).toByte())
            }
        }
        val endTime = SystemClock.uptimeMillis()
    }

    fun recognizeImage(bitmap: Bitmap): List<Result> {
        // Log this method so that it can be analyzed with systrace.
        Trace.beginSection("recognizeImage")

        Trace.beginSection("preprocessBitmap")

        var startTime: Long
        val endTime: Long
        startTime = SystemClock.uptimeMillis()

        convertBitmapToByteBuffer(bitmap)

        // Run the inference call.
        Trace.beginSection("run")
        startTime = SystemClock.uptimeMillis()
        tfLite!!.run(imgData, labelProb)
        endTime = SystemClock.uptimeMillis()
        Trace.endSection()

        // Find the best classifications.
        val pq = PriorityQueue<Result>(
                3,
                Comparator<Result> { lhs, rhs ->
                    // Intentionally reversed to put high confidence at the head of the queue.
                    java.lang.Float.compare(rhs.confidence!!, lhs.confidence!!)
                })
        for (i in labels.indices) {
            pq.add(Result(
                    "" + i,
                    if (labels.size > i) labels[i] else "unknown",
                    labelProb[0][i].toFloat(), null))
        }
        val recognitions = ArrayList<Result>()
        val recognitionsSize = Math.min(pq.size, MAX_RESULTS)
        for (i in 0 until recognitionsSize) {
            recognitions.add(pq.poll())
        }
        Trace.endSection() // "recognizeImage"
        return recognitions
    }

    companion object {
        /** Memory-map the model file in Assets.  */
        @Throws(IOException::class)
        private fun loadModelFile(assets: AssetManager, modelFilename: String): MappedByteBuffer {
            val fileDescriptor = assets.openFd(modelFilename)
            val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
            val fileChannel = inputStream.channel
            val startOffset = fileDescriptor.startOffset
            val declaredLength = fileDescriptor.declaredLength
            return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
        }

        /**
         * Initializes a native TensorFlow session for classifying images.
         *
         * @param assetManager The asset manager to be used to load assets.
         * @param modelFilename The filepath of the model GraphDef protocol buffer.
         * @param labelFilename The filepath of label file for classes.
         * @param inputSize The input size. A square image of inputSize x inputSize is assumed.
         * @throws IOException
         */
        fun create(
                assetManager: AssetManager, modelFilename: String, labelFilename: String, inputSize: Int): ImageClassifier {
            val c = ImageClassifier()
            var br: BufferedReader? = null
            try {
                br = BufferedReader(InputStreamReader(assetManager.open(labelFilename)))
                while (true) {
                    val line = br.readLine() ?: break
                    c.labels.add(line)
                }
                br.close()
            } catch (e: IOException) {
                throw RuntimeException("Problem reading label file!", e)
            }

            c.imgData = ByteBuffer.allocateDirect(
                    DIM_BATCH_SIZE * DIM_IMG_SIZE_X * DIM_IMG_SIZE_Y * DIM_PIXEL_SIZE)

            c.imgData!!.order(ByteOrder.nativeOrder())
            try {
                c.tfLite = Interpreter(loadModelFile(assetManager, modelFilename))
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
            // Pre-allocate buffers.
            c.intValues = IntArray(inputSize * inputSize)
            c.labelProb = Array(1) { ByteArray(c.labels.size) }
            return c
        }
    }

    fun close() {
        tfLite?.close()
    }
}