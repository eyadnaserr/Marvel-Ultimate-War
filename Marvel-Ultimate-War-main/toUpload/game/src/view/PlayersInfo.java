package view;

import javax.swing.ImageIcon;


import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;

public class PlayersInfo extends JPanel implements ActionListener {
	
	private GameConsole controller;
	private JLabel l1 = new JLabel("Player1 name : ");
	private JLabel l2 = new JLabel("Player2 name : ");
	private JTextField t1 = new JTextField();
	private JTextField t2 = new JTextField();
	private JButton start = new JButton(" START ") ;
	
	private JLabel back = new JLabel("");

	public PlayersInfo(GameConsole controller) throws IOException {
		
		this.controller=controller;
		this.setLayout(null);
		
		
		
		l1.setBounds(150,25,100,30);
		l1.setForeground(Color.WHITE);
		this.add(l1);
		
		
		t1.setBounds(140,80,130,30);
		t1.setFont(new Font ("SansSerif", Font.ITALIC, 15));
		t1.setForeground(Color.RED);
		this.add(t1);
		
		l2.setBounds(450,25,100,30);
		l2.setForeground(Color.WHITE);
		this.add(l2);
		
		
		t2.setBounds(440,80,130,30);
		t2.setFont(new Font ("SansSerif", Font.ITALIC, 15));
		t2.setForeground(Color.BLUE);
		this.add(t2);
		
		start.setBounds(320,200,100,40);
		start.setForeground(Color.GRAY);
		start.setFont(new Font ("Helvetica", Font.BOLD, 18));
		start.addActionListener(this);
		this.add(start);
		
		ImageIcon background= new ImageIcon(getClass().getResource("image1.jpeg"));
		back=new JLabel(background);
		back.setBounds(0,0,750,300);
		this.add(back);
		
		
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==start && t1.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Player1 must enter a name" ,"Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (e.getSource()==start && t2.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Player2 must enter a name" ,"Error", JOptionPane.ERROR_MESSAGE);
		}
		else if(e.getSource()==start) {
			controller.remove(this);
			controller.selectingChampions(t1.getText(),t2.getText());
			
		}
		

		
	}

	
	
}

