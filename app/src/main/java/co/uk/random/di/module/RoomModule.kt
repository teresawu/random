package co.uk.random.di.module

import co.uk.random.BuildConfig
import co.uk.room.api.RoomApiService
import co.uk.room.util.SchedulerProvider
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RoomModule() {
    @Provides
    @Singleton
    fun provideYoutubeApiService(okHttpClient: OkHttpClient, gson: Gson): RoomApiService {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.YOUTUBE_MAIN)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(RoomApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider {
        return SchedulerProvider(Schedulers.io(), AndroidSchedulers.mainThread())
    }
}