package com.doo.taekwon.taekwondoclient;

import android.content.Intent;
import android.os.Bundle;
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
            }
        });

        btnTul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(ChoiceActivity.this, TulActivity.class);
                ChoiceActivity.this.startActivity(loginIntent);
            }
        });
    }
}