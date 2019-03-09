package baitaplonjava.model;

public class HocVien_KhoaHoc {

	private int hocVien_Ma;
	private int khoaHoc_Ma;

	public HocVien_KhoaHoc() {
		super();
	}

	public HocVien_KhoaHoc(int hocVien_Ma, int khoaHoc_Ma) {
		super();
		this.hocVien_Ma = hocVien_Ma;
		this.khoaHoc_Ma = khoaHoc_Ma;
	}

	public int getHocVien_Ma() {
		return hocVien_Ma;
	}

	public void setHocVien_Ma(int hocVien_Ma) {
		this.hocVien_Ma = hocVien_Ma;
	}

	public int getKhoaHoc_Ma() {
		return khoaHoc_Ma;
	}

	public void setKhoaHoc_Ma(int khoaHoc_Ma) {
		this.khoaHoc_Ma = khoaHoc_Ma;
	}

}
