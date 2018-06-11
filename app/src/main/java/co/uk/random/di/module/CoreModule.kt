package co.uk.random.di.module

import android.app.Application
import android.arch.persistence.room.Room
import co.uk.arch.database.AppDatabase
import co.uk.random.BuildConfig
import co.uk.youtube.util.SchedulerProvider
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmConfiguration
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class CoreModule() {

    @Provides
    @Singleton
    fun provideYoutubeApiService(okHttpClient: OkHttpClient, gson: Gson): co.uk.youtube.api.YoutubeApiService {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.YOUTUBE_MAIN)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(co.uk.youtube.api.YoutubeApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideRealm(): Realm {
        val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()  // only while developing
                .build()
        return Realm.getInstance(config)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(application: Application): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val cacheDir = File(application.cacheDir, UUID.randomUUID().toString())
        // 10 MiB cache
        val cache = Cache(cacheDir, 10 * 1024 * 1024)

        return OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()
    }

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider {
        return SchedulerProvider(Schedulers.io(), AndroidSchedulers.mainThread())
    }

    @Provides
    @Singleton
    fun provideGson() = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()

    @Provides
    @Singleton
    fun providesAppDatabase(application: Application): AppDatabase =
            Room.databaseBuilder(application, AppDatabase::class.java, "random-app-database").allowMainThreadQueries().build()

    @Provides
    @Singleton
    fun providesChannelDao(database: AppDatabase) = database.channelDAO()

    @Provides
    @Singleton
    fun providesDefaultDao(database: AppDatabase) = database.defaultDAO()

    @Provides
    @Singleton
    fun providesHighDao(database: AppDatabase) = database.highDao()

    @Provides
    @Singleton
    fun providesItemDao(database: AppDatabase) = database.itemDao()

    @Provides
    @Singleton
    fun providesLocalizedDao(database: AppDatabase) = database.localizedDao()

    @Provides
    @Singleton
    fun providesMaxresDao(database: AppDatabase) = database.maxresDao()

    @Provides
    @Singleton
    fun providesMediumDao(database: AppDatabase) = database.mediumDao()

    @Provides
    @Singleton
    fun providesPageInfoDao(database: AppDatabase) = database.pageInfoDao()

    @Provides
    @Singleton
    fun providesPlayerDao(database: AppDatabase) = database.playerDao()

    @Provides
    @Singleton
    fun providesPlaylistDao(database: AppDatabase) = database.playlistDao()

    @Provides
    @Singleton
    fun providesResourceIdDao(database: AppDatabase) = database.resourceIdDao()

    @Provides
    @Singleton
    fun providesStandardDao(database: AppDatabase) = database.standardDao()

    @Provides
    @Singleton
    fun providesSnippetDao(database: AppDatabase) = database.snippetDao()

    @Provides
    @Singleton
    fun providesThumbnailsDao(database: AppDatabase) = database.thumbnailsDao()

    @Provides
    @Singleton
    fun providesVideoDao(database: AppDatabase) = database.videoDao()

}