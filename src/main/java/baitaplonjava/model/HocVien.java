package baitaplonjava.model;

public class HocVien {

	private int hocVien_Ma;
	private String hocVien_Ten;
	private int hocVien_Tuoi;
	private String hocVien_Phone;
	
	public HocVien(int hocVien_Ma, String hocVien_Ten, int hocVien_Tuoi, String hocVien_Phone) {
		super();
		this.hocVien_Ma = hocVien_Ma;
		this.hocVien_Ten = hocVien_Ten;
		this.hocVien_Tuoi = hocVien_Tuoi;
		this.hocVien_Phone = hocVien_Phone;
	}
	public HocVien() {
		super();
	}
	public int getHocVien_Ma() {
		return hocVien_Ma;
	}
	public void setHocVien_Ma(int hocVien_Ma) {
		this.hocVien_Ma = hocVien_Ma;
	}
	public String getHocVien_Ten() {
		return hocVien_Ten;
	}
	public void setHocVien_Ten(String hocVien_Ten) {
		this.hocVien_Ten = hocVien_Ten;
	}
	public int getHocVien_Tuoi() {
		return hocVien_Tuoi;
	}
	public void setHocVien_Tuoi(int hocVien_Tuoi) {
		this.hocVien_Tuoi = hocVien_Tuoi;
	}
	public String getHocVien_Phone() {
		return hocVien_Phone;
	}
	public void setHocVien_Phone(String hocVien_Phone) {
		this.hocVien_Phone = hocVien_Phone;
	}
	
	
	
}
