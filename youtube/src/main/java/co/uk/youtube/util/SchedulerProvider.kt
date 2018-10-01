package co.uk.youtube.util

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SchedulerProvider (val backgroundScheduler: Scheduler, val foregroundScheduler: Scheduler) {

    fun <T> getSchedulersForObservable(): (Observable<T>) -> Observable<T> {
        return { observable: Observable<T> ->
            observable.subscribeOn(backgroundScheduler)
                    .observeOn(foregroundScheduler)
        }
    }

    fun <T> getSchedulersForSingle(): (Single<T>) -> Single<T> {
        return { single: Single<T> ->
            single.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    fun getSchedulersForCompletable(): (Completable) -> Completable {
        return { completable: Completable ->
            completable.subscribeOn(backgroundScheduler)
                    .observeOn(foregroundScheduler)
        }
    }

    fun <T> getSchedulersForFlowable(): (Flowable<T>) -> Flowable<T> {
        return { flowable: Flowable<T> ->
            flowable.subscribeOn(backgroundScheduler)
                    .observeOn(foregroundScheduler)
        }
    }
}
