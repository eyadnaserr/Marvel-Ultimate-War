package view;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import engine.Game;
import engine.Player;
import model.world.*;
public class GameConsole extends JFrame   {
	
	private PlayersInfo players;
	private Player p1 ;
	private Player p2;
	private Game game;
	private chooseChampions champioon;
	private chooseLeader leeader;
	private GamePanel gaame;
	
	
	public GameConsole() throws IOException {
		
		players=new PlayersInfo(this);
		this.getContentPane().add(players);
		
		this.setTitle("Marvel");
		this.setVisible(true);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(750,300);
		ImageIcon iconApp = new ImageIcon("appicon.jpeg");
		this.setIconImage(iconApp.getImage());
		
		
	}
	
	
	public void selectingChampions(String A, String B) { 
		
		

		 p1 = new Player(A);
		 p2 = new Player(B);
		 game = new Game(p1,p2);
		try {
			
			game.loadAbilities("Abilities.csv");
			game.loadChampions("Champions.csv");
			this.setSize(1500,1000);
			champioon=new chooseChampions(this);
			this.getContentPane().add(champioon);
		
			
			
		}catch (IOException q){
			q.printStackTrace();
			
		}
		
	}
	public Object [] getChampionsName() {
		Object[]champsName =new Object [16];
	    champsName[0]=("Select Champion");
		for(int i=0;i< this.game.getAvailableChampions().size();i++) {
			champsName[i+1]=this.game.getAvailableChampions().get(i).getName();
		}
		
		return champsName;
	}
	public Object [] getChampions() {
		Object[]champs=new Object [16];
	    champs[0]=("Select Champion");
		for(int i=0;i< this.game.getAvailableChampions().size();i++) {
			champs[i+1]=this.game.getAvailableChampions().get(i);
		}
		
		return champs;
	}
	
	public Game getGame() {
		return game;
	}
	public String getChampionDetails(Champion c ) {
		
		
		String r = "\n"+" Name : "+c.getName()+ "\n" +
		           " Attack Damage : "+c.getAttackDamage()+"\n"+
				   " Attack Range : "+c.getAttackRange()+"\n"+
		           " Health Points : "+c.getCurrentHP()+"\n"+
				   " Available Mana "+c.getMana()+"\n"+
		           " Abilities : "  +"\n";
		
		for (int i=0;i<c.getAbilities().size();i++)
			r+="   "+c.getAbilities().get(i).getName()+"\n";
		return r;
	}
	
	public void selectingLeader(Champion c1,Champion c2,Champion c3,Champion c4,Champion c5,Champion c6) {
		p1.getTeam().add(c1);
		p1.getTeam().add(c2);
		p1.getTeam().add(c3);
		p2.getTeam().add(c4);
		p2.getTeam().add(c5);
		p2.getTeam().add(c6);
		this.setSize(1500,1000);
		leeader=new chooseLeader(this);
		this.getContentPane().add(leeader);
		
		this.validate();
		this.repaint();
		
	}
	
	
   
	

	public void selectingGamePanel(Champion leaderP1, Champion leaderP2) {
		p1.setLeader(leaderP1);
		p2.setLeader(leaderP2);
		this.setSize(1500,1000);
		game = new Game(p1,p2);
		gaame = new GamePanel(this);
		this.getContentPane().add(gaame);
		
		this.validate();
		this.repaint();
	}

	
	
	public static void main(String[]args) throws IOException {
		GameConsole c= new GameConsole();
	}

	
 
}
