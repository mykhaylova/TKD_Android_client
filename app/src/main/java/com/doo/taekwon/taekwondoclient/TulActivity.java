package com.doo.taekwon.taekwondoclient;

import android.content.Context;
import android.content.Intent;
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

public class TulActivity extends AppCompatActivity {
    private String mServerPort = "8080";
    private String mServerIP = null;
    private String mName = null;
    private String mLevel = "one";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tul);

        Button send2ButtonMinus = (Button)findViewById(R.id.btnRedMinusPoints);
        Button send1ButtonMinus = (Button)findViewById(R.id.btnRedPlusPointsPoints);  //fehler
        Button send2ButtonPlus = (Button)findViewById(R.id.btnRedPlusPoints);
        Button send1ButtonPlus = (Button)findViewById(R.id.btnBluePlusPoints);
        Button send1ButtonZero = (Button)findViewById(R.id.btnBlueSetZero);
        Button send2ButtonZero = (Button)findViewById(R.id.btnRedSetZero);
        Button buttonMenu = (Button)findViewById(R.id.btnMenu);
        Button buttonBackRed = (Button)findViewById(R.id.btnBackRed);
        Button buttonBackBlue = (Button)findViewById(R.id.btnBackBlue);

        final Button btnLevel2 = (Button) findViewById(R.id.btnLevel1);

        final TextView etName = (TextView)findViewById(R.id.etReferee);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mName = extras.getString("name");
            etName.setText(mName);
            mServerIP = extras.getString("ip");
        }

        final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        send1ButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute(mLevel, mName, "fighter1", "-0.2");
                vibe.vibrate(100);
            }
        });

        send2ButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute(mLevel, mName, "fighter2", "-0.2");
                vibe.vibrate(100);
            }
        });

        send1ButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute(mLevel, mName, "fighter1", "0.2");
                vibe.vibrate(160);
            }
        });

        send2ButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute(mLevel, mName, "fighter2", "0.2");
                vibe.vibrate(160);
            }
        });

        send1ButtonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute(mLevel, mName, "fighter1", "0");
                vibe.vibrate(200);
            }
        });

        send2ButtonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute(mLevel, mName, "fighter2", "0");
                vibe.vibrate(200);
            }
        });

        buttonBackRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute(mLevel, mName, "fighter1", "10");
                vibe.vibrate(200);
            }
        });

        buttonBackBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute(mLevel, mName, "fighter2", "10");
                vibe.vibrate(200);
            }
        });

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                vibe.vibrate(220);
            }
        });

        btnLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent levelSecond = new Intent(TulActivity.this, TulLevel2Activity.class);
                levelSecond.putExtra("name", mName);
                levelSecond.putExtra("ip", mServerIP);
                TulActivity.this.startActivity(levelSecond);
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