package com.doo.taekwon.taekwondoclient;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private String mName = null;
    private String mIpAddress = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etIpAddress = (EditText) findViewById(R.id.etIpAddress);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if ((mName != null) && (mIpAddress != null)) {
                Intent loginIntent = new Intent(LoginActivity.this, ChoiceActivity.class);
                loginIntent.putExtra("name", mName);
                loginIntent.putExtra("ip", mIpAddress);
                LoginActivity.this.startActivity(loginIntent);
            } else
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setMessage("Please enter your name and network address!")
                        .setNegativeButton("Retry", null)
                        .create()
                        .show();
            }
        }
        });

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged (CharSequence s,int start, int count, int after){
            }

            @Override
            public void onTextChanged (CharSequence s,int start, int before, int count){
            }

            @Override
            public void afterTextChanged (Editable s){
                mName = etName.getText().toString();
            }
        }

        );


        etIpAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged (CharSequence s,int start, int before, int count){
            }

            @Override
            public void afterTextChanged (Editable s){
                mIpAddress = etIpAddress.getText().toString();
            }
        }
        );
    }
}
