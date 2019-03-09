package baitaplonjava.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import baitaplonjava.model.KhoaHoc;

public class KhoaHocMapper {

	public KhoaHoc map(ResultSet result) throws SQLException {
		KhoaHoc kh = new KhoaHoc();
		kh.setKhoaHoc_Ma(result.getInt("khoahoc_ma"));
		kh.setKhoaHoc_Ten(result.getString("khoahoc_ten"));
		kh.setKhoaHoc_Gia(result.getInt("khoahoc_gia"));
		kh.setGiangVien_Ma(result.getInt("giangvien"));
		return kh;
	}
}
