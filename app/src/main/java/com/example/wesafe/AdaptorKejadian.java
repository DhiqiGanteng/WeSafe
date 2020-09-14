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
import com.google.firebase.auth.FirebaseAuth;

public class AdaptorKejadian extends RecyclerView.Adapter<AdaptorKejadian.ViewHolder> {
    public static final String EXTRA_KEJADIAN = "com.example.android.wesafe.extra.KEJADIAN";
    private ListKejadian listKejadian;
    private Context context;
    private Context context2;
    private Intent intent;
    private FirebaseAuth auth;


    public AdaptorKejadian(ListKejadian listKejadian, Context ctx, FirebaseAuth auth){

        this.listKejadian = listKejadian;
        context=ctx;
        this.auth = auth;
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

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

/**

 * Inisiasi ViewHolder

 */
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.daftar_kejadian, parent, false);
// mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(context2, Read.class);
                intent.putExtra(EXTRA_KEJADIAN, listKejadian.getKejadian(position).getNama());
                context2.startActivity(intent);
            }
        });

        final String name = listKejadian.getKejadian(position).getNama();
        holder.tvKejadian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(context2, Read.class);
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
