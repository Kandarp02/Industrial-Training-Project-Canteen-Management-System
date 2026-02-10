package com.canteen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;

import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JTable;
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
import java.sql.*;
import java.awt.Component;
import java.awt.Cursor;

public class View_Package  {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTable table_4;
	private JTable table_5;
	private JTable table_6;
	private JTable table_7;
	private JTable table_8;
	private JTable tblData;
	private JScrollPane scrollPane_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View_Package frame = new View_Package();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public View_Package() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 1195, 704);
		f.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		contentPane.setFont(new Font("Cambria", Font.PLAIN, 20));
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(Color.BLACK);

		f.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("      CANTEEN MANAGEMENT SYSTEM");
		label.setForeground(Color.WHITE);
		label.setBounds(251, 36, 628, 60);
		label.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 35));
		label.setBorder(new LineBorder(Color.WHITE, 3));
		label.setBackground(Color.BLACK);
		contentPane.add(label);
		
		JLabel l1 = new JLabel("Available Packages in our Canteen :");
		l1.setForeground(Color.WHITE);
		l1.setBounds(59, 127, 657, 39);
		l1.setFont(new Font("Cambria", Font.BOLD, 30));
		contentPane.add(l1);
		
		table = new JTable();
		table.setBounds(174, 422, 674, -209);
		contentPane.add(table);
		
		table_1 = new JTable();
		table_1.setBounds(84, 511, 854, -319);
		contentPane.add(table_1);
		
		table_2 = new JTable();
		table_2.setBounds(62, 535, 882, -343);
		contentPane.add(table_2);
		
		table_3 = new JTable();
		table_3.setBounds(90, 190, 1, 1);
		contentPane.add(table_3);
		
		table_4 = new JTable();
		table_4.setBounds(84, 542, 867, -351);
		contentPane.add(table_4);
		
		table_5 = new JTable();
		table_5.setBorder(new LineBorder(Color.BLACK, 1));
		table_5.setForeground(Color.BLACK);
		table_5.setFont(new Font("Cambria", Font.PLAIN, 20));
		table_5.setBackground(Color.WHITE);
		table_5.setBounds(94, 535, 867, -343);
		contentPane.add(table_5);
		
		table_6 = new JTable();
		table_6.setBounds(94, 284, 90, -81);
		contentPane.add(table_6);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(84, 542, 877, -351);
		contentPane.add(scrollPane);
		
		table_7 = new JTable();
		scrollPane.setViewportView(table_7);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(90, 511, 871, -308);
		contentPane.add(scrollPane_1);
		
		table_8 = new JTable();
		scrollPane_1.setViewportView(table_8);
		
		JButton b1 = new JButton("DISPLAY DATA");
		b1.setForeground(Color.BLACK);
		b1.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		b1.setFont(new Font("Cambria", Font.BOLD, 25));
		b1.setBounds(346, 565, 214, 48);
		contentPane.add(b1);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * from package";
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen", "USERNAME", "PASSWORD");
					PreparedStatement st= con.prepareStatement(query);
					
					ResultSet rs=st.executeQuery();
					ResultSetMetaData rsmd = rs.getMetaData();
					DefaultTableModel model = (DefaultTableModel) tblData.getModel();
					
					int cols = rsmd.getColumnCount();
					String[]colName = new String [cols];
					for(int i=0;i<cols;i++) 
						colName[i]=rsmd.getColumnName(i+1);
					model.setColumnIdentifiers(colName);
					String ITEMS, PRICE;
					
					while(rs.next()) {
						ITEMS = rs.getString(1);
						PRICE = rs.getString(2);
						String[]row = {ITEMS, PRICE};
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
		
		
		JButton b2 = new JButton("CLEAR DATA");
		b2.setForeground(Color.BLACK);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				tblData.setModel(new DefaultTableModel());
			}
		});
		b2.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		b2.setFont(new Font("Cambria", Font.BOLD, 25));
		b2.setBounds(596, 565, 214, 48);
		contentPane.add(b2);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		scrollPane_2.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		scrollPane_2.setViewportBorder(new LineBorder(Color.BLACK, 0));
		scrollPane_2.setBackground(Color.WHITE);
		scrollPane_2.setForeground(Color.BLACK);
		scrollPane_2.setFont(new Font("Cambria", Font.PLAIN, 20));
		scrollPane_2.setBounds(59, 176, 1006, 369);
		contentPane.add(scrollPane_2);
		
		tblData = new JTable();
		scrollPane_2.setViewportView(tblData);
		tblData.setFont(new Font("Cambria", Font.PLAIN, 20));
		tblData.setForeground(Color.BLACK);
		tblData.setBorder(new LineBorder(Color.BLACK, 0));
		tblData.setBackground(Color.WHITE);
		
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
		back.setBounds(912, 588, 188, 54);
		contentPane.add(back);
	}
}
