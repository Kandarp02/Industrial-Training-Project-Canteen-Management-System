package com.canteen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class pro_choices {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pro_choices frame = new pro_choices();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public pro_choices() {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(100, 100, 1028, 681);
		f.setVisible(true);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new LineBorder(Color.BLACK, 5));

		f.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAddProduct = new JButton("Add Product");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Add_product();
				f.dispose();
			}
		});
		btnAddProduct.setFont(new Font("Cambria", Font.BOLD, 25));
		btnAddProduct.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		btnAddProduct.setBackground(Color.WHITE);
		btnAddProduct.setBounds(93, 237, 283, 60);
		contentPane.add(btnAddProduct);
		
		JButton btnDeleteProduct = new JButton("View Product");
		btnDeleteProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new View_product();
				f.dispose();
			}
		});
		btnDeleteProduct.setFont(new Font("Cambria", Font.BOLD, 25));
		btnDeleteProduct.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		btnDeleteProduct.setBackground(Color.WHITE);
		btnDeleteProduct.setBounds(599, 382, 283, 60);
		contentPane.add(btnDeleteProduct);
		
		JButton btnUpdateProduct = new JButton("Update Product");
		btnUpdateProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Update_product();
				f.dispose();
			}
		});
		btnUpdateProduct.setFont(new Font("Cambria", Font.BOLD, 25));
		btnUpdateProduct.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		btnUpdateProduct.setBackground(Color.WHITE);
		btnUpdateProduct.setBounds(599, 237, 283, 60);
		contentPane.add(btnUpdateProduct);
		
		JLabel label = new JLabel("      CANTEEN MANAGEMENT SYSTEM");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 35));
		label.setBorder(new LineBorder(Color.WHITE, 3));
		label.setBackground(Color.BLACK);
		label.setBounds(183, 68, 628, 60);
		contentPane.add(label);
		
		JButton btnNewButton = new JButton("<< GO BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Choices();
				f.dispose();
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setFont(new Font("Cambria", Font.BOLD, 25));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setBorder(new LineBorder(Color.BLACK, 2));
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setBounds(381, 502, 191, 60);
		contentPane.add(btnNewButton);
		
		JButton btnDeleteProduct_1 = new JButton("Delete Product");
		btnDeleteProduct_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Delete_product();
				f.dispose();
			}
		});
		btnDeleteProduct_1.setFont(new Font("Cambria", Font.BOLD, 25));
		btnDeleteProduct_1.setBorder(new LineBorder(new Color(0, 153, 204), 3));
		btnDeleteProduct_1.setBackground(Color.WHITE);
		btnDeleteProduct_1.setBounds(93, 382, 283, 60);
		contentPane.add(btnDeleteProduct_1);
	}

}
