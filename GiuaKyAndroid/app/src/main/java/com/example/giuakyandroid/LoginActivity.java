package com.example.giuakyandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.giuakyandroid.AsyncTask.GetDataAsyncTask;
import com.example.giuakyandroid.AsyncTask.LoginAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText edtUserName;
    EditText edtPassword;
    Button btnLogin;
    String mUserName = "";
    String mPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        onInit();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onValidateForm()) {
                    Map<String, String> mMap = new HashMap<>();
                    mMap.put("user_name", mUserName);
                    mMap.put("password", mPassword);


                    new LoginAsyncTask(LoginActivity.this, new ILoginView() {
                        @Override
                        public void onLoginSuccess(String m) {
                            Toast.makeText(LoginActivity.this,m, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, PhoneActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onLoginFail(String m) {
                            Toast.makeText(LoginActivity.this, m, Toast.LENGTH_SHORT).show();
                        }
                    }, mMap).execute("http://www.vidophp.tk/api/account/signin");




//                    JSONObject param = new JSONObject();
//                    try {
//                        param.put("id", "15");
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    new GetDataAsyncTask(new IView() {
//
//                        @Override
//                        public void onRequestSuccess(Bitmap bitmap) {
//
//                        }
//
//                        @Override
//                        public void onGetDataSuccess(JSONArray jsonArray) {
//
//                        }
//                    }, param).execute("http://www.vidophp.tk/api/account/getdata");
                }
            }
        });
    }

    private void onInit() {
        edtUserName = findViewById(R.id.txtUsername);
        edtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.Button);
    }

    private boolean onValidateForm() {
        mUserName = edtUserName.getText().toString();
        if (mUserName.length() < 1) {
            edtUserName.setError("Username field cannot be blank");
            return false;
        }

        mPassword = edtPassword.getText().toString();
        if (mPassword.length() < 1) {
            edtPassword.setError("Password field cannot be blank");
            return false;
        }
        return true;
    }
}