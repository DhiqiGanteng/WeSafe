package com.example.wesafe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wesafe.model.ListKejadian;

public class AdaptorKejadianFU extends RecyclerView.Adapter<AdaptorKejadianFU.ViewHolder>{
    public static final String EXTRA_KEJADIAN = "com.example.android.wesafe.extra.KEJADIAN";
    private ListKejadian listKejadian;
    private Context context;
    private Context context2;
    private Intent intent;

    public AdaptorKejadianFU(ListKejadian listKejadian, Context ctx){

        this.listKejadian = listKejadian;
        context=ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        /**

         * Inisiasi View

         * Di tutorial ini kita hanya menggunakan data String untuk tiap item

         * dan juga view nya hanyalah satu TextView

         */

        CardView cardView;
        TextView tvKejadian;
        ViewHolder(View v) {
            super(v);
            context2 = v.getContext();
            tvKejadian = (TextView) v.findViewById(R.id.tv_Kejadian);
            cardView = (CardView)v.findViewById(R.id.cardview);
        }
    }
    @Override
    public AdaptorKejadianFU.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.daftar_kejadian, parent, false);
        AdaptorKejadianFU.ViewHolder vh = new AdaptorKejadianFU.ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(AdaptorKejadianFU.ViewHolder holder, final int position) {

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(context2, PilihGejalaActivity.class);
                intent.putExtra(EXTRA_KEJADIAN, listKejadian.getKejadian(position).getNama());
                context2.startActivity(intent);
            }
        });
        final String name = listKejadian.getKejadian(position).getNama();
        holder.tvKejadian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(context2, PilihGejalaActivity.class);
                intent.putExtra(EXTRA_KEJADIAN, listKejadian.getKejadian(position).getNama());
                context2.startActivity(intent);
            }
        });

        holder.tvKejadian.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
        holder.tvKejadian.setText(name);

    }

    @Override
    public int getItemCount() {

        return listKejadian.panjang();

    }
}