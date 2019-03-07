package model.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import exceptions.GameActionException;
import exceptions.InvalidPowerUseException;
import exceptions.OccupiedCellException;
import exceptions.UnallowedMovementException;
import exceptions.WrongTurnException;
import model.game.Bot;
import model.game.Direction;
import model.game.Game;
import model.game.Player;
import model.pieces.Piece;
import model.pieces.heroes.ActivatablePowerHero;
import model.pieces.heroes.Armored;
import model.pieces.heroes.Medic;
import model.pieces.heroes.Ranged;
import model.pieces.heroes.Speedster;
import model.pieces.heroes.Super;
import model.pieces.heroes.Tech;
import model.pieces.sidekicks.SideKick;

public class GameViewer extends JFrame implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Game game;
	JPanel main;
	JButtonPiece [] [] board;
	JPanel controls;
	JPanel data;
	JPanel dp1;
	JPanel dp2;
	JButton up;
	JButton upleft;
	JButton upright;
	JButton down;
	JButton downleft;
	JButton downright;
	JButton left;
	JButton right;
	JButton usepower;
	
	JPanel goer;
	Direction direction;
	Piece trigger;
	Piece target;
	Point newPos;
	boolean isPower;
	JButton go;
	JButton reset;
	
	JLabel triggerp;
	JLabel targetp;
	JLabel newPosp;
	JLabel isPowerp;
	JLabel directionp;

	JLabel Switchtext;
	
	JLabel cpl;
	
	JLabel triggerpic;
	JLabel targetpic;
	JLabel directionpic;
	JLabel powerpic;
	
	
	public GameViewer(Game game) {
		this.game=game;
		setResizable(false);
		setTitle("Super Hero Chess");
		setBounds(50,25,1260,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		getContentPane().setBackground(Color.white);
		this.setResizable(false);
		
	
		//1
		main =new JPanel();
		main.setLayout(new GridLayout(7, 6));
		main.setBackground(Color.black);
		board =new JButtonPiece [7] [6];
		
		main.setBounds(0, 0, 800,670 );
		main.setVisible(true);
		
		
		//2
		data=new JPanel();
		data.setBounds(900,0,380,140);
		data.setBorder(BorderFactory.createLineBorder(Color.cyan));
		getContentPane().add(data);
		data.setLayout(null);
		data.setBackground(Color.white);
		
		JLabel P1=new JLabel( game.getPlayer1().getName());
		JLabel P2=new JLabel( game.getPlayer2().getName());
		JLabel payp1=new JLabel();
		payp1.setBounds(265, 5,65 , 40);
		JLabel payp2= new JLabel();
		//payp1.setIcon(new ImageIcon("bat0 white.jpg")) ; validate();
		data.add(payp1);
		//payp2.setIcon(new ImageIcon("bat0 white.jpg"));
		data.add(payp2);
		payp2.setBounds(265, 45, 65, 40);
		switch(game.getPlayer1().getPayloadPos()) {
		case 0:payp1.setIcon(new ImageIcon("bat0 white.jpg")) ; validate() ;break;
		case 1:payp1.setIcon(new ImageIcon("bat1 white.jpg")) ; validate() ;break;
		case 2:payp1.setIcon(new ImageIcon("bat2 white.jpg")) ; validate() ;break;
		case 3:payp1.setIcon(new ImageIcon("bat3 white.jpg")) ; validate() ;break;
		case 4:payp1.setIcon(new ImageIcon("bat4.jpg")) ; validate() ;break;
		case 5:payp1.setIcon(new ImageIcon("bat5.jpg")) ; validate() ;break;
		case 6:payp1.setIcon(new ImageIcon("bat6 white.jpg")) ; validate() ;break;
		}
		switch(game.getPlayer2().getPayloadPos()) {
		case 0:payp2.setIcon(new ImageIcon("bat0 white.jpg")) ; validate() ;break;
		case 1:payp2.setIcon(new ImageIcon("bat1 white.jpg")) ; validate() ;break;
		case 2:payp2.setIcon(new ImageIcon("bat2 white.jpg")) ; validate() ;break;
		case 3:payp2.setIcon(new ImageIcon("bat3 white.jpg")) ; validate() ;break;
		case 4:payp2.setIcon(new ImageIcon("bat4.jpg")) ; validate() ;break;
		case 5:payp2.setIcon(new ImageIcon("bat5.jpg")) ; validate() ;break;
		case 6:payp2.setIcon(new ImageIcon("bat6 dark.jpg")) ; validate() ;break;
		}
		JLabel current=new JLabel("Current Player           "+game.getCurrentPlayer().getName());
		JLabel p1pic =new JLabel();
		p1pic.setIcon(new ImageIcon("player1.png"));
		p1pic.setBounds(5, 5, 200, 50);
		validate();
		JLabel p2pic =new JLabel();
		p2pic.setIcon(new ImageIcon("player2.png"));
		p2pic.setBounds(5, 50, 200, 50);
		validate();
		
		data.add(P1);
		data.add(p1pic);
		data.add(P2);
		data.add(p2pic);
		data.add(current);
		P1.setBounds(210,5, 50, 50);
		P2.setBounds(210, 40, 50,50 );
		current.setBounds(10, 90, 380, 50);
		
		
		controls=new JPanel();
		controls.setBounds(900,370, 360,300);
		getContentPane().add(controls);
		controls.setLayout(new GridLayout(3,3));
		controls.setBorder(BorderFactory.createLineBorder(Color.cyan));
		
		//3
		if(game.getCurrentPlayer()==game.getPlayer1()) {
			controls.setBackground(Color.white);

		
			up = new JButton();
			up.addMouseListener(this);
			up.setIcon(new ImageIcon("arrowUp white.png"));
			up.setBorder(BorderFactory.createEmptyBorder());
			validate();
			up.setBackground(Color.white);
			upleft = new JButton();
			upleft.setBackground(Color.WHITE);
			upleft.addMouseListener(this);
			upleft.setIcon(new ImageIcon("arrowUpleft white.png"));
			upleft.setBorder(BorderFactory.createEmptyBorder());
			validate();
			upleft.setBackground(Color.white);
			upright = new JButton();
			upright.addMouseListener(this);
			upright.setIcon(new ImageIcon("arrowUpright white.png"));
			upright.setBorder(BorderFactory.createEmptyBorder());
			validate();
			upright.setBackground(Color.white);
			down = new JButton();
			down.addMouseListener(this);
			down.setIcon(new ImageIcon("arrowDown white.png"));
			down.setBorder(BorderFactory.createEmptyBorder());
			validate();
			down.setBackground(Color.white);
			downleft = new JButton();
			downleft.addMouseListener(this);
			downleft.setIcon(new ImageIcon("arrowDownleft white.png"));
			downleft.setBorder(BorderFactory.createEmptyBorder());
			validate();
			downleft.setBackground(Color.white);
			downright = new JButton();
			downright.addMouseListener(this);
			downright.setIcon(new ImageIcon("arrowDownright white.png"));
			downright.setBorder(BorderFactory.createEmptyBorder());
			validate();
			downright.setBackground(Color.white);
			right = new JButton();
			right.addMouseListener(this);
			right.setIcon(new ImageIcon("arrowRight white.png"));
			right.setBorder(BorderFactory.createEmptyBorder());
			validate();
			right.setBackground(Color.white);
			left = new JButton();
			left.addMouseListener(this);
			left.setIcon(new ImageIcon("arrowLeft white.png"));
			left.setBorder(BorderFactory.createEmptyBorder());
			validate();
			left.setBackground(Color.white);
			usepower = new JButton();
			usepower.addMouseListener(this);
			usepower.setIcon(new ImageIcon("power white.png"));
			usepower.setBorder(BorderFactory.createEmptyBorder());
			validate();
			usepower.setBackground(Color.white);
		}
		else {
			controls.setBackground(Color.black);
			up = new JButton();
			up.addMouseListener(this);
			up.setIcon(new ImageIcon("arrowUp dark.png"));
			up.setBorder(BorderFactory.createEmptyBorder());
			validate();
			up.setBackground(Color.black);
			upleft = new JButton();
			upleft.setBackground(Color.black);
			upleft.addMouseListener(this);
			upleft.setIcon(new ImageIcon("arrowUpleft dark.png"));
			upleft.setBorder(BorderFactory.createEmptyBorder());
			validate();
			upleft.setBackground(Color.black);
			upright = new JButton();
			upright.addMouseListener(this);
			upright.setIcon(new ImageIcon("arrowUpright dark.png"));
			upright.setBorder(BorderFactory.createEmptyBorder());
			validate();
			upright.setBackground(Color.black);
			down = new JButton();
			down.addMouseListener(this);
			down.setIcon(new ImageIcon("arrowDown dark.png"));
			down.setBorder(BorderFactory.createEmptyBorder());
			validate();
			down.setBackground(Color.black);
			downleft = new JButton();
			downleft.addMouseListener(this);
			downleft.setIcon(new ImageIcon("arrowDownleft dark.png"));
			downleft.setBorder(BorderFactory.createEmptyBorder());
			validate();
			downleft.setBackground(Color.black);
			downright = new JButton();
			downright.addMouseListener(this);
			downright.setIcon(new ImageIcon("arrowDownright dark.png"));
			downright.setBorder(BorderFactory.createEmptyBorder());
			validate();
			downright.setBackground(Color.black);
			right = new JButton();
			right.addMouseListener(this);
			right.setIcon(new ImageIcon("arrowRight dark.png"));
			right.setBorder(BorderFactory.createEmptyBorder());
			validate();
			right.setBackground(Color.black);
			left = new JButton();
			left.addMouseListener(this);
			left.setIcon(new ImageIcon("arrowLeft dark.png"));
			left.setBorder(BorderFactory.createEmptyBorder());
			validate();
			left.setBackground(Color.black);
			usepower = new JButton();
			usepower.addMouseListener(this);
			usepower.setIcon(new ImageIcon("power dark.png"));
			usepower.setBorder(BorderFactory.createEmptyBorder());
			validate();
			usepower.setBackground(Color.black);
			

		}
		
		controls.add(upleft);
		controls.add(up);
		controls.add(upright);
		controls.add(left);
		controls.add(usepower);
		controls.add(right);
		controls.add(downleft);
		controls.add(down);
		controls.add(downright);
		
		
		
		//4
		dp2=new JPanel();
		dp2.setBounds(800, 0, 100, 340);
		dp2.setBackground(Color.black);
		dp2.setLayout(new GridLayout(0,2));
		dp2.setBorder(BorderFactory.createLineBorder(Color.cyan));

		getContentPane().add(dp2);
		ArrayList<Piece> dp2l=game.getPlayer2().getDeadCharacters();
		for(Piece cp:dp2l) {
			JButtonPiece btn =new JButtonPiece(null,cp);
			if(cp.getOwner()==game.getPlayer2()) {
				btn.setBackground(Color.black);
				if(cp instanceof Armored) {
					btn.setIcon(new ImageIcon("armor dark.png"));
					validate();
					}
					if(cp instanceof Medic) {
						btn.setIcon(new ImageIcon("syringe dark.jpg"));
						validate();
						}
				
					if(cp instanceof Super) {
						btn.setIcon(new ImageIcon("super dark.jpg"));
						validate();
						}
					if(cp instanceof Ranged) {
						btn.setIcon(new ImageIcon("ranged dark.jpg"));
						validate();
						}
					if(cp instanceof Tech) {
						btn.setIcon(new ImageIcon("tool dark.png"));
						validate();
						}
					if(cp instanceof Speedster) {
						btn.setIcon(new ImageIcon("speedster dark.png"));
						validate();
						}
					if(cp instanceof SideKick) {
						btn.setIcon(new ImageIcon("sidekick dark.jpg"));
						validate();
						}
					
			
				}
			btn.addMouseListener(this);
			dp2.add(btn);
		}
		
		//5
		dp1=new JPanel();
		dp1.setBounds(800, 340, 100, 340);
		dp1.setBackground(Color.white);
		dp1.setLayout(new GridLayout(0,2));
		dp1.setBorder(BorderFactory.createLineBorder(Color.cyan));

		getContentPane().add(dp1);
		ArrayList<Piece> dp1l=game.getPlayer1().getDeadCharacters();
		for(Piece cp:dp1l) {
			JButtonPiece btn =new JButtonPiece(null,cp);
			if(cp.getOwner()==game.getPlayer1()) {
				btn.setBackground(Color.white);
				if(cp instanceof Armored) {
				btn.setIcon(new ImageIcon("armor light.png"));
				validate();
				}
				if(cp instanceof Medic) {
					btn.setIcon(new ImageIcon("syringe light.jpg"));
					validate();
					}
			
				if(cp instanceof Super) {
					btn.setIcon(new ImageIcon("super light.jpg"));
					validate();
					}
				if(cp instanceof Ranged) {
					btn.setIcon(new ImageIcon("ranged light.png"));
					validate();
					}
				if(cp instanceof Tech) {
					btn.setIcon(new ImageIcon("tool light.png"));
					validate();
					}
				if(cp instanceof Speedster) {
					btn.setIcon(new ImageIcon("speedster light.png"));
					validate();
					}
				if(cp instanceof SideKick) {
					btn.setIcon(new ImageIcon("sidekick light.jpg"));
					validate();
					}
				
				
			}
			btn.addMouseListener(this);
			dp1.add(btn);
		}
		// 6 
		goer =new JPanel();
		goer.setLayout(null);
		goer.setBounds(900,140 , 360, 230);
		goer.setBackground(Color.black);
		goer.setBorder(BorderFactory.createLineBorder(Color.cyan));
		go=new JButton();
		reset=new JButton();
		go.setIcon(new ImageIcon("go.png"));
		go.setBackground(Color.black);
		validate();
		reset.setIcon(new ImageIcon("switch.png"));
		reset.setBackground(Color.black);
		Switchtext=new JLabel("Choosing Trigger Piece");
		Switchtext.setForeground(Color.MAGENTA);
		
		Switchtext.setBounds(30, 160, 150, 30);
		go.setBounds(195,190,130,30);
		reset.setBounds(30, 190, 130, 30);		
		reset.addMouseListener(this);
		go.addMouseListener(this);
		triggerp =new JLabel("Trigger: "); 
		triggerp.setFont(new Font("san-serif",  Font.BOLD , 15));
		targetp =new JLabel("Target: ");
		targetp.setFont(new Font("san-serif", Font.BOLD, 15));

		triggerpic=new JLabel();
		triggerpic.setBounds(90, 5, 55 ,55);
		if(trigger==null) {triggerpic.setIcon(null);}
		if(trigger!=null) {
		if(trigger.getOwner()==game.getPlayer1()) {
			triggerpic.setBackground(Color.black);
			if(trigger instanceof Armored) {
				triggerpic.setIcon(new ImageIcon("armor lightsmall.png"));
			validate();
			}
			if(trigger instanceof Medic) {
				triggerpic.setIcon(new ImageIcon("syringe lightsmall.jpg"));
				validate(); 
				}
		
			if(trigger instanceof Super) {
				triggerpic.setIcon(new ImageIcon("super lightsmall.jpg"));
				validate();
				}
			if(trigger instanceof Ranged) {
				triggerpic.setIcon(new ImageIcon("ranged lightsmall.png"));
				validate();
				}
			if(trigger instanceof Tech) {
				triggerpic.setIcon(new ImageIcon("tool lightsmall.png"));
				validate();
				}
			if(trigger instanceof Speedster) {
				triggerpic.setIcon(new ImageIcon("speedster lightsmall.png"));
				validate();
				}
			if(trigger instanceof SideKick) {
				triggerpic.setIcon(new ImageIcon("sidekick lightsmall.jpg"));
				validate();
				}
			
			
		}
		if(trigger.getOwner()==game.getPlayer2()) {
		triggerpic.setBackground(Color.black);
		if(trigger instanceof Armored) {
			triggerpic.setIcon(new ImageIcon("armor darksmall.png"));
			validate();
			}
			if(trigger instanceof Medic) {
				triggerpic.setIcon(new ImageIcon("syringe darksmall.jpg"));
				validate();
				}
		
			if(trigger instanceof Super) {
				triggerpic.setIcon(new ImageIcon("super darksmall.jpg"));
				validate();
				}
			if(trigger instanceof Ranged) {
				triggerpic.setIcon(new ImageIcon("ranged darksmall.jpg"));
				validate();
				}
			if(trigger instanceof Tech) {
				triggerpic.setIcon(new ImageIcon("tool darksmall.png"));
				validate();
				}
			if(trigger instanceof Speedster) {
				triggerpic.setIcon(new ImageIcon("speedster darksmall.png"));
				validate();
				}
			if(trigger instanceof SideKick) {
				triggerpic.setIcon(new ImageIcon("sidekick darksmall.jpg"));
				validate();
				}
			
			
	
		}
		}
		targetpic=new JLabel();
		targetpic.setBounds(90,100 , 55, 55);
		
		if(target==null) {targetpic.setIcon(null);}
		if(target!=null) {
		if(target.getOwner()==game.getPlayer1()) {
			targetpic.setBackground(Color.black);
			if(target instanceof Armored) {
				targetpic.setIcon(new ImageIcon("armor lightsmall.png"));
			validate();
			}
			if(target instanceof Medic) {
				targetpic.setIcon(new ImageIcon("syringe lightsmall.jpg"));
				validate(); 
				}
		
			if(target instanceof Super) {
				targetpic.setIcon(new ImageIcon("super lightsmall.jpg"));
				validate();
				}
			if(target instanceof Ranged) {
				targetpic.setIcon(new ImageIcon("ranged lightsmall.png"));
				validate();
				}
			if(target instanceof Tech) {
				targetpic.setIcon(new ImageIcon("tool lightsmall.png"));
				validate();
				}
			if(target instanceof Speedster) {
				targetpic.setIcon(new ImageIcon("speedster lightsmall.png"));
				validate();
				}
			if(target instanceof SideKick) {
				targetpic.setIcon(new ImageIcon("sidekick lightsmall.jpg"));
				validate();
				}
			
			
		}
		if(target.getOwner()==game.getPlayer2()) {
		triggerpic.setBackground(Color.black);
		if(target instanceof Armored) {
			targetpic.setIcon(new ImageIcon("armor darksmall.png"));
			validate();
			}
			if(target instanceof Medic) {
				targetpic.setIcon(new ImageIcon("syringe darksmall.jpg"));
				validate();
				}
		
			if(target instanceof Super) {
				targetpic.setIcon(new ImageIcon("super darksmall.jpg"));
				validate();
				}
			if(target instanceof Ranged) {
				targetpic.setIcon(new ImageIcon("ranged darksmall.jpg"));
				validate();
				}
			if(target instanceof Tech) {
				targetpic.setIcon(new ImageIcon("tool darksmall.png"));
				validate();
				}
			if(target instanceof Speedster) {
				targetpic.setIcon(new ImageIcon("speedster darksmall.png"));
				validate();
				}
			if(target instanceof SideKick) {
				targetpic.setIcon(new ImageIcon("sidekick darksmall.jpg"));
				validate();
				}
			
			
	
		}}
		powerpic=new JLabel();
		if(isPower) {
		powerpic.setIcon(new ImageIcon("power.png"));
		}
		powerpic.setBounds(175,55, 40, 40);
		directionp =new JLabel("Direction: ");
		newPosp =new JLabel("New position: "+newPos);
		newPosp.setFont(new Font("san-serif", Font.BOLD, 15));

		isPowerp =new JLabel("Power: ");
		isPowerp.setFont(new Font("san-serif", Font.BOLD, 15));
		directionp.setFont(new Font("san-serif", Font.BOLD, 15));
		directionpic =new JLabel();
		directionpic.setBounds(300, 10, 40, 40);
		triggerp.setBounds(10,15, 100, 30);
		directionp.setBounds(200,15, 140, 30);
		isPowerp.setBounds(120,65, 100, 20);
		targetp.setBounds(10,110, 100, 30);
		newPosp.setBounds(200,110, 150, 30);
		triggerp.setForeground(Color.CYAN);
		targetp.setForeground(Color.CYAN);
		directionp.setForeground(Color.CYAN);
		newPosp.setForeground(Color.CYAN);
		isPowerp.setForeground(Color.CYAN);
		if(game.getCurrentPlayer()==game.getPlayer2() && game.getPlayer2()instanceof Bot) {
			if(((game.getPlayer1().getPayloadPos()>=6)||(game.getPlayer2().getPayloadPos()>=6))){
				JLabel end=new JLabel("THIS IS THE END!");
				end.setFont(new Font("san-serif", Font.BOLD, 25));
				end.setForeground(Color.red);
				end.setBounds(70, 90, 250, 50);
				goer.add(end);
			}
			else {
			JLabel botmove=new JLabel("Press GO for Bot's Turn");
			botmove.setFont(new Font("san-serif", Font.BOLD, 15));

			botmove.setForeground(Color.cyan);
			botmove.setBounds(150, 150, 200, 30);
			goer.add(botmove);
			goer.add(go);
			
			}
		
		}
		else {
			if(((game.getPlayer1().getPayloadPos()>=6)||(game.getPlayer2().getPayloadPos()>=6))){
				JLabel end=new JLabel("THIS IS THE END!");
				end.setFont(new Font("san-serif", Font.BOLD, 25));
				end.setForeground(Color.red);
				end.setBounds(70, 90, 250, 50);
				goer.add(end);
			}
			else {
			goer.add(Switchtext);
			goer.add(reset);
			goer.add(triggerp);
			goer.add(targetp);
			goer.add(directionp);
			goer.add(newPosp);
			goer.add(isPowerp);
			goer.add(targetpic);
			goer.add(triggerpic);
			goer.add(directionpic);
			goer.add(powerpic);
			goer.add(go);
			}
		}
		
		
		getContentPane().add(goer);
		
		for(int i =0 ;i<board.length;i++) {
			for(int j =0;j<board[i].length;j++) {
				
				Piece cp =game.getCellAt(i, j).getPiece();
				board[i][j]=new JButtonPiece(cp);
				
				if(cp==null) {board[i][j].setBackground(Color.DARK_GRAY);
					board[i][j].setIcon(new ImageIcon("ground.jpg"));}
				
				
				else { 
				if(cp.getOwner()==game.getPlayer1()) {
					board[i][j].setBackground(Color.white);
					if(cp instanceof Armored) {
					board[i][j].setIcon(new ImageIcon("armor light.png"));
					validate();
					}
					if(cp instanceof Medic) {
						board[i][j].setIcon(new ImageIcon("syringe light.jpg"));
						validate();
						}
				
					if(cp instanceof Super) {
						board[i][j].setIcon(new ImageIcon("super light.jpg"));
						validate();
						}
					if(cp instanceof Ranged) {
						board[i][j].setIcon(new ImageIcon("ranged light.png"));
						validate();
						}
					if(cp instanceof Tech) {
						board[i][j].setIcon(new ImageIcon("tool light.png"));
						validate();
						}
					if(cp instanceof Speedster) {
						board[i][j].setIcon(new ImageIcon("speedster light.png"));
						validate();
						}
					if(cp instanceof SideKick) {
						board[i][j].setIcon(new ImageIcon("sidekick light.jpg"));
						validate();
						}
					
					
				}
				if(cp.getOwner()==game.getPlayer2()) {
				board[i][j].setBackground(Color.black);
				if(cp instanceof Armored) {
					board[i][j].setIcon(new ImageIcon("armor dark.png"));
					validate();
					}
					if(cp instanceof Medic) {
						board[i][j].setIcon(new ImageIcon("syringe dark.jpg"));
						validate();
						}
				
					if(cp instanceof Super) {
						board[i][j].setIcon(new ImageIcon("super dark.jpg"));
						validate();
						}
					if(cp instanceof Ranged) {
						board[i][j].setIcon(new ImageIcon("ranged dark.jpg"));
						validate();
						}
					if(cp instanceof Tech) {
						board[i][j].setIcon(new ImageIcon("tool dark.png"));
						validate();
						}
					if(cp instanceof Speedster) {
						board[i][j].setIcon(new ImageIcon("speedster dark.png"));
						validate();
						}
					if(cp instanceof SideKick) {
						board[i][j].setIcon(new ImageIcon("sidekick dark.jpg"));
						validate();
						}
					
			
				}
				}
				
//				if(cp!=null)
//					cpl=new JLabel(cp.toString());
//				else 
//				cpl=new JLabel("");
//				cpl.setBackground(Color.GREEN);
//				//board[i][j].setText(cpl.getText());
				main.add(board[i][j]);
				
				board[i][j].addMouseListener(this);
				board[i][j].setPoint(new Point(i, j));
			}
		}
		
		
		
		getContentPane().add(main);
		setVisible(true);
	}
	
		
	
	@Override
	public void mouseClicked(MouseEvent e) {
	JButton x=(JButton) e.getSource();
	//x.setBackground(Color.DARK_GRAY);
	if(x==up) {
		 direction=Direction.UP;directionp.setText("Direction: ");directionpic.setIcon(new ImageIcon("arrowUp darksmall.png")); validate(); return;
	}
	if(x==upleft) {
		 direction=Direction.UPLEFT;directionp.setText("Direction: ");directionpic.setIcon(new ImageIcon("arrowUpleft darksmall.png")); validate();return;
	}
	if(x==upright) {
		direction=Direction.UPRIGHT;directionp.setText("Direction: ");directionpic.setIcon(new ImageIcon("arrowUpright darksmall.png")); validate();return;
	}
	if(x==right) {
		direction=Direction.RIGHT;directionp.setText("Direction: ");directionpic.setIcon(new ImageIcon("arrowRight darksmall.png")); validate();return;
	}
	if(x==left) {
		direction=Direction.LEFT;directionp.setText("Direction: ");directionpic.setIcon(new ImageIcon("arrowLeft darksmall.png")); validate();return;
	}
	if(x==down) {
		direction=Direction.DOWN;directionp.setText("Direction: ");directionpic.setIcon(new ImageIcon("arrowDown darksmall.png")); validate();return;
	}
	if(x==downleft) {
		direction=Direction.DOWNLEFT;directionp.setText("Direction: ");directionpic.setIcon(new ImageIcon("arrowDownleft darksmall.png")); validate();return;
	}
	if(x==downright) {
		direction=Direction.DOWNRIGHT;directionp.setText("Direction: ");directionpic.setIcon(new ImageIcon("arrowDownright darksmall.png")); validate();return;
	}
	if(x==go) {
		go();return;
	}
	if(x==reset) {
		flip();return;
	}
	if(x==usepower) {
		isPower=!isPower;isPowerp.setText("Power: ");
		if(isPower) {
			powerpic.setIcon(new ImageIcon("power.png"));
			}
		else {
			powerpic.setIcon(null);
		}
		if(trigger!=null) {
			if(trigger.getOwner()==game.getPlayer1()) {
				//triggerpic.setBackground(Color.white);
				if(trigger instanceof Armored) {
					triggerpic.setIcon(new ImageIcon("armor lightsmall.png"));
					validate();
					up.setIcon(new ImageIcon("arrowUp white.png"));
					upleft.setIcon(new ImageIcon("arrowUpleft white.png"));
					upright.setIcon(new ImageIcon("arrowUpright white.png"));
					down.setIcon(new ImageIcon("arrowDown white.png"));
					downleft.setIcon(new ImageIcon("arrowDownleft white.png"));
					downright.setIcon(new ImageIcon("arrowDownright white.png"));
					right.setIcon(new ImageIcon("arrowRight white.png"));
					left.setIcon(new ImageIcon("arrowLeft white.png"));
					usepower.setIcon(null);
				}
				if(trigger instanceof Medic) {
					triggerpic.setIcon(new ImageIcon("syringe lightsmall.jpg"));
					validate();
					up.setIcon(new ImageIcon("arrowUp white.png"));
					upleft.setIcon(null);
					upright.setIcon(null);
					down.setIcon(new ImageIcon("arrowDown white.png"));
					downleft.setIcon(null);
					downright.setIcon(null);
					right.setIcon(new ImageIcon("arrowRight white.png"));
					left.setIcon(new ImageIcon("arrowLeft white.png"));
					usepower.setIcon(new ImageIcon("power white.png"));
					if(isPower) {
						up.setIcon(new ImageIcon("arrowUp white.png"));
						upleft.setIcon(new ImageIcon("arrowUpleft white.png"));
						upright.setIcon(new ImageIcon("arrowUpright white.png"));
						down.setIcon(new ImageIcon("arrowDown white.png"));
						downleft.setIcon(new ImageIcon("arrowDownleft white.png"));
						downright.setIcon(new ImageIcon("arrowDownright white.png"));
						right.setIcon(new ImageIcon("arrowRight white.png"));
						left.setIcon(new ImageIcon("arrowLeft white.png"));
						usepower.setIcon(new ImageIcon("power white.png"));
					}
				}
			
				if(trigger instanceof Super) {
					triggerpic.setIcon(new ImageIcon("super lightsmall.jpg"));
					validate();
					up.setIcon(new ImageIcon("arrowUp white.png"));
					upleft.setIcon(null);
					upright.setIcon(null);
					down.setIcon(new ImageIcon("arrowDown white.png"));
					downleft.setIcon(null);
					downright.setIcon(null);
					right.setIcon(new ImageIcon("arrowRight white.png"));
					left.setIcon(new ImageIcon("arrowLeft white.png"));
					usepower.setIcon(new ImageIcon("power white.png"));
					}
				if(trigger instanceof Ranged) {
					triggerpic.setIcon(new ImageIcon("ranged lightsmall.png"));
					validate();
					up.setIcon(new ImageIcon("arrowUp white.png"));
					upleft.setIcon(new ImageIcon("arrowUpleft white.png"));
					upright.setIcon(new ImageIcon("arrowUpright white.png"));
					down.setIcon(new ImageIcon("arrowDown white.png"));
					downleft.setIcon(new ImageIcon("arrowDownleft white.png"));
					downright.setIcon(new ImageIcon("arrowDownright white.png"));
					right.setIcon(new ImageIcon("arrowRight white.png"));
					left.setIcon(new ImageIcon("arrowLeft white.png"));
					usepower.setIcon(new ImageIcon("power white.png"));
					if(isPower) {
						up.setIcon(new ImageIcon("arrowUp white.png"));
						upleft.setIcon(null);
						upright.setIcon(null);
						down.setIcon(new ImageIcon("arrowDown white.png"));
						downleft.setIcon(null);
						downright.setIcon(null);
						right.setIcon(new ImageIcon("arrowRight white.png"));
						left.setIcon(new ImageIcon("arrowLeft white.png"));
						usepower.setIcon(new ImageIcon("power white.png"));
					}
					}
				if(trigger instanceof Tech) {
					triggerpic.setIcon(new ImageIcon("tool lightsmall.png"));
					validate();
					up.setIcon(null);
					upleft.setIcon(new ImageIcon("arrowUpleft white.png"));
					upright.setIcon(new ImageIcon("arrowUpright white.png"));
					down.setIcon(null);
					downleft.setIcon(new ImageIcon("arrowDownleft white.png"));
					downright.setIcon(new ImageIcon("arrowDownright white.png"));
					right.setIcon(null);
					left.setIcon(null);
					usepower.setIcon(new ImageIcon("power white.png"));
					if(isPower) {
						up.setIcon(null);
						upleft.setIcon(null);
						upright.setIcon(null);
						down.setIcon(null);
						downleft.setIcon(null);
						downright.setIcon(null);
						right.setIcon(null);
						left.setIcon(null);
						usepower.setIcon(new ImageIcon("power white.png"));
					}
					
					}
				if(trigger instanceof Speedster) {
					triggerpic.setIcon(new ImageIcon("speedster lightsmall.png"));
					validate();
					up.setIcon(new ImageIcon("arrowUp white.png"));
					upleft.setIcon(new ImageIcon("arrowUpleft white.png"));
					upright.setIcon(new ImageIcon("arrowUpright white.png"));
					down.setIcon(new ImageIcon("arrowDown white.png"));
					downleft.setIcon(new ImageIcon("arrowDownleft white.png"));
					downright.setIcon(new ImageIcon("arrowDownright white.png"));
					right.setIcon(new ImageIcon("arrowRight white.png"));
					left.setIcon(new ImageIcon("arrowLeft white.png"));
					usepower.setIcon(null);
					}
				if(trigger instanceof SideKick) {
					triggerpic.setIcon(new ImageIcon("sidekick lightsmall.jpg"));
					validate();
					up.setIcon(new ImageIcon("arrowUp white.png"));
					upleft.setIcon(new ImageIcon("arrowUpleft white.png"));
					upright.setIcon(new ImageIcon("arrowUpright white.png"));
					down.setIcon(null);
					downleft.setIcon(null);
					downright.setIcon(null);
					right.setIcon(new ImageIcon("arrowRight white.png"));
					left.setIcon(new ImageIcon("arrowLeft white.png"));
					usepower.setIcon(null);
					}
				
				
			}
			if(trigger.getOwner()==game.getPlayer2()) {
			triggerpic.setBackground(Color.black);
			if(trigger instanceof Armored) {
				triggerpic.setIcon(new ImageIcon("armor darksmall.png"));
				validate();
				up.setIcon(new ImageIcon("arrowUp dark.png"));
				upleft.setIcon(new ImageIcon("arrowUpleft dark.png"));
				upright.setIcon(new ImageIcon("arrowUpright dark.png"));
				down.setIcon(new ImageIcon("arrowDown dark.png"));
				downleft.setIcon(new ImageIcon("arrowDownleft dark.png"));
				downright.setIcon(new ImageIcon("arrowDownright dark.png"));
				right.setIcon(new ImageIcon("arrowRight dark.png"));
				left.setIcon(new ImageIcon("arrowLeft dark.png"));
				usepower.setIcon(null);
				
				}
				if(trigger instanceof Medic) {
					triggerpic.setIcon(new ImageIcon("syringe darksmall.jpg"));
					validate();
					up.setIcon(new ImageIcon("arrowUp dark.png"));
					upleft.setIcon(null);
					upright.setIcon(null);
					down.setIcon(new ImageIcon("arrowDown dark.png"));
					downleft.setIcon(null);
					downright.setIcon(null);
					right.setIcon(new ImageIcon("arrowRight dark.png"));
					left.setIcon(new ImageIcon("arrowLeft dark.png"));
					usepower.setIcon(new ImageIcon("power dark.png"));
					if(isPower) {
						up.setIcon(new ImageIcon("arrowUp dark.png"));
						upleft.setIcon(new ImageIcon("arrowUpleft dark.png"));
						upright.setIcon(new ImageIcon("arrowUpright dark.png"));
						down.setIcon(new ImageIcon("arrowDown dark.png"));
						downleft.setIcon(new ImageIcon("arrowDownleft dark.png"));
						downright.setIcon(new ImageIcon("arrowDownright dark.png"));
						right.setIcon(new ImageIcon("arrowRight dark.png"));
						left.setIcon(new ImageIcon("arrowLeft dark.png"));
						usepower.setIcon(new ImageIcon("power dark.png"));
					}
					}
			
				if(trigger instanceof Super) {
					triggerpic.setIcon(new ImageIcon("super darksmall.jpg"));
					validate();
					up.setIcon(new ImageIcon("arrowUp dark.png"));
					upleft.setIcon(null);
					upright.setIcon(null);
					down.setIcon(new ImageIcon("arrowDown dark.png"));
					downleft.setIcon(null);
					downright.setIcon(null);
					right.setIcon(new ImageIcon("arrowRight dark.png"));
					left.setIcon(new ImageIcon("arrowLeft dark.png"));
					usepower.setIcon(new ImageIcon("power dark.png"));
					if(isPower) {
						up.setIcon(new ImageIcon("arrowUp dark.png"));
						upleft.setIcon(null);
						upright.setIcon(null);
						down.setIcon(new ImageIcon("arrowDown dark.png"));
						downleft.setIcon(null);
						downright.setIcon(null);
						right.setIcon(new ImageIcon("arrowRight dark.png"));
						left.setIcon(new ImageIcon("arrowLeft dark.png"));
						usepower.setIcon(new ImageIcon("power dark.png"));
					}
					}
				if(trigger instanceof Ranged) {
					triggerpic.setIcon(new ImageIcon("ranged darksmall.jpg"));
					validate();
					up.setIcon(new ImageIcon("arrowUp dark.png"));
					upleft.setIcon(new ImageIcon("arrowUpleft dark.png"));
					upright.setIcon(new ImageIcon("arrowUpright dark.png"));
					down.setIcon(new ImageIcon("arrowDown dark.png"));
					downleft.setIcon(new ImageIcon("arrowDownleft dark.png"));
					downright.setIcon(new ImageIcon("arrowDownright dark.png"));
					right.setIcon(new ImageIcon("arrowRight dark.png"));
					left.setIcon(new ImageIcon("arrowLeft dark.png"));
					usepower.setIcon(new ImageIcon("power dark.png"));
					if(isPower) {
						up.setIcon(new ImageIcon("arrowUp dark.png"));
						upleft.setIcon(null);
						upright.setIcon(null);
						down.setIcon(new ImageIcon("arrowDown dark.png"));
						downleft.setIcon(null);
						downright.setIcon(null);
						right.setIcon(new ImageIcon("arrowRight dark.png"));
						left.setIcon(new ImageIcon("arrowLeft dark.png"));
						usepower.setIcon(new ImageIcon("power dark.png"));
						}
					}
				if(trigger instanceof Tech) {
					triggerpic.setIcon(new ImageIcon("tool darksmall.png"));
					validate();
					up.setIcon(null);
					upleft.setIcon(new ImageIcon("arrowUpleft dark.png"));
					upright.setIcon(new ImageIcon("arrowUpright dark.png"));
					down.setIcon(null);
					downleft.setIcon(new ImageIcon("arrowDownleft dark.png"));
					downright.setIcon(new ImageIcon("arrowDownright dark.png"));
					right.setIcon(null);
					left.setIcon(null);
					usepower.setIcon(new ImageIcon("power dark.png"));
					if(isPower) {
						up.setIcon(null);
						upleft.setIcon(null);
						upright.setIcon(null);
						down.setIcon(null);
						downleft.setIcon(null);
						downright.setIcon(null);
						right.setIcon(null);
						left.setIcon(null);
						usepower.setIcon(new ImageIcon("power dark.png"));
						
					}
					}
				if(trigger instanceof Speedster) {
					triggerpic.setIcon(new ImageIcon("speedster darksmall.png"));
					validate();
					up.setIcon(new ImageIcon("arrowUp dark.png"));
					upleft.setIcon(new ImageIcon("arrowUpleft dark.png"));
					upright.setIcon(new ImageIcon("arrowUpright dark.png"));
					down.setIcon(new ImageIcon("arrowDown dark.png"));
					downleft.setIcon(new ImageIcon("arrowDownleft dark.png"));
					downright.setIcon(new ImageIcon("arrowDownright dark.png"));
					right.setIcon(new ImageIcon("arrowRight dark.png"));
					left.setIcon(new ImageIcon("arrowLeft dark.png"));
					usepower.setIcon(null);
					}
				if(trigger instanceof SideKick) {
					triggerpic.setIcon(new ImageIcon("sidekick darksmall.jpg"));
					validate();
					up.setIcon(null);
					upleft.setIcon(null);
					upright.setIcon(null);
					down.setIcon(new ImageIcon("arrowDown dark.png"));
					downleft.setIcon(new ImageIcon("arrowDownleft dark.png"));
					downright.setIcon(new ImageIcon("arrowDownright dark.png"));
					right.setIcon(new ImageIcon("arrowRight dark.png"));
					left.setIcon(new ImageIcon("arrowLeft dark.png"));
					usepower.setIcon(null);
					}
				
				
		
			}
			
		if(trigger.getOwner()!=game.getCurrentPlayer()) {
			up.setIcon(null);
			upleft.setIcon(null);
			upright.setIcon(null);
			down.setIcon(null);
			downleft.setIcon(null);
			downright.setIcon(null);
			right.setIcon(null);
			left.setIcon(null);
			usepower.setIcon(null);
		}
		}
		return;
	}
	
	/*switch(x.getText()) {
		case "USE POWER": isPower=!isPower;isPowerp.setText("Power? "+isPower);return;
		case "UP": direction=Direction.UP;directionp.setText("Direction: "+ direction);return;
		case "UPLEFT": direction=Direction.UPLEFT;directionp.setText("Direction: "+ direction);return;
		case "UPRIGHT":direction=Direction.UPRIGHT;directionp.setText("Direction: "+ direction);return;
		case "RIGHT":direction=Direction.RIGHT;directionp.setText("Direction: "+ direction);return;
		case "LEFT":direction=Direction.LEFT;directionp.setText("Direction: "+ direction);return;
		case "DOWN":direction=Direction.DOWN;directionp.setText("Direction: "+ direction);return;
		case "DOWNLEFT":direction=Direction.DOWNLEFT;directionp.setText("Direction: "+ direction);return;
		case "DOWNRIGHT":	direction=Direction.DOWNRIGHT;directionp.setText("Direction: "+ direction);return;
		
		case "GO": go();return;
		case "Switch": flip();return;
		default: break;
		}*/
	if(Switchtext.getText().equals("Choosing Trigger Piece")) {
		trigger=((JButtonPiece) x).getPiece();
		if(trigger==null) {triggerpic.setIcon(null);
			if(game.getCurrentPlayer()==game.getPlayer1()) {
				up.setIcon(new ImageIcon("arrowUp white.png"));
				upleft.setIcon(new ImageIcon("arrowUpleft white.png"));
				upright.setIcon(new ImageIcon("arrowUpright white.png"));
				down.setIcon(new ImageIcon("arrowDown white.png"));
				downleft.setIcon(new ImageIcon("arrowDownleft white.png"));
				downright.setIcon(new ImageIcon("arrowDownright white.png"));
				right.setIcon(new ImageIcon("arrowRight white.png"));
				left.setIcon(new ImageIcon("arrowLeft white.png"));
				usepower.setIcon(new ImageIcon("power white.png"));
			}
			else {
				up.setIcon(new ImageIcon("arrowUp dark.png"));
				upleft.setIcon(new ImageIcon("arrowUpleft dark.png"));
				upright.setIcon(new ImageIcon("arrowUpright dark.png"));
				down.setIcon(new ImageIcon("arrowDown dark.png"));
				downleft.setIcon(new ImageIcon("arrowDownleft dark.png"));
				downright.setIcon(new ImageIcon("arrowDownright dark.png"));
				right.setIcon(new ImageIcon("arrowRight dark.png"));
				left.setIcon(new ImageIcon("arrowLeft dark.png"));
				usepower.setIcon(new ImageIcon("power dark.png"));
			}
			
		}
		
		if(trigger!=null) {
			if(trigger.getOwner()==game.getPlayer1()) {
				//triggerpic.setBackground(Color.white);
				if(trigger instanceof Armored) {
					triggerpic.setIcon(new ImageIcon("armor lightsmall.png"));
					validate();
					up.setIcon(new ImageIcon("arrowUp white.png"));
					upleft.setIcon(new ImageIcon("arrowUpleft white.png"));
					upright.setIcon(new ImageIcon("arrowUpright white.png"));
					down.setIcon(new ImageIcon("arrowDown white.png"));
					downleft.setIcon(new ImageIcon("arrowDownleft white.png"));
					downright.setIcon(new ImageIcon("arrowDownright white.png"));
					right.setIcon(new ImageIcon("arrowRight white.png"));
					left.setIcon(new ImageIcon("arrowLeft white.png"));
					usepower.setIcon(null);
					isPower=false;
					isPowerp.setText("Power: ");
					powerpic.setIcon(null);
			
					
				}
				if(trigger instanceof Medic) {
					triggerpic.setIcon(new ImageIcon("syringe lightsmall.jpg"));
					validate();
					up.setIcon(new ImageIcon("arrowUp white.png"));
					upleft.setIcon(null);
					upright.setIcon(null);
					down.setIcon(new ImageIcon("arrowDown white.png"));
					downleft.setIcon(null);
					downright.setIcon(null);
					right.setIcon(new ImageIcon("arrowRight white.png"));
					left.setIcon(new ImageIcon("arrowLeft white.png"));
					usepower.setIcon(new ImageIcon("power white.png"));
					if(isPower) {
						up.setIcon(new ImageIcon("arrowUp white.png"));
						upleft.setIcon(new ImageIcon("arrowUpleft white.png"));
						upright.setIcon(new ImageIcon("arrowUpright white.png"));
						down.setIcon(new ImageIcon("arrowDown white.png"));
						downleft.setIcon(new ImageIcon("arrowDownleft white.png"));
						downright.setIcon(new ImageIcon("arrowDownright white.png"));
						right.setIcon(new ImageIcon("arrowRight white.png"));
						left.setIcon(new ImageIcon("arrowLeft white.png"));
						usepower.setIcon(new ImageIcon("power white.png"));
					}
				}
			
				if(trigger instanceof Super) {
					triggerpic.setIcon(new ImageIcon("super lightsmall.jpg"));
					validate();
					up.setIcon(new ImageIcon("arrowUp white.png"));
					upleft.setIcon(null);
					upright.setIcon(null);
					down.setIcon(new ImageIcon("arrowDown white.png"));
					downleft.setIcon(null);
					downright.setIcon(null);
					right.setIcon(new ImageIcon("arrowRight white.png"));
					left.setIcon(new ImageIcon("arrowLeft white.png"));
					usepower.setIcon(new ImageIcon("power white.png"));
					}
				if(trigger instanceof Ranged) {
					triggerpic.setIcon(new ImageIcon("ranged lightsmall.png"));
					validate();
					up.setIcon(new ImageIcon("arrowUp white.png"));
					upleft.setIcon(new ImageIcon("arrowUpleft white.png"));
					upright.setIcon(new ImageIcon("arrowUpright white.png"));
					down.setIcon(new ImageIcon("arrowDown white.png"));
					downleft.setIcon(new ImageIcon("arrowDownleft white.png"));
					downright.setIcon(new ImageIcon("arrowDownright white.png"));
					right.setIcon(new ImageIcon("arrowRight white.png"));
					left.setIcon(new ImageIcon("arrowLeft white.png"));
					usepower.setIcon(new ImageIcon("power white.png"));
					if(isPower) {
						up.setIcon(new ImageIcon("arrowUp white.png"));
						upleft.setIcon(null);
						upright.setIcon(null);
						down.setIcon(new ImageIcon("arrowDown white.png"));
						downleft.setIcon(null);
						downright.setIcon(null);
						right.setIcon(new ImageIcon("arrowRight white.png"));
						left.setIcon(new ImageIcon("arrowLeft white.png"));
						usepower.setIcon(new ImageIcon("power white.png"));
					}
					}
				if(trigger instanceof Tech) {
					triggerpic.setIcon(new ImageIcon("tool lightsmall.png"));
					validate();
					up.setIcon(null);
					upleft.setIcon(new ImageIcon("arrowUpleft white.png"));
					upright.setIcon(new ImageIcon("arrowUpright white.png"));
					down.setIcon(null);
					downleft.setIcon(new ImageIcon("arrowDownleft white.png"));
					downright.setIcon(new ImageIcon("arrowDownright white.png"));
					right.setIcon(null);
					left.setIcon(null);
					usepower.setIcon(new ImageIcon("power white.png"));
					if(isPower) {
						up.setIcon(null);
						upleft.setIcon(null);
						upright.setIcon(null);
						down.setIcon(null);
						downleft.setIcon(null);
						downright.setIcon(null);
						right.setIcon(null);
						left.setIcon(null);
						usepower.setIcon(new ImageIcon("power white.png"));
					}
					
					}
				if(trigger instanceof Speedster) {
					triggerpic.setIcon(new ImageIcon("speedster lightsmall.png"));
					validate();
					up.setIcon(new ImageIcon("arrowUp white.png"));
					upleft.setIcon(new ImageIcon("arrowUpleft white.png"));
					upright.setIcon(new ImageIcon("arrowUpright white.png"));
					down.setIcon(new ImageIcon("arrowDown white.png"));
					downleft.setIcon(new ImageIcon("arrowDownleft white.png"));
					downright.setIcon(new ImageIcon("arrowDownright white.png"));
					right.setIcon(new ImageIcon("arrowRight white.png"));
					left.setIcon(new ImageIcon("arrowLeft white.png"));
					usepower.setIcon(null);
					isPower=false;
					isPowerp.setText("Power: ");
					powerpic.setIcon(null);
					}
				if(trigger instanceof SideKick) {
					triggerpic.setIcon(new ImageIcon("sidekick lightsmall.jpg"));
					validate();
					up.setIcon(new ImageIcon("arrowUp white.png"));
					upleft.setIcon(new ImageIcon("arrowUpleft white.png"));
					upright.setIcon(new ImageIcon("arrowUpright white.png"));
					down.setIcon(null);
					downleft.setIcon(null);
					downright.setIcon(null);
					right.setIcon(new ImageIcon("arrowRight white.png"));
					left.setIcon(new ImageIcon("arrowLeft white.png"));
					usepower.setIcon(null);
					isPower=false;
					isPowerp.setText("Power: ");
					powerpic.setIcon(null);
					}
				
				
			}
			if(trigger.getOwner()==game.getPlayer2()) {
			triggerpic.setBackground(Color.black);
			if(trigger instanceof Armored) {
				triggerpic.setIcon(new ImageIcon("armor darksmall.png"));
				validate();
				up.setIcon(new ImageIcon("arrowUp dark.png"));
				upleft.setIcon(new ImageIcon("arrowUpleft dark.png"));
				upright.setIcon(new ImageIcon("arrowUpright dark.png"));
				down.setIcon(new ImageIcon("arrowDown dark.png"));
				downleft.setIcon(new ImageIcon("arrowDownleft dark.png"));
				downright.setIcon(new ImageIcon("arrowDownright dark.png"));
				right.setIcon(new ImageIcon("arrowRight dark.png"));
				left.setIcon(new ImageIcon("arrowLeft dark.png"));
				usepower.setIcon(null);
				isPower=false;
				isPowerp.setText("Power: ");
				powerpic.setIcon(null);
				
				}
				if(trigger instanceof Medic) {
					triggerpic.setIcon(new ImageIcon("syringe darksmall.jpg"));
					validate();
					up.setIcon(new ImageIcon("arrowUp dark.png"));
					upleft.setIcon(null);
					upright.setIcon(null);
					down.setIcon(new ImageIcon("arrowDown dark.png"));
					downleft.setIcon(null);
					downright.setIcon(null);
					right.setIcon(new ImageIcon("arrowRight dark.png"));
					left.setIcon(new ImageIcon("arrowLeft dark.png"));
					usepower.setIcon(new ImageIcon("power dark.png"));
					if(isPower) {
						up.setIcon(new ImageIcon("arrowUp dark.png"));
						upleft.setIcon(new ImageIcon("arrowUpleft dark.png"));
						upright.setIcon(new ImageIcon("arrowUpright dark.png"));
						down.setIcon(new ImageIcon("arrowDown dark.png"));
						downleft.setIcon(new ImageIcon("arrowDownleft dark.png"));
						downright.setIcon(new ImageIcon("arrowDownright dark.png"));
						right.setIcon(new ImageIcon("arrowRight dark.png"));
						left.setIcon(new ImageIcon("arrowLeft dark.png"));
						usepower.setIcon(new ImageIcon("power dark.png"));
					}
					}
			
				if(trigger instanceof Super) {
					triggerpic.setIcon(new ImageIcon("super darksmall.jpg"));
					validate();
					up.setIcon(new ImageIcon("arrowUp dark.png"));
					upleft.setIcon(null);
					upright.setIcon(null);
					down.setIcon(new ImageIcon("arrowDown dark.png"));
					downleft.setIcon(null);
					downright.setIcon(null);
					right.setIcon(new ImageIcon("arrowRight dark.png"));
					left.setIcon(new ImageIcon("arrowLeft dark.png"));
					usepower.setIcon(new ImageIcon("power dark.png"));
					if(isPower) {
						up.setIcon(new ImageIcon("arrowUp dark.png"));
						upleft.setIcon(null);
						upright.setIcon(null);
						down.setIcon(new ImageIcon("arrowDown dark.png"));
						downleft.setIcon(null);
						downright.setIcon(null);
						right.setIcon(new ImageIcon("arrowRight dark.png"));
						left.setIcon(new ImageIcon("arrowLeft dark.png"));
						usepower.setIcon(new ImageIcon("power dark.png"));
					}
					}
				if(trigger instanceof Ranged) {
					triggerpic.setIcon(new ImageIcon("ranged darksmall.jpg"));
					validate();
					up.setIcon(new ImageIcon("arrowUp dark.png"));
					upleft.setIcon(new ImageIcon("arrowUpleft dark.png"));
					upright.setIcon(new ImageIcon("arrowUpright dark.png"));
					down.setIcon(new ImageIcon("arrowDown dark.png"));
					downleft.setIcon(new ImageIcon("arrowDownleft dark.png"));
					downright.setIcon(new ImageIcon("arrowDownright dark.png"));
					right.setIcon(new ImageIcon("arrowRight dark.png"));
					left.setIcon(new ImageIcon("arrowLeft dark.png"));
					usepower.setIcon(new ImageIcon("power dark.png"));
					if(isPower) {
						up.setIcon(new ImageIcon("arrowUp dark.png"));
						upleft.setIcon(null);
						upright.setIcon(null);
						down.setIcon(new ImageIcon("arrowDown dark.png"));
						downleft.setIcon(null);
						downright.setIcon(null);
						right.setIcon(new ImageIcon("arrowRight dark.png"));
						left.setIcon(new ImageIcon("arrowLeft dark.png"));
						usepower.setIcon(new ImageIcon("power dark.png"));
						}
					}
				if(trigger instanceof Tech) {
					triggerpic.setIcon(new ImageIcon("tool darksmall.png"));
					validate();
					up.setIcon(null);
					upleft.setIcon(new ImageIcon("arrowUpleft dark.png"));
					upright.setIcon(new ImageIcon("arrowUpright dark.png"));
					down.setIcon(null);
					downleft.setIcon(new ImageIcon("arrowDownleft dark.png"));
					downright.setIcon(new ImageIcon("arrowDownright dark.png"));
					right.setIcon(null);
					left.setIcon(null);
					usepower.setIcon(new ImageIcon("power dark.png"));
					if(isPower) {
						up.setIcon(null);
						upleft.setIcon(null);
						upright.setIcon(null);
						down.setIcon(null);
						downleft.setIcon(null);
						downright.setIcon(null);
						right.setIcon(null);
						left.setIcon(null);
						usepower.setIcon(new ImageIcon("power dark.png"));
						
					}
					}
				if(trigger instanceof Speedster) {
					triggerpic.setIcon(new ImageIcon("speedster darksmall.png"));
					validate();
					up.setIcon(new ImageIcon("arrowUp dark.png"));
					upleft.setIcon(new ImageIcon("arrowUpleft dark.png"));
					upright.setIcon(new ImageIcon("arrowUpright dark.png"));
					down.setIcon(new ImageIcon("arrowDown dark.png"));
					downleft.setIcon(new ImageIcon("arrowDownleft dark.png"));
					downright.setIcon(new ImageIcon("arrowDownright dark.png"));
					right.setIcon(new ImageIcon("arrowRight dark.png"));
					left.setIcon(new ImageIcon("arrowLeft dark.png"));
					usepower.setIcon(null);
					isPower=false;
					isPowerp.setText("Power: ");
					powerpic.setIcon(null);
					}
				if(trigger instanceof SideKick) {
					triggerpic.setIcon(new ImageIcon("sidekick darksmall.jpg"));
					validate();
					up.setIcon(null);
					upleft.setIcon(null);
					upright.setIcon(null);
					down.setIcon(new ImageIcon("arrowDown dark.png"));
					downleft.setIcon(new ImageIcon("arrowDownleft dark.png"));
					downright.setIcon(new ImageIcon("arrowDownright dark.png"));
					right.setIcon(new ImageIcon("arrowRight dark.png"));
					left.setIcon(new ImageIcon("arrowLeft dark.png"));
					usepower.setIcon(null);
					isPower=false;
					isPowerp.setText("Power: ");
					powerpic.setIcon(null);					}
				
				
		
			}
			
		if(trigger.getOwner()!=game.getCurrentPlayer()) {
			up.setIcon(null);
			upleft.setIcon(null);
			upright.setIcon(null);
			down.setIcon(null);
			downleft.setIcon(null);
			downright.setIcon(null);
			right.setIcon(null);
			left.setIcon(null);
			usepower.setIcon(null);
		}
		}
	
	
	}
	if(Switchtext.getText().equals("Choosing Target Piece")) {
		target=((JButtonPiece) x).getPiece();
		if(target==null) {targetpic.setIcon(null);}
		if(target!=null) {
		if(target.getOwner()==game.getPlayer1()) {
			targetpic.setBackground(Color.black);
			if(target instanceof Armored) {
				targetpic.setIcon(new ImageIcon("armor lightsmall.png"));
			validate();
			}
			if(target instanceof Medic) {
				targetpic.setIcon(new ImageIcon("syringe lightsmall.jpg"));
				validate(); 
				}
		
			if(target instanceof Super) {
				targetpic.setIcon(new ImageIcon("super lightsmall.jpg"));
				validate();
				}
			if(target instanceof Ranged) {
				targetpic.setIcon(new ImageIcon("ranged lightsmall.png"));
				validate();
				}
			if(target instanceof Tech) {
				targetpic.setIcon(new ImageIcon("tool lightsmall.png"));
				validate();
				}
			if(target instanceof Speedster) {
				targetpic.setIcon(new ImageIcon("speedster lightsmall.png"));
				validate();
				}
			if(target instanceof SideKick) {
				targetpic.setIcon(new ImageIcon("sidekick lightsmall.jpg"));
				validate();
				}
			
			
		}
		if(target.getOwner()==game.getPlayer2()) {
		triggerpic.setBackground(Color.black);
		if(target instanceof Armored) {
			targetpic.setIcon(new ImageIcon("armor darksmall.png"));
			validate();
			}
			if(target instanceof Medic) {
				targetpic.setIcon(new ImageIcon("syringe darksmall.jpg"));
				validate();
				}
		
			if(target instanceof Super) {
				targetpic.setIcon(new ImageIcon("super darksmall.jpg"));
				validate();
				}
			if(target instanceof Ranged) {
				targetpic.setIcon(new ImageIcon("ranged darksmall.jpg"));
				validate();
				}
			if(target instanceof Tech) {
				targetpic.setIcon(new ImageIcon("tool darksmall.png"));
				validate();
				}
			if(target instanceof Speedster) {
				targetpic.setIcon(new ImageIcon("speedster darksmall.png"));
				validate();
				}
			if(target instanceof SideKick) {
				targetpic.setIcon(new ImageIcon("sidekick darksmall.jpg"));
				validate();
				}
			
			
	
		}}
		
	}
	if(Switchtext.getText().equals("Choosing New Position")) {
		newPos=((JButtonPiece) x).getPoint();
		newPosp.setText("New position: "+(int)newPos.getX()+","+(int)newPos.getY());
		
		
		
	}
	
	
	
		
	}
	public void go() {
		if(game.getCurrentPlayer()==game.getPlayer2() && game.getPlayer2() instanceof Bot) {
			try {
				game.doBotMove();
			} catch (GameActionException e) {
				//System.out.println("blabizo");
			}
		}
		else {
			if(isPower && !(trigger instanceof ActivatablePowerHero )) {
				new ErrorWindow("This piece cannot use power");
				return;
			}
			try {
			if(trigger==null||(direction==null&&!isPower)){
				new ErrorWindow("No piece or direction selected");
				return;
			}
			
			if(direction==null&&isPower&&!(trigger instanceof Tech))
				{new ErrorWindow("Choose direction for power use");
					return;
				}
			
				
				
		if(isPower && trigger instanceof ActivatablePowerHero) {
				if(((ActivatablePowerHero) trigger).isPowerUsed()) {
					new ErrorWindow("Power is already used");
					return;
				}
				try {
					((ActivatablePowerHero)trigger).usePower(direction,target,newPos);
				} catch (InvalidPowerUseException e) {
					new ErrorWindow(e.getMessage());
					return;
					
				} catch (WrongTurnException e) {
					new ErrorWindow("This is not your piece");
					return;
				
				}
				
		
		
		
		}
		if(!isPower) {
			try {
				trigger.move(direction);
			} catch (UnallowedMovementException e) {
				new ErrorWindow("This piece cannot move in this direction");
				return;
		
			} catch (OccupiedCellException e) {
				new ErrorWindow("This cell is occupied by a friendly piece");
				return;
			} catch (WrongTurnException e) {
				new ErrorWindow("This is not your piece");
				return;
				
			}
		}
		}
			catch(NullPointerException e){
				 new ErrorWindow("Not all parameters are choosen");
				 return;
				
			}
			
		}
		//refreshBoard();
	    if(game.getPlayer1().getPayloadPos()>=6) {
	    	new WinnerWindow("Congrats! "+game.getPlayer1().getName());
	    		
	    	}
	    else if  (game.getPlayer2().getPayloadPos()>=6) {
	    		new WinnerWindow("Congrats! "+game.getPlayer2().getName());
	    		
	    	}
		
	    refreshBoard();
	  
	}
	public void flip() {
		if(Switchtext.getText().equals("Choosing Trigger Piece")) {
			Switchtext.setText("Choosing Target Piece");
			}
		else if(Switchtext.getText().equals("Choosing Target Piece")){
			Switchtext.setText("Choosing New Position");
		}
		else {
			Switchtext.setText("Choosing Trigger Piece");
		}
	
	}
	
	public void refreshBoard() {

		dispose();
		new GameViewer(game);
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		JButton x=(JButton) e.getSource();
		
		if(x instanceof JButtonPiece) {
			Piece p=((JButtonPiece) x).getPiece();
			if(p==null) {return;}
			x.setIcon(null);
			
			 
		
			 if(p.getOwner()==game.getPlayer2()) {x.setForeground(Color.WHITE);}
			if(p instanceof ActivatablePowerHero) {
				x.setLayout(new BorderLayout());
				String str="";
				if(((ActivatablePowerHero) p).isPowerUsed()) {
					str="Power is used";
				}
				else
					str="Power isn't used";
					
				JLabel powerused=new JLabel(str);
				if(p.getOwner()==game.getPlayer2()) {powerused.setForeground(Color.WHITE);}
				x.add(powerused,BorderLayout.NORTH);
				x.setText("Owner: "+p.getOwner().getName());
			}
			if(p instanceof Armored) {
				x.setLayout(new BorderLayout());
				JLabel powerused=new JLabel();
				if(((Armored) p).isArmorUp()) {
					powerused=new JLabel("Armor is up");
				}
					else {
					 powerused=new JLabel("Armor is down");
					}
					
				
				if(p.getOwner()==game.getPlayer2()) {powerused.setForeground(Color.WHITE);}
				x.add(powerused,BorderLayout.NORTH);
				
				x.setText("Owner: "+p.getOwner().getName());

				}
			if(p instanceof SideKick || p instanceof Speedster )
			{
				x.setText("Owner: "+p.getOwner().getName());
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JButton x=(JButton) e.getSource();
		if(x instanceof JButtonPiece) {
			 Piece cp=((JButtonPiece) x).getPiece();
			if(cp==null){return;}
			x.removeAll();
			x.setText(null);
				if(cp.getOwner()==game.getPlayer1()) {
					x.setBackground(Color.white);
					if(cp instanceof Armored) {
					x.setIcon(new ImageIcon("armor light.png"));
					validate();
					}
					if(cp instanceof Medic) {
						x.setIcon(new ImageIcon("syringe light.jpg"));
						validate();
						}
				
					if(cp instanceof Super) {
						x.setIcon(new ImageIcon("super light.jpg"));
						validate();
						}
					if(cp instanceof Ranged) {
						x.setIcon(new ImageIcon("ranged light.png"));
						validate();
						}
					if(cp instanceof Tech) {
						x.setIcon(new ImageIcon("tool light.png"));
						validate();
						}
					if(cp instanceof Speedster) {
						x.setIcon(new ImageIcon("speedster light.png"));
						validate();
						}
					if(cp instanceof SideKick) {
						x.setIcon(new ImageIcon("sidekick light.jpg"));
						validate();
						}
					
					
				}
				if(cp.getOwner()==game.getPlayer2()) {
				x.setBackground(Color.black);
				if(cp instanceof Armored) {
					x.setIcon(new ImageIcon("armor dark.png"));
					validate();
					}
					if(cp instanceof Medic) {
						x.setIcon(new ImageIcon("syringe dark.jpg"));
						validate();
						}
				
					if(cp instanceof Super) {
						x.setIcon(new ImageIcon("super dark.jpg"));
						validate();
						}
					if(cp instanceof Ranged) {
						x.setIcon(new ImageIcon("ranged dark.jpg"));
						validate();
						}
					if(cp instanceof Tech) {
						x.setIcon(new ImageIcon("tool dark.png"));
						validate();
						}
					if(cp instanceof Speedster) {
						x.setIcon(new ImageIcon("speedster dark.png"));
						validate();
						}
					if(cp instanceof SideKick) {
						x.setIcon(new ImageIcon("sidekick dark.jpg"));
						validate();
						}
					
		
				}}
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
//		JButton x=(JButton) e.getSource();
//		x.setBackground(Color.DARK_GRAY);
//		if(x==up) {System.out.println("<3 dayna");}
//			
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	
			
	}
	

}
