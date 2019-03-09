package baitaplonjava.ui.HocVien;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import baitaplonjava.Main.MainApp;
import baitaplonjava.model.HocVien;

public class TimKiemHocVien_Panel extends JPanel {

	private static final long serialVersionUID = 1L;
	private HocVien_Panel parrent;
	private JTextField textField;
	private JTable table;
	private DefaultTableModel model;

	public TimKiemHocVien_Panel() {
		this.setLayout(null);
		this.setSize(1400, 800);

		model = new DefaultTableModel(new Object[][] {}, new String[] { "stt", "Id học viên", "tên học viên",
				"tuổi học viên", "sdt học viên", "khóa học tham gia" }) {
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
		scrollPane.setBounds(0, 300, 1400, 500);
		add(scrollPane);

		JLabel lblTmKimHc = new JLabel("TÌM KIẾM HỌC VIÊN", JLabel.CENTER);
		lblTmKimHc.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTmKimHc.setBounds(500, 46, 400, 43);
		add(lblTmKimHc);

		JLabel lblTnHcVin = new JLabel("Tên học viên");
		lblTnHcVin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTnHcVin.setBounds(500, 146, 91, 43);
		add(lblTnHcVin);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setBounds(603, 146, 297, 43);
		add(textField);
		textField.setColumns(10);

		JButton btnTmKim = new JButton("Tìm kiếm");
		btnTmKim.setBounds(912, 146, 97, 43);
		btnTmKim.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String str = textField.getText().trim();
				while (table.getRowCount() > 0) {
					model.removeRow(0);
				}
				if (!str.equals("")) {
					List<HocVien> hvs = null;
					try {
						hvs = MainApp.hocVienDao.findHocVien(str);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					int stt = 1;
					for (HocVien hv : hvs) {
						try {
							model.addRow(new Object[] { stt, hv.getHocVien_Ma(), hv.getHocVien_Ten(),
									hv.getHocVien_Tuoi(), hv.getHocVien_Phone(),
									MainApp.hocVien_khoaHocDao.getKhoaHocByHocVien(hv.getHocVien_Ma()).size() });
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						stt += 1;
					}
				}

			}
		});
		add(btnTmKim);

		JButton btnQuayLi = new JButton("Quay lại");
		btnQuayLi.setBounds(85, 89, 106, 43);
		btnQuayLi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				parrent.timKiemHocVien_Panel.setVisible(false);
				parrent.quanLyHocVien_Panel.setVisible(true);

			}
		});
		add(btnQuayLi);
	}

	public void setParrent(HocVien_Panel panel) {
		this.parrent = panel;
	}

}
