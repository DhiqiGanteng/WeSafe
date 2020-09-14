package com.example.wesafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class NodarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nodar);
    }


    public void rumahsakit(View view) {
        Intent intent = new Intent(NodarActivity.this, LokasiActivity.class);
        startActivity(intent);
    }

    public void Polisi(View view) {
        Intent intent = new Intent(NodarActivity.this, PolisiActivity.class);
        startActivity(intent);
    }
}
