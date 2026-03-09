package com.example.csc325_firebase_webview_auth.model;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.cloud.FirestoreClient;
import java.io.IOException;

/**
 *
 * @author MoaathAlrajab
 */
public class FirestoreContext {

    public Firestore firebase() {
        try {
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(
                                getClass().getResourceAsStream("/files/key.json")))
                        .setStorageBucket("csc325firebaseapp.firebasestorage.app")
                                        .build();

                                FirebaseApp.initializeApp(options);
                                System.out.println("Firebase is initialized");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return FirestoreClient.getFirestore();
    }

    public FirebaseAuth firebaseAuth() {
        if (FirebaseApp.getApps().isEmpty()) {
            firebase();
        }
        return FirebaseAuth.getInstance();
    }
}