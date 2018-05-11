//package app.co.uk.tensorflow.util
//
//import android.content.res.AssetManager
//import android.graphics.Bitmap
//import android.os.Trace
//import app.co.uk.tensorflow.model.Result
//import app.co.uk.tensorflow.util.Keys.DIM_BATCH_SIZE
//import app.co.uk.tensorflow.util.Keys.DIM_IMG_SIZE_X
//import app.co.uk.tensorflow.util.Keys.DIM_IMG_SIZE_Y
//import app.co.uk.tensorflow.util.Keys.DIM_PIXEL_SIZE
//import app.co.uk.tensorflow.util.Keys.INPUT_NAME
//import app.co.uk.tensorflow.util.Keys.INPUT_SIZE
//import app.co.uk.tensorflow.util.Keys.LABEL_PATH
//import app.co.uk.tensorflow.util.Keys.MAX_RESULTS
//import app.co.uk.tensorflow.util.Keys.MODEL_PATH
//import app.co.uk.tensorflow.util.Keys.OUTPUT_NAME
//import io.reactivex.Single
//import org.tensorflow.contrib.android.TensorFlowInferenceInterface
////import org.tensorflow.lite.Interpreter
//import java.io.BufferedReader
//import java.io.FileInputStream
//import java.io.IOException
//import java.io.InputStreamReader
//import java.nio.ByteBuffer
//import java.nio.ByteOrder
//import java.nio.MappedByteBuffer
//import java.nio.channels.FileChannel
//import java.util.*
//
//class ImageClassifier constructor(private val assetManager: AssetManager) {
//    private var inferenceInterface: TensorFlowInferenceInterface? = null
//    private val labels = Vector<String>()
//    private val intValues by lazy { IntArray(INPUT_SIZE * INPUT_SIZE) }
//    private var imgData: ByteBuffer
//    private var outputs: FloatArray? = null
//    private var floatValues: FloatArray = FloatArray(INPUT_SIZE * INPUT_SIZE * 3)
//
//    init {
//        try {
//            val br = BufferedReader(InputStreamReader(assetManager.open(LABEL_PATH)))
//            while (true) {
//                val line = br.readLine() ?: break
//                labels.add(line)
//            }
//            br.close()
//        } catch (e: IOException) {
//            throw RuntimeException("Problem reading label file!", e)
//        }
//        imgData = ByteBuffer.allocateDirect(DIM_BATCH_SIZE * DIM_IMG_SIZE_X * DIM_IMG_SIZE_Y * DIM_PIXEL_SIZE)
//        imgData!!.order(ByteOrder.nativeOrder())
//        try {
//            inferenceInterface = TensorFlowInferenceInterface(assetManager, MODEL_PATH)
//        } catch (e: Exception) {
//            throw RuntimeException(e)
//        }
//    }
//
//
//    private fun convertBitmapToByteBuffer(bitmap: Bitmap) {
//        if (imgData == null) return
//        imgData!!.rewind()
//        bitmap.getPixels(intValues, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
//        var pixel = 0
//        for (i in 0 until DIM_IMG_SIZE_X) {
//            for (j in 0 until DIM_IMG_SIZE_Y) {
//                val value = intValues!![pixel++]
//                imgData!!.put((value shr 16 and 0xFF).toByte())
//                imgData!!.put((value shr 8 and 0xFF).toByte())
//                imgData!!.put((value and 0xFF).toByte())
//            }
//        }
//    }
//
//    private fun loadModelFile(assets: AssetManager, modelFilename: String): MappedByteBuffer {
//        val fileDescriptor = assets.openFd(modelFilename)
//        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
//        val fileChannel = inputStream.channel
//        val startOffset = fileDescriptor.startOffset
//        val declaredLength = fileDescriptor.declaredLength
//        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
//    }
//
//    fun recognizeImage(bitmap: Bitmap): Single<List<Result>> {
//        return Single.just(bitmap).flatMap {
//            convertBitmapToByteBuffer(it)
//            inferenceInterface!!.feed(INPUT_NAME, floatValues, 1, INPUT_SIZE.toLong(), INPUT_SIZE.toLong(), 3)
//
//            // Run the inference call.
//            Trace.beginSection("run")
//            inferenceInterface!!.run(arrayOf(OUTPUT_NAME), false)
//
//            // Copy the output Tensor back into the output array.
//            inferenceInterface!!.fetch(OUTPUT_NAME, outputs)
//
//            val pq = PriorityQueue<Result>(3,
//                    Comparator<Result> { lhs, rhs ->
//                        // Intentionally reversed to put high confidence at the head of the queue.
//                        java.lang.Float.compare(rhs.confidence!!, lhs.confidence!!)
//                    })
//            for (i in labels.indices) {
//                pq.add(Result("" + i, if (labels.size > i) labels[i] else "unknown", outputs[i], null))
//            }
//            val recognitions = ArrayList<Result>()
//            val recognitionsSize = Math.min(pq.size, MAX_RESULTS)
//            for (i in 0 until recognitionsSize) recognitions.add(pq.poll())
//            return@flatMap Single.just(recognitions)
//        }
//    }
//
//    fun close() {
////        interpreter?.close()
//    }
//}