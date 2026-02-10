package com.canteen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.awt.event.ActionEvent;

public class Add_product  {

	private JPanel contentPane;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_product frame = new Add_product();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Add_product() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 983, 652);
		f.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new LineBorder(Color.WHITE, 10));

		f.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("      CANTEEN MANAGEMENT SYSTEM");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 35));
		label.setBorder(new LineBorder(Color.WHITE, 3));
		label.setBackground(Color.BLACK);
		label.setBounds(178, 59, 628, 60);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("Product Name                     :");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 25));
		lblNewLabel.setBounds(28, 278, 320, 37);
		contentPane.add(lblNewLabel);
		
		JLabel lblProductId = new JLabel("Product ID (Numerical)   :");
		lblProductId.setForeground(Color.WHITE);
		lblProductId.setFont(new Font("Cambria", Font.BOLD, 25));
		lblProductId.setBounds(28, 198, 344, 37);
		contentPane.add(lblProductId);
		
		JLabel lblProductCost = new JLabel("Product Cost                       :");
		lblProductCost.setForeground(Color.WHITE);
		lblProductCost.setFont(new Font("Cambria", Font.BOLD, 25));
		lblProductCost.setBounds(28, 361, 330, 37);
		contentPane.add(lblProductCost);
		
		t1 = new JTextField();
		t1.setFont(new Font("Cambria", Font.PLAIN, 25));
		t1.setColumns(10);
		t1.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		t1.setBounds(370, 197, 493, 38);
		contentPane.add(t1);
		
		t2 = new JTextField();
		t2.setFont(new Font("Cambria", Font.PLAIN, 25));
		t2.setColumns(10);
		t2.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		t2.setBounds(370, 277, 493, 38);
		contentPane.add(t2);
		
		t3 = new JTextField();
		t3.setFont(new Font("Cambria", Font.PLAIN, 25));
		t3.setColumns(10);
		t3.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		t3.setBounds(368, 360, 493, 38);
		contentPane.add(t3);
		
		JButton b1 = new JButton("SUBMIT");
		b1.setForeground(Color.BLACK);
		b1.setFont(new Font("Cambria", Font.BOLD, 25));
		b1.setBorderPainted(false);
		b1.setBorder(new LineBorder(Color.BLACK, 3, true));
		b1.setBackground(Color.GREEN);
		b1.setBounds(356, 460, 199, 54);
		contentPane.add(b1);
		
		JButton back = new JButton("<< GO BACK");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new pro_choices();
				f.dispose();
			}
		});
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Cambria", Font.BOLD, 25));
		back.setBorderPainted(false);
		back.setBorder(new LineBorder(Color.BLACK, 2));
		back.setBackground(Color.BLACK);
		back.setBounds(731, 517, 193, 54);
		contentPane.add(back);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Connection c = null;
				PreparedStatement p = null;
				
				int id = Integer.parseInt(t1.getText());
				String name  = t2.getText();
				int cost = Integer.parseInt(t3.getText());
				
				try {
					 
					 
					c = DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen","DATABASE NAME ","PASSWORD");
					if(c!=null) 
					{
						System.out.println("DATABASE CONNECTED ");
					}
					else 
					{
						System.out.println("CONNECTION FAILED");
					}
				
			PreparedStatement x = c.prepareStatement("insert into product(PRODUCT_ID, PRODUCT_NAME, PRODUCT_COST )VALUES (?,?,?)");
			
			x.setInt(1, id);
			x.setString(2, name);
			x.setInt(3, cost);
			
			
			int b = x.executeUpdate();
			System.out.println("ROWS AFFECTED ARE "+b);
			JOptionPane.showMessageDialog(f, "PRODUCT ADDED !!!");
			new pro_choices();
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
