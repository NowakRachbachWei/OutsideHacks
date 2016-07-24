package com.example.outsidehack.outsidelove;

import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class getServerData extends AppCompatActivity {


    public static final String TAG = getServerData.class.getSimpleName();
    public String resultdata;

    TextView results;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_server_data);

        new getServer().execute();

        results = (TextView)findViewById(R.id.serverResult);
        results.setText(resultdata);
 /*       String serverhttp = "http://52.34.38.109/api";
        HttpURLConnection httpURLConnection = null;
        URL url = null;
        StringBuilder result = new StringBuilder();

        try {
            url = new URL(serverhttp);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            int code = httpURLConnection.getResponseCode();

            if (code == 200 || code == 201){
                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));


                String line;
                while ((line=bufferedReader.readLine())!= null){
                    result.append(line);
                }
            }

        } catch (IOException e){
            System.out.println("error"  + e.getMessage());
        } finally{
            httpURLConnection.disconnect();
        }*/





    }

    private class getServer extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {
            String serverhttp = "http://52.34.38.109/api";
            HttpURLConnection httpURLConnection = null;
            URL url = null;
            StringBuilder result = new StringBuilder();

            try {
                url = new URL(serverhttp);
                httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();
                int code = httpURLConnection.getResponseCode();

                if (code == 200 || code == 201){

                    Log.i(TAG, "successs bitches");
                    InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));


                    String line;
                    while ((line=bufferedReader.readLine())!= null){
                        result.append(line);
                    }
                    resultdata = result.toString();
                    //Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
                }

            } catch (IOException e){
                System.out.println("error"  + e.getMessage());
            } finally{
                httpURLConnection.disconnect();
            }

            return null;

        }

        
        protected void onPostExecute(String result) {
            results.setText(resultdata);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_server_data, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
