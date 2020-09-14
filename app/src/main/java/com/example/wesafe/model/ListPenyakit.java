package com.example.wesafe.model;

import java.io.Serializable;
import java.util.ArrayList;

public class ListPenyakit implements Serializable {

    private ArrayList<Penyakit> listPenyakit;
    private Penyakit penyakit;

    public ListPenyakit(){
        listPenyakit = new ArrayList<>();
    }

    public void tambah(Penyakit penyakit){
        listPenyakit.add(new Penyakit(penyakit.getId(),penyakit.getId_kejadian(),penyakit.getNama(),penyakit.getDeskripsi(),penyakit.getGejala(),penyakit.getPenyebab(),penyakit.getPertolongan(),penyakit.getCatatan(),penyakit.getLinkYoutube()));
    }
    public void hapus(int id){
        listPenyakit.remove(id);

    }
    public void edit(int id,Penyakit penyakit){
        this.penyakit = listPenyakit.get(id);
        this.penyakit.setId_kejadian(penyakit.getId_kejadian());
        this.penyakit.setNama(penyakit.getNama());
        this.penyakit.setDeskripsi(penyakit.getDeskripsi());
        this.penyakit.setGejala((penyakit.getGejala()));
        this.penyakit.setPenyebab(penyakit.getPenyebab());
        this.penyakit.setPertolongan(penyakit.getPertolongan());
        this.penyakit.setCatatan(penyakit.getCatatan());
        this.penyakit.setLinkYoutube(penyakit.getLinkYoutube());

    }
    public Penyakit getPenyakit(int aa){
        return listPenyakit.get(aa);
    }
    public int panjang(){
        return listPenyakit.size();
    }
}
