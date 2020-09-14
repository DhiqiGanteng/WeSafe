package com.example.wesafe;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wesafe.model.ListRumahSakit;
import com.example.wesafe.model.RumahSakit;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LokasiActivity extends AppCompatActivity {
    private static final String LOG_TAG = PertolonganActivity.class.getSimpleName();
    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private AdaptorLokasi adaptor;
    private RecyclerView.LayoutManager layoutManager;
    private ListRumahSakit listRumahSakit;
    private RumahSakit rumahSakit;
    private Intent intent;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi);
        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);
        database = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

        database.child("RumahSakit").orderByChild("jarak").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listRumahSakit = new ListRumahSakit();
                int i = 0;
                float[] distance = new float[1];
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    RumahSakit rumahSakit = noteDataSnapshot.getValue(RumahSakit.class);
                    rumahSakit.setKey(noteDataSnapshot.getKey());
                    listRumahSakit.tambah(rumahSakit);
                    i++;

                }
                adapter = new AdaptorLokasi(listRumahSakit, LokasiActivity.this);
                adaptor = new AdaptorLokasi(listRumahSakit, LokasiActivity.this);
                rvView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });


    }
}
