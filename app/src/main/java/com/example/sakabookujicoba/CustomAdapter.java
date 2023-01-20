package com.example.sakabookujicoba;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    Activity activity;
    ArrayList cf_id, cf_cash, cf_date, cf_kete, cf_desc;

    CustomAdapter(Activity activity, Context context, ArrayList cf_id, ArrayList cf_cash, ArrayList cf_date, ArrayList cf_kete, ArrayList cf_desc){
        this.activity = activity;
        this.context = context;
        this.cf_id = cf_id;
        this.cf_cash = cf_cash;
        this.cf_date = cf_date;
        this.cf_kete = cf_kete;
        this.cf_desc = cf_desc;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.template_transaksi, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.cf_id_txt.setText(String.valueOf(cf_id.get(position)));
        holder.cf_cash_txt.setText(String.valueOf(cf_cash.get(position)));
        holder.cf_date_txt.setText(String.valueOf(cf_date.get(position)));
        holder.cf_kete_txt.setText(String.valueOf(cf_kete.get(position)));
        holder.cf_desc_txt.setText(String.valueOf(cf_desc.get(position)));


        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("id", String.valueOf(cf_id.get(holder.getAdapterPosition())));
                intent.putExtra("cash", String.valueOf(cf_cash.get(holder.getAdapterPosition())));
                intent.putExtra("date", String.valueOf(cf_date.get(holder.getAdapterPosition())));
                intent.putExtra("kete", String.valueOf(cf_kete.get(holder.getAdapterPosition())));
                intent.putExtra("desc", String.valueOf(cf_desc.get(holder.getAdapterPosition())));
                activity.startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public int getItemCount() { return cf_id.size(); }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView cf_id_txt, cf_cash_txt, cf_date_txt, cf_kete_txt, cf_desc_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cf_id_txt = itemView.findViewById(R.id.idtransaksi);
            cf_cash_txt = itemView.findViewById(R.id.transaksijumlah);
            cf_date_txt = itemView.findViewById(R.id.transaksitanggal);
            cf_kete_txt = itemView.findViewById(R.id.transaksiketerangan);
            cf_desc_txt = itemView.findViewById(R.id.transaksideskripsi);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

}
