package com.example.listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername;
    EditText edtPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ontInit();
        onvalidayform();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onvalidayform()){
                    if(edtUsername.getText().toString().endsWith("toan") && edtPassword.getText().toString().equals("toan")){
                        Toast.makeText(LoginActivity.this,"Login Success",Toast.LENGTH_LONG).show();
                        Intent i = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(LoginActivity.this,"login fail!!!",Toast.LENGTH_LONG).show();

                    }
                }
            }
        });
    }

    private boolean onvalidayform() {
        if(edtUsername.getText().toString().length()<1){
            edtUsername.setError("wrong");
            return false;
        }
        if(edtPassword.getText().toString().length()<1){
            edtPassword.setError("wrong");
            return false;
        }
        return true;

    }

    private void ontInit() {
        edtUsername = findViewById(R.id.main_UserName);
        edtPassword = findViewById(R.id.main_Password);
        btnLogin = findViewById(R.id.main_Button);
    }
}