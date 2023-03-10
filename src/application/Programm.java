package application;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Programm {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();
		
		List<ChessPiece> captured = new ArrayList<>();
		
		
		
		while(!chessMatch.getChekMate()) {
			try {
				UI.clearScreen();
				UI.printMatch(chessMatch, captured);
				
				System.out.println();
				UI.gabarito();
				System.out.println();
				
				System.out.print("Posicao da peca que vai mover: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				boolean[][] possibleMoves = chessMatch.possibleMoves(source);
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(), possibleMoves);
				System.out.println();
				System.out.println();
				UI.gabarito();
				System.out.println();
				
				System.out.print("Pra onde mover essa peca?: ");
				ChessPosition target = UI.readChessPosition(sc);
				
				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
				if(capturedPiece != null) {
					captured.add(capturedPiece);
				}
				
				if(chessMatch.getPromoted() !=null) {
					System.out.print("Para qual peca vai promover seu peao (B/H/R/Q)?: ");
					String type = sc.nextLine().toUpperCase();
					while (!type.equals("B") && !type.equals("H") && !type.equals("R")  && !type.equals("Q")) {
						System.out.print("Letra invalida!! Escolha entre (B/H/R/Q) para promover seu peao?: ");
						type = sc.nextLine().toUpperCase();
						
					}
					
					chessMatch.replacePromotedPiece(type);
				}
			}
			catch(ChessException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println(e.getMessage());
				sc.nextLine();
			}
			
		}
		
		UI.clearScreen();
		UI.printMatch(chessMatch, captured);

	}

}
