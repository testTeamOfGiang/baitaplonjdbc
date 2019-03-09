package baitaplonjava.model;

public class KhoaHoc {

	private int khoaHoc_Ma;
	private String khoaHoc_Ten;
	private int khoaHoc_Gia;
	private int giangVien_Ma;
	
	public KhoaHoc() {
		super();
	}

	public KhoaHoc(int khoaHoc_Ma, String khoaHoc_Ten, int khoaHoc_Gia, int giangVien_Ma) {
		super();
		this.khoaHoc_Ma = khoaHoc_Ma;
		this.khoaHoc_Ten = khoaHoc_Ten;
		this.khoaHoc_Gia = khoaHoc_Gia;
		this.giangVien_Ma = giangVien_Ma;
	}

	public int getKhoaHoc_Ma() {
		return khoaHoc_Ma;
	}

	public void setKhoaHoc_Ma(int khoaHoc_Ma) {
		this.khoaHoc_Ma = khoaHoc_Ma;
	}

	public String getKhoaHoc_Ten() {
		return khoaHoc_Ten;
	}

	public void setKhoaHoc_Ten(String khoaHoc_Ten) {
		this.khoaHoc_Ten = khoaHoc_Ten;
	}

	public int getKhoaHoc_Gia() {
		return khoaHoc_Gia;
	}

	public void setKhoaHoc_Gia(int khoaHoc_Gia) {
		this.khoaHoc_Gia = khoaHoc_Gia;
	}

	public int getGiangVien_Ma() {
		return giangVien_Ma;
	}

	public void setGiangVien_Ma(int giangVien_Ma) {
		this.giangVien_Ma = giangVien_Ma;
	}
	
	
}
