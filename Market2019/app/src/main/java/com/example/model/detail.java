package com.example.model;

public class detail {
    private String TenGH;
    public detail(String TenGH, String Ngay){
        this.TenGH = TenGH;
        this.Ngay = Ngay;
    }
    public String getNgay() {
        return Ngay;
    }

    public void setNgay(String ngay) {
        Ngay = ngay;
    }

    private String Ngay;

    public detail(String tenGH) {
        TenGH = tenGH;
    }

    public String getTenGH() {
        return TenGH;
    }

    public void setTenGH(String tenGH) {
        TenGH = tenGH;
    }



}
