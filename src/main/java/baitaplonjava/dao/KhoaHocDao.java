package baitaplonjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import baitaplonjava.config.JDBC_Connection;
import baitaplonjava.model.KhoaHoc;

public class KhoaHocDao {

	private Connection con;
	private KhoaHocMapper mapper;

	public KhoaHocDao() {
		con = JDBC_Connection.getConnection();
		mapper = new KhoaHocMapper();
	}

	public void addKhoaHoc(KhoaHoc kh) throws SQLException {
		String sql = "insert into KHOAHOC(khoahoc_ten,khoahoc_gia,giangvien) values(?,?,?)";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, kh.getKhoaHoc_Ten());
		statement.setInt(2, kh.getKhoaHoc_Gia());
		statement.setInt(3, kh.getGiangVien_Ma());
		statement.executeUpdate();
	}

	public void updateKhoaHoc(KhoaHoc kh) throws SQLException {
		String sql = "update KHOAHOC set khoahoc_ten = ?,khoahoc_gia = ?,giangvien = ? where khoahoc_ma = ?";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, kh.getKhoaHoc_Ten());
		statement.setInt(2, kh.getKhoaHoc_Gia());
		statement.setInt(3, kh.getGiangVien_Ma());
		statement.setInt(4, kh.getKhoaHoc_Ma());
		statement.executeUpdate();
	}

	public void deleteKhoaHoc(KhoaHoc kh) throws SQLException {
		String sql = "delete from KHOAHOC where khoahoc_ma = ?";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, kh.getKhoaHoc_Ma());
		statement.executeUpdate();
	}

	public KhoaHoc getKhoaHoc(int id) throws SQLException {
		KhoaHoc kh = null;
		String sql = "select * from KHOAHOC where khoahoc_ma = ?";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			kh = mapper.map(result);
		}
		return kh;
	}

	public List<KhoaHoc> getAllKhoaHoc() throws SQLException {
		ArrayList<KhoaHoc> list = new ArrayList<KhoaHoc>();
		String sql = "select * from KHOAHOC";
		PreparedStatement statement = con.prepareStatement(sql);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			KhoaHoc kh = mapper.map(result);
			list.add(kh);
		}
		return list;
	}

	public List<KhoaHoc> getPage(int page) throws SQLException {
		ArrayList<KhoaHoc> list = new ArrayList<KhoaHoc>();
		String sql = "select * from(select *,ROW_NUMBER() over (order by khoahoc_ma) as rownum from KHOAHOC)"
				+ " as kh where kh.rownum BETWEEN ? and 50";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, page * 50);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			KhoaHoc kh = mapper.map(result);
			list.add(kh);
		}
		return list;
	}

	public List<KhoaHoc> findKhoaHoc(String key) throws SQLException {
		ArrayList<KhoaHoc> list = new ArrayList<KhoaHoc>();
		String sql = "select * from KHOAHOC where khoahoc_ten like concat('%',?,'%')";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, key);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			KhoaHoc kh = mapper.map(result);
			list.add(kh);
		}
		return list;
	}

	public int count() throws SQLException {
		int count = 0;
		String sql = "select count(*) from KHOAHOC";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet result = st.executeQuery();
		while (result.next()) {
			count = result.getInt(1);
		}
		return count;
	}

}
