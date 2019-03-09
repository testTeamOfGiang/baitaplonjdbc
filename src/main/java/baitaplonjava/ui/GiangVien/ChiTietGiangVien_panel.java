package baitaplonjava.ui.GiangVien;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import baitaplonjava.Main.MainApp;
import baitaplonjava.model.GiangVien;
import baitaplonjava.model.KhoaHoc;

import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;

public class ChiTietGiangVien_panel extends JPanel {

	private static final long serialVersionUID = 1L;

	private GiangVien_Panel parrent;
	private JTable table;
	private DefaultTableModel model;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public GiangVien gv;

	public ChiTietGiangVien_panel() {
		this.setLayout(null);
		this.setSize(1400, 800);

		model = new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Id khóa học", "Tên khóa học", "Học phí­" }) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};

		table = new JTable(model);
		table.setRowHeight(40);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 400, 1400, 400);
		add(scrollPane);

		textField = new JTextField();
		textField.setBounds(609, 129, 219, 40);
		add(textField);
		textField.setColumns(10);

		JLabel lblTnGingVin = new JLabel("Tên giảng viên");
		lblTnGingVin.setBounds(506, 129, 91, 40);
		add(lblTnGingVin);

		JLabel lblSinThoi = new JLabel("Số điện thoại");
		lblSinThoi.setBounds(522, 205, 75, 40);
		add(lblSinThoi);

		textField_1 = new JTextField();
		textField_1.setBounds(609, 205, 219, 40);
		add(textField_1);
		textField_1.setColumns(10);

		JLabel lblCcLpang = new JLabel("Các lớp đang dạy", JLabel.CENTER);
		lblCcLpang.setFont(new Font("Arial", Font.BOLD, 18));
		lblCcLpang.setBounds(506, 279, 374, 59);
		add(lblCcLpang);

		JLabel lblMGingVin = new JLabel("Mã giảng viên");
		lblMGingVin.setBounds(506, 55, 91, 40);
		add(lblMGingVin);

		textField_2 = new JTextField();
		textField_2.setBounds(609, 55, 219, 40);
		add(textField_2);
		textField_2.setColumns(10);

		JButton btnQuayLi = new JButton("Quay lại");
		btnQuayLi.setBounds(46, 55, 110, 40);
		btnQuayLi.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				parrent.chiTietGiangVien_panel.setVisible(false);
				parrent.quanLyGiangVien_Panel.setVisible(true);

			}
		});
		add(btnQuayLi);

	}

	public void loadData() throws SQLException {
		while (table.getRowCount() > 0) {
			model.removeRow(0);
		}
		textField_2.setText(this.gv.getGiangVien_Ma() + "");
		textField.setText(this.gv.getGiangVien_Ten());
		textField_1.setText(this.gv.getGiangVien_Phone());

		int stt = 1;
		for (KhoaHoc kh : MainApp.giangVienDao.getKhoaHocByGiangVien(gv.getGiangVien_Ma())) {
			model.addRow(new Object[] { stt, kh.getKhoaHoc_Ma(), kh.getKhoaHoc_Ten(), kh.getKhoaHoc_Gia() });
			stt += 1;
		}

	}

	public void setParrent(GiangVien_Panel giangVien_Panel) {
		this.parrent = giangVien_Panel;
	}

}
