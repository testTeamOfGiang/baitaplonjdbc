package baitaplonjava.ui.GiangVien;

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
import baitaplonjava.model.GiangVien;

public class QuanLyGiangVien_Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	private GiangVien_Panel parrent;
	private JTable table;
	private JTextField tfname;
	private JTextField tfsdt;
	private DefaultTableModel model;
	public Map<Integer, GiangVien> data;
	int page;

	public QuanLyGiangVien_Panel() {

		page = 0;
		data = new HashMap<Integer, GiangVien>();

		this.setLayout(null);
		this.setSize(1400, 800);

		model = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "ID giảng viên", "Tên giảng viên", "số điện thoại", "Số lớp dạy" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(model);
		table.setRowHeight(40);
		table.setSize(1400, 400);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1) {
					int current = table.getSelectedRow();
					GiangVien gv = data.get(current);
					tfname.setText(gv.getGiangVien_Ten());
					tfsdt.setText(gv.getGiangVien_Phone());
				}
				if (e.getClickCount() == 2) {
					parrent.quanLyGiangVien_Panel.setVisible(false);
					parrent.chiTietGiangVien_panel.gv = data.get(table.getSelectedRow());
					try {
						parrent.chiTietGiangVien_panel.loadData();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					parrent.chiTietGiangVien_panel.setVisible(true);

				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 1400, 400);
		add(scrollPane);

		JLabel lblTnGingVin = new JLabel("Tên giảng viên");
		lblTnGingVin.setBounds(502, 523, 91, 40);
		add(lblTnGingVin);

		tfname = new JTextField();
		tfname.setBounds(629, 523, 231, 40);
		add(tfname);
		tfname.setColumns(10);

		JLabel lblSinThoi = new JLabel("Số điện thoại");
		lblSinThoi.setBounds(502, 597, 91, 40);
		add(lblSinThoi);

		tfsdt = new JTextField();
		tfsdt.setBounds(629, 597, 231, 40);
		add(tfsdt);
		tfsdt.setColumns(10);

		JButton btnTmKim = new JButton("Tìm kiếm");
		btnTmKim.setBounds(113, 454, 110, 40);
		btnTmKim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parrent.quanLyGiangVien_Panel.setVisible(false);
				parrent.timKiemGiangVien_Panel.setVisible(true);

			}
		});
		add(btnTmKim);

		JButton btnThm = new JButton("Thêm");
		btnThm.setBounds(661, 673, 110, 40);
		btnThm.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String name = tfname.getText().trim();
					String sdt = tfsdt.getText().trim();
					if (name.equals("") || sdt.equals("")) {
						throw new ThieuThongTinEx();
					}
					GiangVien gv = new GiangVien();
					gv.setGiangVien_Ten(name);
					gv.setGiangVien_Phone(sdt);
					MainApp.giangVienDao.addGiangVien(gv);
					loadData();
					JOptionPane.showMessageDialog(null, "thêm giảng viên thành công");
				} catch (ThieuThongTinEx e) {
					JOptionPane.showMessageDialog(null, "hãy nhập đủ thông tin");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "thêm giảng viên không thành công");
				}
			}
		});
		add(btnThm);

		JButton btnXa = new JButton("Xóa");
		btnXa.setBounds(1143, 454, 115, 40);
		btnXa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int current = table.getSelectedRow();
					if (current == -1) {
						throw new ThieuThongTinEx();
					}
					GiangVien gv = data.get(current);
					MainApp.giangVienDao.deleteGiangVien(gv);
					loadData();
					JOptionPane.showMessageDialog(null, "xóa giảng viên thành công");
				} catch (ThieuThongTinEx ex) {
					JOptionPane.showMessageDialog(null, "hãy chọn giảng viên trước khi xóa");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "xóa giảng viên không thành công");
				}

			}
		});
		add(btnXa);

		JButton btnSa = new JButton("Sửa");
		btnSa.setBounds(1143, 597, 115, 40);
		btnSa.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String name = tfname.getText().trim();
					String sdt = tfsdt.getText().trim();
					if (name.equals("") || sdt.equals("")) {
						throw new ThieuThongTinEx();
					}
					int current = table.getSelectedRow();
					if (current == -1) {
						throw new ChuaChonEx();
					}
					GiangVien gv = data.get(current);
					gv.setGiangVien_Ten(name);
					gv.setGiangVien_Phone(sdt);
					MainApp.giangVienDao.updateGiangVien(gv);
					loadData();
					JOptionPane.showMessageDialog(null, "sửa giảng viên thành công");
				} catch (ThieuThongTinEx ex) {
					JOptionPane.showMessageDialog(null, "hãy nhập thông tin trước khi sửa");
				} catch (ChuaChonEx e2) {
					JOptionPane.showMessageDialog(null, "hãy chọn một giảng viên đẻ sửa");
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "sửa giảng viên không thành công");
				}

			}
		});
		add(btnSa);

		JButton btnChiTit = new JButton("reload");
		btnChiTit.setBounds(113, 597, 110, 40);
		btnChiTit.addActionListener(new ActionListener() {

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
		add(btnChiTit);

		JButton btnTrc = new JButton("Trước");
		btnTrc.setBounds(502, 454, 97, 40);
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
		btnSau.setBounds(756, 454, 104, 40);
		btnSau.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					int pageCount = MainApp.giangVienDao.count() / 50;
					if (pageCount * 50 < MainApp.giangVienDao.count())
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
			e1.printStackTrace();
		}
	}

	public void loadData() throws SQLException {
		if (this.data.size() > 0) {
			this.data.clear();
		}
		while (table.getRowCount() > 0) {
			model.removeRow(0);
		}
		int stt = 1;
		List<GiangVien> list = MainApp.giangVienDao.getPage(this.page);
		for (GiangVien gv : list) {
			model.addRow(new Object[] { stt, gv.getGiangVien_Ma(), gv.getGiangVien_Ten(), gv.getGiangVien_Phone(),
					MainApp.giangVienDao.getKhoaHocByGiangVien(gv.getGiangVien_Ma()).size() });
			data.put(stt - 1, gv);
			stt += 1;
		}
	}

	public void setParrent(GiangVien_Panel giangVien_Panel) {
		this.parrent = giangVien_Panel;
	}
}
