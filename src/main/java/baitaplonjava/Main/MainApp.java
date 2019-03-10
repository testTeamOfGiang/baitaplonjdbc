package baitaplonjava.Main;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import baitaplonjava.dao.GiangVienDao;
import baitaplonjava.dao.HocVienDao;
import baitaplonjava.dao.HocVien_KhoaHocDao;
import baitaplonjava.dao.KhoaHocDao;
import baitaplonjava.ui.Main_Frame;

public class MainApp {

	public static HocVienDao hocVienDao;
	public static GiangVienDao giangVienDao;
	public static KhoaHocDao khoaHocDao;
	public static HocVien_KhoaHocDao hocVien_khoaHocDao;

	public static void init() {
		hocVienDao = new HocVienDao();
		giangVienDao = new GiangVienDao();
		khoaHocDao = new KhoaHocDao();
		hocVien_khoaHocDao = new HocVien_KhoaHocDao();
	}

	public static void main(String[] args) {
		init();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new NimbusLookAndFeel());
					Main_Frame frame = new Main_Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		// System.out.println("test chơi");
	}
}
