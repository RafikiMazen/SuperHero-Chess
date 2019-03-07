package model.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import model.game.*;

public class Opening extends JFrame implements MouseListener {
	
	JLabel p1;
	JLabel p2;
	JTextField t1;
	JTextField t2;
	JButton start;
	
	JLabel background;
	private Boolean vsbot;


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Opening(Boolean vsbot) {
		this.vsbot =vsbot;
		
		setTitle("Start Game");
		setBounds(500, 210, 400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		background =new JLabel();	
		background.setIcon(new ImageIcon("game.jpg"));
		background.setBounds(0, 0, 400, 300);
		p1 = new JLabel();
		p1.setIcon(new ImageIcon("player1.png"));
		validate();
		p2 = new JLabel();
		p2.setIcon(new ImageIcon("player2.png"));
		validate();
		t1 = new JTextField("Light");
		t2 = new JTextField("Dark");
		start = new JButton();
		start.setIcon(new ImageIcon("start.png"));
		validate();
		setLayout(null);
		background.add(p1);
		background.add(t1);
		if(!vsbot) {
		background.add(p2);
		background.add(t2);
		}
		background.add(start);
		p1.setBounds(40,80,200,40);
		p2.setBounds(40,130,200,40);
		p1.setBackground(Color.lightGray);
		p2.setBackground(Color.DARK_GRAY);
		p1.setForeground(Color.BLACK);
		p2.setForeground(Color.black);
		p1.setOpaque(true);
		p2.setOpaque(true);
		t1.setBounds(250, 80, 80, 40);
		t2.setBounds(250, 130, 80, 40);
		start.setBounds(101, 180, 203, 80);
		start.addMouseListener(this);
		 getContentPane().add(background);
		setVisible(true);

		
		
		
	
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		String pln1=t1.getText();
		String pln2=t2.getText();
		Player player1=new Player(pln1);
		Game game;
		if(vsbot) {
			Bot  player22=new Bot();
			game=new Game(player1, player22);
		}
		else {
			Player player2=new Player(pln2);
		    game=new Game(player1, player2);
		}
		new GameViewer(game);
		dispose();
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {

		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}


	
	
	
	
	
	
	

	
}
