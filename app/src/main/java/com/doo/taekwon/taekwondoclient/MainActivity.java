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
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        Button send1Button1Point = (Button)findViewById(R.id.btnF1set0);
         Button send1Button3Points = (Button)findViewById(R.id.button);

        // Button getButton = (Button)findViewById(R.id.btnGetPoints);
        Button send2Button1Point = (Button)findViewById(R.id.btnF2set0);
        Button send1Button2Points = (Button)findViewById(R.id.btnF1send1);
        Button send2Button2Points = (Button)findViewById(R.id.btnF2send1);
        Button send2Button3Points = (Button)findViewById(R.id.btnF2sendHalf);
        //final EditText etIpAddress = (EditText)findViewById(R.id.textIP);
        Button buttonMenu = (Button) findViewById(R.id.btnMenu);

        final TextView etName = (TextView)findViewById(R.id.etName);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mName = extras.getString("name");
            etName.setText(mName);
            mServerIP = extras.getString("ip");
            //etIpAddress.setText(mServerIP);
        }

        final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    /*    send1Button1Point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute(mName, "fighter1", "1");
                vibe.vibrate(160);
            }
        });   */

    /*    send2Button1Point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute(mName, "fighter2", "1");
                vibe.vibrate(160);
            }
        });      */

        send1Button2Points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute(mName, "fighter1", "2");
                vibe.vibrate(160);
            }
        });

        send2Button2Points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute(mName, "fighter2", "2");
                vibe.vibrate(160);
            }
        });

        send1Button3Points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute(mName, "fighter1", "3");
                vibe.vibrate(160);
            }
        });

        send2Button3Points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PointsSender sender = new PointsSender();
                sender.execute(mName, "fighter2", "3");
                vibe.vibrate(160);
            }
        });

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent activityIntent = new Intent(MainActivity.this, ChoiceActivity.class);
                //activityIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //startActivity(activityIntent);
                finish();
                vibe.vibrate(220);
            }
        });

        //getButton.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
              //  PointsReader reader = new PointsReader();
                //reader.execute();
            //}
        //});

//            @Override
//            public void afterTextChanged(Editable s) {
//
//                //ip.setText(mServerIP);
//            }
//        });


        //etIpAddress.addTextChangedListener(new TextWatcher() {
          //  @Override
            //public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            //}

           // @Override
            //public void onTextChanged(CharSequence s, int start, int before, int count) {

            //}

    }

    private class PointsReader extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... v) {
            URL serverUrl = null;
            HttpURLConnection connection = null;
            StringBuilder sb = new StringBuilder();


            try {
                //creating the connection to the http server
                serverUrl = new URL("http://" + mServerIP + ":" + mServerPort + "/count");
                connection = (HttpURLConnection) serverUrl.openConnection();
                connection.setRequestMethod("GET");
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                connection.disconnect();
                Log.d("GET", sb.toString());
            } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
        return null;
        }
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