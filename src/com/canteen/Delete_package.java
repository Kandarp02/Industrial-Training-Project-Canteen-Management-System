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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Delete_package  {

	private JPanel contentPane;
	private JTextField t1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delete_package frame = new Delete_package();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Delete_package() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 1077, 662);
		f.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new LineBorder(Color.BLACK, 5));

		f.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("      CANTEEN MANAGEMENT SYSTEM");
		label.setForeground(Color.WHITE);
		label.setBounds(214, 72, 611, 48);
		label.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 35));
		label.setBorder(new LineBorder(Color.WHITE, 3));
		label.setBackground(Color.BLACK);
		contentPane.add(label);
		
		JLabel lblEnterTheId = new JLabel("Enter the ID of the package whose record is to deleted :");
		lblEnterTheId.setForeground(Color.WHITE);
		lblEnterTheId.setFont(new Font("Cambria", Font.BOLD, 28));
		lblEnterTheId.setBackground(Color.CYAN);
		lblEnterTheId.setBounds(182, 195, 714, 50);
		contentPane.add(lblEnterTheId);
		
		t1 = new JTextField();
		t1.setFont(new Font("Cambria", Font.PLAIN, 25));
		t1.setColumns(10);
		t1.setBorder(new LineBorder(new Color(0, 153, 204), 3, true));
		t1.setBounds(383, 266, 265, 39);
		contentPane.add(t1);
		
		JButton b1 = new JButton("DELETE");
		b1.setForeground(Color.BLACK);
		b1.setBackground(Color.WHITE);
		b1.setFont(new Font("Cambria", Font.BOLD, 25));
		b1.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		b1.setBounds(427, 407, 174, 48);
		contentPane.add(b1);
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String t = t1.getText();
				Connection c = null;
				
		try {
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen","NAME","PASSWORD");
			if(c!=null) {
				System.out.println("DATABASE CONNECTED ");
			}
			else {
				System.out.println("CONNECTION FAILED");
			}
		}catch(SQLException e1) {
			System.out.println("Error connection to the database ");
		}
		try {
		    String sql = "SELECT ID FROM package";
		    PreparedStatement selectStatement = c.prepareStatement(sql);
		    ResultSet resultSet = selectStatement.executeQuery();

		    boolean found = false;
		    while (resultSet.next()) {
		        int id = resultSet.getInt("ID");
		        String idString = String.valueOf(id);
		        
		        if (idString.equals(t1.getText())) {
		            PreparedStatement deleteStatement = c.prepareStatement("DELETE FROM package WHERE ID = ?");
		            deleteStatement.setInt(1, id);
		            int rowsAffected = deleteStatement.executeUpdate();
		            System.out.println("ROWS AFFECTED ARE " + rowsAffected);
		            JOptionPane.showMessageDialog(f, "Package data with ID " + id + " deleted successfully.");
		            found = true;
		            deleteStatement.close();
		            new p_choices();
		            f.dispose();
		            break;
		        }
		    }

		    if (!found) {
		        JOptionPane.showMessageDialog(f, "Package ID not found.");
		        new p_choices();
		        f.dispose();
		    }

		} catch (SQLException e1) {
		    System.out.println("Error executing the query: " + e1.getMessage());
		} 
		}
			
		});
		
		JButton back = new JButton("<< GO BACK");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new p_choices();
				f.dispose();
			}
		});
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Cambria", Font.BOLD, 25));
		back.setBorderPainted(false);
		back.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		back.setBackground(Color.BLACK);
		back.setBounds(821, 525, 188, 54);
		contentPane.add(back);
	}
}
