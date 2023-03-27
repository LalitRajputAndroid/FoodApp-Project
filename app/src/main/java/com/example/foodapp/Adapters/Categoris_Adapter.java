package com.example.foodapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.Adapters.All_Modals.Categoris_Modal;
import com.example.foodapp.R;

import java.util.ArrayList;

public class Categoris_Adapter extends RecyclerView.Adapter<Categoris_Adapter.CategorisViewholder>{

    ArrayList<Categoris_Modal> c_list;

    public Categoris_Adapter(ArrayList<Categoris_Modal> c_list) {
        this.c_list = c_list;
    }

    @NonNull
    @Override
    public CategorisViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.categoris_singlerow,parent,false);

        return new CategorisViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategorisViewholder holder, int position) {

        holder.itemimg.setImageResource(c_list.get(position).getC_img());
        holder.itemname.setText(c_list.get(position).getC_name());
    }

    @Override
    public int getItemCount() {
        return c_list.size();
    }

    public class CategorisViewholder extends RecyclerView.ViewHolder {
        ImageView itemimg;
        TextView itemname;

        public CategorisViewholder(@NonNull View itemView) {
            super(itemView);

            itemimg = itemView.findViewById(R.id.itemimg_id);
            itemname = itemView.findViewById(R.id.itemname_id);
        }
    }
}
