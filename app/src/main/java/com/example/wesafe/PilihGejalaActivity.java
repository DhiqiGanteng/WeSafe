package com.example.wesafe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wesafe.model.Gejala;
import com.example.wesafe.model.ListGejala;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PilihGejalaActivity extends AppCompatActivity {
    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private AdaptorGejala adaptor;
    private RecyclerView.LayoutManager layoutManager;
    private ListGejala listGejala;
    private String gejala1;
    private Button btnGejala;
    private TextView nmKejadian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_gejala);

        Intent intent = getIntent();
        gejala1 = intent.getStringExtra(AdaptorKejadianFU.EXTRA_KEJADIAN);

        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);
        btnGejala = findViewById(R.id.btn_pGejala);
        nmKejadian = findViewById(R.id.nm_Kejadian);
        database = FirebaseDatabase.getInstance().getReference();
        nmKejadian.setText(gejala1);
        database.child("GEJALA").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listGejala = new ListGejala();
                int i = 0;
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Gejala gejala = noteDataSnapshot.getValue(Gejala.class);
                    gejala.setKey(noteDataSnapshot.getKey());
                    if (gejala.getNamaKejadian().equals(gejala1)) {
                        listGejala.tambah(gejala);
                        listGejala.getGejala(i).setKey(gejala.getKey());
                        listGejala.getGejala(i).setChecked(false);
                        i++;
                    }

                }
                adapter = new AdaptorGejala(listGejala, PilihGejalaActivity.this,btnGejala);
                adaptor = new AdaptorGejala(listGejala, PilihGejalaActivity.this,btnGejala);
                rvView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });



    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, PertolonganActivity.class);

    }
}
