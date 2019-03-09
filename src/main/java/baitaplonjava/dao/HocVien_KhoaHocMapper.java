package baitaplonjava.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import baitaplonjava.model.HocVien_KhoaHoc;

public class HocVien_KhoaHocMapper {

	public HocVien_KhoaHoc map(ResultSet result) throws SQLException {
		HocVien_KhoaHoc hvkh = new HocVien_KhoaHoc();
		hvkh.setHocVien_Ma(result.getInt("hocvien_ma"));
		hvkh.setKhoaHoc_Ma(result.getInt("khoahoc_ma"));
		return hvkh;
	}
}
