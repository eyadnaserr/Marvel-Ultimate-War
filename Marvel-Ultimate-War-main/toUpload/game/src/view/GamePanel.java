package view;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import model.abilities.Ability;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.Effect;
import model.world.*;
import engine.*;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;
public class GamePanel extends JPanel implements KeyListener,ActionListener{
	private GameConsole controller;

	private JPanel panel1= new JPanel();
    private JButton[][]board;
     
    private JLabel pl1;
    private JLabel pl2;
    private JLabel l1=new JLabel();
    private JLabel l2=new JLabel();
    
   
    private JTextArea currentChampion=new JTextArea();
    private JScrollPane s1;
    private JTextArea infoPanel=new JTextArea();
    private JScrollPane s2;
    
    private JButton move=new JButton("Move");
    private boolean m=false;
    private JButton endturn= new JButton("End Turn");
	private JButton useLeaderability = new JButton ("Leader Ability");
	private JButton castability = new JButton ("Ability");
	private JButton attack = new JButton ("Attack");
	private boolean a=false;
	


	 public  GamePanel(GameConsole controller)	{
		   this.controller = controller;
		   this.setLayout(null);
		   
		   board= new JButton[5][5];
		   
		   pl1=new JLabel(""+controller.getGame().getFirstPlayer().getName());
		   pl1.setForeground(Color.RED);
		   pl1.setBounds(700, 60, 100, 60);
		   pl1.setFont(new Font("Serif",Font.ITALIC,20));
		   this.add(pl1);
		   
		   
		   l1.setForeground(Color.RED);
		   l1.setBounds(750, 110, 250, 60);
		   l1.setFont(new Font("Serif",Font.ITALIC,12));
		   this.add(l1);
		   
		   pl2=new JLabel(""+controller.getGame().getSecondPlayer().getName());
		   pl2.setForeground(Color.BLUE);
		   pl2.setBounds(700, 680, 100, 60);
		   pl2.setFont(new Font("Serif",Font.ITALIC,20));
		   this.add(pl2);
		   
		  
		   l2.setForeground(Color.BLUE);
		   l2.setBounds(750, 720, 250, 60);
		   l2.setFont(new Font("Serif",Font.ITALIC,12));
		   this.add(l2);
		   
		   updateLeaderUsed();
		   
		   panel1.setLayout(new GridLayout(5,5));
		   panel1.setBounds(480,160,500,500);
		   this.add(panel1);
		  
		   for(int i=0;i<5;i++) {
			   for(int j=0;j<5;j++) {
				   board[i][j]= new JButton();
				   board[i][j].setFont(new Font("Serif",Font.PLAIN,11));
				   board[i][j].addActionListener(this);
				   board[i][j].addKeyListener(this);
				   panel1.add(board[i][j]);
			   }
			  
		    }
		   updateBoard();
		   
		   
		    
		    
		    currentChampion.setPreferredSize(new Dimension(1000,500));
		    this.setCurrentChampionInfo();
		    currentChampion.setEditable(false);
		    currentChampion.setFont(new Font("Serif",Font.PLAIN,12));
			this.add(currentChampion);
		    currentChampion.setVisible(true);
		    
		    s1=new JScrollPane(currentChampion);
		    s1.setBounds(1000,500,370,250);
		    s1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		    s1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		    this.add(s1);
		    
		    
		    infoPanel.setPreferredSize(new Dimension(1000,20));
		    String info=" INSTRUCTIONS:"+"\n"+"\n"+
	                "   1-To move your Champion Just tap on the Move button then "+"\n"+
	                "     the champion you play with then your desired movement "+"\n"+
	    		    "     from keyboard."+"\n"+
	                "   2-To attack Just tap on the Attack button then the champion"+"\n"+
	                "     you play with then your desired "+"\n"+
	                "     movement from keyboard."+"\n"+
	                "   3-To End Turn of Current Champion just tap on End Turn button."+"\n"+
	                "   4-To use your Leader Ability make sure that the current "+"\n"+
	                "     champion is your leader and just tap Leader Ability button";
			infoPanel.setText(info);
		    
		    infoPanel.setEditable(false);
		    infoPanel.setFont(new Font("Serif",Font.PLAIN,13));
			this.add(infoPanel);
		    infoPanel.setVisible(true);
		    
		    s2=new JScrollPane(infoPanel);
		    s2.setBounds(1000,20,400,160);
		    s2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		    s2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		    this.add(s2);
		   
		

		  
		    move.setBounds(1000,200,100,40);
			move.setForeground(Color.GRAY);
			move.setFont(new Font ("Helvetica", Font.BOLD, 18));
			move.addActionListener(this);
			this.add(move);
			
			endturn.setBounds(1000,300,100,40);
			endturn.setForeground(Color.GRAY);
			endturn.setFont(new Font ("Helvetica", Font.BOLD, 18));
			endturn.addActionListener(this);
			this.add(endturn);
		  
			useLeaderability.setBounds(1080,400,160,40);
			useLeaderability.setForeground(Color.GRAY);
			useLeaderability.setFont(new Font ("Helvetica", Font.BOLD, 18));
			useLeaderability.addActionListener(this);
			this.add(useLeaderability);
			
			castability.setBounds(1200,200,100,40);
			castability.setForeground(Color.GRAY);
			castability.setFont(new Font ("Helvetica", Font.BOLD, 18));
			castability.addActionListener(this);
			this.add(castability);
			
			attack.setBounds(1200,300,100,40);
			attack.setForeground(Color.GRAY);
			attack.setFont(new Font ("Helvetica", Font.BOLD, 18));
			attack.addActionListener(this);
			this.add(attack);
		
	}

   public void actionPerformed(ActionEvent e) {
		if(e.getSource()==move)
			m=true;
		if(e.getSource()==endturn) {
		    controller.getGame().endTurn();
		    setCurrentChampionInfo();
			updateBoard();
		}
		if(e.getSource()==useLeaderability)
			try {
				controller.getGame().useLeaderAbility();
				setCurrentChampionInfo();
				updateLeaderUsed();
				updateBoard();
			} catch (LeaderNotCurrentException | LeaderAbilityAlreadyUsedException e1) {
				
				JOptionPane.showMessageDialog(this,((Throwable) e1).getMessage(),"Error",JOptionPane.ERROR_MESSAGE );
			}
		if(e.getSource()==attack) {
			a=true;
			setCurrentChampionInfo();
			updateBoard();
		}
		
	}

	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {

		 if(e.getKeyCode()==KeyEvent.VK_LEFT ) {
			 if(m) {
					try {
						this.controller.getGame().move(Direction.LEFT);
						
						m=false;
					} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
						
						JOptionPane.showMessageDialog(this,((Throwable) e1).getMessage(),"Error",JOptionPane.ERROR_MESSAGE );
					} 
					updateBoard();
				}
				     else if(a) {
				    	 try {
							this.controller.getGame().attack(Direction.LEFT);
						} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
							
							JOptionPane.showMessageDialog(this,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE );
						}
				     }
					 this.setCurrentChampionInfo();
					 updateBoard();
			}
			if(e.getKeyCode()==KeyEvent.VK_UP ) {
				 if(m) {
						try {
							this.controller.getGame().move(Direction.UP);
							
							m=false;
						} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
							
							JOptionPane.showMessageDialog(this,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE );
						} 
						updateBoard();
					}
					     else if(a) {
					    	 try {
								this.controller.getGame().attack(Direction.UP);
							} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
								
								JOptionPane.showMessageDialog(this,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE );
							}
					    	 updateBoard();
					     }
						 this.setCurrentChampionInfo();
						 updateBoard();
			} 
		    if(e.getKeyCode()==KeyEvent.VK_RIGHT ) {
		    	 if(m) {
						try {
							this.controller.getGame().move(Direction.RIGHT);
							
							m=false;
						} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
							
							JOptionPane.showMessageDialog(this,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE );
						} 
					}
					     else if(a) {
					    	 try {
								this.controller.getGame().attack(Direction.RIGHT);
								a=false;
							} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
								
								JOptionPane.showMessageDialog(this,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE );
							}
					     }
						 this.setCurrentChampionInfo();
						 updateBoard();
			} 
			 if(e.getKeyCode()==KeyEvent.VK_DOWN ) {
				 if(m) {
				try {
					this.controller.getGame().move(Direction.DOWN);
					
					m=false;
				} catch (NotEnoughResourcesException | UnallowedMovementException e1) {
					
					JOptionPane.showMessageDialog(this,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE );
				} 
			}
			     else if(a) {
			    	 try {
						this.controller.getGame().attack(Direction.DOWN);
					} catch (NotEnoughResourcesException | ChampionDisarmedException | InvalidTargetException e1) {
						
						JOptionPane.showMessageDialog(this,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE );
					}
			     }
				 this.setCurrentChampionInfo();
				 updateBoard();
			 }
			 
			 
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void updateBoard() {
		
		
		 Object[][]boa = controller.getGame().getBoard();
		 
		   
		 for(int i=0;i<5;i++) {
			   for(int j=0;j<5;j++) {
				   
				   
				   if(boa[i][j]instanceof Champion) {
					   Champion c=(Champion)boa[i][j];
					   board[i][j].setText(c.getName());
					   if(controller.getGame().getFirstPlayer().getTeam().contains(c)) {
						   
						   board[i][j].setBackground(Color.WHITE);
						   board[i][j].setForeground(Color.RED);
						   
						   
					   }
					   else if(controller.getGame().getSecondPlayer().getTeam().contains(c)) {
						   board[i][j].setBackground(Color.WHITE);
						   board[i][j].setForeground(Color.BLUE);
						   
					   }
					   
				   }
				   else if(boa[i][j]instanceof Cover) {
					   Cover c=(Cover)boa[i][j];
					   board[i][j]= new JButton(c.getCurrentHP()+"");
					   board[i][j].setBackground(Color.WHITE);
					   board[i][j].setForeground(Color.BLACK);
						   
					   
				   }
				   else {
					   board[i][j].setBackground(Color.WHITE);
					   board[i][j].setForeground(Color.WHITE);
				   }
		   }
		   }
		   }
		
	
		   
	

	public void setCurrentChampionInfo() {
		Champion c = controller.getGame().getCurrentChampion();
		String s="  "+c.getName()+": "+"\n";
		if(c instanceof Hero) {
			s+="   -Hero, "+"\n";
		}
		else if(c instanceof AntiHero) {
			s+="   -AntiHero, "+"\n";
		}
		else if(c instanceof Villain) {
			s+="   -Villain, "+"\n";
		}
		s+="   -Current HP: "+c.getCurrentHP()+", "+"\n"+
		   "   -Available Mana: "+c.getMana()+", "+"\n"+
		   "   -Available Action points: "+c.getCurrentActionPoints()+", "+"\n"+
		   "   -Abilities: "+"\n";
		for(int i=0;i<c.getAbilities().size();i++) {
			Ability a = c.getAbilities().get(i);
			int ie=i+1;
			s+="    "+ie+"-"+getAbilityInfo(a)+", "+"\n";
		}
		if(c.getAppliedEffects().size()==0)
			s+="   -No applied Effects, "+"\n";
		else {
		s+="   -Effects: "+"\n";	
		for(int i=0;i<c.getAppliedEffects().size();i++) {
			Effect e= c.getAppliedEffects().get(i);
			int ie=i+1;
			s+="    "+ie+"-"+getEffectInfo(e)+", "+"\n";
		}
		}
		s+="   -Attack Damage: "+c.getAttackDamage()+", "+"\n"+
		   "   -Attack Range: "+c.getAttackRange();
			
		
		currentChampion.setText(s);
		if(controller.getGame().getFirstPlayer().getTeam().contains(controller.getGame().getCurrentChampion())) {
			 currentChampion.setForeground(Color.RED);
		}
		else if(controller.getGame().getSecondPlayer().getTeam().contains(controller.getGame().getCurrentChampion())) {
			 currentChampion.setForeground(Color.BLUE);
		}
		
	}

	public static String getAbilityInfo(Ability a) {
		String s="" + a.getName()+":";
		
		s+=" Area of Effect: "+a.getCastArea()+", Cast Range: "+a.getCastRange()+","+ 
		   " Mana Cost: "+a.getManaCost()+", Action Costs: "+a.getRequiredActionPoints()+","+
		   " Current cooldowns: "+a.getCurrentCooldown()+ ", Base cooldowns: "+a.getBaseCooldown()+","+"\n"+
		   "       Type:";
		if (a instanceof CrowdControlAbility) {
			 s+=" Crowd Control Ability, Crowd Control Effect: "+((CrowdControlAbility) a).getEffect().getName();
		}
		if (a instanceof DamagingAbility) {
			 s+=" Damaging Ability, Damaging Amount: "+((DamagingAbility) a).getDamageAmount();
		}
		if (a instanceof HealingAbility) {
			 s+=" Healing Ability, Heal Amount: "+((HealingAbility) a).getHealAmount();
		}
		return s;
	}

	public String getRemainingChampsInfo(Champion c) {
		String s =c.getName()+ ": "+"\n" +
		           " Attack Damage: "+c.getAttackDamage()+"\n"+
				   " Attack Range: "+c.getAttackRange()+"\n"+
		           " Health Points: "+c.getCurrentHP()+"\n"+
				   " Available Mana: "+c.getMana()+"\n"+
				   " Max actions/turn: "+c.getMaxActionPointsPerTurn()+"\n"+
				   " Speed: "+c.getSpeed();
		if(c instanceof Hero) {
			s+=" Hero, "+"\n";
		}
		else if(c instanceof AntiHero) {
			s+=" AntiHero, "+"\n";
		}
		else if(c instanceof Villain) {
			s+=" Villain, "+"\n";
		}
		if(controller.getGame().getFirstPlayer().getLeader()==c||controller.getGame().getSecondPlayer().getLeader()==c)
			s+="Leader";
		else
			s+="Member";
	
        s+=" Effects: "+"\n";
		for(int i=0;i<c.getAppliedEffects().size();i++) {
			Effect e= c.getAppliedEffects().get(i);
			int ie=i+1;
			s+="  "+ie+"-"+getEffectInfo(e)+", "+"\n";
		}
		return s;

		}
	

	public static String getEffectInfo(Effect e) {
		String s = "Effect name: "+e.getName()+" Duration: "+e.getDuration();
		return s;
	}

	public void updateLeaderUsed() {
		String u1="";
		String u2="";
		if(controller.getGame().isFirstLeaderAbilityUsed())
			u1="Leader Ability of "+""+controller.getGame().getFirstPlayer().getName()+" is already used";
		else if(!controller.getGame().isFirstLeaderAbilityUsed())
			u1="Leader Ability of "+""+controller.getGame().getFirstPlayer().getName()+" is not used";
		if(controller.getGame().isSecondLeaderAbilityUsed())
			u2="Leader Ability of "+""+controller.getGame().getSecondPlayer().getName()+" is already used";
		else if(!controller.getGame().isSecondLeaderAbilityUsed())
			u2="Leader Ability of "+""+controller.getGame().getSecondPlayer().getName()+" is not used";
		l1.setText(u1);
		l2.setText(u2);
	}

	}
