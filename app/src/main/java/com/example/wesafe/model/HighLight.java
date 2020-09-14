package com.example.wesafe.model;

import java.io.Serializable;

public class HighLight implements Serializable {
    private String Key;
    private int id;
    private String judul;
    private String tanggal;
    private String gambar;
    private String link;

    public HighLight(){

    }
    public HighLight(int id, String judul, String tanggal, String gambar,String link) {
        this.id = id;
        this.judul = judul;
        this.tanggal = tanggal;
        this.gambar = gambar;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
