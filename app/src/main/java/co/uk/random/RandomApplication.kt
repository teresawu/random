package co.uk.random

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import co.uk.random.di.DaggerAppComponent
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import io.realm.Realm
import javax.inject.Inject

class RandomApplication : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject lateinit var activityDispatchingInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var fragmentDispatchingjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        FirebaseApp.initializeApp(this, FirebaseOptions.Builder().build())
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityDispatchingInjector

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingjector

}
