package view;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import engine.*;
import model.world.*;

public class chooseLeader extends JPanel implements ActionListener{


	private GameConsole controller;
	private JLabel player1 ;
	private JLabel l1 = new JLabel ("Please select your own LEADER :");
	private JLabel player2 ;
	private JLabel l2 = new JLabel ("Please select your own LEADER :");
	private JLabel note ;
	private JRadioButton c1;
	private JRadioButton c2;
	private JRadioButton c3;
	private JRadioButton c4;
	private JRadioButton c5;
	private JRadioButton c6;
	
	private JButton play=new JButton(" PLAY ") ;
	private JLabel back = new JLabel("");
	
	public chooseLeader(GameConsole controller)  {
		this.controller=controller;
		this.setLayout(null);
		this.setVisible(true);
		
		player1 = new JLabel(this.controller.getGame().getFirstPlayer().getName()+"'s Team");
		player1.setBounds(200,50,400,200);
		player1.setForeground(Color.WHITE);
		player1.setFont(new Font("Serif",Font.PLAIN,20));
		this.add(player1);
		
		l1.setBounds(250,140,300,100);
		l1.setForeground(Color.WHITE);
		l1.setFont(new Font("Serif",Font.PLAIN,15));
		this.add(l1);
		
		
		c1=new JRadioButton(this.controller.getGame().getFirstPlayer().getTeam().get(0).getName());
		c1.setBounds(250,200,160,100);		
		c1.setForeground(Color.WHITE);
		c1.setSelected(true);
		this.add(c1);
		
		c2=new JRadioButton(this.controller.getGame().getFirstPlayer().getTeam().get(1).getName());
		c2.setBounds(250,300,160,100);
		c2.setForeground(Color.WHITE);
		this.add(c2);
		
		c3=new JRadioButton(this.controller.getGame().getFirstPlayer().getTeam().get(2).getName());
		c3.setBounds(250,400,160,100);
		c3.setForeground(Color.WHITE);
	    this.add(c3);
		
	    ButtonGroup b1=new ButtonGroup();
	    b1.add(c1);
	    b1.add(c2);
	    b1.add(c3);
	    
		player2 = new JLabel(this.controller.getGame().getSecondPlayer().getName()+"'s Team");
		player2.setBounds(1000,50,400,200);
		player2.setForeground(Color.WHITE);
		player2.setFont(new Font("Serif",Font.PLAIN,20));
		this.add(player2);
		
		l2.setBounds(1050,140,300,100);
		l2.setForeground(Color.WHITE);
		l2.setFont(new Font("Serif",Font.PLAIN,15));
		this.add(l2);
		
        c4=new JRadioButton(this.controller.getGame().getSecondPlayer().getTeam().get(0).getName());
        c4.setBounds(1050,200,160,100);
		c4.setForeground(Color.WHITE);
		c4.setSelected(true);
        this.add(c4);
        
		c5=new JRadioButton(this.controller.getGame().getSecondPlayer().getTeam().get(1).getName());
		c5.setBounds(1050,300,160,100);
		c5.setForeground(Color.WHITE);
		this.add(c5);
		
		c6=new JRadioButton(this.controller.getGame().getSecondPlayer().getTeam().get(2).getName());
		c6.setBounds(1050,400,160,100);
		c6.setForeground(Color.WHITE);
		this.add(c6);
		
		ButtonGroup b2=new ButtonGroup();
	    b2.add(c4);
	    b2.add(c5);
	    b2.add(c6);
		
		play.setBounds(650,630,150,50);
		play.setForeground(Color.GRAY);
		play.setFont(new Font ("Helvetica", Font.BOLD, 25));
		play.addActionListener(this);
		this.add(play);
		
		
		ImageIcon background= new ImageIcon(getClass().getResource("vs2.jpg"));
		back=new JLabel(background);
		back.setBounds(0,0,1500,1000);
		this.add(back);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Champion leaderP1=null;
		Champion leaderP2=null;
	
		if(e.getSource()==play) {
		if(c1.isSelected()) {
			leaderP1=getChamp(c1.getText());	
		}
		if(c2.isSelected()) {
			leaderP1=getChamp(c2.getText());	
		}
		if(c3.isSelected()) {
			leaderP1=getChamp(c3.getText());	
		}
		if(c4.isSelected()) {
			leaderP2=getChamp(c4.getText());
		}
		if(c5.isSelected()) {
			leaderP2=getChamp(c5.getText());	
		}
		if(c6.isSelected()) {
			leaderP2=getChamp(c6.getText());	
		}
		controller.remove(this);
		this.validate();
		this.repaint();
		controller.selectingGamePanel(leaderP1,leaderP2);
				}
	}
	public Champion getChamp(String s) {
		Object[]champs = controller.getChampions();
		Champion c=null;
		for(int i=1;i<champs.length;i++) {
			if(s.equals(((Champion)champs[i]).getName())) {
				c = ((Champion)champs[i]);
			}
		}
		
			return c;
		
	}

}