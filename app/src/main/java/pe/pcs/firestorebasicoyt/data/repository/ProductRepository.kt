package pe.pcs.firestorebasicoyt.data.repository

import kotlinx.coroutines.tasks.await
import pe.pcs.firestorebasicoyt.data.common.FirestoreCnnstants
import pe.pcs.firestorebasicoyt.data.common.FirestoreInstance
import pe.pcs.firestorebasicoyt.data.model.ProductModel

class ProductRepository {

    suspend fun listar(dato: String): List<ProductModel> {
        return FirestoreInstance.get().collection(FirestoreCnnstants.COLECCION_PRODUCTO)
            .orderBy("descripcion").startAt(dato).endAt(dato + "\uf8ff").get()
            .await().toObjects(ProductModel::class.java)
    }

    private suspend fun registrar(model: ProductModel): Boolean {
        val documento =
            FirestoreInstance.get().collection(FirestoreCnnstants.COLECCION_PRODUCTO).document()
        model.id = documento.id
        documento.set(model).await()

        return true
    }

    private suspend fun actualizar(model: ProductModel): Boolean {
        val documento = FirestoreInstance.get().collection(FirestoreCnnstants.COLECCION_PRODUCTO)
            .document(model.id)
        documento.update(
            mapOf(
                "descripcion" to model.descripcion,
                "codigobarra" to model.codigobarra,
                "precio" to model.precio
            )
        ).await()

        return true
    }

    suspend fun eliminar(model: ProductModel): Boolean {
        val documento = FirestoreInstance.get().collection(FirestoreCnnstants.COLECCION_PRODUCTO)
            .document(model.id)
        documento.delete().await()

        return true
    }

    suspend fun grabar(model: ProductModel): Boolean {
        return if (model.id.isEmpty()) {
            registrar(model)
        } else {
            actualizar(model)
        }
    }

}