package com.example.vanessaperry.demoappscreens;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    TextView generalAnnouncementsTV;
    String generalAnnouncementsString;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generalAnnouncementsTV = findViewById(R.id.generalAnnouncementsTV);

        // This calls the Inner Class
        MyAsyncTask async = new MyAsyncTask();
        try {
            // This gets the return value from the doInBackground method from the inner class. 
            generalAnnouncementsString = async.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        generalAnnouncementsTV.setText(generalAnnouncementsString);

    }

    /**
     * We need this Async Class because this exception -> NetworkOnMainThreadException is thrown if
     * you attempt to run the following code below inside a regular method/class. This is due to the
     * fact that newer versions of android don't allow the application to perform networking
     * operations on its main thread. So to combat this we must create an asynchronous thread. This
     * thread runs the network transactions in the background.
     */
    private class MyAsyncTask extends AsyncTask<Void, Void, String> {

            // This is what we are going to be returning.
            String innerGeneralAnnouncements = "";

            @Override
            protected String doInBackground(Void... arg0) {
                try {
                    // Create a URL for the desired page
                    String httpsURL = "https://agora.cs.wcu.edu/~veperry1/";
                    URL url = new URL(httpsURL);
                    Scanner s = new Scanner(url.openStream());
                    while (s.hasNextLine()) {
                        innerGeneralAnnouncements =
                                innerGeneralAnnouncements.concat(s.nextLine());
                    }
                    s.close();
                } catch (MalformedURLException a) {
                    System.out.println("Couldn't read from URL");
                } catch (IOException b) {
                    System.out.println("IO EXCEPTION");
                }
                return innerGeneralAnnouncements;
            }
    }
}




