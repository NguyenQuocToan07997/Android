package com.example.nguyenquoctoan_1706020089;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<Courselist> Courselist = new ArrayList<>();
    ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onInt();
        Add();


        adapter = new ContactAdapter(this, R.layout.contactitem, Courselist);
        listView.setAdapter(adapter);
    }

    private void Add() {
        Courselist.add(new Courselist("Android Cơ Bản","2TH111",3));
        Courselist.add(new Courselist("Android Anh Văn 2","2DC11",3));
        Courselist.add(new Courselist("Android Cơ Bản","3Tt111",3));
    }

    private void onInt() {
        listView = findViewById(R.id.listview);
    }
}

