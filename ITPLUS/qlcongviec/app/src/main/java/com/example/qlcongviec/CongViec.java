package com.example.qlcongviec;

public class CongViec {
    private String tieuDe;
    private String noiDung;

    public CongViec() {
    }

    public CongViec(String tieuDe, String noiDung) {
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    @Override
    public String toString() {
        return "CongViec{" +
                "tieuDe='" + tieuDe + '\'' +
                ", noiDung='" + noiDung + '\'' +
                '}';
    }
}
