package com.doo.taekwon.taekwondoclient;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TulLevel2Activity extends AppCompatActivity {

    private String mName = null;
    private String mServerIP = null;
    //private String mIpAddress = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tul_level2);

        Button buttonLevel1 = (Button) findViewById(R.id.btnLevel1);
        final Button buttonLevel3 = (Button) findViewById(R.id.btnLevel3);

        Button send1Button0Points = (Button)findViewById(R.id.btnF2set0);
        Button send2Button0Points = (Button)findViewById(R.id.btnF2send1);
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

        send1Button0Points.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vibe.vibrate(220);
            }
        });

        send2Button0Points.setOnClickListener(new View.OnClickListener() {
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
