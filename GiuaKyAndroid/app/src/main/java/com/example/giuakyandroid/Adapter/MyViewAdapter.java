package com.example.giuakyandroid.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.giuakyandroid.Model.PhoneModel;
import com.example.giuakyandroid.R;

import java.util.List;

public class MyViewAdapter extends RecyclerView.Adapter<MyViewAdapter.ViewHolder> {
    List<PhoneModel> models;
    int mResource;
    Context mContext;

    public MyViewAdapter(Context context, int resource, List<PhoneModel> objects) {
        this.mContext = context;
        this.mResource = resource;
        this.models = objects;
    }

    @NonNull
    @Override
    public MyViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(mResource, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewAdapter.ViewHolder viewHolder, int i) {
        PhoneModel model = models.get(i);
        viewHolder.edtproduct.setText(model.getProduct_name());
        viewHolder.edtprice.setText(String.valueOf(model.getPrice()));
        viewHolder.edtdescription.setText(String.valueOf(model.getDescription()));
        viewHolder.edtproducer.setText(model.getProducer());

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private EditText edtproduct;
        private EditText edtprice;
        private EditText edtdescription;
        private EditText edtproducer;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.edtproduct = itemView.findViewById(R.id.edt_product);
            this.edtprice = itemView.findViewById(R.id.edt_price);
            this.edtdescription = itemView.findViewById(R.id.edt_description);
            this.edtproducer = itemView.findViewById(R.id.edt_producer);

        }
    }
}