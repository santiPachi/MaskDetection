package com.example.maskdetection;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
public class Mask implements  Serializable{
    @SerializedName("res1")
    public String res1;
    @SerializedName("res2")
    public String res2;
    @SerializedName("name")
    public String name;

    public Mask(String res1, String res2,String name) {
        this.res1 = res1;
        this.res2 = res2;
        this.name = name;
    }
}
