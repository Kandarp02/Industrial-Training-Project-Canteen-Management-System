package com.canteen;

import java.awt.EventQueue;

import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.TextField;
import java.awt.Toolkit;

import javax.swing.JTextField;


import com.toedter.calendar.JDateChooser;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import java.util.Date;

public class Add_Student  {

	private JPanel contentPane;
	private JTextField t3;
	private JTextField t2;
	private JTextField t1;
	String s = null;
	int l = 0;
	private JTextField t4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Student frame = new Add_Student();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Add_Student() {
		
		
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 1085, 773);
		f.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new LineBorder(Color.WHITE, 5));

		f.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("      CANTEEN MANAGEMENT SYSTEM");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 35));
		label.setBorder(new LineBorder(Color.WHITE, 3));
		label.setBackground(Color.BLACK);
		label.setBounds(221, 60, 628, 60);
		contentPane.add(label);
		
		JLabel l1 = new JLabel("Student Name        :");
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("Cambria", Font.BOLD, 23));
		l1.setBounds(72, 168, 207, 30);
		contentPane.add(l1);
		
		JLabel l2 = new JLabel("Mobile number     :");
		l2.setForeground(Color.WHITE);
		l2.setFont(new Font("Cambria", Font.BOLD, 23));
		l2.setBounds(72, 240, 207, 30);
		contentPane.add(l2);
		
		JLabel l3 = new JLabel("Email Id                    :");
		l3.setForeground(Color.WHITE);
		l3.setFont(new Font("Cambria", Font.BOLD, 23));
		l3.setBounds(72, 318, 193, 30);
		contentPane.add(l3);
		
		t3 = new JTextField();
		t3.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		t3.setFont(new Font("Cambria", Font.PLAIN, 25));
		t3.setColumns(10);
		t3.setBounds(378, 318, 493, 39);
		contentPane.add(t3);
		
		t2 = new JTextField();
		t2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				char c = e.getKeyChar();
				
				if(Character.isLetter(c)) {
					t2.setEditable(false);
					JOptionPane.showMessageDialog(f,"The input can only be a digit");
				}
				else {
					t2.setEditable(true);
					
					
					if(t2.getText().length()>9){
						JOptionPane.showMessageDialog(f,"Only 10 digits are allowed ");
						t2.setText("");
					}
					
					
				}
				
			}
		});
		t2.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		t2.setFont(new Font("Cambria", Font.PLAIN, 25));
		t2.setColumns(10);
		t2.setBounds(378, 235, 493, 39);
		contentPane.add(t2);
		
	    
		t1 = new JTextField();
		t1.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		t1.setFont(new Font("Cambria", Font.PLAIN, 25));
		t1.setColumns(10);
		t1.setBounds(378, 163, 493, 38);
		contentPane.add(t1);
		
		
		JLabel l4 = new JLabel("DOB                            :");
		l4.setForeground(Color.WHITE);
		l4.setFont(new Font("Cambria", Font.BOLD, 23));
		l4.setBounds(72, 396, 193, 30);
		contentPane.add(l4);
		
		JDateChooser d1 = new JDateChooser();
		d1.getCalendarButton().setBorder(new LineBorder(Color.WHITE));
		d1.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		d1.setFont(new Font("Cambria", Font.PLAIN, 25));
		d1.setBounds(378, 396, 493, 39);
		contentPane.add(d1);
		
		JLabel lblDoTheStudent = new JLabel("Do the student want to purchase the package?");
		lblDoTheStudent.setForeground(Color.WHITE);
		lblDoTheStudent.setFont(new Font("Cambria", Font.BOLD, 25));
		lblDoTheStudent.setBounds(221, 511, 571, 60);
		contentPane.add(lblDoTheStudent);
		
		
		JRadioButton b1 = new JRadioButton(" YES");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(b1.isSelected()) {	
					s = "YES" ;
					ShowPackagee obj = new ShowPackagee();
				//	f.dispose();
				}
			}
		});
		b1.setFont(new Font("Cambria", Font.BOLD, 25));
		b1.setBorder(new LineBorder(Color.BLACK, 2));
		b1.setBackground(new Color(0, 255, 0));
		b1.setForeground(Color.BLACK);
		b1.setBounds(366, 582, 96, 45);
		contentPane.add(b1);
	
		
		JRadioButton b2 = new JRadioButton(" NO");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(b2.isSelected()) {
					s = "NO";
					new Registration();
					f.dispose();
				}
			}
		});
		b2.setForeground(Color.BLACK);
		b2.setFont(new Font("Cambria", Font.BOLD, 25));
		b2.setBorder(new LineBorder(Color.BLACK, 2));
		b2.setBackground(new Color(255, 51, 51));
		b2.setBounds(497, 582, 96, 45);
		contentPane.add(b2);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(b1);
		bg.add(b2);
		
		
		JButton back = new JButton("<< GO BACK");
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Cambria", Font.BOLD, 25));
		back.setBorderPainted(false);
		back.setBorder(new LineBorder(Color.BLACK, 2));
		back.setBackground(Color.BLACK);
		back.setBounds(837, 651, 193, 54);
		contentPane.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				f.dispose();
				
			}
		});
		
		
		
		JButton btnSubmit = new JButton("SUBMIT");
		
		btnSubmit.setForeground(Color.BLACK);
		btnSubmit.setFont(new Font("Cambria", Font.BOLD, 25));
		btnSubmit.setBorderPainted(false);
		btnSubmit.setBorder(new LineBorder(Color.WHITE, 7, true));
		btnSubmit.setBackground(new Color(152, 251, 152));
		btnSubmit.setBounds(378, 651, 199, 54);
		contentPane.add(btnSubmit);
		
		JLabel lblStudentid = new JLabel("Student_id               :");
		lblStudentid.setForeground(Color.WHITE);
		lblStudentid.setFont(new Font("Cambria", Font.BOLD, 23));
		lblStudentid.setBounds(72, 471, 193, 30);
		contentPane.add(lblStudentid);
		
		t4 = new JTextField();
		t4.setFont(new Font("Cambria", Font.PLAIN, 25));
		t4.setColumns(10);
		t4.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		t4.setBounds(378, 462, 493, 39);
		contentPane.add(t4);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection c = null;
				PreparedStatement p = null;
				int id = Integer.parseInt(t4.getText());
				String name = t1.getText();
				String mob = t2.getText();
				String email = t3.getText();
				Date dob = (Date) d1.getDate();
				java.util.Date utilDate = d1.getDate();
			    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				try {
					 
					 
					c = DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen","NAME","PASSWORD");
					if(c!=null) 
					{
						System.out.println("DATABASE CONNECTED ");
					}
					else 
					{
						System.out.println("CONNECTION FAILED");
					}
				
			PreparedStatement x = c.prepareStatement("insert into stud_reg(id,Name,Mobile_number, Email_id, DOB, Package_Purchased)VALUES (?,?,?,?,?,?)");
			
			x.setInt(1, id);
			x.setString(2, name);
			x.setString(3, mob);
			x.setString(4, email);
			x.setDate(5, sqlDate);
			x.setString(6, s);
			
			int b = x.executeUpdate();
			System.out.println("ROWS AFFECTED ARE "+b);
			JOptionPane.showMessageDialog(f, "STUDENT ADDED !!!");
			new Registration();
			f.dispose();
			x.close();	
			c.close();
			
			
		}
		catch(SQLException e1) 
		{
			System.out.println(e1);
		}
				
			}
		
		});
	
		
		
		
		
	}
}

		
		
							
	
					
	
	

