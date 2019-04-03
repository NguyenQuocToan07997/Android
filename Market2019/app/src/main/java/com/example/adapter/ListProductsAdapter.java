package com.example.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.market2019.R;
import com.example.model.Product;
import com.example.model.ShoppingCard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListProductsAdapter extends ArrayAdapter<Product> {
    private Context mContext;

    public ArrayList<Product> getList() {
        return list;
    }

    public void setiShowChecked(boolean iShowChecked) {
        this.iShowChecked = iShowChecked;
    }

    private boolean iShowChecked = false;

    public void setEditAble(boolean editAble) {
        isEditAble = editAble;
    }

    private boolean isEditAble = false;

    private ArrayList<Product> list = new ArrayList<>();
    private int mResource;

    public ListProductsAdapter(Context context, int resource, ArrayList<Product> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.list = objects;
        this.mResource = resource;
    }

    public void setDatas(ArrayList<Product> objects) {
        this.list = objects;
        notifyDataSetChanged();
    }

    public class ViewHolder {
        TextView textViewTP;
        TextView textViewGia;
        TextView textViewWeight;
        CheckBox item_check;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            convertView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textViewTP = convertView.findViewById(R.id.txt_TenTP);
            viewHolder.textViewGia = convertView.findViewById(R.id.txt_Gia);
            viewHolder.textViewWeight = convertView.findViewById(R.id.txt_KL);
            viewHolder.item_check = convertView.findViewById(R.id.item_check);

        } else {
            viewHolder = (ListProductsAdapter.ViewHolder) convertView.getTag();
        }
        final Product item = list.get(position);
        viewHolder.textViewTP.setText(String.valueOf(item.getName()));
        viewHolder.textViewGia.setText(String.valueOf(item.getPrice()));
        viewHolder.textViewWeight.setText(String.valueOf(item.getWeight()));
        if (item.isPayed()) {
            viewHolder.item_check.setChecked(true);
        } else {
            viewHolder.item_check.setChecked(false);
        }
        if (iShowChecked) {
            viewHolder.item_check.setVisibility(View.VISIBLE);
        } else {
            viewHolder.item_check.setVisibility(View.GONE);
        }
        if (isEditAble) {
            viewHolder.item_check.setEnabled(true);
            viewHolder.item_check.setTag(position);
            viewHolder.item_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    int checkPos = (int) compoundButton.getTag();
                    Product item = list.get(checkPos);
                    if (item.isPayed()) {
                        item.setPayed(false);
                    } else {
                        item.setPayed(true);
                    }
                }
            });
        } else {
            viewHolder.item_check.setEnabled(false);
        }


        return convertView;
    }
}

