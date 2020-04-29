package com.example.dithi.model;

import com.google.gson.annotations.SerializedName;

public class Book {
    @SerializedName("_id")
    String id;
    @SerializedName("tenSach")
    String name;
    @SerializedName("theLoai")
    String type;
    @SerializedName("tacGia")
    String author;
    @SerializedName("moTa")
    String des;

    public Book(String id, String name, String type, String author, String des) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.author = author;
        this.des = des;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
