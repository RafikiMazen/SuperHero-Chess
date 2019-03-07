package model.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ErrorWindow extends JFrame implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel error;
	JButton ok;
	public ErrorWindow(String str) {
		setTitle("Error");
		setBounds(500, 210, 500, 100);
		ok=new JButton("OK");
		getContentPane().add(ok, BorderLayout.SOUTH);
		ok.addMouseListener(this);
		//setLayout(null);
		error =new JLabel(str);
		error.setOpaque(true);
		getContentPane().add(error,BorderLayout.CENTER);
		error.setBounds(0, 0, 500, 100);
		getContentPane().setBackground(Color.blue);
		setVisible(true);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		dispose();
		
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
