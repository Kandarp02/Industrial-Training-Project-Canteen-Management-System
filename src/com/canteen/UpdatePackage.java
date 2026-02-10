package com.canteen;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class UpdatePackage {

	private JPanel contentPane;
	private JTextField t1;
	Connection c = null;
	PreparedStatement p = null;
	String new_id = null;
	String new_items = null;
	String new_cost = null;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdatePackage frame = new UpdatePackage();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdatePackage() {
		

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
		f.setBounds(100, 100, 1077, 661);
		f.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 5));

		f.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("      CANTEEN MANAGEMENT SYSTEM");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 35));
		label.setBorder(new LineBorder(Color.WHITE, 3));
		label.setBackground(Color.BLACK);
		label.setBounds(214, 74, 628, 60);
		contentPane.add(label);
		
		JLabel lblEnterTheId = new JLabel("Enter the ID of package to be updated :");
		lblEnterTheId.setForeground(Color.WHITE);
		lblEnterTheId.setFont(new Font("Cambria", Font.BOLD, 30));
		lblEnterTheId.setBounds(29, 258, 549, 39);
		contentPane.add(lblEnterTheId);
		
		t1 = new JTextField();
		t1.setFont(new Font("Cambria", Font.BOLD, 25));
		t1.setColumns(10);
		t1.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		t1.setBounds(599, 260, 247, 38);
		contentPane.add(t1);
		
		JRadioButton id = new JRadioButton("ID");
		id.setBackground(Color.BLACK);
		id.setForeground(Color.WHITE);
		id.setBorder(new LineBorder(Color.BLACK, 3));
		id.setFont(new Font("Cambria", Font.BOLD, 20));
		id.setBounds(156, 414, 111, 31);
		contentPane.add(id);
		id.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (id.isSelected()) {
				    if (t1.getText().equals("")) {
				        JOptionPane.showMessageDialog(f, "Please fill the required details");
				    } else {
				        if (id.isSelected()) {
				            String input = JOptionPane.showInputDialog(f, "ENTER THE NEW ID OF THE PACKAGE TO BE UPDATED: ");

				            // Check if the input is not null and represents a valid integer
				            if (input != null && input.matches("^\\d+$")) {
				                int new_id = Integer.parseInt(input);
				                try {
				                    p = c.prepareStatement("UPDATE package SET ID = ? WHERE id = ?");
				                    p.setInt(1, new_id);
				                    p.setInt(2, Integer.parseInt(t1.getText()));

				                    int b = p.executeUpdate();
				                    System.out.println("ROWS AFFECTED ARE " + b);
				                    JOptionPane.showMessageDialog(f, "ID of the package with initial ID: " + t1.getText() + ", updated to " + new_id);
				                    f.dispose();
				                    new p_choices();
				                    p.close();
				                    c.close();
				                } catch (SQLException e1) {
				                    System.out.println("ERROR IN UPDATING THE DATA: " + e1.getMessage());
				                } catch (NumberFormatException e2) {
				                    System.out.println("Invalid ID format: " + e2.getMessage());
				                }
				            } else {
				                JOptionPane.showMessageDialog(f, "Please Enter a valid integer value for the new ID ");
				            }
				        }
				    }
				}

			}
		});
		
		
		JLabel lblPleaseSelectThe = new JLabel("Please select the appropriate option to update the package :");
		lblPleaseSelectThe.setForeground(Color.WHITE);
		lblPleaseSelectThe.setFont(new Font("Cambria", Font.BOLD, 30));
		lblPleaseSelectThe.setBounds(29, 350, 866, 39);
		contentPane.add(lblPleaseSelectThe);
		
		JRadioButton cost = new JRadioButton("COST");
		cost.setForeground(Color.WHITE);
		cost.setBackground(Color.BLACK);
		cost.setBorder(new LineBorder(Color.BLACK, 3));
		cost.setFont(new Font("Cambria", Font.BOLD, 20));
		cost.setBounds(397, 414, 126, 31);
		contentPane.add(cost);
		cost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cost.isSelected()) {
				    if (t1.getText().equals("")) {
				        JOptionPane.showMessageDialog(f, "Please fill the required details");
				    } else {
				        if (cost.isSelected()) {
				            String input = JOptionPane.showInputDialog(f, "ENTER THE NEW COST OF THE PACKAGE TO BE UPDATED: ");

				            // Check if the input is not null and represents a valid integer
				            if (input != null && input.matches("^\\d+$")) {
				                int new_cost = Integer.parseInt(input);
				                try {
				                    p = c.prepareStatement("UPDATE package SET COST = ? WHERE id = ?");
				                    p.setInt(1, new_cost);
				                    p.setInt(2, Integer.parseInt(t1.getText()));

				                    int b = p.executeUpdate();
				                    System.out.println("ROWS AFFECTED ARE " + b);
				                    JOptionPane.showMessageDialog(f, "COST of the package with initial ID: " + t1.getText() + ", updated to " + new_cost);
				                    f.dispose();
				                    new p_choices();
				                    p.close();
				                    c.close();
				                } catch (SQLException e1) {
				                    System.out.println("ERROR IN UPDATING THE DATA: " + e1.getMessage());
				                } catch (NumberFormatException e2) {
				                    System.out.println("Invalid cost format: " + e2.getMessage());
				                }
				            } else {
				                JOptionPane.showMessageDialog(f, "Please Enter a valid integer value for the new cost ");
				            }
				        }
				    }
				}

				
			}
		});
		
		
		JRadioButton items = new JRadioButton("ITEMS");
		items.setBackground(Color.BLACK);
		items.setForeground(Color.WHITE);
		items.setBorder(new LineBorder(Color.BLACK, 3));
		items.setFont(new Font("Cambria", Font.BOLD, 20));
		items.setBounds(656, 414, 126, 31);
		contentPane.add(items);
		items.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(items.isSelected()) {
					if (t1.getText().equals("")) {
					    JOptionPane.showMessageDialog(f, "Please fill the required details");
					} else {
					    if (items.isSelected()) {
					        String new_items = JOptionPane.showInputDialog(f, "ENTER THE NEW ITEMS TO BE UPDATED/ADDED IN THE PACKAGE: ");

					        // Check if the input is not null and contains only characters (letters)
					        if (new_items != null && new_items.matches("^[a-zA-Z]+$")) {
					            try {
					                p = c.prepareStatement("UPDATE package SET ITEMS = ? WHERE id = ?");
					                p.setString(1, new_items);
					                p.setInt(2, Integer.parseInt(t1.getText()));

					                int b = p.executeUpdate();
					                System.out.println("ROWS AFFECTED ARE " + b);
					                JOptionPane.showMessageDialog(f, "Items of the package with initial ID: " + t1.getText() + ", updated to " + new_items);
					                f.dispose();
					                new p_choices();
					                p.close();
					                c.close();
					            } catch (SQLException e1) {
					                System.out.println("ERROR IN UPDATING THE DATA: " + e1.getMessage());
					            } catch (NumberFormatException e2) {
					                System.out.println("Invalid ID format: " + e2.getMessage());
					            }
					        } else {
					            JOptionPane.showMessageDialog(f, "Please enter a valid input containing only characters for the new items.");
					        }
					    }
					}
					
				}
			}
		});
		
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(id);
		bg.add(cost);
		bg.add(items);
		
		JButton back = new JButton("<< GO BACK");
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Cambria", Font.BOLD, 25));
		back.setBorderPainted(false);
		back.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		back.setBackground(Color.BLACK);
		back.setBounds(844, 533, 188, 54);
		contentPane.add(back);
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new p_choices();
				f.dispose();
			}
		});
		
	}
}
