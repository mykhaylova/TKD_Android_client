package com.doo.taekwon.taekwondoclient;

import android.app.AlertDialog;
import android.content.Context;
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
                if (!mName.equals("referee5")) {
                    Intent loginIntent = new Intent(ChoiceActivity.this, MainActivity.class);
                    loginIntent.putExtra("name", mName);
                    loginIntent.putExtra("ip", mIpAddress);
                    ChoiceActivity.this.startActivity(loginIntent);
                    vibe.vibrate(220);
                } else
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ChoiceActivity.this);
                    builder.setMessage("Wrong referee ID! Please register with the ID 1 till 4!")
                            .setNegativeButton("Retry", null)
                            .create()
                            .show();
                }
            }
        });

        btnTul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentTul = new Intent(ChoiceActivity.this, TulActivity.class);
                intentTul.putExtra("name", mName);
                intentTul.putExtra("ip", mIpAddress);
                ChoiceActivity.this.startActivity(intentTul);
                vibe.vibrate(220);
            }
        });
    }
    public void clickExit(View v){
        finish();
        final Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(220);
    }
}