package com.example.wesafe;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wesafe.model.ListRumahSakit;
import com.example.wesafe.model.RumahSakit;
import com.google.firebase.auth.FirebaseAuth;

public class AdaptorLokasi extends RecyclerView.Adapter<AdaptorLokasi.ViewHolder> {
    private static final String LOG_TAG = PertolonganActivity.class.getSimpleName();
    private Context context;
    private Context context2;
    private Intent intent;
    private FirebaseAuth auth;
    private ListRumahSakit listRumahSakit;
    private RumahSakit rumahSakit;


    public AdaptorLokasi(ListRumahSakit listRumahSakit, Context ctx){
        this.listRumahSakit = listRumahSakit;
        context=ctx;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imRS;
        TextView tvNama;
        TextView tvAlamat;
        TextView tvNoTelp;
        TextView tvJarak;
        Button btLokasi;
        Resources res ;
        Button btTelp;
        Drawable draw;
        ViewHolder(View v) {
            super(v);
            context2 = v.getContext();
            imRS = v.findViewById(R.id.imRs);
            tvNama = v.findViewById(R.id.tvNama);
            tvAlamat = v.findViewById(R.id.tvAlamat);
            tvNoTelp = v.findViewById(R.id.tvNoTelp);
            tvJarak = v.findViewById(R.id.tvJarak);
            btLokasi = v.findViewById(R.id.btLokasi);
            btTelp = v.findViewById(R.id.btTelp);
            res = v.getResources();




        }
    }
    @Override
    public AdaptorLokasi.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.daftar_lokasi, parent, false);
        AdaptorLokasi.ViewHolder vh = new AdaptorLokasi.ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(final AdaptorLokasi.ViewHolder holder, final int position) {
        final String name = listRumahSakit.getRumahSakit(position).getNama();
        holder.tvNama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.tvNama.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
        holder.tvNama.setText(name);

        final String alamat = listRumahSakit.getRumahSakit(position).getAlamat();
        holder.tvAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.tvAlamat.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
        holder.tvAlamat.setText(alamat);

        final String noTelp = listRumahSakit.getRumahSakit(position).getNoTelp();
        holder.tvNoTelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.tvNoTelp.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
        holder.tvNoTelp.setText(noTelp);
        final String jarak;
        if (listRumahSakit.getRumahSakit(position).getJarak()/1000 >=1){
            jarak = ""+listRumahSakit.getRumahSakit(position).getJarak()/1000+" KM";
        }else{
            jarak = ""+listRumahSakit.getRumahSakit(position).getJarak()+" M";
        }

        holder.tvJarak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.tvJarak.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                return true;
            }
        });
        holder.tvJarak.setText("Jarak : "+jarak);

        holder.btLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+listRumahSakit.getRumahSakit(position).getNama()));
                context.startActivity(intent);
            }
        });
        holder.btTelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+listRumahSakit.getRumahSakit(position).getNoTelp()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listRumahSakit.panjang();

    }
}
