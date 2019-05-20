package com.example.thicuoikyandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

public class ProductActivity extends AppCompatActivity {

    EditText edtcredits;
    EditText edtdescription;
    EditText edtsubject_code;
    EditText edtsubject_name;

    Button btnEdit;
    Button btnSave;
    DatabaseReference myRef;

    @Override
    protected void onResume() {
        super.onResume();
        MainActivity.adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        OnIt();
        Enable();
        final Intent intent = getIntent();
        edtcredits.setText(intent.getStringExtra("credits"));
        edtdescription.setText(intent.getStringExtra("description"));
        edtsubject_code.setText(intent.getStringExtra("subject_code"));
        edtsubject_name.setText(intent.getStringExtra("subject_name"));

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disable();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disable();
                MainActivity.myRef = MainActivity.db.getReference("AdvancedAndroidFinalTest");
                MainActivity.myRef.child(String.valueOf(Integer.parseInt(edtcredits.getText().toString()))).child("credits").setValue(Integer.parseInt(edtcredits.getText().toString()));
                MainActivity.myRef.child(edtsubject_code.getText().toString()).child("description").setValue(edtdescription.getText().toString());
                MainActivity.myRef.child(edtsubject_code.getText().toString()).child("subject_code").setValue(edtsubject_code.getText().toString());
                MainActivity.myRef.child(edtsubject_code.getText().toString()).child("subject_name").setValue(edtsubject_name.getText().toString());
                finish();
            }
        });
    }

    private void OnIt() {
        edtcredits = findViewById(R.id.edt_dt_credits);
        edtdescription = findViewById(R.id.edt_dt_description);
        edtsubject_code = findViewById(R.id.edt_dt_subject_code);
        edtsubject_name = findViewById(R.id.edt_dt_subject_name);
        btnEdit = findViewById(R.id.btn_Edit);
        btnSave = findViewById(R.id.btn_Save);
    }

    private void Enable()
    {
        edtcredits.setEnabled(false);
        edtdescription.setEnabled(false);
        edtsubject_code.setEnabled(false);
        edtsubject_name.setEnabled(false);
    }

    private void Disable()
    {
        edtcredits.setEnabled(true);
        edtdescription.setEnabled(true);
        edtsubject_code.setEnabled(true);
        edtsubject_name.setEnabled(true);
    }
}
