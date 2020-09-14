package com.example.wesafe.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Penyakit implements Serializable {
    private String key;
    private int id;
    private String id_kejadian;
    private String nama;
    private String deskripsi;
    private ArrayList<String> gejala;
    private String penyebab;
    private String pertolongan;
    private String catatan;
    private String linkYoutube;

    private int maksKomponen;
    private int jmlSama;
    private double kemiripan;

    public Penyakit() {
    }

    public Penyakit(int id,String id_kejadian, String nama,String deskripsi, ArrayList<String> gejala, String penyebab, String pertolongan, String catatan,String linkYoutube) {
        this.id = id;
        this.id_kejadian = id_kejadian;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.gejala = gejala;
        this.penyebab = penyebab;
        this.pertolongan = pertolongan;
        this.catatan = catatan;
        this.linkYoutube = linkYoutube;
    }

    public String getLinkYoutube() {
        return linkYoutube;
    }

    public void setLinkYoutube(String linkYoutube) {
        this.linkYoutube = linkYoutube;
    }

    public void setKemiripan(double kemiripan) {
        this.kemiripan = kemiripan;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_kejadian() {
        return id_kejadian;
    }

    public void setId_kejadian(String id_kejadian) {
        this.id_kejadian = id_kejadian;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public ArrayList<String> getGejala() {
        return gejala;
    }

    public String gejalaToString(){
        String gej ="";
        for (int i =0; i <gejala.size();i++){
            gej = gej + gejala.get(i)+", ";
        }
        return  gej;
    }
    public void setGejala(ArrayList<String> gejala) {
        this.gejala = gejala;
    }

    public String getPenyebab() {
        return penyebab;
    }

    public void setPenyebab(String penyebab) {
        this.penyebab = penyebab;
    }

    public String getPertolongan() {
        return pertolongan;
    }

    public void setPertolongan(String pertolongan) {
        this.pertolongan = pertolongan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {

        this.catatan = catatan;
    }

    public int getMaksKomponen() {
        return maksKomponen;
    }

    public void setMaksKomponen(int maksKomponen) {
        this.maksKomponen = maksKomponen;
    }

    public int getJmlSama() {
        return jmlSama;
    }

    public void setJmlSama(int jmlSama) {
        this.jmlSama = jmlSama;
    }

    public double getKemiripan() {
        return kemiripan;
    }

    public void setKemiripan(int i , int j) {
        this.kemiripan = (double)i/j;
    }

    public String kemiripanToString(){
        return ""+kemiripan;
    }
}
