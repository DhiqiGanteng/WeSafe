package com.example.wesafe;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wesafe.model.HighLight;
import com.example.wesafe.model.ListHighLight;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HighLightActivity extends AppCompatActivity {
    private DatabaseReference database;
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView txJudul;
    private TextView txTanggal;
    private ImageView imBerita;
    private ListHighLight listHighLight;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highligh);
        rvView = (RecyclerView) findViewById(R.id.rv_main);
        rvView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,2);
        rvView.setLayoutManager(layoutManager);

        txJudul = findViewById(R.id.namaBerita);
        txTanggal = findViewById(R.id.tanggal);
        imBerita = findViewById(R.id.imBerita1);

        database = FirebaseDatabase.getInstance().getReference();

        database.child("HighLight").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listHighLight = new ListHighLight();
                int i = 0;
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    HighLight highLight = noteDataSnapshot.getValue(HighLight.class);
                    highLight.setKey(noteDataSnapshot.getKey());
                    listHighLight.tambah(highLight);
                    listHighLight.getHighLight(i).setKey(highLight.getKey());
                    i++;
                }
                adapter = new AdaptorHighLight(listHighLight, HighLightActivity.this);
                rvView.setAdapter(adapter);
                getView();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails() + " " + databaseError.getMessage());
            }
        });




    }
    public void getView() {
        txJudul.setText(listHighLight.getHighLight(0).getJudul());
        txJudul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(listHighLight.getHighLight(0).getLink())));
            }
        });
        txTanggal.setText(listHighLight.getHighLight(0).getTanggal());
        txTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(listHighLight.getHighLight(0).getLink())));
            }
        });
        int image = getResources().getIdentifier("@drawable/" + listHighLight.getHighLight(0).getGambar(), null, HighLightActivity.this.getPackageName());
        imBerita.setImageResource(image);
        imBerita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(listHighLight.getHighLight(0).getLink())));
            }
        });
    }
}
