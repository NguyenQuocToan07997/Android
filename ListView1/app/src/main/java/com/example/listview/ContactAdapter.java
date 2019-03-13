package com.example.listview;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<ContactMedel> {
    List<ContactMedel> list;
    int GroupID;
    Context context;

    public ContactAdapter(Context context, int vg, List<ContactMedel> list) {
        super(context, vg, list);
        this.list = list;
        GroupID = vg;
        this.context = context;
    }

    public void setDatas( List<ContactMedel> list){
        this.list = list;
        notifyDataSetChanged();
    }

    class Viewholder {
        TextView textView1;
        TextView textView2;
        Button buttonCall;
        Button btnIcon;
        Button btnDe;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {

        View rowView = convertView;
        Viewholder viewholder = new Viewholder();
        final ContactMedel contactMedel = getItem(position);
        if (rowView == null) {
            rowView = LayoutInflater.from(getContext()).inflate(GroupID, parent, false);
            viewholder.textView1 = rowView.findViewById(R.id.txt);
            viewholder.textView2 = rowView.findViewById(R.id.txt1);
            viewholder.buttonCall = rowView.findViewById(R.id.btn);
            viewholder.btnIcon = rowView.findViewById(R.id.btnClick);
            viewholder.btnDe = rowView.findViewById(R.id.btnDelete);

        } else {
            viewholder = (Viewholder) rowView.getTag();
        }

        viewholder.textView1.setText(contactMedel.getName());
        viewholder.textView2.setText(contactMedel.getPhoneNumber());

        viewholder.buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contactMedel.getPhoneNumber()));
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                context.startActivity(intent);

            }
        });

        viewholder.btnIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ContactDetailActivity.class);
                intent.putExtra("name", contactMedel.getName());
                intent.putExtra("phone", contactMedel.getPhoneNumber());
                intent.putExtra("address", contactMedel.getAddress());
                intent.putExtra("city", contactMedel.getCity());
                context.startActivity(intent);

            }
        });

        viewholder.btnDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();


            }
        });

        rowView.setTag(viewholder);
        return rowView;

    }
}

