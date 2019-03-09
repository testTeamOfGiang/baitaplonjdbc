package baitaplonjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import baitaplonjava.config.JDBC_Connection;
import baitaplonjava.model.GiangVien;
import baitaplonjava.model.KhoaHoc;

public class GiangVienDao {

	private Connection con;
	private GiangVienMapper mapper;
	private KhoaHocMapper khMapper;

	public GiangVienDao() {
		con = JDBC_Connection.getConnection();
		mapper = new GiangVienMapper();
		khMapper = new KhoaHocMapper();
	}

	public void addGiangVien(GiangVien gv) throws SQLException {
		String sql = "insert into GIANGVIEN(giangvien_ten,giangvien_sdt) values(?,?)";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, gv.getGiangVien_Ten());
		statement.setString(2, gv.getGiangVien_Phone());
		statement.executeUpdate();
	}

	public void updateGiangVien(GiangVien gv) throws SQLException {
		String sql = "update GIANGVIEN set giangvien_ten = ?,giangvien_sdt = ? where giangvien_ma = ?";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, gv.getGiangVien_Ten());
		statement.setString(2, gv.getGiangVien_Phone());
		statement.setInt(3, gv.getGiangVien_Ma());
		statement.executeUpdate();
	}

	public void deleteGiangVien(GiangVien gv) throws SQLException {
		String sql = "delete from GIANGVIEN where giangvien_ma = ?";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, gv.getGiangVien_Ma());
		statement.executeUpdate();
	}

	public GiangVien getGiangVien(int id) throws SQLException {
		GiangVien gv = null;
		String sql = "select * from GIANGVIEN where giangvien_ma = ?";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, id);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			gv = mapper.map(result);
		}
		return gv;
	}

	public List<GiangVien> getAllGiangVien() throws SQLException {
		ArrayList<GiangVien> list = new ArrayList<GiangVien>();
		String sql = "select * from GIANGVIEN";
		PreparedStatement statement = con.prepareStatement(sql);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			GiangVien gv = mapper.map(result);
			list.add(gv);
		}
		return list;
	}

	public List<GiangVien> getPage(int page) throws SQLException {
		ArrayList<GiangVien> list = new ArrayList<GiangVien>();
		String sql = "select * from( select *,ROW_NUMBER() over (order by giangvien_ma) as "
				+ "rownum from GIANGVIEN) as gv where gv.rownum BETWEEN ? and 50";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, page * 50);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			GiangVien gv = mapper.map(result);
			list.add(gv);
		}
		return list;
	}

	public List<KhoaHoc> getKhoaHocByGiangVien(int idGV) throws SQLException {
		List<KhoaHoc> list = new ArrayList<KhoaHoc>();
		String sql = "select * from KHOAHOC as kh where kh.giangvien = ?";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, idGV);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			KhoaHoc kh = khMapper.map(result);
			list.add(kh);
		}
		return list;
	}

	public List<GiangVien> findGiangVien(String key) throws SQLException {
		List<GiangVien> list = new ArrayList<GiangVien>();
		String sql = "select * from GIANGVIEN where giangvien_ten like concat('%',?,'%')";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, key);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			GiangVien gv = mapper.map(result);
			list.add(gv);
		}
		return list;
	}

	public int count() throws SQLException {
		int count = 0;
		String sql = "select count(*) from GIANGVIEN";
		PreparedStatement statment = con.prepareStatement(sql);
		ResultSet result = statment.executeQuery();
		while (result.next()) {
			count = result.getInt(1);
		}
		return count;
	}

}
