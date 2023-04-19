package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button loginBtn;
    private final String correctPassword = "123456";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.button);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!password.getText().toString().equals(correctPassword)) {
                    Toast.makeText(getApplicationContext(), "Please enter the correct password", Toast.LENGTH_LONG).show();
                } else {
                    Log.d("Khoadz", password.getText().toString());
                    Intent intent = new Intent(getApplicationContext(), HelloActivity.class);
                    intent.putExtra("username", username.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}

