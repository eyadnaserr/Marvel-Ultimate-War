package view;

import java.awt.*;

import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import model.world.*;
public class chooseChampions extends JPanel implements ActionListener,ItemListener{

	private GameConsole controller ;
	private JLabel p1 = new JLabel("Please select your own champions :");
	private JLabel note = new JLabel("P.S,select 3 Champions Maximum");
	private JLabel p2 = new JLabel("Please select your own champions :");
	private JComboBox c1;
	private JComboBox c2;
	private JComboBox c3;
	private JComboBox c4;
	private JComboBox c5;
	private JComboBox c6;
    private JTextArea detailsP1=new JTextArea();
    private JTextArea detailsP2=new JTextArea();
	
	
	

   
    private JButton next = new JButton(" NEXT ");
	
    private JLabel back = new JLabel("");

	public chooseChampions(GameConsole controller) {
		this.setLayout(null);
		this.controller=controller;
		
		this.setVisible(true);
		
		p1.setBounds(150,50,400,200);
		p1.setForeground(Color.WHITE);
		p1.setFont(new Font("Serif",Font.PLAIN,20));
		this.add(p1);
		
		c1=new JComboBox(this.controller.getChampionsName());
		c1.setBounds(250,200,190,70);
		c1.setForeground(Color.RED);
		c1.addItemListener(this);
		this.add(c1);
		
		c2=new JComboBox(this.controller.getChampionsName());
		c2.setBounds(250,300,190,70);
		c2.setForeground(Color.RED);
		c2.addItemListener(this);
		this.add(c2);
		
		c3=new JComboBox(this.controller.getChampionsName());
		c3.setBounds(250,400,190,70);
		c3.setForeground(Color.RED);
		c3.addItemListener(this);
		this.add(c3);
		
		p2.setBounds(950,50,400,200);
		p2.setForeground(Color.WHITE);
		p2.setFont(new Font("Serif",Font.PLAIN,20));
		this.add(p2);
		
		c4=new JComboBox(this.controller.getChampionsName());
		c4.setBounds(1050,200,190,70);
		c4.setForeground(Color.BLUE);
		c4.addItemListener(this);
		this.add(c4);
		
		c5=new JComboBox(this.controller.getChampionsName());
		c5.setBounds(1050,300,190,70);
		c5.setForeground(Color.BLUE);
		c5.addItemListener(this);
		this.add(c5);
		
		c6=new JComboBox(this.controller.getChampionsName());
		c6.setBounds(1050,400,190,70);
		c6.setForeground(Color.BLUE);
		c6.addItemListener(this);
		this.add(c6);
		
		next.setBounds(650,630,150,50);
		next.setForeground(Color.GRAY);
		next.setFont(new Font ("TimesRoman", Font.BOLD, 25));
		next.addActionListener(this);
		this.add(next);
		
		detailsP1.setBounds(170,500,200,250);
		detailsP1.setEditable(false);
		detailsP1.setForeground(Color.RED);
		detailsP1.setFont(new Font("Serif",Font.PLAIN,15));
		this.add(detailsP1);
		
		detailsP2.setBounds(1050,500,200,250);
		detailsP2.setEditable(false);
		detailsP2.setForeground(Color.BLUE);
		detailsP2.setFont(new Font("Serif",Font.PLAIN,15));
		this.add(detailsP2);
		
		ImageIcon background= new ImageIcon(getClass().getResource("vs2.jpg"));
		back=new JLabel(background);
		back.setBounds(0,0,1500,1000);
		this.add(back);
		
	}




	@Override
	public void itemStateChanged(ItemEvent e) {
		Object[]champs=controller.getChampions();
		if(e.getSource()==c1) {
			detailsP1.setText(controller.getChampionDetails((Champion)champs[c1.getSelectedIndex()]));
		}
		if(e.getSource()==c2) {
			detailsP1.setText(controller.getChampionDetails((Champion)champs[c2.getSelectedIndex()]));
		}
		if(e.getSource()==c3) {
			detailsP1.setText(controller.getChampionDetails((Champion)champs[c3.getSelectedIndex()]));
		}
		if(e.getSource()==c4) {
			detailsP2.setText(controller.getChampionDetails((Champion)champs[c4.getSelectedIndex()]));
		}
		if(e.getSource()==c5) {
			detailsP2.setText(controller.getChampionDetails((Champion)champs[c5.getSelectedIndex()]));
		}
		if(e.getSource()==c6) {
			detailsP2.setText(controller.getChampionDetails((Champion)champs[c6.getSelectedIndex()]));
		}
		
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		
			if (e.getSource()==next && c1.getSelectedIndex()==0 || c2.getSelectedIndex()==0 || c3.getSelectedIndex()==0)
				JOptionPane.showMessageDialog(this, "Player1 must select 3 Champions" ,"Error", JOptionPane.ERROR_MESSAGE);
			else if (e.getSource()==next && c3.getSelectedIndex()==0 || c4.getSelectedIndex()==0 || c5.getSelectedIndex()==0)
				JOptionPane.showMessageDialog(this, "Player2 must select 3 Champions" ,"Error", JOptionPane.ERROR_MESSAGE);
			else if (c1.getSelectedIndex()==c2.getSelectedIndex() || c1.getSelectedIndex()==c3.getSelectedIndex() || c1.getSelectedIndex()==c4.getSelectedIndex() ||c1.getSelectedIndex()==c5.getSelectedIndex() ||c1.getSelectedIndex()==c6.getSelectedIndex() ||
					 c2.getSelectedIndex()==c3.getSelectedIndex() || c2.getSelectedIndex()==c4.getSelectedIndex() || c2.getSelectedIndex()==c5.getSelectedIndex() ||c2.getSelectedIndex()==c6.getSelectedIndex() || 
					 c3.getSelectedIndex()==c4.getSelectedIndex() || c3.getSelectedIndex()==c5.getSelectedIndex() || c3.getSelectedIndex()==c6.getSelectedIndex() ||
					 c4.getSelectedIndex()==c5.getSelectedIndex() || c4.getSelectedIndex()==c6.getSelectedIndex() ||
					 c5.getSelectedIndex()==c6.getSelectedIndex())
				
				JOptionPane.showMessageDialog(this, "MUST select different Champions" ,"Error", JOptionPane.ERROR_MESSAGE);
			else if (e.getSource()==next) {
				controller.remove(this);
				this.validate();
				this.repaint();
				Object[]champs=controller.getChampions();
				controller.selectingLeader((Champion)champs[c1.getSelectedIndex()],(Champion)champs[c2.getSelectedIndex()],(Champion)champs[c3.getSelectedIndex()],(Champion)champs[c4.getSelectedIndex()],(Champion)champs[c5.getSelectedIndex()],(Champion)champs[c6.getSelectedIndex()]);
			}
		
	}
	
	
	
	
	
	
	
}
