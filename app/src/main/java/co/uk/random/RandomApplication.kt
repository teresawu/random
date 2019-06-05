package co.uk.random

import android.app.Application
import co.uk.random.Keys.API_KEY_FIREBASE
import co.uk.random.Keys.APP_ID
import co.uk.random.Keys.APP_NAME
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions

class RandomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        val firebaseOptions = FirebaseOptions.Builder()
                .setApiKey(API_KEY_FIREBASE)
                .setApplicationId(APP_ID).build()
        FirebaseApp.initializeApp(this, firebaseOptions, APP_NAME)
    }
}
