package com.example.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Model.UserModel;
import com.example.thicuoikyandroid.ProductActivity;
import com.example.thicuoikyandroid.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    List<UserModel> models;
    int mResource;
    Context mContext;

    public ContactAdapter(List<UserModel> models, int mResource, Context mContext) {
        this.models = models;
        this.mResource = mResource;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(mResource, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        final UserModel model = models.get(position);
        viewHolder.credits.setText(String.valueOf(model.getCredits()));
        viewHolder.description.setText(model.getDescription());
        viewHolder.subject_code.setText(model.getSubject_code());
        viewHolder.subject_name.setText(model.getSubject_name());

        viewHolder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference reference;
                reference = FirebaseDatabase.getInstance().getReference("AdvancedAndroidFinalTest");
                reference.child(model.getKeyParent()).removeValue();
            }
        });
        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, ProductActivity.class);
                i.putExtra("credits", String.valueOf(model.getCredits()));
                i.putExtra("description", model.getDescription());
                i.putExtra("subject_code", model.getSubject_code());
                i.putExtra("subject_name", model.getSubject_name());
                i.putExtra("Key",model.getKeyParent());
                mContext.startActivity(i);
            }
        });
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView credits;
        TextView description;
        TextView subject_code;
        TextView subject_name;
        LinearLayout linearLayout;
        ImageView image;
        Button btndelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.linearLayout = itemView.findViewById(R.id.place_delete);
            this.credits = itemView.findViewById(R.id.txt_credits);
            this.description = itemView.findViewById(R.id.txt_description);
            this.subject_code = itemView.findViewById(R.id.txt_subject_code);
            this.subject_name = itemView.findViewById(R.id.txt_subject_name);
            this.image = itemView.findViewById(R.id.img_product);
            this.btndelete = itemView.findViewById(R.id.btn_Delete);

        }
    }

}