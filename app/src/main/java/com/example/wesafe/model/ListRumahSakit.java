package com.example.wesafe.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ListRumahSakit implements Serializable {
    private RumahSakit rumahSakit;
    private ArrayList<RumahSakit> listRumahSakit;

    public ListRumahSakit(){
        listRumahSakit = new ArrayList<>();
    }

    public void tambah(RumahSakit rumahSakit){
        listRumahSakit.add(new RumahSakit(rumahSakit.getId(),rumahSakit.getNama(),rumahSakit.getAlamat(),rumahSakit.getNoTelp(),rumahSakit.getGambar(),rumahSakit.getJarak(),rumahSakit.getLatitude(),rumahSakit.getLongtitude()));
    }
    public void hapus(int id){
        listRumahSakit.remove(id);
    }
    public void Edit(int id,RumahSakit rumahSakit){
        listRumahSakit.set(id,rumahSakit);
    }
    public int panjang(){
        return listRumahSakit.size();
    }
    public RumahSakit getRumahSakit(int i){
        return listRumahSakit.get(i);
    }
    public int getIndex(RumahSakit rumahSakit){
        return listRumahSakit.indexOf(rumahSakit);
    }
}
