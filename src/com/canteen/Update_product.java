package com.canteen;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.regex.*;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Update_product  {

	private JPanel contentPane;
	private JTextField t1;
	Connection c = null;
	PreparedStatement p = null;
	String new_id =null;
	String new_name=null;
	String new_cost = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update_product frame = new Update_product();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Update_product() {

		try 
		{
			c = DriverManager.getConnection("jdbc:mysql://localhost:3306/canteen","NAME","PASSWORD");
					if(c!=null) 
					{
						System.out.println("DATABASE CONNECTED ");
					}
					else 
					{
						System.out.println("CONNECTION FAILED");
					}
		
		}
		catch(SQLException e) 
		{
			System.out.println("Error connection to the database");
		}
		
		
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 1075, 626);
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
		label.setBounds(216, 35, 628, 60);
		contentPane.add(label);
		
		t1 = new JTextField();
		t1.setFont(new Font("Cambria", Font.PLAIN, 25));
		t1.setColumns(10);
		t1.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		t1.setBounds(667, 183, 244, 38);
		contentPane.add(t1);
		
		JLabel lblPleaseSelectThe_1 = new JLabel("Please select the appropriate option to update the student data :");
		lblPleaseSelectThe_1.setForeground(Color.WHITE);
		lblPleaseSelectThe_1.setFont(new Font("Cambria", Font.BOLD, 30));
		lblPleaseSelectThe_1.setBounds(46, 282, 910, 39);
		contentPane.add(lblPleaseSelectThe_1);
		
		JLabel lblEnterTheId = new JLabel("Enter the ID of the product to be updated : ");
		lblEnterTheId.setForeground(Color.WHITE);
		lblEnterTheId.setFont(new Font("Cambria", Font.BOLD, 30));
		lblEnterTheId.setBounds(46, 181, 603, 39);
		contentPane.add(lblEnterTheId);
		
		JRadioButton product_id = new JRadioButton("PRODUCT_ID ");
		product_id.setBackground(Color.BLACK);
		product_id.setForeground(Color.WHITE);
		product_id.setFont(new Font("Cambria", Font.BOLD, 20));
		product_id.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		product_id.setBounds(96, 359, 161, 31);
		contentPane.add(product_id);
		ButtonGroup bg = new ButtonGroup();
		bg.add(product_id);
		
		
		product_id.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (t1.getText().isEmpty()) {
				    JOptionPane.showMessageDialog(f, "Please fill the required details");
				} else {
				    if (product_id.isSelected()) {
				        String new_id = JOptionPane.showInputDialog(f, "ENTER THE NEW ID TO BE UPDATED : ");

				        // Check if the new_id is not null and contains a valid integer value
				        if (new_id != null && new_id.matches("\\d+")) {
				            int idd = Integer.parseInt(new_id);
				            try {
				                p = c.prepareStatement("UPDATE product SET PRODUCT_ID = ? WHERE PRODUCT_ID = ?");

				                p.setInt(1, idd);
				                p.setInt(2, Integer.parseInt(t1.getText()));

				                int rowsAffected = p.executeUpdate();
				                System.out.println("ROWS AFFECTED ARE " + rowsAffected);
				                JOptionPane.showMessageDialog(f, "Product ID updated from " + t1.getText() + " to " + idd);
				                f.dispose();
				                new pro_choices();
				            } catch (SQLException e1) {
				                System.out.println("ERROR IN UPDATING THE DATA: " + e1.getMessage());
				            }
				        } else {
				            JOptionPane.showMessageDialog(f, "Invalid ID format, Please enter a valid integer value ");
				        }
				    }
				}

				
			}
		});
		
		
		JRadioButton product_name = new JRadioButton("PRODUCT_NAME");
		product_name.setBackground(Color.BLACK);
		product_name.setForeground(Color.WHITE);
		product_name.setFont(new Font("Cambria", Font.BOLD, 20));
		product_name.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		product_name.setBounds(387, 359, 187, 31);
		contentPane.add(product_name);
		bg.add(product_name);
				product_name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (t1.getText().isEmpty()) {
				    JOptionPane.showMessageDialog(f, "Please fill the required details");
				} else {
				    if (product_name.isSelected()) {
				        String new_name = "";

				        // Keep prompting until a non-empty string is provided
				        while (true) {
				            new_name = JOptionPane.showInputDialog(f, "ENTER THE NEW NAME OF THE PRODUCT TO BE UPDATED : ");

				            if (new_name == null) {
				                // User clicked cancel or closed the dialog
				                return; // exit the method or return to the previous state
				            }

				            // Check if the input contains only characters (letters and spaces)
				            if (new_name.matches("^[a-zA-Z ]+$")) {
				                break; // Break out of the loop if the input is valid
				            } else {
				                JOptionPane.showMessageDialog(f, "Invalid input. Please enter valid Name format ");
				            }
				        }



				        try {
				            p = c.prepareStatement("UPDATE product SET PRODUCT_NAME = ? WHERE PRODUCT_ID = ?");
				            p.setString(1, new_name);
				            p.setInt(2, Integer.parseInt(t1.getText()));

				            int rowsAffected = p.executeUpdate();
				            System.out.println("ROWS AFFECTED ARE " + rowsAffected);
				            JOptionPane.showMessageDialog(f, "Product Name with ID " + t1.getText() + " updated to " + new_name);
				            f.dispose();
				            new pro_choices();
				        } catch (SQLException e1) {
				            System.out.println("ERROR IN UPDATING THE DATA: " + e1.getMessage());
				        } catch (NumberFormatException e2) {
				            System.out.println("Invalid ID format: " + e2.getMessage());
				        }
				    }
				}

				
				
			}
		});
		
		
		JRadioButton product_cost = new JRadioButton("PRODUCT_ COST");
		product_cost.setBackground(Color.BLACK);
		product_cost.setForeground(Color.WHITE);
		product_cost.setFont(new Font("Cambria", Font.BOLD, 20));
		product_cost.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		product_cost.setBounds(683, 359, 187, 31);
		contentPane.add(product_cost);
		bg.add(product_cost);

		
		product_cost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (t1.getText().isEmpty()) {
				    JOptionPane.showMessageDialog(f, "Please fill the required details");
				} else {
				    if (product_cost.isSelected()) {
				        String new_cost = JOptionPane.showInputDialog(f, "ENTER THE NEW COST OF THE PRODUCT TO BE UPDATED : ");

				        // Check if the new_cost is not null and not empty, and is a valid integer
				        if (new_cost != null && !new_cost.trim().isEmpty() && new_cost.matches("\\d+")) {
				            int idd = Integer.parseInt(new_cost);
				            try {
				                p = c.prepareStatement("UPDATE product SET PRODUCT_COST = ? WHERE PRODUCT_ID = ?");

				                p.setInt(1, idd);
				                p.setInt(2, Integer.parseInt(t1.getText()));

				                int rowsAffected = p.executeUpdate();
				                System.out.println("ROWS AFFECTED ARE " + rowsAffected);
				                JOptionPane.showMessageDialog(f, "Product ID updated from " + t1.getText() + " to " + idd);
				                f.dispose();
				                new pro_choices();
				            } catch (SQLException e1) {
				                System.out.println("ERROR IN UPDATING THE DATA: " + e1.getMessage());
				            } catch (NumberFormatException e2) {
				                System.out.println("Invalid ID format: " + e2.getMessage());
				            }
				        } else {
				            JOptionPane.showMessageDialog(f, "Invalid cost, Please enter a valid numeric value ");
				        }
				    }
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
		back.setBounds(823, 502, 188, 54);
		contentPane.add(back);
	}
}
