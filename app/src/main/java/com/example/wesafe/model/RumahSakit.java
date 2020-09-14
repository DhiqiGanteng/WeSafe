package com.example.wesafe.model;

import java.io.Serializable;

public class RumahSakit implements Serializable {
    private String nama;
    private String alamat;
    private String noTelp;
    private String Key;
    private String gambar;
    private double latitude;
    private double longtitude;
    private int id;
    private int jarak;

    public RumahSakit(){}
    public RumahSakit( int id,String nama, String alamat, String noTelp,String gambar,int jarak,double latitude, double longtitude) {
        this.nama = nama;
        this.alamat = alamat;
        this.noTelp = noTelp;
        this.id = id;
        this.gambar = gambar;
        this.jarak = jarak;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public int getJarak() {
        return jarak;
    }

    public void setJarak(int jarak) {
        this.jarak = jarak;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
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
}
