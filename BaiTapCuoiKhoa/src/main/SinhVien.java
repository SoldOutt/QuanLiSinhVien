package main;


import java.text.*;
import java.util.*;

public class SinhVien {
	private String maSV,hoDem,ten,gioiTinh;
	private Date ngaySinh;
	public SinhVien() {
		maSV = hoDem = ten = gioiTinh = "khong ro";
		ngaySinh = new Date();
	}
	public SinhVien(String _maSV , String _hoDem,String _ten, String _ngaySinh,String _gioiTinh) throws Exception {
		maSV = _maSV;
		hoDem = _hoDem;
		ten = _ten;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			ngaySinh = sdf.parse(_ngaySinh);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			throw new Exception("Chuoi ngay sinh khong hop le");
		}
		gioiTinh = _gioiTinh;
	}
	
	public String getMaSV() {
		return maSV;
	}
	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}
	public String getHoDem() {
		return hoDem;
	}
	public void setHoDem(String hoDem) {
		this.hoDem = hoDem;
	}
	public String getTen() {
		return ten;
	}
	public void setTen(String ten) {
		this.ten = ten;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getHoTen() {
		return this.hoDem+" "+this.ten;
	}
	@SuppressWarnings("deprecation")
	public String getNgaySinhString() {
		return this.ngaySinh.getDate()+"/"+this.ngaySinh.getMonth()+"/"+(this.ngaySinh.getYear()+1900);
	}
	@SuppressWarnings("deprecation")
	public void xuat() {
		//System.out.println(maSV + " "  + hoDem + " " + ten + " "+ ngaySinh.getDate()+"/"+(ngaySinh.getMonth()+1)+"/"+(ngaySinh.getYear()+1900)+ " " + gioiTinh);
		System.out.format("│%-9s│%-23s│%-11s│%02d/%02d/%02d  │%-9s│\n",maSV,hoDem,ten,ngaySinh.getDate(),ngaySinh.getMonth()+1,ngaySinh.getYear()+1900,gioiTinh);
	}
	public void suaSV(String _hoDem,String _ten, String _ngaySinh,String _gioiTinh) throws Exception {
		if(!_hoDem.isEmpty()) {
			this.hoDem = _hoDem;
		}
		if(!_ten.isEmpty()) {
			this.ten = _ten;
		}
		if(!_ngaySinh.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			try {
				this.ngaySinh = sdf.parse(_ngaySinh);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
				throw new Exception("");
			}
		}
		if(!_gioiTinh.isEmpty()) {
			this.gioiTinh=_gioiTinh;
		}
	}
}
