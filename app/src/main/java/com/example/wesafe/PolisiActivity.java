package com.example.wesafe;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wesafe.model.ListPolisi;
import com.example.wesafe.model.Polisi;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PolisiActivity extends AppCompatActivity {
    private static final String LOG_TAG = PertolonganActivity.class.getSimpleName();
    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private AdaptorLokasi adaptor;
    private RecyclerView.LayoutManager layoutManager;
    private ListPolisi listPolisi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polisi);
        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);
        database = FirebaseDatabase.getInstance().getReference();

        database.child("POLISI").orderByChild("jarak").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listPolisi = new ListPolisi();
                int i = 0;
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Polisi polisi = noteDataSnapshot.getValue(Polisi.class);
                    polisi.setKey(noteDataSnapshot.getKey());
                    listPolisi.tambah(polisi);
                    i++;

                }
                adapter = new AdaptorPolisi(listPolisi, PolisiActivity.this);
                rvView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }
}
