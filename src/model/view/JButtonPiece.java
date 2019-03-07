package model.view;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import model.pieces.Piece;

public class JButtonPiece extends JButton implements MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Piece piece;
	private Point point;
	public JButtonPiece(Piece P) {
		super();
		setPiece(P);
	}
	public JButtonPiece(String s,Piece P) {
	super(s);
	setPiece(P);
	}
	public Piece getPiece() {
		return piece;
	}
	private void setPiece(Piece piece) {
		this.piece = piece;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		JButton x=(JButton) e.getSource();
		x.setBackground(Color.red);
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	

}
