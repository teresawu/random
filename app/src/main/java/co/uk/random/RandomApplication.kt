package co.uk.random

import android.app.Activity
import androidx.multidex.MultiDexApplication
import androidx.fragment.app.Fragment
import co.uk.random.Keys.API_KEY_FIREBASE
import co.uk.random.Keys.APP_ID
import co.uk.random.Keys.APP_NAME
import co.uk.random.di.DaggerAppComponent
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import io.realm.Realm
import javax.inject.Inject

class RandomApplication : MultiDexApplication(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var activityDispatchingInjector: DispatchingAndroidInjector<Activity>
    @Inject
    lateinit var fragmentDispatchingjector: DispatchingAndroidInjector<androidx.fragment.app.Fragment>

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val firebaseOptions = FirebaseOptions.Builder()
                .setApiKey(API_KEY_FIREBASE)
                .setApplicationId(APP_ID).build()
        FirebaseApp.initializeApp(this, firebaseOptions, APP_NAME)
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingInjector

    override fun supportFragmentInjector(): AndroidInjector<androidx.fragment.app.Fragment> = fragmentDispatchingjector

}
