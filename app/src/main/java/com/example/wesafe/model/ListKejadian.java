package com.example.wesafe.model;

import com.example.wesafe.Create;
import com.example.wesafe.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class ListKejadian implements Serializable {
    private ArrayList<Kejadian> listKejadian;
    private  Kejadian kejadian;
    private DatabaseReference database;
    private boolean sukses;

    public ListKejadian(){
        listKejadian = new ArrayList<>();
        database = FirebaseDatabase.getInstance().getReference();
    }

    public void tambah(Kejadian kejadian){
        listKejadian.add(new Kejadian(kejadian.getId(),kejadian.getNama()));
    }
    public void hapus(int id){
        listKejadian.remove(id);
    }
    public void edit(int id,Kejadian kejadian){
        this.kejadian = listKejadian.get(id);
        this.kejadian.setNama(kejadian.getNama());

    }
    public boolean getSukses(){
        return sukses;
    }
    public void setSukses(boolean a){
        sukses = a;
    }
    public Kejadian getKejadian(int i){
        return listKejadian.get(i);
    }
    public int panjang(){
        return listKejadian.size();
    }
}
