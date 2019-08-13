package com.sebastiansoftwareengineer.barbershopauthorizationserver.data;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FirestoreConnection {

    private Firestore db;

    public FirestoreConnection() throws IOException {

        InputStream serviceAccount = new FileInputStream("/home/sebastian/Projects.Java/barbershop-authorization-server/firestoreCredentials.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

        FirestoreOptions options = FirestoreOptions.newBuilder()
                .setCredentials(credentials)
                .setTimestampsInSnapshotsEnabled(true)
                .build();

        Firestore db = options.getService();
        this.db = db;
    }

    public Firestore getDb() {
        return this.db;
    }
}
