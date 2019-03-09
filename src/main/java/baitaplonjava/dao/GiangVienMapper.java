package baitaplonjava.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import baitaplonjava.model.GiangVien;

public class GiangVienMapper {

	public GiangVien map(ResultSet result) throws SQLException {
		GiangVien gv = new GiangVien();
		gv.setGiangVien_Ma(result.getInt("giangvien_ma"));
		gv.setGiangVien_Ten(result.getString("giangvien_ten"));
		gv.setGiangVien_Phone(result.getString("giangvien_sdt"));
		return gv;
	}
}
