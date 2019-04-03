package com.example.market2019;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adapter.ListProductsAdapter;
import com.example.model.ShoppingCard;
import com.example.model.detail;
import com.example.utils.DBHelper;

public class ChiTietActivity extends AppCompatActivity {
    TextView textViewTenGH;
    TextView textViewNgay;
    ListView listViewdetail;
    ListProductsAdapter listProductsAdapter;
    LinearLayout back, linearDone;
    ShoppingCard itemCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        onInit();
        onGetIntent();
        onEvent();
    }

    private void onEvent() {
        back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if (back != null)
                    DBHelper.saveLoinInfo(ChiTietActivity.this, null);
                Intent intent = new Intent(ChiTietActivity.this, HomeActivity.class);
                startActivity(intent);
                Toast.makeText(ChiTietActivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });
        linearDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemCard.setProducts(listProductsAdapter.getList());
                itemCard.setDone(true);
                DBHelper.saveCard(ChiTietActivity.this, itemCard);
                finish();
            }
        });
    }


    private void onGetIntent() {
        Intent intent = getIntent();
        itemCard = (ShoppingCard) intent.getSerializableExtra("CT");
        if (itemCard != null) {
            textViewTenGH.setText(itemCard.getCardName());
            textViewNgay.setText((itemCard.getDate()));
            listProductsAdapter = new ListProductsAdapter(ChiTietActivity.this, R.layout.sanpham_item, itemCard.getProducts());
            listProductsAdapter.setiShowChecked(true);
            listViewdetail.setAdapter(listProductsAdapter);
            if(itemCard.isDone()){
                linearDone.setVisibility(View.GONE);
                listProductsAdapter.setEditAble(false);
            }else{
                linearDone.setVisibility(View.VISIBLE);
                listProductsAdapter.setEditAble(true);
            }
        }
    }

    private void onInit() {
        textViewTenGH = findViewById(R.id.TenTP);
        textViewNgay = findViewById(R.id.Giatien);
        listViewdetail = findViewById(R.id.lvChiTiet);
        back = findViewById(R.id.linearLogout);
        linearDone = findViewById(R.id.linearDone);

    }
}
