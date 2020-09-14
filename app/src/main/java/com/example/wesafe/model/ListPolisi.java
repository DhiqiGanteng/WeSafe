package com.example.wesafe.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ListPolisi implements Serializable {
    private Polisi polisi;
    private ArrayList<Polisi> listPolisi;

    public ListPolisi(){
        listPolisi = new ArrayList<>();
    }

    public void tambah(Polisi polisi){
        listPolisi.add(new Polisi(polisi.getId(),polisi.getNama(),polisi.getAlamat(),polisi.getNoTelp(),polisi.getLatitude(),polisi.getLongtitude(),polisi.getJarak()));
    }
    public int panjang(){
        return listPolisi.size();
    }
    public Polisi getPolisi(int i){
        return listPolisi.get(i);
    }
}
