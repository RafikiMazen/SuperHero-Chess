package model.game;

import java.util.ArrayList;
import exceptions.GameActionException;
import model.game.Game;
import model.pieces.Piece;
import model.pieces.heroes.*;
import model.pieces.sidekicks.SideKick;
import model.pieces.sidekicks.SideKickP1;
import model.pieces.sidekicks.SideKickP2;
import modifiedBTree.BTree;

public class AI {
	private Game game;

	public AI(Game game) {
		this.game = game;
	}

	public Game cloneGame() {

		//cloning p1
		Player p1=new Player("clone1");
		p1.setPayloadPos(this.game.getPlayer1().getPayloadPos());
		p1.setSideKilled(game.getPlayer1().getSideKilled());
		for(Piece p:game.getPlayer1().getDeadCharacters()) {
			p1.getDeadCharacters().add(p); 															//new pieces have to be.
		}
		//cloning p2
		Player p2=new Player("clone1");
		p2.setPayloadPos(this.game.getPlayer2().getPayloadPos());
		p2.setSideKilled(game.getPlayer2().getSideKilled());
		for(Piece p:game.getPlayer2().getDeadCharacters()) {
			p2.getDeadCharacters().add(p);
		}
		Player piecePlayer;
		//cloning Game
		Game simulation =new Game(p1,p2);
		simulation.setCurrentPlayer(p2);    //try
		//cloning board
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				simulation.getCellAt(i, j).setPiece(null);;
			}
		}

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				Piece x = game.getCellAt(i, j).getPiece();
				if(x!=null) {
				if(x.getOwner()==game.getPlayer1()) {
					piecePlayer=p1;
				}
				else {
					piecePlayer=p2;
				}
				
				 if (x instanceof Armored) {
					simulation.getCellAt(i, j).setPiece(new Armored(piecePlayer, simulation, "botA"));
					simulation.getCellAt(i, j).getPiece().setPosI(i);
					simulation.getCellAt(i, j).getPiece().setPosJ(j);
					if (!((Armored) x).isArmorUp()) {
						((Armored) (simulation.getCellAt(i, j).getPiece())).setArmorUp(false);
					}

				} else if (x instanceof ActivatablePowerHero) {
					if (x instanceof Super) {
						simulation.getCellAt(i, j).setPiece(new Super(piecePlayer, simulation, "botA"));
						simulation.getCellAt(i, j).getPiece().setPosI(i);
						simulation.getCellAt(i, j).getPiece().setPosJ(j);
					} else if (x instanceof Medic) {
						simulation.getCellAt(i, j).setPiece(new Medic(piecePlayer, simulation, "botA"));
						simulation.getCellAt(i, j).getPiece().setPosI(i);
						simulation.getCellAt(i, j).getPiece().setPosJ(j);
					} else if (x instanceof Ranged) {
						simulation.getCellAt(i, j).setPiece(new Ranged(piecePlayer, simulation, "botA"));
						simulation.getCellAt(i, j).getPiece().setPosI(i);
						simulation.getCellAt(i, j).getPiece().setPosJ(j);
					} else if (x instanceof Tech) {
						simulation.getCellAt(i, j).setPiece(new Tech(piecePlayer, simulation, "botA"));
						simulation.getCellAt(i, j).getPiece().setPosI(i);
						simulation.getCellAt(i, j).getPiece().setPosJ(j);
					}
					if (((ActivatablePowerHero) x).isPowerUsed()) {
						((ActivatablePowerHero)simulation.getCellAt(i, j).getPiece()).setPowerUsed(true);
					}
				} else if (x instanceof Speedster) {
					simulation.getCellAt(i, j).setPiece(new Speedster(piecePlayer, simulation, "botA"));
					simulation.getCellAt(i, j).getPiece().setPosI(i);
					simulation.getCellAt(i, j).getPiece().setPosJ(j);

				} else if (x instanceof SideKick) {
					if (x instanceof SideKickP1) {
						simulation.getCellAt(i, j).setPiece(new SideKickP1(simulation, "botA"));
						simulation.getCellAt(i, j).getPiece().setPosI(i);
						simulation.getCellAt(i, j).getPiece().setPosJ(j);
					} else {
						simulation.getCellAt(i, j).setPiece(new SideKickP2(simulation, "botA"));
						simulation.getCellAt(i, j).getPiece().setPosI(i);
						simulation.getCellAt(i, j).getPiece().setPosJ(j);
					}

				}

			 }
			}
		}
		return simulation;

	}
	
	public Cell[][] getBoard(){
		Cell[][] board=new Cell[7][6];
		for(int i=0 ;i<7;i++) {
			for(int j=0;j<6;j++) {
				board[i][j]=game.getCellAt(i, j);
			}
		}
		return board;
	}
	
	public ArrayList<AIMove> generateMoves(Cell[][] board) {
		ArrayList<AIMove> moves = new ArrayList<AIMove>();
		// moves directions
		Direction[] dMSM = { Direction.DOWN, Direction.UP, Direction.RIGHT, Direction.LEFT };
		Direction[] dMRAS = { Direction.DOWN, Direction.UP, Direction.RIGHT, Direction.LEFT, Direction.DOWNLEFT,
				Direction.DOWNRIGHT, Direction.UPLEFT, Direction.UPRIGHT };
		Direction[] dMT = { Direction.DOWNLEFT, Direction.DOWNRIGHT, Direction.UPLEFT, Direction.UPRIGHT };
		Direction[] dMSide = { Direction.DOWN, Direction.RIGHT, Direction.LEFT, Direction.DOWNLEFT,
				Direction.DOWNRIGHT };// assuming he is always P2
		// powers directions
		Direction[] dPSR = { Direction.DOWN, Direction.UP, Direction.RIGHT, Direction.LEFT };
	//	Direction[] dPM = { Direction.DOWN, Direction.UP, Direction.RIGHT, Direction.LEFT, Direction.DOWNLEFT,
		//		Direction.DOWNRIGHT, Direction.UPLEFT, Direction.UPRIGHT };

		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				Piece x = game.getCellAt(i, j).getPiece();
				if(x!=null) {
				if (x.getOwner() == game.getPlayer2()) {
					// moves
					if (x instanceof Super || x instanceof Medic) {
						for (int w = 0; w < dMSM.length; w++) {
							moves.add(new AIMove(x, dMSM[w], null,true));
						}
					} else if (x instanceof Ranged || x instanceof Armored || x instanceof Speedster) {
						for (int w = 0; w < dMRAS.length; w++) {
							moves.add(new AIMove(x, dMRAS[w], null,true));
						}
					} else if (x instanceof Tech) {
						for (int w = 0; w < dMT.length; w++) {
							moves.add(new AIMove(x, dMT[w], null,true));
						}
					} else if (x instanceof SideKickP2) {
						for (int w = 0; w < dMSide.length; w++) {
							moves.add(new AIMove(x, dMSide[w], null,true));
						}

					}
					// end moves

					// powers
					if (x instanceof Super || x instanceof Ranged) {
						for (int w = 0; w < dPSR.length; w++) {
							moves.add(new AIMove(x, dPSR[w], null,false));
						}
					} /*else if (x instanceof Medic) {
						for (Direction d : dPM) {
							for (Piece dead : game.getPlayer2().getDeadCharacters()) {
								if (dead instanceof SideKick) {
									continue;
								}
								moves.add(new AIMove(x, d, dead,false));
							}
						}

					}*/ /*else if (x instanceof Tech) {
						for (int t = 0; i < 7; i++) {
							for (int y = 0; j < 6; j++) {
								if(game.getCellAt(t, y).getPiece()!=null) {
								if (game.getCellAt(t, y).getPiece().getOwner() == game.getPlayer1()) {
									if (game.getCellAt(t, y).getPiece() instanceof ActivatablePowerHero) {
										moves.add(new AIMove(x, null, game.getCellAt(t, y).getPiece(),false));
									}
								}
							}
							}
						}
					}*/

				}
				}
			}
		}

		return moves;
	}

	public BTree evaluteMoves(ArrayList<AIMove> moves) {
		BTree b = new BTree();
		for (AIMove m : moves) {
			b.insertBST(moveEvaluator(m, cloneGame()), m);
		}
		return b;
	}

	public int moveEvaluator(AIMove move, Game sim) {
		Piece x=sim.getCellAt(move.getDoer().getPosI(), move.getDoer().getPosJ()).getPiece();
		
		/*if(move.getDirection()==null && move.getTarget()!=null&& (!move.getMove())) {
			Piece y=sim.getCellAt(move.getTarget().getPosI(), move.getTarget().getPosJ()).getPiece();
			try {
				((Tech)x).usePower(null, y, null);
			} catch (GameActionException e) {
				return -2;
			}
			return 2 ; 
		}*/
		//is armorup
		Piece y;
		Boolean ari = false;
		Boolean arf =false;
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 6; j++) {
				y = sim.getCellAt(i, j).getPiece();
				if(y!=null && y.getOwner()==sim.getPlayer1() && y instanceof Armored && ((Armored) y).isArmorUp()) {
					ari =true;
					break;
				}
				
			}
			
		}
		
		if(move.getDirection()!=null&&move.getMove()&& (x instanceof Speedster)) {
			int w=sim.getPlayer2().getPayloadPos();
			int sidek=sim.getPlayer2().getSideKilled();

			try {
				((Speedster)x).move(move.getDirection());
			} catch (GameActionException e) {
				return -2;
			}
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 6; j++) {
					y = sim.getCellAt(i, j).getPiece();
					if(y!=null && y.getOwner()==sim.getPlayer1() && y instanceof Armored && ((Armored) y).isArmorUp()) {
						arf =true;
						break;
					}
					
				}
				
			}
			int z=sim.getPlayer2().getPayloadPos();
			int sidkf=sim.getPlayer2().getSideKilled();

			if(z-w>0 && sidkf-sidek==0) {
				return 10;
			}
			if (z-w>0 &&sidkf-sidek>0){
				return 8;
			}
			if(z-w==0 && (arf==false && ari==true))
				return 7;
			if(z-w==0 && sidkf-sidek>0) {
				return 6;
			}
			else {
				return 0;
			}
			
		}
		if(move.getDirection()!=null&&move.getMove()&& !(x instanceof Speedster)) {
			int w=sim.getPlayer2().getPayloadPos();
			int sidek=sim.getPlayer2().getSideKilled();
			
			try {
				x.move(move.getDirection());
			} catch (GameActionException e) {
				return -2 ;
			} 
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 6; j++) {
					y = sim.getCellAt(i, j).getPiece();
					if(y!=null && y.getOwner()==sim.getPlayer1() && y instanceof Armored && ((Armored) y).isArmorUp()) {
						arf =true;
						break;
					}
					
				}
				
			}
			int z=sim.getPlayer2().getPayloadPos();
			int sidkf=sim.getPlayer2().getSideKilled();
			if(x instanceof SideKick&& z-w>0 && sidkf-sidek==0) {
				return 15;
			}
			if(x instanceof SideKick&& z-w>0 && sidkf-sidek>0) {
				return 11;
			}
			if(z-w>0 && sidkf-sidek==0) {
				return 10;
			}
			
			if (z-w>0 &&sidkf-sidek>0){
				return 8;
			}
			if(z-w==0 && (arf==false && ari==true))
				return 7;
			
			if(z-w==0 && sidkf-sidek>0) {
				return 6;
			}
			else {
				return 0;
			}
				
		}
		/*if(x instanceof Medic&&move.getTarget()!=null&&move.getDirection()!=null&&!(move.getMove())) {
			Piece y=sim.getCellAt(move.getTarget().getPosI(), move.getTarget().getPosJ()).getPiece();
			try {
				((Medic)x).usePower(move.getDirection(), y, null);
			} catch (GameActionException e) {
				return -1 ;
			} 
			return 1;
		}*/
		if(x instanceof Super&&move.getTarget()==null&&move.getDirection()!=null&&!(move.getMove())) {
			int w=sim.getPlayer2().getPayloadPos();
			int sidek=sim.getPlayer2().getSideKilled();
			try {
				((Super)x).usePower(move.getDirection(), null, null);
			} catch (GameActionException e) {
				return -2 ;
			} 
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 6; j++) {
					y = sim.getCellAt(i, j).getPiece();
					if(y!=null && y.getOwner()==sim.getPlayer1() && y instanceof Armored && ((Armored) y).isArmorUp()) {
						arf =true;
						break;
					}
					
				}
				
			}
			int z=sim.getPlayer2().getPayloadPos();
			int sidkf=sim.getPlayer2().getSideKilled();
			if(z-w>1) {
				return 16 ;
			}
			if(z-w>0 && sidkf-sidek==0) {
				return 11;
			}
			if (z-w>0 &&sidkf-sidek>0){
				return 9;
			}
			if(z-w==0 && (arf==false && ari==true))
				return 7;
			if(z-w==0 && sidkf-sidek>0) {
				return 6;
			}
			else {
				return 0;
			}
		}
		if(x instanceof Ranged&&move.getTarget()==null&&move.getDirection()!=null&&!(move.getMove())) {
			int w=sim.getPlayer2().getPayloadPos();
			int sidek=sim.getPlayer2().getSideKilled();

			try {
				((Ranged)x).usePower(move.getDirection(), null, null);
			} catch (GameActionException e) {
				return -2 ;
			} 
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j < 6; j++) {
					y = sim.getCellAt(i, j).getPiece();
					if(y!=null && y.getOwner()==sim.getPlayer1() && y instanceof Armored && ((Armored) y).isArmorUp()) {
						arf =true;
						break;
					}
					
				}
				
			}
			int z=sim.getPlayer2().getPayloadPos();
			int sidkf=sim.getPlayer2().getSideKilled();
			if(z-w>0 && sidkf-sidek==0) {
				return 11;
			}
			if (z-w>0 &&sidkf-sidek>0){
				return 9;
			}
			if(z-w==0 && (arf==false && ari==true))
				return 7;
			if(z-w==0 && sidkf-sidek>0) {
				return 6;
			}
			else {
				return 0;
			}
		}		
		
		return -2 ;
	}
	public AIMove getBestMove(BTree b) {
		return b.getMax();
	}
	
	public AIMove botMove() {
		AIMove x=getBestMove(evaluteMoves(generateMoves(getBoard())));
		return x;
	}
	
}