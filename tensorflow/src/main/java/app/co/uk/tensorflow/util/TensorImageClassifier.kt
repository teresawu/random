//package app.co.uk.tensorflow.util
//
//import android.content.res.AssetManager
//import android.graphics.Bitmap
//import app.co.uk.tensorflow.model.Result
//import app.co.uk.tensorflow.util.Keys.IMAGE_MEAN
//import app.co.uk.tensorflow.util.Keys.IMAGE_STD
//import app.co.uk.tensorflow.util.Keys.INPUT_NAME
//import app.co.uk.tensorflow.util.Keys.INPUT_SIZE
//import app.co.uk.tensorflow.util.Keys.MAX_RESULTS
//import app.co.uk.tensorflow.util.Keys.OUTPUT_NAME
//import io.reactivex.Single
//import org.tensorflow.contrib.android.TensorFlowInferenceInterface
//import java.io.BufferedReader
//import java.io.IOException
//import java.io.InputStreamReader
//import java.util.*
//
//class TensorImageClassifier constructor(private val assetManager: AssetManager) {
//    private val THRESHOLD = 0.1f
//    private var intValues: IntArray
//    private var floatValues: FloatArray
//    private var outputs: FloatArray
//    private var outputNames: Array<String>
//    private var inferenceInterface: TensorFlowInferenceInterface
//    private var statString: String
//    private val labels = Vector<String>()
//
//    init {
//        try {
//            val br = BufferedReader(InputStreamReader(assetManager.open(Keys.LABEL_PATH)))
//            while (true) {
//                val line = br.readLine() ?: break
//                labels.add(line)
//            }
//            br.close()
//        } catch (e: IOException) {
//            throw RuntimeException("Problem reading label file!", e)
//        }
//
//        try {
//            inferenceInterface = TensorFlowInferenceInterface(assetManager, Keys.MODEL_PATH)
//            statString = inferenceInterface.statString
//            val operation = inferenceInterface.graphOperation(OUTPUT_NAME)
//            val numClasses = operation.output<Any>(0).shape().size(1).toFloat()
//            outputs = floatArrayOf(numClasses)
//            floatValues = floatArrayOf(INPUT_SIZE * INPUT_SIZE * 3.toFloat())
//            intValues = intArrayOf(INPUT_SIZE * INPUT_SIZE)
//            outputNames = arrayOf(OUTPUT_NAME)
//        } catch (e: Exception) {
//            throw RuntimeException(e)
//        }
//    }
//
//    fun recognizeImage(bitmap: Bitmap): Single<List<Result>> {
//        return Single.just(bitmap).flatMap {
//            // Preprocess the image data from 0-255 int to normalized float based on the provided parameters.
//            bitmap.getPixels(intValues, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)
//            for (i in intValues!!.indices) {
//                val value = intValues!![i]
//                floatValues[i * 3 + 0] = ((value shr 16 and 0xFF) - IMAGE_MEAN) / IMAGE_STD
//                floatValues[i * 3 + 1] = ((value shr 8 and 0xFF) - IMAGE_MEAN) / IMAGE_STD
//                floatValues[i * 3 + 2] = ((value and 0xFF) - IMAGE_MEAN) / IMAGE_STD
//            }
//
//            // Copy the input data into TensorFlow
//            inferenceInterface!!.feed(INPUT_NAME, floatValues, 1, INPUT_SIZE.toLong(), INPUT_SIZE.toLong(), 3)
//
//            // Run the inference call.
//            inferenceInterface!!.run(outputNames, false)
//
//            // Copy the output Tensor back into the output array.
//            inferenceInterface!!.fetch(OUTPUT_NAME, outputs!!)
//
//            // Find the best classifications.
//            val pq = PriorityQueue<Result>(3,
//                    Comparator<Result> { lhs, rhs ->
//                        // Intentionally reversed to put high confidence at the head of the queue.
//                        java.lang.Float.compare(rhs.confidence!!, lhs.confidence!!)
//                    })
//
//            for (i in outputs!!.indices) {
//                if (outputs!![i] > THRESHOLD) {
//                    pq.add(Result("" + i, if (labels.size > i) labels[i] else "unknown", outputs[i], null))
//                }
//            }
//            val recognitions = ArrayList<Result>()
//            val recognitionsSize = Math.min(pq.size, MAX_RESULTS)
//            for (i in 0 until recognitionsSize) recognitions.add(pq.poll())
//            return@flatMap Single.just(recognitions)
//        }
//    }
//
//    fun close() {
//        inferenceInterface!!.close()
//    }
//}
