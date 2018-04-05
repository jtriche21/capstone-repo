package com.example.vanessaperry.demoappscreens;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity {

    TextView generalAnnouncementsTV;
    String generalAnnouncementsString;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generalAnnouncementsTV = findViewById(R.id.generalAnnouncementsTV);
        generalAnnouncementsTV.setMovementMethod(new ScrollingMovementMethod());

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
        //generalAnnouncementsTV.setText("ugh");
    }

    public void goMapScreen(View view){
        Intent i = new Intent(MainActivity.this, MapScreen.class);
        startActivity(i);
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
        String dottedLine = "---------------------------------------------------------------------------\n";

        @Override
        protected String doInBackground(Void... arg0) {
            try {
                // Create a URL for the desired page
                String httpsURL = "https://agora.cs.wcu.edu/~veperry1/";
                URL url = new URL(httpsURL);
                Scanner s = new Scanner(url.openStream());
                String line = "";
                while (s.hasNextLine()) {
                    line = s.nextLine();
                    if (!line.equals("<html>") && !line.equals("<body>") &&
                            !line.equals("</html>") && !line.equals("</body>")) {
                        if (line.equals("")){
                            innerGeneralAnnouncements = innerGeneralAnnouncements.concat(dottedLine);
                        } else {
                            innerGeneralAnnouncements = innerGeneralAnnouncements.concat(line + "\n");
                        }
                    }
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
