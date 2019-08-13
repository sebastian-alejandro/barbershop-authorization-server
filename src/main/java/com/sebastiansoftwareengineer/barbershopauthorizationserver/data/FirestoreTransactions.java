package com.sebastiansoftwareengineer.barbershopauthorizationserver.data;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import java.io.IOException;
import java.util.List;

public class FirestoreTransactions {

    private Firestore db;

    public FirestoreTransactions() throws IOException {
        FirestoreConnection connection = new FirestoreConnection();
        this.db = connection.getDb();
    }

    public List<QueryDocumentSnapshot> findByField(String collection, String field, String value) throws Exception {

        ApiFuture<QuerySnapshot> query = db
                .collection(collection)
                .whereEqualTo(field, value)
                .get();

        return query.get().getDocuments();
    }
}
