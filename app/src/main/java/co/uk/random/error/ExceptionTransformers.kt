package co.uk.random.error

import io.reactivex.Single
import retrofit2.Response

class ExceptionTransformers {

    fun <T> wrapRetrofitExceptionSingle(): (Single<Response<T>>) -> Single<T> {
        return { it: Single<Response<T>> ->
            it.flatMap { t: Response<T> ->
                if (t.errorBody() != null) {
                    Single.error { ErrorMapper.transform(t.code(), t.errorBody()!!.string()) }
                } else {
                    Single.just(t.body())
                }
            }
        }
    }
}