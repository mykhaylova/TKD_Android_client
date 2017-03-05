package com.doo.taekwon.taekwondoclient;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ChoiceActivity extends AppCompatActivity {
    private String mName = null;
    private String mIpAddress = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        final Button btnSparring = (Button) findViewById(R.id.btnSparring);
        final Button btnTul = (Button) findViewById(R.id.btnTul);
        final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mName = extras.getString("name");
            mIpAddress = extras.getString("ip");
        }

        btnSparring.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(ChoiceActivity.this, MainActivity.class);
                loginIntent.putExtra("name", mName);
                loginIntent.putExtra("ip", mIpAddress);
                ChoiceActivity.this.startActivity(loginIntent);
                vibe.vibrate(220);
            }
        });

        btnTul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(ChoiceActivity.this, TulActivity.class);
                loginIntent.putExtra("name", mName);
                loginIntent.putExtra("ip", mIpAddress);
                ChoiceActivity.this.startActivity(loginIntent);
                vibe.vibrate(220);
            }
        });
    }
    public void clickExit(View v){
        onBackPressed();
        final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(220);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    ChoiceActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}