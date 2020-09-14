package com.example.wesafe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity {
    private Button btCreate;
    private Button btRead;
    private TextView btLogout;
    private FirebaseAuth auth;
    private String email;
    private String password;
    private DrawerLayout dl1;
    private ActionBarDrawerToggle t1;
    private NavigationView nv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

//        btCreate = findViewById(R.id.btnCreate);
//        btRead = findViewById(R.id.btnRead);
//        btLogout = findViewById(R.id.btLogout);

        auth = FirebaseAuth.getInstance();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        dl1 = (DrawerLayout)findViewById(R.id.activity_admin);
        t1 = new ActionBarDrawerToggle(this, dl1,R.string.Open, R.string.Close);

        dl1.addDrawerListener(t1);
        t1.syncState();

        nv1 = (NavigationView)findViewById(R.id.nv1);
        nv1.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Activity selectedActivity = null;
                switch(id)
                {
                    case R.id.account1:
                        login1();
                        break;
                    case R.id.settings1:
                        about1();
                        break;
                    case R.id.mycart1:
                        help1();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });


    }

    public void login1(){
        Intent intent = new Intent(AdminActivity.this, MainActivity.class);
        startActivity(intent);
    }
    public void about1(){
        Intent intent = new Intent(AdminActivity.this, AboutActivity.class);
        startActivity(intent);
    }
    public void help1(){
        Intent intent = new Intent(AdminActivity.this, HelpActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t1.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    public void baca(View view){
        Intent intent = new Intent(AdminActivity.this, pilihKejadian.class);
        startActivity(intent);
    }

    public void tulis(View view){
        Intent intent = new Intent(AdminActivity.this, Create.class);
        startActivity(intent);
    }
}
