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

public class Delete_stud {

	private JPanel contentPane;
	private JTextField t1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delete_stud frame = new Delete_stud();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Delete_stud() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 1149, 671);
		f.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new LineBorder(Color.BLACK, 5));

		f.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("      CANTEEN MANAGEMENT SYSTEM");
		label.setForeground(Color.WHITE);
		label.setBounds(264, 76, 603, 48);
		label.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 35));
		label.setBorder(new LineBorder(Color.WHITE, 3));
		label.setBackground(Color.BLACK);
		contentPane.add(label);
		
		JLabel lblEnterTheId = new JLabel("Enter the ID of the student whose record is to deleted :");
		lblEnterTheId.setForeground(Color.WHITE);
		lblEnterTheId.setFont(new Font("Cambria", Font.BOLD, 28));
		lblEnterTheId.setBackground(Color.CYAN);
		lblEnterTheId.setBounds(228, 214, 714, 50);
		contentPane.add(lblEnterTheId);
		
		t1 = new JTextField();
		t1.setForeground(Color.BLACK);
		t1.setFont(new Font("Cambria", Font.PLAIN, 25));
		t1.setColumns(10);
		t1.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		t1.setBounds(430, 293, 253, 39);
		contentPane.add(t1);
		
		JButton b1 = new JButton("DELETE");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				    String t = t1.getText();
				    Connection c = null;

				    if (t == null || t.trim().isEmpty()) {
				        JOptionPane.showMessageDialog(f, "Please fill the required details");
				        return;
				    }

				    try {
				        c = DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen", "NAME", "PASSWORD");
				        if (c != null) {
				            System.out.println("DATABASE CONNECTED");
				        } else {
				            System.out.println("CONNECTION FAILED");
				        }
				    } catch (SQLException e1) {
				        System.out.println("Error connecting to the database: " + e1.getMessage());
				    }

				    try {
				        String sql = "SELECT id FROM stud_reg";
				        PreparedStatement selectStatement = c.prepareStatement(sql);
				        ResultSet resultSet = selectStatement.executeQuery();

				        boolean found = false;
				        while (resultSet.next()) {
				            int id = resultSet.getInt("id");

				            if (id == Integer.parseInt(t)) {
				                PreparedStatement deleteStatement = c.prepareStatement("DELETE FROM stud_reg WHERE ID = ?");
				                deleteStatement.setInt(1, id);
				                int rowsAffected = deleteStatement.executeUpdate();
				                System.out.println("ROWS AFFECTED ARE " + rowsAffected);
				                JOptionPane.showMessageDialog(f, "Student data with ID " + id + " deleted successfully.");
				                found = true;
				                deleteStatement.close();
				                new Registration();
				                f.dispose();
				                break;
				            }
				        }

				        if (!found) {
				            JOptionPane.showMessageDialog(f, "Student ID not found.");
				            new Registration();
				            f.dispose();
				        }

				    } catch (NumberFormatException e1) {
				        JOptionPane.showMessageDialog(f, "Invalid Student ID, Please enter a valid integer value.");
				    } catch (SQLException e1) {
				        System.out.println("Error executing the query: " + e1.getMessage());
				    } 
				}

					});

		b1.setFont(new Font("Cambria", Font.BOLD, 25));
		b1.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		b1.setBounds(462, 429, 207, 48);
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
		back.setBounds(890, 523, 188, 54);
		contentPane.add(back);
		
	}
}

