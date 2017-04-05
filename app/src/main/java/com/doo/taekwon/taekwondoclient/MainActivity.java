package com.doo.taekwon.taekwondoclient;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private String mServerPort = "8080";
    private String mServerIP = null;
    private String mName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button send2Button1Point = (Button)findViewById(R.id.btnBluePlusOne);
        Button send1Button1Point = (Button)findViewById(R.id.btnRedSetZero);
        Button send2ButtonMinusPoint = (Button)findViewById(R.id.btnBlueSetZero);
        Button send1ButtonMinusPoint = (Button)findViewById(R.id.btnBluePlusPoints);
        Button buttonMenu = (Button) findViewById(R.id.btnMenu);

        final TextView etName = (TextView)findViewById(R.id.etReferee);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mName = extras.getString("name");
            etName.setText(mName);
            mServerIP = extras.getString("ip");
        }

        final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        send1Button1Point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute(mName, "fighter2", "1");
                vibe.vibrate(100);
            }
        });

        send2Button1Point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute(mName, "fighter1", "1");
                vibe.vibrate(100);
            }
        });

        send1ButtonMinusPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute(mName, "fighter2", "-1");
                vibe.vibrate(160);
            }
        });

        send2ButtonMinusPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute(mName, "fighter1", "-1");
                vibe.vibrate(160);
            }
        });

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                vibe.vibrate(220);
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
                //params0 == refereeID, params1 == fighterID, params2 == points
                String txt =  params[0] + ":" + params[1] + ":" + params[2];
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