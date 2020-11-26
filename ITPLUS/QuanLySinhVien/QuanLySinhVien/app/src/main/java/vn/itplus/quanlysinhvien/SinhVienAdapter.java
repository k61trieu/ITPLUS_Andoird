package vn.itplus.quanlysinhvien;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {
    private int layout;
    private Context context;
    private List<SinhVien> listSinhVien;

    public SinhVienAdapter(int layout, Context context, List<SinhVien> listSinhVien) {
        this.layout = layout;
        this.context = context;
        this.listSinhVien = listSinhVien;
    }

    @Override
    public int getCount() {
        return listSinhVien.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView txtHoten;
        TextView txtSua;
        TextView txtXoa;
        TextView txtNgaySinh;
        TextView txtDiaChi;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            viewHolder.txtDiaChi = convertView.findViewById(R.id.txtDiaChi);
            viewHolder.txtHoten = convertView.findViewById(R.id.txtHoTen);
            viewHolder.txtNgaySinh = convertView.findViewById(R.id.txtNgaySinh);
            viewHolder.txtSua = convertView.findViewById(R.id.btnSua);
            viewHolder.txtXoa = convertView.findViewById(R.id.btnXoa);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SinhVien sinhVien = listSinhVien.get(position);
        viewHolder.txtHoten.setText(sinhVien.getHoten());
        viewHolder.txtNgaySinh.setText(sinhVien.getNgaysinh());
        viewHolder.txtDiaChi.setText(sinhVien.getDiachi());
        return convertView;
    }
}
