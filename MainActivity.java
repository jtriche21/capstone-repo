package com.example.jeremy.downloadfileasync;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;

public class MainActivity extends Activity implements View.OnClickListener{

    Button d, l;
    TextView tv;
    String agora = "https://agora.cs.wcu.edu/~jtriche1/";
    String filename = "downloaded_html.txt";
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        d = findViewById(R.id.dButton);
        l = findViewById(R.id.lButton);
        TextView tv = findViewById(R.id.textView);
        file  = new File(this.getFilesDir(), filename);
        d.setOnClickListener(this);
        l.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view == d){
            save();
        }else if(view == l){//the letter l not the number one
            String temp = load();
            if(tv == null){
                Toast.makeText(getApplicationContext(), "the textView is null", Toast.LENGTH_LONG).show();
            }
            //tv.setText(temp);
        }
    }


    public void save(){
        Download download = new Download();
        download.execute(agora);

    }

    public String load(){
        StringBuilder text = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null){
                text.append(line);
                text.append("\n");
            }
            br.close();
        }catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

        String data = new String(text);
        if(data.length() > 0){
            file.delete();
            return data;
        }
        return "load() failed";

    }

    private class Download extends AsyncTask<String, Integer, String>{

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Downloading in Progress...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setMax(100);
            progressDialog.setProgress(0);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String path = params[0];//should be the agora url passed in
            int file_length;

            try{
                URL url = new URL(path);//
                URLConnection urlConnection = url.openConnection();//open the connection to agora
                urlConnection.connect();//connect to the url
                file_length = urlConnection.getContentLength();//get the length of the
                /*File new_folder = new File("Download/agoraFile");
                if(!new_folder.exists()){
                    new_folder.mkdirs();
                }*/
                //File input_file = new File(getFilesDir(), filename);
                InputStream inputStream = new BufferedInputStream(url.openStream(), 8192);
                byte[] data = new byte[1024];//create a buffer
                int count;
                int total = 0;//the total number of bytes read
                OutputStream outputStream = new FileOutputStream(file);
                while((count = inputStream.read(data)) != -1){
                    total += count;
                    outputStream.write(data, 0, count);
                    int progress = total*100/file_length;
                    publishProgress(progress);
                }
                inputStream.close();
                outputStream.close();

            }catch(MalformedURLException m){
                m.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "Download Complete...";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.hide();
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
        }

    }
}
