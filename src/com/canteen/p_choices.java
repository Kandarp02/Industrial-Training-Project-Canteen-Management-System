package com.canteen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class p_choices{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					p_choices frame = new p_choices();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public p_choices() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 1017, 673);
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
		label.setBounds(186, 100, 628, 60);
		contentPane.add(label);
		
		JButton b1 = new JButton("Add Packages");
		b1.setForeground(Color.BLACK);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Add_Package obj = new Add_Package();
				f.dispose();
			}
		});
		b1.setFont(new Font("Cambria", Font.BOLD, 25));
		b1.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		b1.setBackground(Color.WHITE);
		b1.setBounds(63, 254, 283, 60);
		contentPane.add(b1);
		
		JButton b3 = new JButton("Update Packages");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new UpdatePackage();
				f.dispose();
			}
		});
		b3.setFont(new Font("Cambria", Font.BOLD, 25));
		b3.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		b3.setBackground(Color.WHITE);
		b3.setBounds(63, 386, 283, 60);
		contentPane.add(b3);
		
		JButton b2 = new JButton("Delete Packages");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Delete_package();
				f.dispose();
			}
		});
		b2.setFont(new Font("Cambria", Font.BOLD, 25));
		b2.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		b2.setBackground(Color.WHITE);
		b2.setBounds(598, 386, 283, 60);
		contentPane.add(b2);
		
		JButton b4 = new JButton("View Packages");
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowPackageee obj = new ShowPackageee();
				f.dispose();
				
				
			}
		});
		b4.setFont(new Font("Cambria", Font.BOLD, 25));
		b4.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		b4.setBackground(Color.WHITE);
		b4.setBounds(598, 254, 283, 60);
		contentPane.add(b4);
		
		JButton back = new JButton("<< GO BACK");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Choices obj = new Choices();
				f.dispose();
			}
		});
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Cambria", Font.BOLD, 25));
		back.setBorderPainted(false);
		back.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		back.setBackground(Color.BLACK);
		back.setBounds(363, 506, 188, 54);
		contentPane.add(back);
	}
}
