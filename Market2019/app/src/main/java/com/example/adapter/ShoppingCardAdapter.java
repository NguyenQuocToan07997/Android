package com.example.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.market2019.HomeActivity;
import com.example.market2019.R;
import com.example.model.Product;
import com.example.model.ShoppingCard;
import com.example.utils.DBHelper;

import java.util.List;

public class ShoppingCardAdapter extends ArrayAdapter<ShoppingCard> {

    private Context mContext;
    private List<ShoppingCard> list;
    private ShoppingCardAdapter adapter;
    private int mResource;

    public ShoppingCardAdapter(Context context, int resource, List<ShoppingCard> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.list = objects;
        this.mResource = resource;
    }

    public void setDatas(List<ShoppingCard> objects) {
        this.list = objects;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (viewHolder == null) {
            convertView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textViewTenGioHang = convertView.findViewById(R.id.txt_TenGioHang);
            viewHolder.textViewTongTien = convertView.findViewById(R.id.txt_TongTien);
            viewHolder.textViewNgay = convertView.findViewById(R.id.txt_Ngay);
            viewHolder.Delete = convertView.findViewById(R.id.item_delete);
            viewHolder.imgStatus = convertView.findViewById(R.id.imgStatus);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ShoppingCard item = list.get(position);
        viewHolder.textViewTenGioHang.setText(String.valueOf(item.getCardName()));
        int tongTien = 0;
        if (item.getProducts().size() > 0) {
            for (int i = 0; i < item.getProducts().size(); i++) {
                tongTien = tongTien + item.getProducts().get(i).getPrice();
            }

        }
        viewHolder.textViewTongTien.setText(tongTien + "vnđ");
        viewHolder.textViewNgay.setText(String.valueOf(item.getDate()));
        viewHolder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle("Thông Báo")
                        .setMessage("Bạn có muốn xóa không?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                DBHelper.deleteCard(mContext, item.getId());
                                list.remove(item);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();

            }

        });
        if (item.isDone()) {
            viewHolder.imgStatus.setImageResource(R.mipmap.iconnn);
        } else {
            viewHolder.imgStatus.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp);
        }
        return convertView;
    }

    public class ViewHolder {
        TextView textViewTenGioHang;
        TextView textViewTongTien;
        TextView textViewNgay;
        ImageView Delete, imgStatus;

    }
}