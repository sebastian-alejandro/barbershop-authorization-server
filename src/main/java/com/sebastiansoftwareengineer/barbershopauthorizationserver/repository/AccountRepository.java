package com.sebastiansoftwareengineer.barbershopauthorizationserver.repository;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.sebastiansoftwareengineer.barbershopauthorizationserver.data.FirestoreTransactions;
import com.sebastiansoftwareengineer.barbershopauthorizationserver.model.Account;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class AccountRepository {

    private FirestoreTransactions firestoreTransactions;

    public AccountRepository() throws IOException {
        this.firestoreTransactions = new FirestoreTransactions();
    }

    public Optional<Account> findByEmail(String email) throws Exception {

        List<QueryDocumentSnapshot> documents = firestoreTransactions.findByField("accounts", "email", email);

        if (documents.size() == 0)
            return null;
        else {
            QueryDocumentSnapshot document = documents.get(0);

            String username = document.getId();
            String password = document.get("password").toString();
            String role = document.get("role").toString();
            Timestamp timestamp = (Timestamp) document.get("createdAt");
            Date createdAt = timestamp.toDate();
            Timestamp timestamp2 = (Timestamp) document.get("updatedAt");
            Date updatedAt = timestamp2.toDate();
            Map<String, Boolean> details = (Map<String, Boolean>) document.get("details");

            return Optional.of(new Account(username, email, password, role, createdAt, updatedAt, details));
        }
    }
}
