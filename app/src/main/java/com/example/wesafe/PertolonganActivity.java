package com.example.wesafe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wesafe.model.ListPenyakit;
import com.example.wesafe.model.Penyakit;
import com.example.wesafe.model.Polisi;
import com.example.wesafe.model.RumahSakit;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PertolonganActivity extends AppCompatActivity {
    private static final String LOG_TAG = PertolonganActivity.class.getSimpleName();;
    private TextView tPenyakit;
    private TextView tPresetase;
    private TextView tDeskripsi;
    private TextView tPertolongan;
    private TextView tvNamaPolisi;
    private TextView tvNoTelpPolisi;
    private TextView tvJarakPolisi;
    private Button btTelpPolisi;
    private TextView tvNamaRs;
    private TextView tvNoTelpRs;
    private TextView tvJarakRs;
    private Button btTelpRs;
    private DatabaseReference database;
    private ListPenyakit listPenyakit;
    private Penyakit penyakit;
    private ArrayList<String> dataGejala;
    private Boolean isThreshold;
    private int index;
    private String noTelpPolisi;
    private String noTelpRS;

    @Override
    protected void onStart() {
        super.onStart();
        loadPenyakit();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertolongan);

        tPenyakit = findViewById(R.id.tnamapenyakit);
        tPresetase = findViewById(R.id.tPresentase);
        tDeskripsi = findViewById(R.id.tDeskripsi);
        tPertolongan = findViewById(R.id.tpertolongan);
        tvNamaPolisi = findViewById(R.id.tvNamaPolisi);
        tvJarakPolisi = findViewById(R.id.tvJarakPolisi);
        tvNoTelpPolisi = findViewById(R.id.tvNoTelpPolisi);
        btTelpPolisi = findViewById(R.id.btTelpPolisi);
        tvNamaRs = findViewById(R.id.tvNamaRS);
        tvJarakRs = findViewById(R.id.tvJarakRS);
        tvNoTelpRs = findViewById(R.id.tvNoTelpRS);
        btTelpRs = findViewById(R.id.btTelpRS);
        database = FirebaseDatabase.getInstance().getReference();
        dataGejala = (ArrayList<String>) getIntent().getSerializableExtra("data");
        loadPenyakit();
        loadPolisiRs();

        btTelpPolisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+noTelpPolisi));
                startActivity(intent);
            }
        });
        btTelpRs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+noTelpRS));
                startActivity(intent);
            }
        });





    }
    public void loadPolisiRs(){
        database.child("POLISI").orderByChild("jarak").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    if (i == 0){
                        Polisi polisi = noteDataSnapshot.getValue(Polisi.class);
                        final String jarak;
                        if (polisi.getJarak()/1000 >=1){
                            jarak = "Jarak : "+polisi.getJarak()/1000+" KM";
                        }else{
                            jarak = "Jarak : "+polisi.getJarak()+" M";
                        }
                        tvNamaPolisi.setText(polisi.getNama());
                        tvJarakPolisi.setText(jarak);
                        tvNoTelpPolisi.setText("Telephone : "+polisi.getNoTelp());
                        noTelpPolisi = polisi.getNoTelp();
                    }
                    i++;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
        database.child("RumahSakit").orderByChild("jarak").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    if (i == 0){
                        RumahSakit rumahSakit = noteDataSnapshot.getValue(RumahSakit.class);
                        final String jarak;
                        if (rumahSakit.getJarak()/1000 >=1){
                            jarak = "Jarak : "+rumahSakit.getJarak()/1000+" KM";
                        }else{
                            jarak = "Jarak : "+rumahSakit.getJarak()+" M";
                        }
                        tvNamaRs.setText(rumahSakit.getNama());
                        tvJarakRs.setText(jarak);
                        tvNoTelpRs.setText("Telephone : "+rumahSakit.getNoTelp());
                        noTelpRS = rumahSakit.getNoTelp();
                    }
                    i++;
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }
    public void loadPenyakit(){
        database.child("PENYAKIT").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listPenyakit = new ListPenyakit();
                int i = 0;
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Penyakit penyakit = noteDataSnapshot.getValue(Penyakit.class);
                    penyakit.setKey(noteDataSnapshot.getKey());
                    listPenyakit.tambah(penyakit);
                    listPenyakit.getPenyakit(i).setKey(penyakit.getKey());
                    i++;
                }
                caseBase();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }

    public void caseBase(){
        isThreshold = false;
        ArrayList<Double> maxKemiripan = new ArrayList<>();
        int maxKomponen;
        int jmlSama;
        double threshold=0.5;
        for (int k = 0 ;k<listPenyakit.panjang();k++) {
            jmlSama = 0;
            for (int i = 0 ;i<dataGejala.size();i++){
                for (int j = 0; j < listPenyakit.getPenyakit(k).getGejala().size(); j++) {
                    if (dataGejala.get(i).equals(listPenyakit.getPenyakit(k).getGejala().get(j))) {
                        jmlSama++;
                    }else{
                        jmlSama = jmlSama;
                    }
                }
            }
            listPenyakit.getPenyakit(k).setJmlSama(jmlSama);
            maxKomponen = Math.max(dataGejala.size(), listPenyakit.getPenyakit(k).getGejala().size());
            listPenyakit.getPenyakit(k).setMaksKomponen(maxKomponen);
            listPenyakit.getPenyakit(k).setKemiripan(listPenyakit.getPenyakit(k).getJmlSama() , listPenyakit.getPenyakit(k).getMaksKomponen());

        }
        double smt = 0;
        for (int i = 0;i<listPenyakit.panjang();i++){
            isThreshold = true;
                if (listPenyakit.getPenyakit(i).getKemiripan()>smt) {
                    smt = listPenyakit.getPenyakit(i).getKemiripan();
                    index = i;
                }
        }
        if (isThreshold){
            Log.d(LOG_TAG,""+listPenyakit.getPenyakit(index).getJmlSama());
            Log.d(LOG_TAG,""+listPenyakit.getPenyakit(index).getMaksKomponen());
            Log.d(LOG_TAG,""+listPenyakit.getPenyakit(index).getKemiripan());
            tPenyakit.setText(listPenyakit.getPenyakit(index).getNama());
            tPresetase.setText(listPenyakit.getPenyakit(index).kemiripanToString());
            tDeskripsi.setText(listPenyakit.getPenyakit(index).getDeskripsi());
            tPertolongan.setText(listPenyakit.getPenyakit(index).getPertolongan());
        }else{
            tPenyakit.setText("Tidak ada penyakit yang cocok");
        }

    }
}
