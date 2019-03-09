package baitaplonjava.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import baitaplonjava.config.JDBC_Connection;

public class Main_Frame extends JFrame {

	private static final long serialVersionUID = 1L;

	public Main_Frame() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showOptionDialog(null, "Bạn có muốn thoát không", "Xác nhận đóng cửa sổ",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						new String[] { "Đồng ý", "Không!" }, JOptionPane.NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					e.getWindow().dispose();
					try {
						JDBC_Connection.getConnection().close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		this.setTitle("Quan Ly Khoa Hoc");
		this.setContentPane(new Main_Panel());
		this.setSize(1400, 840);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}

}
