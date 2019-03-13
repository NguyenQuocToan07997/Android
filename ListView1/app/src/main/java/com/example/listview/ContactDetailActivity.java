package com.example.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ContactDetailActivity extends AppCompatActivity {
    EditText name;
    EditText Number;
    EditText Address;
    EditText City;
    Button btnB;
    Button btnAdd;

    String mName = "";
    String mPhone = "";
    String mAddress = "";
    String mCity = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        Init();
        final Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        Number.setText(intent.getStringExtra("phone"));
        Address.setText(intent.getStringExtra("address"));
        City.setText(intent.getStringExtra("city"));

        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
               finish();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ValidateForm()) {
                    Intent i = new Intent();
                    i.putExtra("Name", mName);
                    i.putExtra("phone", mPhone);
                    i.putExtra("address", mAddress);
                    i.putExtra("city", mCity);


                    setResult(RESULT_OK, i);
                    finish();

                }
            }

        });
    }

    private boolean ValidateForm() {
        mName = name.getText().toString();
        if (mName.length() < 1) {
            name.setError("Name cannot be null");
            return false;
        }
        mAddress = Address.getText().toString();
        if (mAddress.length() < 1) {
            Address.setError("Address cannot be null");
            return false;
        }
        mPhone = Number.getText().toString();
        if (mPhone.length() < 1) {
            Number.setError("Number cannot be null");
            return false;
        }
        mCity = City.getText().toString();
        if (mCity.length() < 1) {
            City.setError("City cannot be null");
            return false;
        }
        return true;
    }


    public void Init() {
        name = findViewById(R.id.txt2);
        Number = findViewById(R.id.txt3);
        Address = findViewById(R.id.txt4);
        City = findViewById(R.id.txt5);
        btnB = findViewById(R.id.btnBack);
        btnAdd = findViewById(R.id.Add);
    }

}
