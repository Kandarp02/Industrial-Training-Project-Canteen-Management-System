package com.canteen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Choices{

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Choices frame = new Choices();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Choices() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 1080, 685);
		f.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new LineBorder(Color.BLACK, 5));

		f.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton b1 = new JButton("Student Registration");
		b1.setForeground(Color.BLACK);
		b1.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Registration obj = new Registration();
				f.dispose();
			}
		});
		b1.setFont(new Font("Cambria", Font.BOLD, 25));
		b1.setBackground(Color.WHITE);
		b1.setBounds(64, 287, 283, 60);
		contentPane.add(b1);
		
		JButton b2 = new JButton("Packages");
		b2.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p_choices obj = new p_choices();
				f.dispose();
			}
		});
		b2.setFont(new Font("Cambria", Font.BOLD, 25));
		b2.setBackground(Color.WHITE);
		b2.setBounds(396, 287, 283, 60);
		contentPane.add(b2);
		
		JButton b3 = new JButton("Product");
		b3.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new pro_choices();
				f.dispose();
			}
		});
		b3.setFont(new Font("Cambria", Font.BOLD, 25));
		b3.setBackground(Color.WHITE);
		b3.setBounds(724, 287, 283, 60);
		contentPane.add(b3);
		
		JLabel label = new JLabel("      CANTEEN MANAGEMENT SYSTEM");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 35));
		label.setBorder(new LineBorder(Color.WHITE, 3));
		label.setBackground(Color.BLACK);
		label.setBounds(219, 111, 628, 60);
		contentPane.add(label);
		
		JButton btnNewButton = new JButton("<< GO BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login obj = new Login();
				f.dispose();
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setFont(new Font("Cambria", Font.BOLD, 25));
		btnNewButton.setBorder(new LineBorder(Color.BLACK, 2));
		btnNewButton.setBounds(434, 451, 191, 60);
		contentPane.add(btnNewButton);
	}

}
