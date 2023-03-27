package com.example.foodapp.Adapters.All_Modals;

public class Categoris_Modal {
    private int c_img;
    private String c_name;

    public Categoris_Modal() {
    }

    public Categoris_Modal(int c_img, String c_name) {
        this.c_img = c_img;
        this.c_name = c_name;
    }

    public int getC_img() {
        return c_img;
    }

    public void setC_img(int c_img) {
        this.c_img = c_img;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }
}
