package com.example.wesafe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wesafe.model.Gejala;
import com.example.wesafe.model.Kejadian;
import com.example.wesafe.model.ListGejala;
import com.example.wesafe.model.ListKejadian;
import com.example.wesafe.model.ListPenyakit;
import com.example.wesafe.model.Penyakit;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Create extends AppCompatActivity {
    private DatabaseReference database;

    private Penyakit penyakit;
    private ListKejadian listKejadian;
    private ListGejala listGejala;
    private ListPenyakit listPenyakit;
    private Kejadian kejadian;
    private Gejala gejala;


    private Button btKejaidan;
    private Button btPenyakit;
    private Button btGejala;

    private EditText etNamaKejadian;
    private EditText etNamaPenyakit;
    private EditText etPenyebab;
    private EditText etPertolongan;
    private EditText etCatatan;
    private EditText etNamaGejala;
    private EditText etDeskripsi;
    private EditText etLink;

    private Spinner spGejala1;
    private Spinner spGejala2;
    private Spinner spGejala3;
    private Spinner spGejala4;
    private Spinner spGejala5;
    private Spinner spGejala6;
    private Spinner spGejala7;
    private  Spinner spIdKejadian;
    private  Spinner spIdKejadian1;

    @Override
    protected void onStart() {
        super.onStart();
        loadGejala();
        loadPenyakit();
        loadKejadian();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);


        etNamaKejadian = (EditText) findViewById(R.id.ETnama_kejadian);
        etNamaPenyakit = (EditText) findViewById(R.id.ETnama_penyakit);
        etPenyebab= (EditText) findViewById(R.id.ETpenyebab);
        etPertolongan =(EditText) findViewById(R.id.ETpertolongan);
        etCatatan = (EditText) findViewById(R.id.ETcatatan);
        etNamaGejala = (EditText) findViewById(R.id.ETgejala);
        etDeskripsi = (EditText) findViewById(R.id.ETdeskripsi);
        etLink = (EditText) findViewById(R.id.ETlinkYoutube);

        spGejala1 = (Spinner) findViewById(R.id.SPgejala1);
        spGejala2 = (Spinner) findViewById(R.id.SPgejala2);
        spGejala3 = (Spinner) findViewById(R.id.SPgejala3);
        spGejala4 = (Spinner) findViewById(R.id.SPgejala4);
        spGejala5 = (Spinner) findViewById(R.id.SPgejala5);
        spGejala6 = (Spinner) findViewById(R.id.SPgejala6);
        spGejala7 = (Spinner) findViewById(R.id.SPgejala7);
        spIdKejadian = (Spinner) findViewById(R.id.SPkejadian);
        spIdKejadian1 = (Spinner) findViewById(R.id.SPkejadian2);

        btKejaidan = (Button) findViewById(R.id.create_kejadian);
        btGejala = (Button) findViewById(R.id.create_gejala);
        btPenyakit = (Button) findViewById(R.id.create_Penyakit);

        database = FirebaseDatabase.getInstance().getReference();
        penyakit = (Penyakit) getIntent().getSerializableExtra("data");

        loadKejadian();
        loadGejala();
        loadPenyakit();
        setEdit();




    }
    public void setEdit() {
        if (penyakit != null) {
            etNamaPenyakit.setText(penyakit.getNama());
            etPenyebab.setText(penyakit.getPenyebab());
            etDeskripsi.setText(penyakit.getDeskripsi());
            etPertolongan.setText(penyakit.getPertolongan());
            etCatatan.setText(penyakit.getCatatan());
            etLink.setText(penyakit.getLinkYoutube());
        }
    }
    public void setEditKejadian() {
        if (penyakit != null) {
            ArrayAdapter adapter = (ArrayAdapter) spIdKejadian.getAdapter();
            spIdKejadian.setSelection(adapter.getPosition(penyakit.getId_kejadian()));

        }
    }
    public void setEditGejala() {
        if (penyakit != null) {
            ArrayAdapter adapter1 = (ArrayAdapter) spGejala1.getAdapter();
            for (int i = 0;i<penyakit.getGejala().size();i++) {
                if (i == 0){
                    spGejala1.setSelection(adapter1.getPosition(penyakit.getGejala().get(0)));
                }else if(i==1){
                    spGejala2.setSelection(adapter1.getPosition(penyakit.getGejala().get(1)));
                }else if(i==2){
                    spGejala3.setSelection(adapter1.getPosition(penyakit.getGejala().get(2)));
                }else if(i==3){
                    spGejala4.setSelection(adapter1.getPosition(penyakit.getGejala().get(3)));
                }else if(i==4){
                    spGejala5.setSelection(adapter1.getPosition(penyakit.getGejala().get(4)));
                }else if(i==5){
                    spGejala6.setSelection(adapter1.getPosition(penyakit.getGejala().get(5)));
                }else if(i==6){
                    spGejala7.setSelection(adapter1.getPosition(penyakit.getGejala().get(6)));
                }
            }
        }
    }

    public void onCreateKejadian(View view) {
        if(!isEmpty(etNamaKejadian.getText().toString())){
            int id;
            if (listKejadian==null){
                id = 1;
            }else {
                id = listKejadian.getKejadian(listKejadian.panjang() - 1).getId() + 1;
            }
            kejadian = new Kejadian(id,etNamaKejadian.getText().toString());
            listKejadian.tambah(kejadian);
            database.child("KEJADIAN").push().setValue(kejadian).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    etNamaKejadian.setText("");
                    Snackbar.make(findViewById(R.id.create_kejadian), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
                }
            });
        } else {
            Snackbar.make(findViewById(R.id.create_kejadian), "Data Kejadian tidak boleh kosong", Snackbar.LENGTH_LONG).show();
        }
        InputMethodManager imm = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(

                etNamaKejadian.getWindowToken(), 0);
    }

    public void onCreatePenyakit(View view) {
        if (penyakit != null){
            if(!isEmpty(etNamaPenyakit.getText().toString()) && !isEmpty(etPenyebab.getText().toString()) && !isEmpty(etPertolongan.getText().toString()) && !spIdKejadian.getSelectedItem().toString().equals(" ") && !isEmpty(etDeskripsi.getText().toString()) && !isEmpty(etLink.getText().toString()) && !spGejala1.getSelectedItem().toString().equals(" ")){
                ArrayList<String> listGejala1 = new ArrayList<>();
                if (!spGejala1.getSelectedItem().toString().equals("")) {
                    listGejala1.add(spGejala1.getSelectedItem().toString());
                }
                if (!spGejala2.getSelectedItem().toString().equals("")) {
                    listGejala1.add(spGejala2.getSelectedItem().toString());
                }
                if (!spGejala3.getSelectedItem().toString().equals("")) {
                    listGejala1.add(spGejala3.getSelectedItem().toString());
                }
                if (!spGejala4.getSelectedItem().toString().equals("")) {
                    listGejala1.add(spGejala4.getSelectedItem().toString());
                }
                if (!spGejala5.getSelectedItem().toString().equals("")) {
                    listGejala1.add(spGejala5.getSelectedItem().toString());
                }
                if (!spGejala6.getSelectedItem().toString().equals("")) {
                    listGejala1.add(spGejala6.getSelectedItem().toString());
                }
                if (!spGejala7.getSelectedItem().toString().equals("")) {
                    listGejala1.add(spGejala7.getSelectedItem().toString());
                }
                if (etCatatan.getText() == null) {
                    penyakit.setCatatan("");
                }else{
                    penyakit.setCatatan(etCatatan.getText().toString());
                }
                if (etLink.getText() == null) {
                    penyakit.setLinkYoutube("");
                }else{
                    penyakit.setLinkYoutube(etLink.getText().toString());
                }

                int id = penyakit.getId();
                penyakit.setNama(etNamaPenyakit.getText().toString());
                penyakit.setId_kejadian(spIdKejadian.getSelectedItem().toString());
                penyakit.setDeskripsi(etDeskripsi.getText().toString());
                penyakit.setGejala(listGejala1);
                penyakit.setPenyebab(etPenyebab.getText().toString());
                penyakit.setPertolongan(etPertolongan.getText().toString());
                int index=0;
                for (int i=0;i<listPenyakit.panjang();i++){
                    if (listPenyakit.getPenyakit(i).getId()==id){
                        index = i;
                    }
                }
                listPenyakit.edit(index,penyakit);

                database.child("PENYAKIT").child(penyakit.getKey()).setValue(penyakit).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        etNamaPenyakit.setText("");
                        etDeskripsi.setText("");
                        etPenyebab.setText("");
                        etPertolongan.setText("");
                        etCatatan.setText("");
                        etLink.setText("");
                        ArrayAdapter adapter1 = (ArrayAdapter) spGejala1.getAdapter();
                        spGejala1.setSelection(adapter1.getPosition(""));
                        spGejala2.setSelection(adapter1.getPosition(""));
                        spGejala3.setSelection(adapter1.getPosition(""));
                        spGejala4.setSelection(adapter1.getPosition(""));
                        spGejala5.setSelection(adapter1.getPosition(""));
                        spGejala6.setSelection(adapter1.getPosition(""));
                        spGejala7.setSelection(adapter1.getPosition(""));
                        ArrayAdapter adapter = (ArrayAdapter) spIdKejadian.getAdapter();
                        spIdKejadian.setSelection(adapter.getPosition(" "));
                        Snackbar.make(findViewById(R.id.create_kejadian), "Data berhasil diedit", Snackbar.LENGTH_LONG).show();
                    }
                });


            } else {
                Snackbar.make(findViewById(R.id.create_Penyakit), "Data Penyakit tidak boleh kosong", Snackbar.LENGTH_LONG).show();
            }
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);

        }else {
            if (!isEmpty(etNamaPenyakit.getText().toString()) && !isEmpty(etPenyebab.getText().toString()) && !isEmpty(etPertolongan.getText().toString()) && !spIdKejadian.getSelectedItem().toString().equals(" ") && !isEmpty(etDeskripsi.getText().toString()) && !isEmpty(etLink.getText().toString()) && !spGejala1.getSelectedItem().toString().equals(" ")) {
                ArrayList<String> listGejala1 = new ArrayList<>();
                String link;
                String catatan;
                if (!spGejala1.getSelectedItem().toString().equals("")) {
                    listGejala1.add(spGejala1.getSelectedItem().toString());
                }
                if (!spGejala2.getSelectedItem().toString().equals("")) {
                    listGejala1.add(spGejala2.getSelectedItem().toString());
                }
                if (!spGejala3.getSelectedItem().toString().equals("")) {
                    listGejala1.add(spGejala3.getSelectedItem().toString());
                }
                if (!spGejala4.getSelectedItem().toString().equals("")) {
                    listGejala1.add(spGejala4.getSelectedItem().toString());
                }
                if (!spGejala5.getSelectedItem().toString().equals("")) {
                    listGejala1.add(spGejala5.getSelectedItem().toString());
                }
                if (!spGejala6.getSelectedItem().toString().equals("")) {
                    listGejala1.add(spGejala6.getSelectedItem().toString());
                }
                if (!spGejala7.getSelectedItem().toString().equals("")) {
                    listGejala1.add(spGejala7.getSelectedItem().toString());
                }
                if (etCatatan.getText() == null) {
                    catatan = "";
                }else{
                    catatan = etCatatan.getText().toString();
                }
                if (etLink.getText() == null) {
                    link ="";
                }else{
                    link = etLink.getText().toString();
                }
                int id;
                    id = listPenyakit.getPenyakit(listPenyakit.panjang() - 1).getId() + 1;
                penyakit = new Penyakit(id, spIdKejadian.getSelectedItem().toString(), etNamaPenyakit.getText().toString(), etDeskripsi.getText().toString(), listGejala1, etPenyebab.getText().toString(), etPertolongan.getText().toString(), catatan,link);
                listPenyakit.tambah(penyakit);

                database.child("PENYAKIT").push().setValue(penyakit).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        etNamaPenyakit.setText("");
                        etDeskripsi.setText("");
                        etPenyebab.setText("");
                        etPertolongan.setText("");
                        etCatatan.setText("");
                        etLink.setText("");
                        ArrayAdapter adapter1 = (ArrayAdapter) spGejala1.getAdapter();
                        spGejala1.setSelection(adapter1.getPosition(""));
                        spGejala2.setSelection(adapter1.getPosition(""));
                        spGejala3.setSelection(adapter1.getPosition(""));
                        spGejala4.setSelection(adapter1.getPosition(""));
                        spGejala5.setSelection(adapter1.getPosition(""));
                        spGejala6.setSelection(adapter1.getPosition(""));
                        spGejala7.setSelection(adapter1.getPosition(""));
                        ArrayAdapter adapter = (ArrayAdapter) spIdKejadian.getAdapter();
                        spIdKejadian.setSelection(adapter.getPosition(" "));
                        penyakit = null;
                        Snackbar.make(findViewById(R.id.create_kejadian), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
                    }
                });


            } else {
                Snackbar.make(findViewById(R.id.create_Penyakit), "Data Penyakit tidak boleh kosong", Snackbar.LENGTH_LONG).show();
            }
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
        }

    }

    public void onCreateGejala(View view) {
        if(!isEmpty(etNamaGejala.getText().toString()) && !spIdKejadian1.getSelectedItem().toString().equals(" ")){
            int id;
            if (listGejala==null){
                id = 1;
            }else {
                id = listGejala.getGejala(listGejala.panjang() - 1).getId() + 1;
            }
            gejala = new Gejala(id,etNamaGejala.getText().toString(),spIdKejadian1.getSelectedItem().toString());
            listGejala.tambah(gejala);
            database.child("GEJALA").push().setValue(gejala).addOnSuccessListener(this, new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    etNamaGejala.setText("");
                    ArrayAdapter adapter = (ArrayAdapter) spIdKejadian.getAdapter();
                    spIdKejadian.setSelection(adapter.getPosition(" "));
                    Snackbar.make(findViewById(R.id.create_kejadian), "Data berhasil ditambahkan", Snackbar.LENGTH_LONG).show();
                }
            });
        } else {
            Snackbar.make(findViewById(R.id.create_gejala), "Data gejala tidak boleh kosong", Snackbar.LENGTH_LONG).show();
        }
        InputMethodManager imm = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    private boolean isEmpty(String s) {

// Cek apakah ada fields yang kosong, sebelum disubmit

        return TextUtils.isEmpty(s);

    }
    public static Intent getActIntent(Activity activity) {

// kode untuk pengambilan Intent

        return new Intent(activity, Create.class);

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
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }
    public void loadKejadian(){
        database.child("KEJADIAN").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listKejadian = new ListKejadian();
                int i = 0 ;
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Kejadian kejadian = noteDataSnapshot.getValue(Kejadian.class);
                    kejadian.setKey(noteDataSnapshot.getKey());
                    listKejadian.tambah(kejadian);
                    listKejadian.getKejadian(i).setKey(kejadian.getKey());
                    i++;
                }
                setSpinnerKejadian();


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }
    public void loadGejala(){

        database.child("GEJALA").addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(DataSnapshot dataSnapshot) {
                listGejala = new ListGejala();
                int i = 0;
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Gejala gejala = noteDataSnapshot.getValue(Gejala.class);
                    gejala.setKey(noteDataSnapshot.getKey());
                    listGejala.tambah(gejala);
                    listGejala.getGejala(i).setKey(gejala.getKey());
                    i++;
                }
                setSpinnerGejala();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println(databaseError.getDetails()+" "+databaseError.getMessage());
            }
        });
    }

    public void setSpinnerKejadian(){
        String[] dataKejadian;
        dataKejadian = new String[listKejadian.panjang() + 1];
        dataKejadian[0]=" ";
        for (int i = 1; i <= listKejadian.panjang(); i++) {
                dataKejadian[i] = listKejadian.getKejadian(i-1).getNama();
        }

        final ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, dataKejadian);

        spIdKejadian.setAdapter(adapter2);
        spIdKejadian1.setAdapter(adapter2);
        setEditKejadian();
    }

    public void setSpinnerGejala(){
        String[] dataGejala;
            dataGejala = new String[listGejala.panjang() + 1];
            dataGejala[0] = "";
            for (int i = 1; i <= listGejala.panjang(); i++) {
                dataGejala[i] = listGejala.getGejala(i - 1).getNama();
            }
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, dataGejala);

        spGejala1.setAdapter(adapter);
        spGejala2.setAdapter(adapter);
        spGejala3.setAdapter(adapter);
        spGejala4.setAdapter(adapter);
        spGejala5.setAdapter(adapter);
        spGejala6.setAdapter(adapter);
        spGejala7.setAdapter(adapter);
        setEditGejala();
    }


}
