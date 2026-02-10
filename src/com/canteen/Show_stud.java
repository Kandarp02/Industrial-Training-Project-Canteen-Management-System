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
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Show_stud  {

	private JPanel contentPane;
	private JTable tblData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Show_stud frame = new Show_stud();
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Show_stud() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 1212, 714);
		f.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 3));

		f.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("      CANTEEN MANAGEMENT SYSTEM");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 35));
		label.setBorder(new LineBorder(Color.WHITE, 3));
		label.setBackground(Color.BLACK);
		label.setBounds(274, 24, 628, 60);
		contentPane.add(label);
		
		JLabel lblRegisteredStudentsIn = new JLabel("Registered Students in our Canteen :");
		lblRegisteredStudentsIn.setForeground(Color.WHITE);
		lblRegisteredStudentsIn.setFont(new Font("Cambria", Font.BOLD, 30));
		lblRegisteredStudentsIn.setBounds(28, 110, 657, 39);
		contentPane.add(lblRegisteredStudentsIn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 10));
		scrollPane.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		scrollPane.setBounds(28, 166, 1141, 288);
		contentPane.add(scrollPane);
		
		tblData = new JTable();
		scrollPane.setViewportView(tblData);
		tblData.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton b1 = new JButton("DISPLAY DATA");
		b1.setFont(new Font("Cambria", Font.BOLD, 25));
		b1.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		b1.setBounds(336, 498, 214, 48);
		contentPane.add(b1);
		
		JButton back = new JButton("<< GO BACK");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Registration();
				f.dispose();
			}
		});
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Cambria", Font.BOLD, 25));
		back.setBorderPainted(false);
		back.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		back.setBackground(Color.BLACK);
		back.setBounds(944, 589, 188, 54);
		contentPane.add(back);
		
		JButton b2 = new JButton("CLEAR DATA");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tblData.setModel(new DefaultTableModel());
			}
		});
		b2.setFont(new Font("Cambria", Font.BOLD, 25));
		b2.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		b2.setBounds(603, 498, 214, 48);
		contentPane.add(b2);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		try {
					
					String query = "select * from stud_reg";
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
					String name, mob, email, dob, purchased,id;
				
					
					
					while(rs.next()) {
						id = rs.getString(1);
						name = rs.getString(2);
						mob = rs.getString(3);
						email = rs.getString(4);
						dob = rs.getString(5);
						purchased = rs.getString(6);
						String[]row = {id,name,mob,email,dob,purchased};
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
		
	}
}
