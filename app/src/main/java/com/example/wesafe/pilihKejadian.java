package com.example.wesafe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.wesafe.model.Kejadian;
import com.example.wesafe.model.ListKejadian;
import com.example.wesafe.model.ListPenyakit;
import com.example.wesafe.model.Penyakit;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class pilihKejadian extends AppCompatActivity {

    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private AdaptorKejadian adaptor;
    private RecyclerView.LayoutManager layoutManager;
    private ListKejadian listKejadian;
    private Intent intent;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_kejadian);

        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);


        database = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

        database.child("KEJADIAN").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listKejadian = new ListKejadian();
                int i = 0;
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Kejadian kejadian = noteDataSnapshot.getValue(Kejadian.class);
                    kejadian.setKey(noteDataSnapshot.getKey());
                    listKejadian.tambah(kejadian);
                    listKejadian.getKejadian(i).setKey(kejadian.getKey());
                    i++;
                }
                adapter = new AdaptorKejadian(listKejadian, pilihKejadian.this,auth);
                adaptor = new AdaptorKejadian(listKejadian, pilihKejadian.this,auth);
                rvView.setAdapter(adapter);
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



}
