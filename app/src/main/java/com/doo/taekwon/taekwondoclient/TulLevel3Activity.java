package com.doo.taekwon.taekwondoclient;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TulLevel3Activity extends AppCompatActivity {

    private String mName = null;
    private String mServerIP = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tul_level3);

        Button buttonLevel2 = (Button) findViewById(R.id.btnLevel2);
        Button buttonFertig = (Button) findViewById(R.id.btnFertig);

        Button send1Button5Points = (Button) findViewById(R.id.btnF2set0);
        Button send2Button5Points = (Button)findViewById(R.id.btnF2send1);

        Button send1Button1Point = (Button)findViewById(R.id.btnF1set0);
        Button send2Button1Point = (Button)findViewById(R.id.btnF1sendHalf);


        final TextView etName = (TextView)findViewById(R.id.etName);
        final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mName = extras.getString("name");
            etName.setText(mName);
            mServerIP = extras.getString("ip");
        }

        buttonLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                etName.setText(mName);
                vibe.vibrate(220);
            }
        });

        buttonFertig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = "Resultat wird an den Server geschickt";
                Toast.makeText(getApplicationContext(), item, Toast.LENGTH_SHORT).show();
                vibe.vibrate(220);
            }
        });

        send1Button5Points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vibe.vibrate(220);
            }
        });

        send2Button5Points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vibe.vibrate(220);
            }
        });

        send1Button1Point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vibe.vibrate(220);
            }
        });

        send2Button1Point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vibe.vibrate(220);
            }
        });
    }
}
