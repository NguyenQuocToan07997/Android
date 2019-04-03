package com.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.market2019.R;
import com.example.model.Product;
import com.example.model.ShoppingCard;

import java.util.List;

public class ListProductsAdapter extends ArrayAdapter<Product> {
    private Context mContext;
    private List<Product> list;
    private int mResource;

    public ListProductsAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.list = objects;
        this.mResource = resource;
    }

    public void setDatas(List<Product> objects) {
        this.list = objects;
        notifyDataSetChanged();
    }

    public class ViewHolder {
        TextView textViewTP;
        TextView textViewGia;
        TextView textViewWeight;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            convertView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textViewTP = convertView.findViewById(R.id.txt_TenTP);
            viewHolder.textViewGia = convertView.findViewById(R.id.txt_Gia);
            viewHolder.textViewWeight = convertView.findViewById(R.id.txt_KhoiLuong);

        } else {
            viewHolder = (ListProductsAdapter.ViewHolder) convertView.getTag();
        }
        final Product item = list.get(position);
        viewHolder.textViewTP.setText(String.valueOf(item.getName()));
        viewHolder.textViewGia.setText(String.valueOf(item.getPrice()));
        viewHolder.textViewWeight.setText(String.valueOf(item.getWeight()));

        return convertView;
    }
}

