package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {
	
	private Color color;
	private int moveCount;

	public ChessPiece(Board board, Color color) {
		super(board);
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
	
	public ChessPosition getChessPosition() {
		return ChessPosition.fromPosition(position);
	}
	
	public int GetmoveCount() {
		return moveCount;
	}
	
	
	public void increasemoveCount() {
		moveCount++;
	}
	
	public void decreasemoveCount() {
		moveCount--;
	}
	
	
	
	protected boolean isThereOpponetPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p !=null && p.getColor() != color;
	}
	
	

}
