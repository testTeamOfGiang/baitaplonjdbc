package baitaplonjava.ui.KhoaHoc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import baitaplonjava.Exception.ChuaChonEx;
import baitaplonjava.Exception.ThieuThongTinEx;
import baitaplonjava.Main.MainApp;
import baitaplonjava.model.HocVien;
import baitaplonjava.model.KhoaHoc;

public class QuanLyKhoaHoc_Panel extends JPanel {

	private static final long serialVersionUID = 1L;

	private KhoaHoc_Panel parrent;
	private JTable table;
	private DefaultTableModel model;
	private int page;
	private Map<Integer, KhoaHoc> data;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public QuanLyKhoaHoc_Panel() {
		page = 0;
		data = new HashMap<Integer, KhoaHoc>();

		this.setLayout(null);
		this.setSize(1400, 800);

		model = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Id khóa học", "Tên khóa học", "Học phí", "Giáo viên", "số học viên" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};

		table = new JTable(model);
		table.setRowHeight(40);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				KhoaHoc kh = data.get(table.getSelectedRow());
				if (e.getClickCount() == 1) {

					textField.setText(kh.getKhoaHoc_Ten());
					textField_1.setText(kh.getKhoaHoc_Gia() + "");
					textField_2.setText(kh.getGiangVien_Ma() + "");
				}
				if (e.getClickCount() == 2) {
					parrent.quanLyKhoaHoc_Panel.setVisible(false);
					parrent.chiTietKhoaHoc_Panel.kh = kh;
					try {
						parrent.chiTietKhoaHoc_Panel.loadData();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					parrent.chiTietKhoaHoc_Panel.setVisible(true);
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 1400, 400);
		add(scrollPane);

		JLabel lblTnKhaHc = new JLabel("Tên khóa học");
		lblTnKhaHc.setBounds(528, 512, 86, 40);
		add(lblTnKhaHc);

		JLabel lblGiKhaHc = new JLabel("Giá khóa học");
		lblGiKhaHc.setBounds(528, 577, 86, 40);
		add(lblGiKhaHc);

		JLabel lblMGingVin = new JLabel("Mã Giảng viên");
		lblMGingVin.setBounds(528, 644, 86, 40);
		add(lblMGingVin);

		textField = new JTextField();
		textField.setBounds(626, 512, 227, 40);
		add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(626, 577, 227, 40);
		add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(626, 644, 227, 40);
		add(textField_2);
		textField_2.setColumns(10);

		JButton btnThm = new JButton("Thêm");
		btnThm.setBounds(656, 715, 112, 40);
		btnThm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String name = textField.getText().trim();
					String giaTam = textField_1.getText().trim();
					String maGvTam = textField_2.getText().trim();
					if (name.equals("") || giaTam.equals("") || maGvTam.equals("")) {
						throw new ThieuThongTinEx();
					}
					System.err.println("dang chay");
					int gia = Integer.parseInt(giaTam);
					int maGv = Integer.parseInt(maGvTam);

					KhoaHoc kh = new KhoaHoc();
					kh.setKhoaHoc_Ten(name);
					kh.setKhoaHoc_Gia(gia);
					kh.setGiangVien_Ma(maGv);
					MainApp.khoaHocDao.addKhoaHoc(kh);
					loadData();
					JOptionPane.showMessageDialog(null, "Thêm khóa học thành công");
				} catch (ThieuThongTinEx ex) {
					JOptionPane.showMessageDialog(null, "Hãy nhập đủ thông tin");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Thêm khóa học thất bại");
				}

			}
		});
		add(btnThm);

		JButton btnTmKim = new JButton("Tìm kiếm");
		btnTmKim.setBounds(86, 512, 112, 40);
		btnTmKim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parrent.quanLyKhoaHoc_Panel.setVisible(false);
				parrent.timKiemKhoaHoc_Panel.setVisible(true);
			}
		});
		add(btnTmKim);

		JButton btnReload = new JButton("Reload");
		btnReload.setBounds(86, 644, 112, 40);
		btnReload.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					loadData();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		add(btnReload);

		JButton btnSa = new JButton("Sửa");
		btnSa.setBounds(1180, 512, 112, 40);
		btnSa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String name = textField.getText().trim();
					String giaTam = textField_1.getText().trim();
					String maGvTam = textField_2.getText().trim();
					if (name.equals("") || giaTam.equals("") || maGvTam.equals("")) {
						throw new ThieuThongTinEx();
					}
					int current = table.getSelectedRow();
					if (current == -1) {
						throw new ChuaChonEx();
					}
					KhoaHoc kh = data.get(current);
					kh.setKhoaHoc_Ten(name);
					kh.setKhoaHoc_Gia(Integer.parseInt(giaTam));
					int maGiangVien = Integer.parseInt(maGvTam);
					kh.setGiangVien_Ma(maGiangVien);
					MainApp.khoaHocDao.updateKhoaHoc(kh);
					;
					loadData();
					JOptionPane.showMessageDialog(null, "Sửa khóa học thành công");
				} catch (ThieuThongTinEx ex) {
					JOptionPane.showMessageDialog(null, "Hãy nhập đủ thông tin");
				} catch (ChuaChonEx e2) {
					JOptionPane.showMessageDialog(null, "Hãy chọn khóa học cần sửa");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Sửa khóa học không thành công");
				}
			}
		});
		add(btnSa);

		JButton btnXa = new JButton("Xóa");
		btnXa.setBounds(1180, 644, 112, 40);
		btnXa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int current = table.getSelectedRow();
					if (current == -1) {
						throw new ChuaChonEx();
					}
					KhoaHoc kh = data.get(current);
					for (HocVien hv : MainApp.hocVien_khoaHocDao.getHocVienByKhoaHoc(kh.getKhoaHoc_Ma())) {
						MainApp.hocVien_khoaHocDao.deleteHocVien_KhoaHoc(hv, kh);
					}
					MainApp.khoaHocDao.deleteKhoaHoc(kh);
					loadData();
					JOptionPane.showMessageDialog(null, "Xoa khóa học thành công");
				} catch (ChuaChonEx ex) {
					JOptionPane.showMessageDialog(null, "Hãy chọn khóa học cần xóa");
				} catch (Exception e2) {
					e2.printStackTrace();
					JOptionPane.showMessageDialog(null, "Xóa khóa học không thành công");
				}
			}
		});
		add(btnXa);

		JButton btnTrc = new JButton("Trước");
		btnTrc.setBounds(528, 443, 97, 40);
		btnTrc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (page > 0) {
					page--;
				}
				if (page == 0) {

				}
				try {
					loadData();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		add(btnTrc);

		JButton btnSau = new JButton("Sau");
		btnSau.setBounds(756, 443, 97, 40);
		btnSau.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int pageCount = MainApp.khoaHocDao.count() / 50;
					if (pageCount * 50 < MainApp.khoaHocDao.count())
						pageCount += 1;
					if (page < (pageCount - 1)) {
						page++;
					}
					if (page == (pageCount - 1)) {

					}
					loadData();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}

			}
		});
		add(btnSau);

		try {
			loadData();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void setParrent(KhoaHoc_Panel khoaHoc_Panel) {
		this.parrent = khoaHoc_Panel;
	}

	public void loadData() throws SQLException {
		if (this.data.size() > 0) {
			this.data.clear();
		}
		while (table.getRowCount() > 0) {
			model.removeRow(0);
		}
		int stt = 1;
		List<KhoaHoc> khoahocs = MainApp.khoaHocDao.getPage(this.page);
		for (KhoaHoc kh : khoahocs) {
			model.addRow(new Object[] { stt, kh.getKhoaHoc_Ma(), kh.getKhoaHoc_Ten(), kh.getKhoaHoc_Gia(),
					MainApp.giangVienDao.getGiangVien(kh.getGiangVien_Ma()).getGiangVien_Ten(),
					MainApp.hocVien_khoaHocDao.getHocVienByKhoaHoc(kh.getKhoaHoc_Ma()).size() });
			data.put(stt - 1, kh);
			stt += 1;
		}
	}

}
