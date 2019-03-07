package model.game;


import model.pieces.Piece;

public class AIMove {
	private Piece doer;
	private Direction direction;
	private Piece target;
	private Boolean move;
	
	public AIMove(Piece doer,Direction direction,Piece target,Boolean move ) {
		this.doer=doer;
		this.target=target;
		this.direction=direction;
		this.move=move;
	}

	public Boolean getMove() {
		return move;
	}

	public Piece getDoer() {
		return doer;
	}

	public Piece getTarget() {
		return target;
	}

	public Direction getDirection() {
		return direction;
	}

	
}
