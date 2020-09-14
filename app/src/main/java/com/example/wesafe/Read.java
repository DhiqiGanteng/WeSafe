package com.example.wesafe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wesafe.model.ListPenyakit;
import com.example.wesafe.model.Penyakit;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Read extends AppCompatActivity implements Adaptor.FirebaseDataListener{

    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ListPenyakit listPenyakit;
    private String Kejadian;
    private Bundle bundle;
    private FirebaseAuth auth;
    private TextView nmKejadian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        Intent intent = getIntent();
        Kejadian = intent.getStringExtra(AdaptorKejadian.EXTRA_KEJADIAN);

        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);
        nmKejadian = findViewById(R.id.nm_Kejadian);

        database = FirebaseDatabase.getInstance().getReference();

        auth = FirebaseAuth.getInstance();
        database.child("PENYAKIT").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listPenyakit = new ListPenyakit();
                int i =0;
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Penyakit penyakit = noteDataSnapshot.getValue(Penyakit.class);
                    penyakit.setKey(noteDataSnapshot.getKey());
                    if (penyakit.getId_kejadian().equals(Kejadian)){
                        listPenyakit.tambah(penyakit);
                        listPenyakit.getPenyakit(i).setKey(penyakit.getKey());
                        i++;
                    }
                }
                adapter = new Adaptor(listPenyakit, Read.this ,auth);
                rvView.setAdapter(adapter);
                nmKejadian.setText(Kejadian);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });



    }

    public static Intent getActIntent(Activity activity){
        return new Intent(activity, Read.class);

    }

    @Override
    public void onDeleteData(Penyakit penyakit, final int position) {
        database.child("PENYAKIT").child(penyakit.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(Read.this,"success delete", Toast.LENGTH_LONG).show();
            }
        });
    }
}
