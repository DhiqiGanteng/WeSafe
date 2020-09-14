package com.example.wesafe.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ListGejala implements Serializable {
    private ArrayList<Gejala> listGejala;
    private  Gejala gejala;


    public ListGejala(){
        listGejala = new ArrayList<>();
    }

    public void tambah(Gejala gejala){
        listGejala.add(new Gejala(gejala.getId(),gejala.getNama(),gejala.getNamaKejadian()));
    }
    public void hapus(int id){
        listGejala.remove(id);
    }
    public void edit(int id,Gejala gejala){
        this.gejala = listGejala.get(id);
        this.gejala.setNama(gejala.getNama());
        this.gejala.setNamaKejadian(gejala.getNamaKejadian());
    }
    public int panjang(){
        return listGejala.size();
    }
    public Gejala getGejala(int i){
        return listGejala.get(i);
    }
}
