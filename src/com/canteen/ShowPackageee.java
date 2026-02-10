package com.canteen;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class ShowPackageee  {

	private JPanel contentPane;
	private JTable table;
	private JTable tblData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowPackageee frame = new ShowPackageee();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShowPackageee() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 1131, 713);
		f.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new LineBorder(Color.BLACK, 5));

		f.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("      CANTEEN MANAGEMENT SYSTEM");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 35));
		label.setBorder(new LineBorder(Color.WHITE, 3));
		label.setBackground(Color.BLACK);
		label.setBounds(255, 36, 628, 60);
		contentPane.add(label);
		
		JLabel l1 = new JLabel("Available Packages in our Canteen :");
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("Cambria", Font.BOLD, 30));
		l1.setBounds(21, 123, 657, 39);
		contentPane.add(l1);
		
		JButton b1 = new JButton("DISPLAY DATA");
		b1.setFont(new Font("Cambria", Font.BOLD, 25));
		b1.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		b1.setBounds(316, 576, 214, 48);
		contentPane.add(b1);
		
		b1.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				try {
					
					String query = "select * from package";
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen", "NAME", "PASSWORD");
					PreparedStatement st= con.prepareStatement(query);
					
					
					ResultSet rs=st.executeQuery();
					ResultSetMetaData rsmd = rs.getMetaData();
					DefaultTableModel model = (DefaultTableModel) tblData.getModel();
					
					int cols = rsmd.getColumnCount();
					String[]colName = new String [cols];
					for(int i=0;i<cols;i++) 
						colName[i]=rsmd.getColumnName(i+1);
					model.setColumnIdentifiers(colName);
					String ITEMS, PRICE, I;
					int ID;
					
					while(rs.next()) {
						ID = rs.getInt(1);
						ITEMS = rs.getString(2);
						PRICE = rs.getString(3);
						I = String.valueOf(ID);
						String[]row = {I,ITEMS, PRICE};
						model.addRow(row);
						
					}
					
					 st.close();
					con.close();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton back = new JButton("<< GO BACK");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p_choices obj = new p_choices();
				f.dispose();
			}
		});
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Cambria", Font.BOLD, 25));
		back.setBorderPainted(false);
		back.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		back.setBackground(Color.BLACK);
		back.setBounds(884, 600, 188, 54);
		contentPane.add(back);
		
		table = new JTable();
		table.setBounds(80, 167, 1, 1);
		contentPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setBounds(62, 178, 966, 375);
		contentPane.add(scrollPane);
		
		tblData = new JTable();
		scrollPane.setViewportView(tblData);
		
		JButton b2 = new JButton("CLEAR DATA");
		b2.setFont(new Font("Cambria", Font.BOLD, 25));
		b2.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		b2.setBounds(566, 576, 214, 48);
		contentPane.add(b2);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tblData.setModel(new DefaultTableModel());
			}
		});
		
	}
}