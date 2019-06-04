package co.uk.random.di.module

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class CoreModule() {

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
    fun provideGson() = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()
}