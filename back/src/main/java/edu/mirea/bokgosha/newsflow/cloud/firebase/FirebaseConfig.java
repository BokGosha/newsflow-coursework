package edu.mirea.bokgosha.newsflow.cloud.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class FirebaseConfig {

    private final FirebaseProperties firebaseProperties;

    @PostConstruct
    public void init() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream(firebaseProperties.getServiceAccessKeyPath());

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setStorageBucket(firebaseProperties.getStorageBucket())
                .build();

        FirebaseApp.initializeApp(options);
    }
}
