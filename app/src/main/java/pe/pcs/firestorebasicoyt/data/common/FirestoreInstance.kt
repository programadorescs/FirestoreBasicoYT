package pe.pcs.firestorebasicoyt.data.common

import com.google.firebase.firestore.FirebaseFirestore

object FirestoreInstance {

    fun get(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

}