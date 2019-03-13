package com.example.listview;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.listview.ContactMedel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<ContactMedel> userContacts = new ArrayList<>();
    ContactAdapter adapter;
    ListView listView;
    Button btnAddNumb;
    SwipeRefreshLayout refreshView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.ListView);
        refreshView = findViewById(R.id.refreshView);

        btnAddNumb = findViewById(R.id.btnAdd);
        btnAddNumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ContactDetailActivity.class);
                startActivityForResult(intent, 12);

            }
        });


        AddContacts();

        adapter = new ContactAdapter(this, R.layout.contact_item, userContacts);
        listView.setAdapter(adapter);
        listView.setClickable(true);
        refreshView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });

    }

//    @Override
//    public void onBackPressed() {
//        Toast.makeText(this, "Add item success", Toast.LENGTH_LONG).show();
//    } ======> Back prohibit(cấm, ngăn trở)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 12) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Add item success", Toast.LENGTH_LONG).show();
                String name = data.getStringExtra("Name");
                String phone = data.getStringExtra("phone");
                String address = data.getStringExtra("address");
                String city = data.getStringExtra("city");
                ContactMedel c = new ContactMedel();
                c.setName(name);
                c.setPhoneNumber(phone);
                c.setAddress(address);
                c.setCity(city);
                userContacts.add(c);
                refreshData();


            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Add item fail", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void refreshData() {
        adapter.setDatas(userContacts);
        refreshView.setRefreshing(false);
    }

    void ShowContact() {
        Intent intent = new Intent(this, ContactDetailActivity.class);
        startActivity(intent);
    }

    private void AddContacts() {
        userContacts.add(new ContactMedel("Toan A", "123", "Chau Thanh", "Tay Ninh"));
        userContacts.add(new ContactMedel("Toan B", "111", "Hoa Thanh", "Tay Ninh"));
        userContacts.add(new ContactMedel("Toan C", "222", "Tan Bien", "Tay Ninh"));
        userContacts.add(new ContactMedel("Toan D", "333", "Trang Bang", "Tay Ninh"));
    }

}