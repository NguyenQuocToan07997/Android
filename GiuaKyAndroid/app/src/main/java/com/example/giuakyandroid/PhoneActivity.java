package com.example.giuakyandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.giuakyandroid.Adapter.MyViewAdapter;
import com.example.giuakyandroid.AsyncTask.PhoneAsyncTask;
import com.example.giuakyandroid.Model.PhoneModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<PhoneModel> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        models = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        Map<String,String> map = new HashMap<>();
        map.put("id", "15");
        new PhoneAsyncTask(PhoneActivity.this,new IView(){


            @Override
            public void onGetDataSuccess(JSONArray jsonArray) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        PhoneModel model = new PhoneModel();
                        model.setId(Integer.valueOf(jsonObject.getString("id")));
                        model.setProduct_name(jsonObject.getString("product_name"));
                        model.setPrice(Integer.valueOf(jsonObject.getString("price")));
                        model.setDescription(jsonObject.getString("description"));
                        model.setProducer(jsonObject.getString("producer"));

                        models.add(model);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                MyViewAdapter adapter = new MyViewAdapter(PhoneActivity.this, R.layout.phone_item, models);
                recyclerView.setAdapter(adapter);
            }
        },map).execute("http://www.vidophp.tk/api/account/getdata");
    }
}
