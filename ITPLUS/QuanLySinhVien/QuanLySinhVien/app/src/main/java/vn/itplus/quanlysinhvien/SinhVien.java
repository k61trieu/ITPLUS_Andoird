package vn.itplus.quanlysinhvien;

public class SinhVien {
    private int id;
    private String hoten;
    private String ngaysinh;
    private String diachi;

    public SinhVien(int id, String hoten, String ngaysinh, String diachi) {
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

    @Override
    public String toString() {
        return "SinhVien{" +
                "id=" + id +
                ", hoten='" + hoten + '\'' +
                ", ngaysinh='" + ngaysinh + '\'' +
                ", diachi='" + diachi + '\'' +
                '}';
    }


}
