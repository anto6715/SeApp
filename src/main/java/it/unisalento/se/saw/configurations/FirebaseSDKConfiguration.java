package it.unisalento.se.saw.configurations;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FirebaseSDKConfiguration  {
    FileInputStream serviceAccount;

    {
        try {
            serviceAccount = new FileInputStream("C:\\Users\\Antonio\\IdeaProjects\\SeApp\\seapp-17679-firebase-adminsdk-c61d6-98ab33d900.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    FirebaseOptions options;

    {
        try {
            options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setDatabaseUrl("https://seapp-17679.firebaseio.com")
                        .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initializeSDK(){
        FirebaseApp.initializeApp(options);
    }

    public  String getCiao(){
        return "ciao";
    }
}
