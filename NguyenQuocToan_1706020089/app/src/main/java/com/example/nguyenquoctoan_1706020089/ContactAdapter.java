package com.example.nguyenquoctoan_1706020089;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

    public class ContactAdapter extends ArrayAdapter<Courselist> {
        List<Courselist> list;
        int GroupID;
        Context context;

        public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Courselist> objects) {
            super(context, resource, objects);
            this.list = objects;
            GroupID = resource;
            this.context = context;
        }

        class Viewholder {
            TextView textView1;
            TextView textView2;
            TextView textView3;

        }

        @Override
        public View getView(int position, final View convertView, ViewGroup parent) {

            View rowView = convertView;
            Viewholder viewholder = new Viewholder();
            final Courselist contactMedel = list.get(position);

            if (rowView == null) {
                rowView = LayoutInflater.from(getContext()).inflate(GroupID, parent, false);
                viewholder.textView1 = rowView.findViewById(R.id.txtTenMonHoc);
                viewholder.textView2 = rowView.findViewById(R.id.txtMaMonHoc);
                viewholder.textView3 = rowView.findViewById(R.id.txtSotinchi);
                rowView.setTag(viewholder);

            } else {
                viewholder = (Viewholder) rowView.getTag();
            }

            viewholder.textView1.setText(contactMedel.getTenMH());
            viewholder.textView2.setText(contactMedel.getMaMH());
            viewholder.textView3.setText(Integer.toString(contactMedel.getSoTc()));


            return rowView;

        }


    }
