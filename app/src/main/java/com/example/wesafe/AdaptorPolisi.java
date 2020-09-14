package com.example.wesafe;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wesafe.model.ListPolisi;

public class AdaptorPolisi extends RecyclerView.Adapter<AdaptorPolisi.ViewHolder> {
    private static final String LOG_TAG = PertolonganActivity.class.getSimpleName();
    private Context context;
    private ListPolisi listPolisi;

    public AdaptorPolisi(ListPolisi listPolisi, Context ctx){
        this.listPolisi = listPolisi;
        context=ctx;
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama;
        TextView tvAlamat;
        TextView tvNoTelp;
        TextView tvJarak;
        Button btLokasi;
        Button btTelp;
        ViewHolder(View v) {
            super(v);
            tvNama = v.findViewById(R.id.tvNama);
            tvAlamat = v.findViewById(R.id.tvAlamat);
            tvNoTelp = v.findViewById(R.id.tvNoTelp);
            tvJarak = v.findViewById(R.id.tvJarak);
            btLokasi = v.findViewById(R.id.btLokasi);
            btTelp = v.findViewById(R.id.btTelp);
        }
    }
    @Override
    public AdaptorPolisi.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.daftar_polisi, parent, false);
        AdaptorPolisi.ViewHolder vh = new AdaptorPolisi.ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(final AdaptorPolisi.ViewHolder holder, final int position) {
        final String name = listPolisi.getPolisi(position).getNama();
        holder.tvNama.setText(name);

        final String alamat = listPolisi.getPolisi(position).getAlamat();
        holder.tvAlamat.setText(alamat);

        final String noTelp = listPolisi.getPolisi(position).getNoTelp();
        holder.tvNoTelp.setText(noTelp);
        final String jarak;
        if (listPolisi.getPolisi(position).getJarak()/1000 >=1){
            jarak = ""+listPolisi.getPolisi(position).getJarak()/1000+" KM";
        }else{
            jarak = ""+listPolisi.getPolisi(position).getJarak()+" M";
        }
        holder.tvJarak.setText("Jarak : "+jarak);

        holder.btLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+listPolisi.getPolisi(position).getNama()));
                context.startActivity(intent);
            }
        });
        holder.btTelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+listPolisi.getPolisi(position).getNoTelp()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listPolisi.panjang();

    }
}
