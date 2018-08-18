package it.unisalento.se.saw.configurations;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FirebaseSDKConfiguration  {

    private static FirebaseApp firebaseApp = null;



    public static void initialize() throws IOException {
        if (firebaseApp == null ){
            FileInputStream serviceAccount = new FileInputStream("C:\\Users\\Antonio\\IdeaProjects\\SeApp\\seapp-17679-firebase-adminsdk-c61d6-98ab33d900.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://seapp-17679.firebaseio.com")
                    .setProjectId("seapp-17679")
                    .build();

            firebaseApp = FirebaseApp.initializeApp(options);
        }


    }

}
