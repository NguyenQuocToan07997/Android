package com.example.market2019;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.model.UserModel;
import com.example.utils.DBHelper;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername;
    EditText edtPassword;
    Button btnLogin;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        OntInit();
        Onvalidayfrom();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Onvalidayfrom()) {
                    if (edtUsername.getText().toString().endsWith("toan") && edtPassword.getText().toString().equals("123")) {
                        UserModel userModel = new UserModel(edtUsername.getText().toString().trim(), edtPassword.getText().toString().trim());
                        DBHelper.saveLoinInfo(LoginActivity.this, userModel);
                        Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "login fail!!!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
    private boolean Onvalidayfrom() {
        if (edtUsername.getText().toString().length() < 1) {
            edtUsername.setError("wrong");
            return false;
        }
        if (edtPassword.getText().toString().length() < 1) {
            edtPassword.setError("wrong");
            return false;
        }
        return true;
    }

    private void OntInit() {
        edtUsername = findViewById(R.id.txtUsername);
        edtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.Button);
        imageView = findViewById(R.id.imageView);

    }
}
