package com.canteen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import javax.swing.border.EtchedBorder;



public class Login{

	private JPanel contentPane;
	private JTextField t1;
	private JPasswordField p1;
	private JLabel lblCanteenManagementSystem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 1051, 735);
		f.setVisible(true);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new LineBorder(Color.WHITE, 10));

		f.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel l2 = new JLabel(" USER_NAME   :");
		l2.setForeground(Color.WHITE);
		l2.setFont(new Font("Cambria", Font.BOLD, 28));
		l2.setBounds(188, 409, 262, 59);
		contentPane.add(l2);
		
		JLabel l3 = new JLabel(" PASSWORD   : ");
		l3.setForeground(Color.WHITE);
		l3.setFont(new Font("Cambria", Font.BOLD, 28));
		l3.setBounds(188, 499, 262, 59);
		contentPane.add(l3);
		
		p1 = new JPasswordField();
		p1.setForeground(Color.BLACK);
		p1.setBorder(new LineBorder(Color.WHITE, 2));
		p1.setFont(new Font("Cambria", Font.PLAIN, 25));
		p1.setBounds(440, 509, 399, 41);
		contentPane.add(p1);
		
		t1 = new JTextField();
		t1.setBackground(Color.WHITE);
		t1.setForeground(Color.BLACK);
		t1.setBorder(new LineBorder(Color.WHITE, 2, true));
		t1.setFont(new Font("Cambria", Font.PLAIN, 25));
		t1.setBounds(440, 419, 399, 41);
		contentPane.add(t1);
		t1.setColumns(10);
		
		JButton b1 = new JButton("SIGN IN ");
		b1.setBorderPainted(false);
		b1.setForeground(Color.BLACK);
		b1.setBackground(Color.GREEN);
		b1.setBorder(new LineBorder(Color.WHITE, 3));
		b1.setFont(new Font("Cambria", Font.BOLD, 25));
		b1.setBounds(418, 594, 177, 59);
		contentPane.add(b1);
		
		lblCanteenManagementSystem = new JLabel("      CANTEEN MANAGEMENT SYSTEM");
		lblCanteenManagementSystem.setForeground(Color.WHITE);
		lblCanteenManagementSystem.setBackground(Color.BLACK);
		lblCanteenManagementSystem.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 35));
		lblCanteenManagementSystem.setBorder(new LineBorder(Color.WHITE, 3));
		lblCanteenManagementSystem.setBounds(188, 59, 631, 46);
		contentPane.add(lblCanteenManagementSystem);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/com/canteen/images/canteenlogo2.gif")));
		lblNewLabel.setBounds(130, 135, 754, 251);
		contentPane.add(lblNewLabel);
		
		JLabel lblDesignedBy = new JLabel("Designed By : Kandarp Patil ");
		lblDesignedBy.setForeground(Color.WHITE);
		lblDesignedBy.setFont(new Font("Cambria", Font.BOLD, 20));
		lblDesignedBy.setBounds(753, 617, 262, 59);
		contentPane.add(lblDesignedBy);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection c = null;
				PreparedStatement p = null;
				ResultSet s = null;
				String idd,passs;
				 String id=null,pass=null;
				 
				 String pp = new String(p1.getPassword());
				 
				 if(t1.getText().equals("") || pp.equals("")) {
					 JOptionPane.showMessageDialog(f,"Please fill the required Details","Message",JOptionPane.INFORMATION_MESSAGE);
					 
				 }
				 
				 else {
				try {
				c = DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen","DATABASE NAME ","PASSWORD");
				 
				if(c!=null) {
					System.out.println("DATABASE CONNECTED");
				}
				else {
					System.out.println("CONNECTION FAILED");
				}
				
				
			}catch(SQLException e1) {
				System.out.println(e1);
			}
				
			try {	
					p = c.prepareStatement("select username, password FROM login ");
				s = p.executeQuery();
				   while (s.next()) {
		                id = s.getString("username");
		                pass = s.getString("password");
		            }
				   
		        } catch (SQLException e2) {
		            System.out.println("Error executing the query: " + e2.getMessage());
		        }
			finally {
	            try {
	                if (s != null) {
	                    s.close();
	                }
	                if (p != null) {
	                    p.close();
	                }
	                if (c != null) {
	                    c.close();
	                }
	            } catch (SQLException e3) {
	                System.out.println("Error closing the resources: " + e3.getMessage());
	            }
			}
			
			     idd = t1.getText();
				 passs = new String(p1.getPassword());
				if(id.equals(idd) && pass.equals(passs)) {
			
					JOptionPane.showMessageDialog(f,"Login Successfull !!!!","LOGIN_FORM",JOptionPane.INFORMATION_MESSAGE);
					Choices obj = new Choices();
					f.dispose();
					
				}
				else {
					
					JOptionPane.showMessageDialog(f,"Invalid Details","LOGIN_FORM",JOptionPane.WARNING_MESSAGE);
				}
			}
			}
		});
						
			}
	}
