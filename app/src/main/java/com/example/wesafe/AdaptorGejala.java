package com.example.wesafe;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import com.example.wesafe.model.ListGejala;

import java.util.ArrayList;

public class AdaptorGejala extends RecyclerView.Adapter<AdaptorGejala.ViewHolder> {
    private ListGejala listGejala;
    private Context context;
    private Context context2;
    private Intent intent;
    private Button btnGejala;
    private ArrayList<String> dataGejala;

    public AdaptorGejala(ListGejala listGejala, Context ctx , Button btnGejala){

        this.listGejala = listGejala;
        context=ctx;
        this.btnGejala = btnGejala;
        dataGejala = new ArrayList<>();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        /**

         * Inisiasi View

         * Di tutorial ini kita hanya menggunakan data String untuk tiap item

         * dan juga view nya hanyalah satu TextView

         */

        CheckBox cGejala;
        ViewHolder(View v) {
            super(v);
            context2 = v.getContext();
            cGejala = v.findViewById(R.id.checkGejala);
            btnGejala.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dataGejala = new ArrayList<>();
                    for (int i=0;i<listGejala.panjang();i++){
                        if (listGejala.getGejala(i).getChecked()){
                            dataGejala.add(listGejala.getGejala(i).getNama());
                        }
                    }
                    context2.startActivity(new Intent(context2,PertolonganActivity.class).putExtra("data", dataGejala));
                }
            });
        }
    }
    @Override
    public AdaptorGejala.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pilih_gejala, parent, false);
        AdaptorGejala.ViewHolder vh = new AdaptorGejala.ViewHolder(v);

        return vh;

    }

    @Override
    public void onBindViewHolder(final AdaptorGejala.ViewHolder holder, final int position) {
        final String name = listGejala.getGejala(position).getNama();
        holder.cGejala.setText(name);
        holder.cGejala.setChecked(listGejala.getGejala(position).getChecked());
        holder.cGejala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listGejala.getGejala(position).setChecked(holder.cGejala.isChecked());
            }
        });

        if (holder.cGejala.isChecked()){
            listGejala.getGejala(position).setChecked(true);
        }else{
            listGejala.getGejala(position).setChecked(false);
        }

    }

    @Override
    public int getItemCount() {
        return listGejala.panjang();

    }

    public ArrayList<String> getGejala(){
        return dataGejala;
    }

}
