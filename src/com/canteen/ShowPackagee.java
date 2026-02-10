package com.canteen;
import java.awt.EventQueue;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;



import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import javax.swing.JTextField;

public class ShowPackagee  {

	private JPanel contentPane;
	private JTable table;
	private JTable tblData;
	JTextField t1;
	int  x;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowPackagee frame = new ShowPackagee();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShowPackagee() {
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
		label.setBounds(267, 28, 628, 60);
		contentPane.add(label);
		
		JLabel l1 = new JLabel("Available Packages in our Canteen :");
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("Cambria", Font.BOLD, 30));
		l1.setBounds(22, 118, 657, 39);
		contentPane.add(l1);
		
		JButton b1 = new JButton("DISPLAY DATA");
		b1.setFont(new Font("Cambria", Font.BOLD, 25));
		b1.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		b1.setBounds(420, 437, 214, 48);
		contentPane.add(b1);
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int c = 100;
					
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
					String ITEMS, PRICE;
					
					while(rs.next()) {
						String a = String.valueOf(c);
						ITEMS = rs.getString(2);
						PRICE = rs.getString(3);
					
						String[]row = {a,ITEMS, PRICE};
						model.addRow(row);
						c=c+1;
					}
					 st.close();
					con.close();
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		table = new JTable();
		table.setBounds(80, 167, 1, 1);
		contentPane.add(table);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(0, 153, 204), 5));
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		scrollPane.setBounds(60, 167, 966, 248);
		contentPane.add(scrollPane);
		
		tblData = new JTable();
		scrollPane.setViewportView(tblData);
		
		JLabel l2 = new JLabel("Enter the ID of the package you want to select : ");
		l2.setForeground(Color.WHITE);
		l2.setFont(new Font("Cambria", Font.BOLD, 30));
		l2.setBounds(22, 519, 658, 39);
		contentPane.add(l2);
		
		t1 = new JTextField();
		t1.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		t1.setFont(new Font("Cambria", Font.PLAIN, 25));
		t1.setBounds(690, 521, 203, 39);
		contentPane.add(t1);
		t1.setColumns(10);
		
		JButton back = new JButton("<< GO BACK");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Add_Student();
				f.dispose();
			}
		});
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Cambria", Font.BOLD, 25));
		back.setBorderPainted(false);
		back.setBorder(new LineBorder(Color.BLACK, 2));
		back.setBackground(Color.BLACK);
		back.setBounds(878, 601, 193, 48);
		contentPane.add(back);
		
		JButton b2 = new JButton("SAVE");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection c = null;
				PreparedStatement p = null;
				String id = null;
				try {
					c = DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen","NAME","PASSWORD");
					if(c!=null) {
						System.out.println("DATABASE CONNECTED");
					}
					else {
						System.out.println("CONNECTION PROBLEM");
					}
				}catch(SQLException e1) {
					System.out.println("ERROR IN CONNECTING THE DATABASE ");
					
				}
				
				id = t1.getText();
							
				
				try {
					p = c.prepareStatement("insert into package_id(id)VALUES(?)");
					p.setString(1,id);
					int a = p.executeUpdate();
					System.out.println("ROWS AFFECTED ARE : "+a);
					JOptionPane.showMessageDialog(f,"Package select with ID : "+id);
					//Add_Student ob = new Add_Student();
					f.dispose();
					p.close();
					c.close();
				}catch(SQLException e2) {
					System.out.println("Error in inserting the data to database");
				}
			}
		});
		b2.setForeground(Color.BLACK);
		b2.setFont(new Font("Cambria", Font.BOLD, 25));
		b2.setBorderPainted(false);
		b2.setBorder(new LineBorder(Color.GRAY, 3));
		b2.setBackground(Color.GREEN);
		b2.setBounds(439, 601, 176, 48);
		contentPane.add(b2);
	
		
	}
}