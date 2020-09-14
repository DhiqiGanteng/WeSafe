package com.example.wesafe;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wesafe.model.ListPenyakit;
import com.example.wesafe.model.Penyakit;
import com.google.firebase.auth.FirebaseAuth;

public class Adaptor extends RecyclerView.Adapter<Adaptor.ViewHolder> {
    public static final String EXTRA_IDPENYAKIT = "com.example.android.wesafe.extra.IDPENYAKIT";
    private ListPenyakit listPenyakit;
    private Context context;
    private Context context2;
    private Intent intent;
    private FirebaseAuth auth;
    FirebaseDataListener listener;

    public Adaptor(ListPenyakit listPenyakit, Context ctx, FirebaseAuth auth){

        this.listPenyakit = listPenyakit;
        context=ctx;
        listener = (Read)ctx;
        this.auth = auth;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        /**

         * Inisiasi View

         * Di tutorial ini kita hanya menggunakan data String untuk tiap item

         * dan juga view nya hanyalah satu TextView

         */

        TextView tvNamaPenyakit;
        TextView tvDeskripsi;
        TextView tvGejala;
        TextView tvPenyebab;
        TextView tvPertolongan;
        TextView tvCatatan;
        WebView webYoutube;
        Button btEdit;
        Button btDelete;
        ViewHolder(View v) {
            super(v);
            context2 = v.getContext();
            tvNamaPenyakit = (TextView) v.findViewById(R.id.tv_namapenyakit);
            tvDeskripsi = (TextView) v.findViewById(R.id.tv_deskripsi);
            tvGejala = (TextView) v.findViewById(R.id.tv_gejala);
            tvPenyebab = (TextView) v.findViewById(R.id.tv_penyebab);
            tvPertolongan = (TextView) v.findViewById(R.id.tv_pertolongan);
            tvCatatan = (TextView) v.findViewById(R.id.tv_catatan);
            btDelete = (Button) v.findViewById(R.id.btn_delete);
            btEdit = (Button)v.findViewById(R.id.btn_edit);
            webYoutube = v.findViewById(R.id.webYoutube);

            webYoutube.setWebViewClient(new WebViewClient());
            webYoutube.setWebChromeClient(new WebChromeClient());
            webYoutube.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            webYoutube.getSettings().setJavaScriptEnabled(true);
            webYoutube.getSettings().setPluginState(WebSettings.PluginState.ON);
            webYoutube.getSettings().setDefaultFontSize(18);
        }


    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

/**

 * Inisiasi ViewHolder

 */
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.daftar_penyakit, parent, false);
// mengeset ukuran view, margin, padding, dan parameter layout lainnya
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (listPenyakit.panjang()!=0) {
            if (auth.getCurrentUser() != null) {
                holder.btEdit.setVisibility(View.VISIBLE);
                holder.btDelete.setVisibility((View.VISIBLE));
            } else {
                holder.btEdit.setVisibility(View.INVISIBLE);
                holder.btDelete.setVisibility((View.INVISIBLE));
            }

            final String name = listPenyakit.getPenyakit(position).getNama();
            holder.tvNamaPenyakit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }

            });
            holder.tvNamaPenyakit.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return true;
                }

            });
            holder.tvNamaPenyakit.setText(name);

            final String desk = listPenyakit.getPenyakit(position).getDeskripsi();
            holder.tvDeskripsi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }

            });
            holder.tvDeskripsi.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return true;
                }

            });

            holder.tvDeskripsi.setText(desk);

            final String gej = listPenyakit.getPenyakit(position).gejalaToString();
            holder.tvGejala.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }

            });
            holder.tvGejala.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return true;
                }

            });

            holder.tvGejala.setText(gej);


            final String penyebab = listPenyakit.getPenyakit(position).getPenyebab();
            holder.tvPenyebab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }

            });
            holder.tvPenyebab.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return true;
                }

            });

            holder.tvPenyebab.setText(penyebab);

            final String pert = listPenyakit.getPenyakit(position).getPertolongan();
            holder.tvPertolongan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }

            });
            holder.tvPertolongan.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return true;
                }

            });

            holder.tvPertolongan.setText(pert);

            final String cata = listPenyakit.getPenyakit(position).getCatatan();
            holder.tvCatatan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }

            });
            holder.tvCatatan.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    return true;
                }

            });

            holder.tvCatatan.setText(cata);

            String kodeHTML = "<head></head><body>" +
                    "<iframe width=\"320\" height=\"180\" src=\"https://www.youtube.com/embed/" + listPenyakit.getPenyakit(position).getLinkYoutube() +
                    "\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>" +
                    "</body>";
            if (listPenyakit.getPenyakit(position).getLinkYoutube().equals("")) {
                holder.webYoutube.setVisibility(View.INVISIBLE);
            } else {
                holder.webYoutube.setVisibility(View.VISIBLE);
                holder.webYoutube.loadData(kodeHTML, "text/html; charset=utf-8", null);
            }
            holder.btEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    context2.startActivity(Create.getActIntent((Activity) context2).putExtra("data", listPenyakit.getPenyakit(position)));
                }

            });

            holder.btDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder myAlertBuilder =
                            new AlertDialog.Builder(context2);

                    // Set the dialog title and message.
                    myAlertBuilder.setTitle("DELETE");
                    myAlertBuilder.setMessage("Apakah anda yakin ingin menghapusnya?");

                    // Add the dialog buttons.
                    myAlertBuilder.setPositiveButton(R.string.ok_button,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    listener.onDeleteData(listPenyakit.getPenyakit(position), position);
                                    Toast.makeText(context2.getApplicationContext(),
                                            R.string.pressed_ok,
                                            Toast.LENGTH_SHORT).show();
                                }
                            });

                    myAlertBuilder.setNegativeButton(R.string.cancel_button,
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    // User cancelled the dialog.
                                    Toast.makeText(context2.getApplicationContext(),
                                            R.string.pressed_cancel,
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                    // Create and show the AlertDialog.
                    myAlertBuilder.show();
                }

            });

        }else{
            holder.btEdit.setVisibility(View.INVISIBLE);
            holder.btDelete.setVisibility((View.INVISIBLE));
        }


    }


    @Override

    public int getItemCount() {

/**

 * Mengembalikan jumlah item pada barang

 */

        return listPenyakit.panjang();

    }
    public interface FirebaseDataListener{
        void onDeleteData(Penyakit penyakit, int position);
    }
}
