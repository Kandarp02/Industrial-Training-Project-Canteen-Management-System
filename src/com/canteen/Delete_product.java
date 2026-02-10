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
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Delete_product {

	private JPanel contentPane;
	private JTextField t1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delete_product frame = new Delete_product();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Delete_product() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 1042, 626);
		f.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 5));

		f.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("      CANTEEN MANAGEMENT SYSTEM");
		label.setBounds(226, 48, 618, 48);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 35));
		label.setBorder(new LineBorder(Color.WHITE, 3));
		label.setBackground(Color.CYAN);
		contentPane.add(label);
		
		JLabel lblEnterTheId_1 = new JLabel("Enter the ID of the package whose record is to deleted :");
		lblEnterTheId_1.setForeground(Color.WHITE);
		lblEnterTheId_1.setFont(new Font("Cambria", Font.BOLD, 28));
		lblEnterTheId_1.setBackground(Color.CYAN);
		lblEnterTheId_1.setBounds(169, 159, 714, 50);
		contentPane.add(lblEnterTheId_1);
		
		t1 = new JTextField();
		t1.setFont(new Font("Cambria", Font.PLAIN, 25));
		t1.setColumns(10);
		t1.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		t1.setBounds(374, 240, 255, 39);
		contentPane.add(t1);
		
		JButton b1 = new JButton("DELETE");
		b1.setFont(new Font("Cambria", Font.BOLD, 25));
		b1.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		b1.setBounds(405, 394, 200, 48);
		contentPane.add(b1);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				    int t = 0; // Default value for t
				    Connection c = null;

				    String t1Text = t1.getText().trim(); // Get text and remove leading/trailing spaces

				    if (t1Text.isEmpty()) {
				        JOptionPane.showMessageDialog(f, "Please fill the required Product_id");
				        new pro_choices();
				        f.dispose();
				        return; // Exit the method to avoid executing the rest of the code
				    }

				    try {
				        t = Integer.parseInt(t1Text); // Parse the integer value from the text field
				    } catch (NumberFormatException ex) {
				        JOptionPane.showMessageDialog(f, "Invalid Product_id, Please enter a valid integer value.");
				        new pro_choices();
				        f.dispose();
				        return; // Exit the method as parsing failed
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
				        String sql = "SELECT PRODUCT_ID FROM product";
				        PreparedStatement selectStatement = c.prepareStatement(sql);
				        ResultSet resultSet = selectStatement.executeQuery();

				        boolean found = false;
				        while (resultSet.next()) {
				            int id = resultSet.getInt("PRODUCT_ID");

				            if (id == t) {
				                PreparedStatement deleteStatement = c.prepareStatement("DELETE FROM product WHERE PRODUCT_ID = ?");
				                deleteStatement.setInt(1, id);
				                int rowsAffected = deleteStatement.executeUpdate();
				                System.out.println("ROWS AFFECTED ARE " + rowsAffected);
				                JOptionPane.showMessageDialog(f, "Product data with ID " + id + " Deleted successfully.");
				                found = true;
				                deleteStatement.close();
				                new pro_choices();
				                f.dispose();
				                break;
				            }
				        }

				        if (!found) {
				            JOptionPane.showMessageDialog(f, "Product ID not found.");
				            t1.setText("");
				           
				          
				        }

				    } catch (SQLException e1) {
				        System.out.println("Error executing the query: " + e1.getMessage());
				    }
				}

			
		});
		
		
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
		back.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		back.setBackground(Color.BLACK);
		back.setBounds(807, 497, 188, 54);
		contentPane.add(back);
	}

}
