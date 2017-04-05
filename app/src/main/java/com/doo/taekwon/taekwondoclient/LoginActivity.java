package com.doo.taekwon.taekwondoclient;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class LoginActivity extends AppCompatActivity {
    private String mName = null;
    private String mIpAddress = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etIpAddress = (EditText) findViewById(R.id.etIpAddress);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        final Spinner sDropdownSpinner = (Spinner)findViewById(R.id.sDropdown);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.user_name,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sDropdownSpinner.setAdapter(adapter);

        sDropdownSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               mName = (String)parent.getItemAtPosition(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

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
                builder.setMessage("Please enter username and network address!")
                        .setNegativeButton("Retry", null)
                        .create()
                        .show();
            }
        }
        });

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
