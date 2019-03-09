package baitaplonjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import baitaplonjava.config.JDBC_Connection;
import baitaplonjava.model.HocVien;
import baitaplonjava.model.KhoaHoc;

public class HocVien_KhoaHocDao {

	private Connection con;
	private HocVienDao hocVienDao;
	private KhoaHocDao khoaHocDao;

	public HocVien_KhoaHocDao() {
		con = JDBC_Connection.getConnection();
		hocVienDao = new HocVienDao();
		khoaHocDao = new KhoaHocDao();
	}

	public void addHocVien_KhoaHoc(HocVien hv, KhoaHoc kh) throws SQLException {
		String sql = "insert into HOCVIEN_KHOAHOC values(?,?)";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, hv.getHocVien_Ma());
		statement.setInt(2, kh.getKhoaHoc_Ma());
		statement.executeUpdate();
	}

	public void deleteHocVien_KhoaHoc(HocVien hv, KhoaHoc kh) throws SQLException {
		String sql = "delete from HOCVIEN_KHOAHOC where hocvien_ma = ? and khoahoc_ma = ?";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, hv.getHocVien_Ma());
		statement.setInt(2, kh.getKhoaHoc_Ma());
		statement.executeUpdate();
	}

	public List<HocVien> getHocVienByKhoaHoc(int idKH) throws SQLException {
		List<HocVien> hocViens = new ArrayList<HocVien>();
		String sql = "select hocvien_ma from HOCVIEN_KHOAHOC where khoahoc_ma = ?";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, idKH);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			HocVien hv = hocVienDao.getHocVien(result.getInt("hocvien_ma"));
			hocViens.add(hv);
		}
		return hocViens;
	}

	public List<KhoaHoc> getKhoaHocByHocVien(int idHV) throws SQLException {
		List<KhoaHoc> khoahocs = new ArrayList<KhoaHoc>();
		String sql = "select khoahoc_ma from HOCVIEN_KHOAHOC where hocvien_ma = ?";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, idHV);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			KhoaHoc kh = khoaHocDao.getKhoaHoc(result.getInt("khoahoc_ma"));
			khoahocs.add(kh);
		}
		return khoahocs;
	}

}
