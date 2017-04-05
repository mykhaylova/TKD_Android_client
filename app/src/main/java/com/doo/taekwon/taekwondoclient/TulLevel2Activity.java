package com.doo.taekwon.taekwondoclient;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class TulLevel2Activity extends AppCompatActivity {
    private String mServerPort = "8080";
    private String mName = null;
    private String mServerIP = null;
    private String mLevel = "two";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tul_level2);

        Button buttonLevel1 = (Button) findViewById(R.id.btnLevel1);
        final Button buttonLevel3 = (Button) findViewById(R.id.btnLevel3);

        Button send2ButtonMinusPoints = (Button)findViewById(R.id.btnBlueMinusPoints);
        Button send1ButtonMinusPoints = (Button)findViewById(R.id.btnBluePlusPoints);
        Button send2ButtonPlusPoints = (Button)findViewById(R.id.btnRedMinusPoints);
        Button send1ButtonPlusPoints = (Button)findViewById(R.id.btnRedPlusPoints);

        final TextView etName = (TextView)findViewById(R.id.etReferee);
        final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mName = extras.getString("name");
            etName.setText(mName);
            mServerIP = extras.getString("ip");
        }

        buttonLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                etName.setText(mName);
                vibe.vibrate(220);
            }
        });

        buttonLevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent levelThree = new Intent(TulLevel2Activity.this, TulLevel3Activity.class);
                levelThree.putExtra("name", mName);
                TulLevel2Activity.this.startActivity(levelThree);
                vibe.vibrate(220);
            }
        });

        send1ButtonMinusPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TulLevel2Activity.PointsSender sender = new TulLevel2Activity.PointsSender();
                sender.execute(mLevel, mName, "fighter2", "-0.5");
                vibe.vibrate(100);
            }
        });

        send2ButtonMinusPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TulLevel2Activity.PointsSender sender = new TulLevel2Activity.PointsSender();
                sender.execute(mLevel, mName, "fighter1", "-0.5");
                vibe.vibrate(100);
            }
        });

        send1ButtonPlusPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TulLevel2Activity.PointsSender sender = new TulLevel2Activity.PointsSender();
                sender.execute(mLevel, mName, "fighter2", "0.5");
                vibe.vibrate(160);
            }
        });

        send2ButtonPlusPoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TulLevel2Activity.PointsSender sender = new TulLevel2Activity.PointsSender();
                sender.execute(mLevel, mName, "fighter1", "0.5");
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
                //params0 == level number, params1 == refereeID, params2 == fighterID, params3 == points
                String txt =  params[0] + ":" + params[1] + ":" + params[2] + ":" + params[3];
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
