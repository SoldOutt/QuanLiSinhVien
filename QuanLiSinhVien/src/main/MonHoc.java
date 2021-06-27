package main;


public class MonHoc {
	private String maMonHoc,tenMonHoc;
	private float heSo;
	public MonHoc() {
		maMonHoc = tenMonHoc = "khong ro";
		heSo = 0;
	}
	public MonHoc(String _maMonHoc,String _tenMonHoc,String _heSo) {
		maMonHoc = _maMonHoc;
		tenMonHoc = _tenMonHoc;
		heSo = Float.valueOf(_heSo);
	}
	
	public String getMaMonHoc() {
		return maMonHoc;
	}
	public void setMaMonHoc(String maMonHoc) {
		this.maMonHoc = maMonHoc;
	}
	public String getTenMonHoc() {
		return tenMonHoc;
	}
	public void setTenMonHoc(String tenMonHoc) {
		this.tenMonHoc = tenMonHoc;
	}
	public float getHeSo() {
		return heSo;
	}
	public void setHeSo(float heSo) {
		this.heSo = heSo;
	}
	public void xuat() {
		System.out.format("│%-6s│%-28s│%-7.2f│\n",this.maMonHoc,this.tenMonHoc,this.heSo);
	}
	public void suaMH(String _tenMH, String _heso) {
		// TODO Auto-generated method stub
		if(!_tenMH.isEmpty()) {
			this.tenMonHoc = _tenMH;
		}
		if(!_heso.isEmpty()) {
			this.heSo = Float.valueOf(_heso);
		}
	}
	
}
