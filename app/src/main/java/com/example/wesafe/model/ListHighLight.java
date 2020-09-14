package com.example.wesafe.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ListHighLight implements Serializable {
    private ArrayList<HighLight> listHighLight;
    private HighLight highLight;

    public ListHighLight(){
        listHighLight = new ArrayList<>();
    }

    public void tambah(HighLight highLight){
        listHighLight.add(new HighLight(highLight.getId(),highLight.getJudul(),highLight.getTanggal(),highLight.getGambar(),highLight.getLink()));
    }
    public void hapus(int id){
        listHighLight.remove(id);
    }
    public void edit(int id,HighLight highLight){
        this.highLight = listHighLight.get(id);
        this.highLight.setJudul(highLight.getJudul());
        this.highLight.setTanggal(highLight.getTanggal());
        this.highLight.setGambar(highLight.getGambar());
        this.highLight.setLink(highLight.getLink());
    }
    public int panjang(){
        return listHighLight.size();
    }
    public HighLight getHighLight(int i){
        return listHighLight.get(i);
    }
}
