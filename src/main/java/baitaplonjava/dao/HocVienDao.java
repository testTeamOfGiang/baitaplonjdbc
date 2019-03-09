package baitaplonjava.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import baitaplonjava.config.JDBC_Connection;
import baitaplonjava.model.HocVien;

public class HocVienDao {

	private Connection con;

	public HocVienDao() {
		con = JDBC_Connection.getConnection();
	}

	public HocVien getHocVien(int id) throws SQLException {
		HocVien hv = null;
		String sql = "select * from HOCVIEN where hocvien_ma = ?";
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		ResultSet result = preparedStatement.executeQuery();
		while (result.next()) {
			hv = new HocVien();
			hv.setHocVien_Ma(result.getInt("hocvien_ma"));
			hv.setHocVien_Ten(result.getString("hocvien_ten"));
			hv.setHocVien_Tuoi(result.getInt("hovien_tuoi"));
			hv.setHocVien_Phone(result.getString("hocvien_sdt"));
		}
		return hv;
	}

	public List<HocVien> getAllHocVien() throws SQLException {
		String sql = "select * from HOCVIEN";
		PreparedStatement statement = con.prepareStatement(sql);
		ResultSet result = statement.executeQuery();
		List<HocVien> hocViens = new ArrayList<HocVien>();
		while (result.next()) {
			HocVien hv = new HocVien();
			hv.setHocVien_Ma(result.getInt("hocvien_ma"));
			hv.setHocVien_Ten(result.getString("hocvien_ten"));
			hv.setHocVien_Tuoi(result.getInt("hovien_tuoi"));
			hv.setHocVien_Phone(result.getString("hocvien_sdt"));
			hocViens.add(hv);
		}
		return hocViens;
	}

	public void addHocVien(HocVien hv) throws SQLException {
		String sql = "insert into HOCVIEN(hocvien_ten,hovien_tuoi,hocvien_sdt) values(?,?,?)";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, hv.getHocVien_Ten());
		statement.setInt(2, hv.getHocVien_Tuoi());
		statement.setString(3, hv.getHocVien_Phone());
		statement.executeUpdate();
	}

	public void deleteHocVien(HocVien hv) throws SQLException {
		String sql = "delete from HOCVIEN where hocvien_ma = ?";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, hv.getHocVien_Ma());
		statement.executeUpdate();
	}

	public void updateHocVien(HocVien hv) throws SQLException {
		String sql = "update HOCVIEN set hocvien_ten = ?,hovien_tuoi = ?,hocvien_sdt = ? where hocvien_ma =?";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, hv.getHocVien_Ten());
		statement.setInt(2, hv.getHocVien_Tuoi());
		statement.setString(3, hv.getHocVien_Phone());
		statement.setInt(4, hv.getHocVien_Ma());
		statement.executeUpdate();
	}

	public List<HocVien> findHocVien(String key) throws SQLException {
		ArrayList<HocVien> hocViens = new ArrayList<HocVien>();
		String sql = "select * from HOCVIEN where hocvien_ten like concat('%',?,'%')";
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setString(1, key);
		ResultSet result = statement.executeQuery();
		while (result.next()) {
			HocVien hv = new HocVien();
			hv.setHocVien_Ma(result.getInt("hocvien_ma"));
			hv.setHocVien_Ten(result.getString("hocvien_ten"));
			hv.setHocVien_Tuoi(result.getInt("hovien_tuoi"));
			hv.setHocVien_Phone(result.getString("hocvien_sdt"));
			hocViens.add(hv);
		}
		return hocViens;
	}

	public int count() throws SQLException {
		int count = 0;
		String sql = "select count(*) from HOCVIEN";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet result = st.executeQuery();
		while (result.next()) {
			count = result.getInt(1);
		}
		return count;
	}

}
