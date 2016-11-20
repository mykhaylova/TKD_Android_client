package com.doo.taekwon.taekwondoclient;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class TulActivity extends AppCompatActivity {

    private String mServerPort = "8080";
    private String mServerIP = null;
    private String mName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tul);

        Button send1Button0Points = (Button)findViewById(R.id.btnF1set0);
        Button send2Button0Points = (Button)findViewById(R.id.btnF2set0);
        Button send1Button1Point = (Button)findViewById(R.id.btnF1send1);
        Button send2Button1Point = (Button)findViewById(R.id.btnF2send1);
        Button send1ButtonHalfPoints = (Button)findViewById(R.id.btnF1sendHalf);
        Button send2ButtonHalfPoints = (Button)findViewById(R.id.btnF2sendHalf);

        final EditText etIpAddress = (EditText)findViewById(R.id.textIP);
        final EditText etName = (EditText)findViewById(R.id.etName);
        final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mName = extras.getString("name");
            etName.setText(mName);
            mServerIP = extras.getString("ip");
            etIpAddress.setText(mServerIP);
        }

        send1Button0Points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute("fighter1", "0");
                vibe.vibrate(160);
            }
        });

        send2Button0Points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute("fighter2", "0");
                vibe.vibrate(160);
            }
        });

        send1Button1Point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute("fighter1", "1");
                vibe.vibrate(160);
            }
        });

        send2Button1Point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute("fighter2", "1");
                vibe.vibrate(160);
            }
        });

        send1ButtonHalfPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute("fighter1", "0.5");
                vibe.vibrate(160);
            }
        });

        send2ButtonHalfPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute("fighter2", "0.5");
                vibe.vibrate(160);
            }
        });
    }

    private class PointsSender extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... params) {
            URL serverUrl = null;
            HttpURLConnection connection = null;

            try {
                Log.d("POST", "POST");
                //params0 == fighterID, params2 == points
                String txt =  params[0] + ":" + params[1];
                byte[] outbuf = txt.getBytes("UTF-8");

                serverUrl = new URL("http://" + mServerIP + ":" + mServerPort + "/count");

                connection = (HttpURLConnection) serverUrl.openConnection();
                connection.setDoOutput(true);
                connection.setUseCaches(false);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "text/plain");
                connection.setRequestProperty("Charset", "UTF-8");
                connection.setRequestProperty("Content-Length", String.valueOf(outbuf.length));
                connection.getOutputStream().write(outbuf);

                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK)
                    Log.e("Res", "response code was not OK: " + String.valueOf(connection.getResponseCode()));

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}