package com.example.qlns;

public class Student {
private int id;
private String hoten;
private String ngaysinh;
private String diachi;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", hoten='" + hoten + '\'' +
                ", ngaysinh='" + ngaysinh + '\'' +
                ", diachi='" + diachi + '\'' +
                '}';
    }

    public Student() {
    }

    public Student(int id, String hoten, String ngaysinh, String diachi) {
        this.id = id;
        this.hoten = hoten;
        this.ngaysinh = ngaysinh;
        this.diachi = diachi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
}
