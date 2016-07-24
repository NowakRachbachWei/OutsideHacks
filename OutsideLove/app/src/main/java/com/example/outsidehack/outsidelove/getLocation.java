package com.example.outsidehack.outsidelove;

import android.app.Activity;
import android.location.Location;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class getLocation extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    public static final String TAG = getLocation.class.getSimpleName();


    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private TextView mLatitudeText, mLongitudeText, jsonExample;
    private String LatitudeString, LongitudeString;
    private String server = "http://52.34.38.109";
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate - activity loaded");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        mLatitudeText = (TextView)findViewById(R.id.theLatitude);

        mLongitudeText = (TextView)findViewById(R.id.theLongitude);
        jsonExample = (TextView)findViewById(R.id.jsonObject);

    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.i(TAG, "onConnected - is connected");

        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
            LatitudeString = String.valueOf(mLastLocation.getLatitude());
            LongitudeString = String.valueOf(mLastLocation.getLongitude());
            mLatitudeText.setText(LatitudeString);
            mLongitudeText.setText(LongitudeString);

            //Toast.makeText(this,String.valueOf(mLastLocation.getLatitude()), Toast.LENGTH_SHORT).show();
        }

        /****  JSON creation *****/
        jsonObject = new JSONObject();
        try {

            jsonObject.put("latitidue", LatitudeString);
            jsonObject.put("longitude", LongitudeString);
        } catch (JSONException e) {
            e.printStackTrace();
        }

            jsonExample.setText(String.valueOf(jsonObject));



        new sendLoc().execute();

        /*
        try{
            sendLocation(mLastLocation);
        } catch (IOException e){

        } catch (JSONException e){
        }*/
    }



    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "onConnectedFailed - connection failed");
        mLatitudeText.setText("FAIL");

    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "onConnectedSuspended - Connection is suspended");

    }

    @Override
    protected void onStart(){
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_location, menu);
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

    private class sendLoc extends AsyncTask<Void, Void, Void>{

        String server = "http://52.34.38.109";
        String urlParams = "";
        HttpURLConnection urlConnection = null;

        @Override
        protected Void doInBackground(Void... params) {
            String finalResult="";
            //String jsonData = String.valueOf(jsonObject);

            try{

                /**** open url connection  *****/
                String jsonData = jsonObject.toString(2);
                URL url = new URL(server);
                urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                //urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setRequestProperty("charset", "UTF-8");
                urlConnection.connect();




//                DataOutputStream dataOutputStream = new DataOutputStream(urlConnection.getOutputStream());
//                dataOutputStream.writeBytes(jsonObject.toString());
//                dataOutputStream.flush();
//                dataOutputStream.close();





                OutputStream outputStream = urlConnection.getOutputStream();
                outputStream.write(jsonObject.toString().getBytes("UTF-8"));
                outputStream.flush();


                Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
                writer.write(jsonData);
                writer.close();



                //InputStream inputStream = urlConnection.getInputStream();


                /**** grab response***/
                InputStream inputStream = urlConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String response;
                StringBuffer postResponse = new StringBuffer();
                while ((response=bufferedReader.readLine())!= null){
                    postResponse.append(response);
                    postResponse.append('\r');
                }
                bufferedReader.close();
                Toast.makeText(getApplicationContext(), postResponse.toString(), Toast.LENGTH_SHORT).show();
                finalResult = postResponse.toString();


            } catch (MalformedURLException e){
                System.out.println("error" + e.getMessage());
                Log.i(TAG, "URL connection fail" + e.getMessage());
            } catch (IOException e){

            } catch (JSONException e){

            }
            finally {
                if (urlConnection != null){
                    urlConnection.disconnect();
                }
            }


            return null;
        }
    }



//    private class sendLocation extends AsyncTask<String, Void, String>{
//
//        String server = "http://52.34.38.109";
//        String urlParams = "";
//        HttpURLConnection urlConnection = null;
//
//        @Override
//        protected String doInBackground(String... params) {
//
//            String finalResult="";
//            String jsonData = params[0];
//            try{
//
//                /**** open url connection  *****/
//
//                URL url = new URL(server);
//                urlConnection = (HttpURLConnection)url.openConnection();
//                urlConnection.setDoInput(true);
//                urlConnection.setDoOutput(true);
//                urlConnection.setRequestMethod("POST");
//                urlConnection.setRequestProperty("Content-type", "application/json");
//                urlConnection.setRequestProperty("Accept", "application/json");
//                urlConnection.setRequestProperty("charset", "UTF-8");
//                urlConnection.connect();
//
//
//
//
////                DataOutputStream dataOutputStream = new DataOutputStream(urlConnection.getOutputStream());
////                dataOutputStream.writeBytes(jsonObject.toString());
////                dataOutputStream.flush();
////                dataOutputStream.close();
//
//
//                OutputStream outputStream = urlConnection.getOutputStream();
//                outputStream.write(jsonObject.toString().getBytes("UTF-8"));
//                outputStream.flush();
//
//
//                Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
//                writer.write(jsonData);
//                writer.close();
//
//
//
//                //InputStream inputStream = urlConnection.getInputStream();
//
//
//                /**** grab response***/
//                InputStream inputStream = urlConnection.getInputStream();
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//                String response;
//                StringBuffer postResponse = new StringBuffer();
//                while ((response=bufferedReader.readLine())!= null){
//                    postResponse.append(response);
//                    postResponse.append('\r');
//                }
//                bufferedReader.close();
//                Toast.makeText(getApplicationContext(), postResponse.toString(), Toast.LENGTH_SHORT).show();
//                finalResult = postResponse.toString();
//
//
//            } catch (MalformedURLException e){
//                System.out.println("error" + e.getMessage());
//                Log.i(TAG, "URL connection fail" + e.getMessage());
//            } catch (IOException e){
//
//            } //catch (JSONException e){
//
//            //}
//            finally {
//                if (urlConnection != null){
//                    urlConnection.disconnect();
//                }
//            }
//
//            return finalResult;
//
//        }
//    }

    /*
    public void sendLocation(Location location) throws IOException, JSONException, {
        String server = "http://52.34.38.109";
        String urlParams = "";
        HttpURLConnection urlConnection = null;

        try{

            // open url connection

            URL url = new URL(server);
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-type", "application/json");
            urlConnection.setRequestProperty("charset", "UTF-8");


            /****  JSON creation
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("latitidue", String.valueOf(location.getLatitude()));
            jsonObject.put("longitude", String.valueOf(location.getLongitude()));

            DataOutputStream dataOutputStream = new DataOutputStream(urlConnection.getOutputStream());
            dataOutputStream.writeBytes(jsonObject.toString());
            dataOutputStream.flush();
            dataOutputStream.close();



            /**** grab response
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String response;
            StringBuffer postResponse = new StringBuffer();
            while ((response=bufferedReader.readLine())!= null){
                postResponse.append(response);
                postResponse.append('\r');
            }
            bufferedReader.close();
            Toast.makeText(this, postResponse.toString(), Toast.LENGTH_SHORT).show();




        } catch (MalformedURLException e){
            System.out.println("error" + e.getMessage());
            Log.i(TAG, "URL connection fail" + e.getMessage());
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
        }




    }*/
}
