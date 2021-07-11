package com.design.java;

import java.util.*;



/*
 * Game -- Board, Players, History
 * Board (Singleton) -- Spots, Initialize Piece, Move/Remove Piece 
 * Spot -- Hold Pieces
 * Piece (Abstract) -- Color, PieceType(8 Pawns, 2 Rooks, 2 Bishops, 2 Knights, 1 Queen, 1 King)
 * Player (Abstract) -- List of Piece,  PlayerType(Human, Computer)
 * Command -- Piece, Destination
 */

public class Chess {

}


class Game {
    private Board board;
    private Player white;
    private Player black;
    
    public Game() {
        board = new Board();
    }
    
    public boolean enterPlayer(Player p) {
    	board.initialize(p);
    	return true;
    }
    
    public void processTurn(Player p) {
    	
    }
    
    public void startGame(){
    	
    }
}

class Board {
	private Spot[][] spots;
	
	public Board() {
		spots = new Spot[8][8];
	}
	
	public void initialize(Player p){

    }
}

class Player {
    private List<Piece> pieces = new ArrayList<>();
    public String color;
    
    public Player(String color) {
        super();
        this.color = color;
        initializePieces();
    }
    
    public List<Piece> getPieces() {
        return pieces;
    }
    
    public void initializePieces() {

        if(this.color == "WHITE"){
            for(int i=0; i<8; i++){
                pieces.add(new Pawn(true,i,2));
            }
            /*pieces.add(new Rook(true, 0, 0));
            pieces.add(new Rook(true, 7, 0));
            pieces.add(new Bishop(true, 2, 0));
            pieces.add(new Bishop(true, 5, 0));
            pieces.add(new Knight(true, 1, 0));
            pieces.add(new Knight(true, 6, 0));
            pieces.add(new Queen(true, 3, 0));*/
            pieces.add(new King(true, 4, 0));
        }
        else {
            
        }
    }
}

abstract class Piece {
	private boolean available;
    private int x;
    private int y;

    public Piece(boolean available, int x, int y) {
        super();
        this.available = available;
        this.x = x;
        this.y = y;
    }
    
    public boolean isValid(Board board, int fromX, int fromY, int toX, int toY) {
    	if(toX == fromX && toY == fromY)
            return false;
        if(toX < 0 || toX > 7 || fromX < 0 || fromX > 7 || toY < 0 || toY > 7 || fromY <0 || fromY > 7)
            return false;
        return true;
    }
}


class King extends Piece {
	public King(boolean available, int x, int y) {
		super(available, x, y);
	}
	
	public boolean isValid(Board board, int fromX, int fromY, int toX, int toY) {
		return true;
	}
}
class Pawn extends Piece {
	public Pawn(boolean available, int x, int y) {
		super(available, x, y);
	}
	
	public boolean isValid(Board board, int fromX, int fromY, int toX, int toY) {
		return true;
	}
}

class Spot {
	
}

