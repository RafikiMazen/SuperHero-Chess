package model.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WinnerWindow extends JFrame implements MouseListener  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel winner;
	JButton ok;
	public WinnerWindow(String str) {
		setTitle("Game Over");
		setBounds(500, 210, 300, 100);
		ok=new JButton("OK");
		getContentPane().add(ok, BorderLayout.SOUTH);
		ok.addMouseListener(this);
		//setLayout(null);
		winner =new JLabel(str);
		winner.setOpaque(true);
		getContentPane().add(winner,BorderLayout.CENTER);
		winner.setBounds(0, 0, 300, 100);
		getContentPane().setBackground(Color.blue);
		setAlwaysOnTop(true);
		setVisible(true);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.exit(0);
		
		
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
