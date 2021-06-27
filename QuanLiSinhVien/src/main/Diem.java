package main;



public class Diem {
	private String maSV,maMonHoc;
	private float diem;
	public Diem() {
		maSV = maMonHoc = "khong ro";
		diem =0;
	}
	public Diem(String _maSV , String _maMonHoc,String _diem) {
		maSV = _maSV ; 
		maMonHoc = _maMonHoc;
		diem = Float.valueOf(_diem);
	}
	
	public String getMaSV() {
		return maSV;
	}
	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}
	public String getMaMonHoc() {
		return maMonHoc;
	}
	public void setMaMonHoc(String maMonHoc) {
		this.maMonHoc = maMonHoc;
	}
	public float getDiem() {
		return diem;
	}
	public void setDiem(float diem) {
		this.diem = diem;
	}
	public void xuat() {
		System.out.println(maSV + " " + maMonHoc + " " + diem);
	}
	public void suaDiem(String string) {
		// TODO Auto-generated method stub
		this.diem = Float.valueOf(string);
	}
}
