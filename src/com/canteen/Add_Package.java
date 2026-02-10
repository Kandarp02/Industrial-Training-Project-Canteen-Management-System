package com.canteen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JScrollBar;
import javax.swing.JEditorPane;

public class Add_Package {

	private JPanel contentPane;
	private JTextField t3;
	private JTextField t;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Package frame = new Add_Package();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Add_Package() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 1090, 747);
		f.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new LineBorder(new Color(255, 255, 255), 10));

		f.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("      CANTEEN MANAGEMENT SYSTEM");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 35));
		label.setBorder(new LineBorder(Color.WHITE, 3));
		label.setBackground(Color.BLACK);
		label.setBounds(220, 48, 628, 60);
		contentPane.add(label);
		
		JLabel l1 = new JLabel("Enter all the items which you need to add in the package");
		l1.setForeground(Color.WHITE);
		l1.setBackground(Color.CYAN);
		l1.setFont(new Font("Cambria", Font.BOLD, 28));
		l1.setBounds(156, 146, 739, 50);
		contentPane.add(l1);
		
		JLabel l3 = new JLabel("Enter the cost of the package per year    :");
		l3.setForeground(Color.WHITE);
		l3.setFont(new Font("Cambria", Font.BOLD, 28));
		l3.setBounds(110, 476, 539, 42);
		contentPane.add(l3);
		
		t3 = new JTextField();
		t3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char c = e.getKeyChar();
				
				if(Character.isLetter(c)) {
					t3.setEditable(false);
					JOptionPane.showMessageDialog(f,"The input can only be a digit");
				}
				else {
					t3.setEditable(true);
			}
			}
		});
		
		t3.setFont(new Font("Cambria", Font.PLAIN, 25));
		t3.setColumns(10);
		t3.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		t3.setBounds(668, 480, 227, 36);
		contentPane.add(t3);
		
		JButton b1 = new JButton("SAVE");
		b1.setBorder(new LineBorder(Color.BLACK, 2));
		b1.setBorderPainted(false);
		b1.setBackground(Color.GREEN);
		b1.setForeground(Color.BLACK);
		b1.setFont(new Font("Cambria", Font.BOLD, 25));
		b1.setBounds(419, 622, 188, 50);
		contentPane.add(b1);
		
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
		back.setBorder(new LineBorder(Color.WHITE, 2));
		back.setBackground(Color.BLACK);
		back.setBounds(860, 622, 181, 50);
		contentPane.add(back);
		
		JEditorPane t1 = new JEditorPane();
		t1.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		t1.setFont(new Font("Cambria", Font.BOLD, 20));
		t1.setBounds(272, 213, 456, 225);
		contentPane.add(t1);
		
		JLabel lblEnterTheId = new JLabel("Enter the ID of the package                           : ");
		lblEnterTheId.setForeground(Color.WHITE);
		lblEnterTheId.setFont(new Font("Cambria", Font.BOLD, 28));
		lblEnterTheId.setBounds(110, 543, 539, 42);
		contentPane.add(lblEnterTheId);
		
		t = new JTextField();
		t.setForeground(Color.BLACK);
		t.setFont(new Font("Cambria", Font.PLAIN, 25));
		t.setColumns(10);
		t.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		t.setBounds(668, 544, 227, 42);
		contentPane.add(t);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection c = null;
				int id = Integer.parseInt(t.getText());
				 String items= t1.getText();
				 long cost = Integer.parseInt(t3.getText());
				 long lines = 0;
				 
				 if(t1.getText().equals("") ||  t3.getText().equals("")) {
					 JOptionPane.showMessageDialog(f,"Please fill the required Details","Message",JOptionPane.INFORMATION_MESSAGE);
					 
				 }
				 else {
				
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
		
			PreparedStatement x = c.prepareStatement("insert into package(id,items,cost)VALUES (?,?,?)");
			
			x.setInt(1, id);
			x.setString(2,items);
			
			x.setLong(3,cost);
			//lines = t1.getLineCount();
			int b = x.executeUpdate();
			System.out.println("ROWS AFFECTED ARE "+b);
			JOptionPane.showMessageDialog(f, "Package created, Items added and saved");
			p_choices obj = new p_choices();
			f.dispose();
			x.close();	
			c.close();
			
			
		}
		catch(SQLException e1) 
		{
			System.out.println(e1);
		}
	

				
			}
		
		}
		});
		
		
	}
}
