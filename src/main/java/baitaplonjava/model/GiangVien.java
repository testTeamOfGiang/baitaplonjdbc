package baitaplonjava.model;

public class GiangVien {

	private int giangVien_Ma;
	private String giangVien_Ten;
	private String giangVien_Phone;
	
	public GiangVien() {
		super();
	}
	public GiangVien(int giangVien_Ma, String giangVien_Ten, String giangVien_Phone) {
		super();
		this.giangVien_Ma = giangVien_Ma;
		this.giangVien_Ten = giangVien_Ten;
		this.giangVien_Phone = giangVien_Phone;
	}
	public int getGiangVien_Ma() {
		return giangVien_Ma;
	}
	public void setGiangVien_Ma(int giangVien_Ma) {
		this.giangVien_Ma = giangVien_Ma;
	}
	public String getGiangVien_Ten() {
		return giangVien_Ten;
	}
	public void setGiangVien_Ten(String giangVien_Ten) {
		this.giangVien_Ten = giangVien_Ten;
	}
	public String getGiangVien_Phone() {
		return giangVien_Phone;
	}
	public void setGiangVien_Phone(String giangVien_Phone) {
		this.giangVien_Phone = giangVien_Phone;
	}
	
	
}
