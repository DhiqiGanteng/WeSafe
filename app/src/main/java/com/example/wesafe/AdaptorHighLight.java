package com.example.wesafe;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wesafe.model.ListHighLight;

public class AdaptorHighLight extends RecyclerView.Adapter<AdaptorHighLight.ViewHolder> {
    private ListHighLight listHighLight;
    private Context context;
    private Context context2;

    public AdaptorHighLight(){

    }
    public AdaptorHighLight (ListHighLight listHighLight, Context ctx){

            this.listHighLight = listHighLight;
            context=ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txJudul;
        TextView txTanggal;
        ImageView imBerita;
        ViewHolder(View v) {
            super(v);
            context2 = v.getContext();
            txJudul = v.findViewById(R.id.txBerita);
            txTanggal = v.findViewById(R.id.txTanggal);
            imBerita = v.findViewById(R.id.imBerita);

        }
    }
        @Override
        public AdaptorHighLight.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.daftar_highligh, parent, false);
            AdaptorHighLight.ViewHolder vh = new AdaptorHighLight.ViewHolder(v);
            return vh;

        }
        @Override
        public void onBindViewHolder(final AdaptorHighLight.ViewHolder holder, final int position) {
            final int i =position+1;
            if (listHighLight.panjang()!=i) {
                final String judul = listHighLight.getHighLight(i).getJudul();
                holder.txJudul.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(listHighLight.getHighLight(i).getLink())));
                    }
                });
                holder.txJudul.setText(judul);

                final String tanggal = listHighLight.getHighLight(i).getTanggal();
                holder.txTanggal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(listHighLight.getHighLight(i).getLink())));
                    }
                });
                holder.txTanggal.setText(tanggal);


                int image = context.getResources().getIdentifier("@drawable/" + listHighLight.getHighLight(i).getGambar(), null, context.getPackageName());
                holder.imBerita.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(listHighLight.getHighLight(i).getLink())));
                    }
                });
                holder.imBerita.setImageResource(image);
            }
        }

        @Override
        public int getItemCount() {
            return listHighLight.panjang();

        }

}
