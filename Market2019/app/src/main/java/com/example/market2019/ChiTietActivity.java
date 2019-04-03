package com.example.market2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.model.ShoppingCard;
import com.example.model.detail;

public class ChiTietActivity extends AppCompatActivity {
    TextView textViewTenGH;
    TextView textViewNgay;
    TextView textViewListChiTiet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        onInit();
        onGetIntent();
    }

    private void onGetIntent() {
        Intent intent = getIntent();
        detail item = (detail) intent.getSerializableExtra("CTMH");
        if(item != null) {
            textViewTenGH.setText(String.valueOf(item.getTenGH()));
            textViewNgay.setText(String.valueOf(item.getNgay()));
//            textViewListChiTiet.setText(String.valueOf(item.getListTP()));
        }
    }

    private void onInit() {
        textViewTenGH = findViewById(R.id.txtGH);
        textViewNgay = findViewById(R.id.txtNgay);


    }
}
