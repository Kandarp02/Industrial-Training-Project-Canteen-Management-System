package com.canteen;

import java.awt.EventQueue;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Update_stud {

	private JPanel contentPane;
	private JTextField t1;
	String new_name = null;
	String new_id = null;
	
	String new_mob = null;
	String new_email = null;
	String new_pack = null;
	String new_dob = null;
	Connection c = null;
	PreparedStatement p = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update_stud frame = new Update_stud();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Update_stud() {
		
		
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
		f.setBounds(100, 100, 1076, 684);
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
		label.setBounds(248, 105, 603, 48);
		contentPane.add(label);
		
		JLabel lblEnterTheStudent = new JLabel("Enter the student Id whose data is to be deleted : ");
		lblEnterTheStudent.setForeground(Color.WHITE);
		lblEnterTheStudent.setFont(new Font("Cambria", Font.BOLD, 30));
		lblEnterTheStudent.setBounds(29, 240, 689, 39);
		contentPane.add(lblEnterTheStudent);
		
		t1 = new JTextField();
		
		t1.setFont(new Font("Cambria", Font.BOLD, 25));
		t1.setColumns(10);
		t1.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		t1.setBounds(739, 242, 247, 38);
		contentPane.add(t1);
		
		ButtonGroup bg = new ButtonGroup();
		
		JLabel lblPleaseSelectThe_1 = new JLabel("Please select the appropriate option to update the student data :");
		lblPleaseSelectThe_1.setForeground(Color.WHITE);
		lblPleaseSelectThe_1.setFont(new Font("Cambria", Font.BOLD, 30));
		lblPleaseSelectThe_1.setBounds(29, 327, 910, 39);
		contentPane.add(lblPleaseSelectThe_1);
	
		
		JRadioButton id = new JRadioButton("ID");
		id.setBackground(Color.BLACK);
		id.setForeground(Color.WHITE);
		id.setFont(new Font("Cambria", Font.BOLD, 20));
		id.setBorder(new LineBorder(Color.BLACK, 3));
		id.setBounds(62, 402, 88, 31);
		contentPane.add(id);
		bg.add(id);
		id.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (t1.getText().isEmpty()) {
				    JOptionPane.showMessageDialog(f, "Please fill the required details");
				} else {
				    if (id.isSelected()) {
				        String new_id = JOptionPane.showInputDialog(f, "ENTER THE NEW ID TO BE UPDATED : ");

				        // Check if the new_id is not null and not empty, and is a valid integer
				        if (new_id != null && !new_id.trim().isEmpty() && new_id.matches("\\d+")) {
				            int idd = Integer.parseInt(new_id);
				            try {
				                p = c.prepareStatement("UPDATE stud_reg SET id = ? WHERE id = ?");

				                p.setInt(1, idd);
				                p.setInt(2, Integer.parseInt(t1.getText()));

				                int rowsAffected = p.executeUpdate();
				                System.out.println("ROWS AFFECTED ARE " + rowsAffected);
				                JOptionPane.showMessageDialog(f, "Student ID updated from " + t1.getText() + " to " + idd);
				                f.dispose();
				                new Registration();
				            } catch (SQLException e1) {
				                System.out.println("ERROR IN UPDATING THE DATA: " + e1.getMessage());
				            } catch (NumberFormatException e2) {
				                System.out.println("Invalid ID format: " + e2.getMessage());
				            }
				        } else {
				            JOptionPane.showMessageDialog(f, "Invalid student ID, Please enter a valid numeric value ");
				        }
				    }
				}

			    }
			
			});

	
		
		
		JRadioButton name = new JRadioButton("NAME ");
		name.setForeground(Color.WHITE);
		name.setBackground(Color.BLACK);
		name.setFont(new Font("Cambria", Font.BOLD, 20));
		name.setBorder(new LineBorder(Color.BLACK, 3));
		name.setBounds(249, 402, 101, 31);
		contentPane.add(name);
		bg.add(name);
		name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			

				if (t1.getText().isEmpty()) {
				    JOptionPane.showMessageDialog(f, "Please fill the required details");
				} else {
				    if (name.isSelected()) {
				        String new_name = JOptionPane.showInputDialog(f, "ENTER THE NEW NAME TO BE UPDATED : ");

				        // Check if the new_name is not null and not empty (trimming leading/trailing spaces)
				        if (new_name != null && !new_name.trim().isEmpty()) {
				            // Use regular expression to check if the input contains only characters (no digits)
				            Pattern pattern = Pattern.compile("^[a-zA-Z]+$");
				            Matcher matcher = pattern.matcher(new_name);
				            if (matcher.matches()) {
				                try {
				                    p = c.prepareStatement("UPDATE stud_reg SET Name = ? WHERE id = ?");

				                    p.setString(1, new_name);
				                    p.setInt(2, Integer.parseInt(t1.getText()));

				                    int rowsAffected = p.executeUpdate();
				                    System.out.println("ROWS AFFECTED ARE " + rowsAffected);
				                    JOptionPane.showMessageDialog(f, "Name of the student with ID : " + t1.getText() + ", updated to " + new_name);
				                   
				                    new Registration();
				                    f.dispose();
				                    p.close();
				                    c.close();
				                } catch (SQLException e1) {
				                    System.out.println("ERROR IN UPDATING THE DATA: " + e1.getMessage());
				                } catch (NumberFormatException e2) {
				                    System.out.println("Invalid ID format: " + e2.getMessage());
				                }
				            } else {
				                JOptionPane.showMessageDialog(f, "Invalid name, Please enter a valid name containing only characters");
				            }
				        } else {
				            JOptionPane.showMessageDialog(f, "Invalid name, Please enter a non-empty name");
				        }
				    }
				}

			}
		});
		
		
		JRadioButton mob = new JRadioButton("MOBILE NUMBER");
		mob.setBackground(Color.BLACK);
		mob.setForeground(Color.WHITE);
		mob.setFont(new Font("Cambria", Font.BOLD, 20));
		mob.setBorder(new LineBorder(Color.BLACK, 3));
		mob.setBounds(427, 402, 184, 31);
		contentPane.add(mob);
		bg.add(mob);
		mob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (t1.getText().isEmpty()) {
				    JOptionPane.showMessageDialog(f, "Please fill the required details");
				} else {
				    if (mob.isSelected()) {
				        String new_mob = JOptionPane.showInputDialog(f, "ENTER THE NEW MOBILE NUMBER TO BE UPDATED : ");

				        // Check if the new_mob is not null, not empty, and is a 10-digit number
				        if (new_mob != null && new_mob.matches("\\d{10}")) {
				            try {
				                long mobileNumber = Long.parseLong(new_mob);

				                p = c.prepareStatement("UPDATE stud_reg SET Mobile_Number = ? WHERE id = ?");
				                p.setLong(1, mobileNumber);
				                p.setInt(2, Integer.parseInt(t1.getText()));

				                int rowsAffected = p.executeUpdate();
				                System.out.println("ROWS AFFECTED ARE " + rowsAffected);
				                JOptionPane.showMessageDialog(f, "Mobile number of the student with ID : " + t1.getText() + " has been updated to " + mobileNumber);
				                new Registration();
				                f.dispose();
				                p.close();
				                c.close();
				            } catch (SQLException e1) {
				                System.out.println("ERROR IN UPDATING THE DATA: " + e1.getMessage());
				            } catch (NumberFormatException e2) {
				                System.out.println("Invalid ID format: " + e2.getMessage());
				            }
				        } else {
				            JOptionPane.showMessageDialog(f, "Invalid mobile number, Please enter a 10-digit numeric value.");
				        }
				    }
				}

			}

		});
		
		
		JRadioButton email = new JRadioButton("EMAIL");
		email.setForeground(Color.WHITE);
		email.setBackground(Color.BLACK);
		email.setFont(new Font("Cambria", Font.BOLD, 20));
		email.setBorder(new LineBorder(Color.BLACK, 3));
		email.setBounds(680, 402, 101, 31);
		contentPane.add(email);
		bg.add(email);
		email.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (t1.getText().isEmpty()) {
				    JOptionPane.showMessageDialog(f, "Please fill the required details");
				} else {
				    if (email.isSelected()) {
				        String new_email = JOptionPane.showInputDialog(f, "ENTER THE NEW EMAIL_ID TO BE UPDATED : ");

				        // Check if the new_email is not null and not empty (trimming leading/trailing spaces)
				        if (new_email != null && !new_email.trim().isEmpty()) {
				           
				                try {
				                    p = c.prepareStatement("UPDATE stud_reg SET Email_id = ? WHERE id = ?");

				                    p.setString(1, new_email);
				                    p.setInt(2, Integer.parseInt(t1.getText()));

				                    int rowsAffected = p.executeUpdate();
				                    System.out.println("ROWS AFFECTED ARE " + rowsAffected);
				                    JOptionPane.showMessageDialog(f, "Email_Id of the student with ID : " + t1.getText() + ", updated to " + new_email);
				                    f.dispose();
				                    new Registration();
				                    p.close();
				                    c.close();
				                } catch (SQLException e1) {
				                    System.out.println("ERROR IN UPDATING THE DATA: " + e1.getMessage());
				                } catch (NumberFormatException e2) {
				                    System.out.println("Invalid ID format: " + e2.getMessage());
				                }
				            } 
				        } else {
				            JOptionPane.showMessageDialog(f, "Invalid email, Please enter a non-empty email address.");
				        }
				    }
				}



			
		});
		
		
		
		JRadioButton dob = new JRadioButton("DOB");
		dob.setBackground(Color.BLACK);
		dob.setForeground(Color.WHITE);
		dob.setFont(new Font("Cambria", Font.BOLD, 20));
		dob.setBorder(new LineBorder(Color.BLACK, 3));
		dob.setBounds(853, 402, 101, 31);
		contentPane.add(dob);
		bg.add(dob);
		dob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  if (t1.getText().equals("")) {
				        JOptionPane.showMessageDialog(f, "Please fill the required details");
				    } else {
				        if (dob.isSelected()) {
				            new_dob = JOptionPane.showInputDialog(f, "ENTER THE NEW DOB TO BE UPDATED : ");
				            
				            try {
				                p = c.prepareStatement("UPDATE stud_reg SET DOB = ? WHERE id = ?");

				                p.setString(1, new_dob);
				                p.setInt(2, Integer.parseInt(t1.getText()));

				                int b = p.executeUpdate();
				                System.out.println("ROWS AFFECTED ARE " + b);
				                JOptionPane.showMessageDialog(f, "DOB of the student with ID : "+t1.getText()+", updated to "  + new_dob);
				                f.dispose();
				                new Registration();
				                p.close();
				                c.close();
				            } catch (SQLException e1) {
				                System.out.println("ERROR IN UPDATING THE DATA: " + e1.getMessage());
				            } catch (NumberFormatException e2) {
				                System.out.println("Invalid ID format: " + e2.getMessage());
				            }
				        }
				    }
			}
		});
		
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
		back.setBounds(824, 552, 188, 54);
		contentPane.add(back);
	}
}
