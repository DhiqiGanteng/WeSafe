package com.example.wesafe.model;

import com.google.firebase.database.IgnoreExtraProperties;
import java.io.Serializable;

public class Kejadian implements Serializable {
    private String key;
    private int id;
    private String nama;

    public Kejadian(){}
    public Kejadian(int id,String nama){
        this.id =id;
        this.nama = nama;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
