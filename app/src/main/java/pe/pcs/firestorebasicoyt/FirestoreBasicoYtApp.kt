package pe.pcs.firestorebasicoyt

import android.app.Application
import com.google.firebase.FirebaseApp

class FirestoreBasicoYtApp: Application() {

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

    }

}