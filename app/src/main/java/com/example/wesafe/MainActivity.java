package com.example.wesafe;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.wesafe.model.HighLight;
import com.example.wesafe.model.ListPolisi;
import com.example.wesafe.model.ListRumahSakit;
import com.example.wesafe.model.Polisi;
import com.example.wesafe.model.RumahSakit;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    private TextView btLogin;
    private TextView btRegister;
    private Button btData;
    private Button btFiturUtama;
    private Button btLokasi;
    private Button btHighLight;
    private FirebaseAuth auth;
    private DatabaseReference database;
    private HighLight highLight;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private FusedLocationProviderClient mFusedLocation;
    private Location locMe;
    private ListRumahSakit listRumahSakit;
    private ListPolisi listPolisi;


    @Override
    protected void onStart() {
        super.onStart();
        if (auth.getInstance().getCurrentUser()!=null) {
            auth = FirebaseAuth.getInstance();
            auth.signOut();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance().getReference();
        locMe = new Location("one");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getMyLocation();
        loadRumahSakit();
        loadPolisi();

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "Membutuhkan Izin Lokasi", Toast.LENGTH_SHORT).show();
            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        1);
            }
        } else {
            // Permission has already been granted
            Toast.makeText(this, "Izin Lokasi diberikan", Toast.LENGTH_SHORT).show();
        }

        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Activity selectedActivity = null;
                switch(id)
                {
                    case R.id.account:
                        login();
                        break;
                    case R.id.settings:
                        about();
                        break;
                    case R.id.mycart:
                        help();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
    }

    public void login(){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }
    public void about(){
        Intent intent = new Intent(MainActivity.this, AboutActivity.class);
        startActivity(intent);
    }
    public void help(){
        Intent intent = new Intent(MainActivity.this, HelpActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    public void pertolonganpertama(View view){
        Intent intent = new Intent(MainActivity.this, KejadianFUActivity.class);
        startActivity(intent);
    }

    public void modul(View view){
        Intent intent = new Intent(MainActivity.this, pilihKejadian.class);
        startActivity(intent);
    }

    public void berita(View view){
        Intent intent = new Intent(MainActivity.this, HighLightActivity.class);
        startActivity(intent);
    }

    public void nodar(View view){
        Intent intent = new Intent(MainActivity.this, NodarActivity.class);
        startActivity(intent);
    }

    public void getMyLocation() {
        mFusedLocation = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocation.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    locMe.setLatitude(location.getLatitude());
                    locMe.setLongitude(location.getLongitude());
                    Log.d("My Current location", "Lat : " + location.getLatitude() + " Long : " + location.getLongitude());
                    // Display in Toast
                    Toast.makeText(MainActivity.this,
                            "Lat : " + location.getLatitude() + " Long : " + location.getLongitude(),
                            Toast.LENGTH_LONG).show();
                } else {
                    locMe.setLatitude(location.getLatitude());
                    locMe.setLongitude(location.getLongitude());
                }
            }
        });
    }
    public void loadRumahSakit(){
        database.child("RumahSakit").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listRumahSakit = new ListRumahSakit();
                int i = 0;
                float[] distance = new float[1];
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    RumahSakit rumahSakit = noteDataSnapshot.getValue(RumahSakit.class);
                    rumahSakit.setKey(noteDataSnapshot.getKey());
                    listRumahSakit.tambah(rumahSakit);
                    Location.distanceBetween(locMe.getLatitude(),locMe.getLongitude(),rumahSakit.getLatitude(),rumahSakit.getLongtitude(),distance);
                    listRumahSakit.getRumahSakit(i).setJarak(Math.round(distance[0]));

                    database.child("RumahSakit").child(rumahSakit.getKey()).setValue(listRumahSakit.getRumahSakit(i));
                    i++;

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }

    public void loadPolisi() {
        database.child("POLISI").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listPolisi = new ListPolisi();
                int i = 0;
                float[] distance = new float[1];
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Polisi polisi = noteDataSnapshot.getValue(Polisi.class);
                    polisi.setKey(noteDataSnapshot.getKey());
                    listPolisi.tambah(polisi);
                    Location.distanceBetween(locMe.getLatitude(), locMe.getLongitude(), polisi.getLatitude(), polisi.getLongtitude(), distance);
                    listPolisi.getPolisi(i).setJarak(Math.round(distance[0]));
                    database.child("POLISI").child(polisi.getKey()).setValue(listPolisi.getPolisi(i));
                    i++;

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails() + " " + databaseError.getMessage());
            }
        });
    }
}
