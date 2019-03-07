package model.view;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class GameStarterWindow extends JFrame implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton vsP2;
	JButton vsBot;
	JLabel background;
	
	public GameStarterWindow() {
		setTitle("Start Game");
		setBounds(500, 210, 400, 300);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		background =new JLabel();	
		background.setIcon(new ImageIcon("game.jpg"));
		background.setBounds(0, 0, 400, 300);
		background.setBackground(Color.BLACK);

		vsP2 =new JButton();
		vsP2.addMouseListener(this);
		vsP2.setIcon(new ImageIcon("vsPlayer.png"));
		vsBot =new JButton();
		vsBot.addMouseListener(this);
		vsBot.setIcon(new ImageIcon("vsBot.jpg"));
		vsP2.setBounds(30,10,86,87);
		vsBot.setBounds(290,10,86,87);
		vsP2.setBorder(BorderFactory.createEmptyBorder());
		vsBot.setBorder(BorderFactory.createEmptyBorder());
		background.add(vsP2);
		background.add(vsBot);
		validate();
		
		
		getContentPane().add(background);
		setVisible(true);
	}	
	
	public static void main(String[] args) {
		new GameStarterWindow();
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		JButton x=(JButton) e.getSource();
		if(x==vsP2) {
			new Opening(false);
			dispose();
		}
		if(x==vsBot) {
			new Opening(true);			
			dispose();
		}
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
