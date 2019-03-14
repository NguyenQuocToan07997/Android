package com.example.nguyenquoctoan_1706020089;

public class Courselist {
    private String TenMH;
    private String MaMH ;
    private int SoTc;

    public String getTenMH() {
        return TenMH;
    }

    public void setTenMH(String tenMH) {
        TenMH = tenMH;
    }

    public String getMaMH() {
        return MaMH;
    }

    public void setMaMH(String maMH) {
        MaMH = maMH;
    }

    public int getSoTc() {
        return SoTc;
    }

    public void setSoTc(int soTc) {
        SoTc = soTc;
    }


    public Courselist(String TenMH, String MaMH, int SoTC) {
        this.TenMH = TenMH;
        this.MaMH = MaMH;
        this.SoTc = SoTC;

    }
}
