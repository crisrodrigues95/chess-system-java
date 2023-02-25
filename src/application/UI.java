package application;


import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	// https://stackoverflow.com/questions/2979383/java-clear-the-console
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static void gabarito() {
		System.out.println("Gabarito das pecas");
		System.out.println("K: Rei");
		System.out.println("R: Torre");
		System.out.println("B: Bispo");
		System.out.println("P: Peao");
		System.out.println("Q: Rainha");
		System.out.println("H: Cavalo");
	}

	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.nextLine().toLowerCase();
			char column = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));
			return new ChessPosition(column, row);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Erro ao ler posicao. Valores validos sao de ai a h8");
		}

	}

	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {
		printBoard(chessMatch.getPieces());
		System.out.println();
		System.out.println();
		printCapturedPieces(captured);
		System.out.println();
		System.out.println("Turno: " + chessMatch.getTurn());
		if (!chessMatch.getChekMate()) {
			System.out.println();
			System.out.println(
					"Esperando jogador: " + (chessMatch.getCurrentPlayer() == Color.WHITE ? "BRANCO" : "PRETO"));
			if (chessMatch.getCheck()) {
				System.out.println("CHECK !!!");
			}
		}
		else {
			System.out.println("CHECKMATE! ");
			System.out.println("VENCEDOR: " + (chessMatch.getCurrentPlayer() == Color.WHITE ? "BRANCO" : "PRETO"));

		}

	}

	
	private static String letter =(ANSI_BLACK+ANSI_WHITE_BACKGROUND+"  a b c d e f g h   "+ ANSI_RESET);
	
	public static void printBoard(ChessPiece[][] pieces) {
		
		
		System.out.println(letter);
		/*System.out.println(ANSI_BLACK+"  ________________________________"+ANSI_RESET);*/
		
		for (int i = 0; i < pieces.length; i++) {
			
			System.out.print(ANSI_BLACK+ANSI_WHITE_BACKGROUND+" "+(8 - i)+ANSI_RESET);
			for (int j = 0; j < pieces.length; j++) {
				if ( (i+j) % 2 == 0) {
					System.out.print(ANSI_BLUE_BACKGROUND);
					
				}
				else {
					System.out.print(ANSI_PURPLE_BACKGROUND);
					
				}
				
				printPiece(pieces[i][j], false);
				
				

			}
			
			
			System.out.print(ANSI_RESET);
			System.out.println(ANSI_BLACK+ANSI_WHITE_BACKGROUND+(8 - i)+" "+ANSI_RESET);
			
			
			
			
		}
		
		
		System.out.println(letter);

	}

	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		System.out.println(letter);
		/*System.out.println(ANSI_BLACK+"  ________________________________"+ANSI_RESET);*/
		for (int i = 0; i < pieces.length; i++) {
			System.out.print(ANSI_BLACK+ANSI_WHITE_BACKGROUND+" "+(8 - i)+ANSI_RESET);
			for (int j = 0; j < pieces.length; j++) {
				if ( (i+j) % 2 == 0) {
					System.out.print(ANSI_BLUE_BACKGROUND);
				}
				else {
					System.out.print(ANSI_PURPLE_BACKGROUND);
				}
				printPiece(pieces[i][j], possibleMoves[i][j]);
				
			}
			System.out.print(ANSI_RESET);
			System.out.println(ANSI_BLACK+ANSI_WHITE_BACKGROUND+(8 - i)+" "+ANSI_RESET);
			/*System.out.println(ANSI_BLACK+"  ________________________________"+ANSI_RESET);*/
		}
		System.out.println(letter);
	}

	private static void printPiece(ChessPiece piece, boolean background) {
		if (background) {
			System.out.print(ANSI_RED_BACKGROUND);
		}
		if (piece == null) {
			System.out.print("  " + ANSI_RESET);
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
			
			
		}
		
	}

	private static void printCapturedPieces(List<ChessPiece> captured) {
		List<ChessPiece> white = captured.stream().filter(x -> x.getColor() == Color.WHITE)
				.collect(Collectors.toList());
		List<ChessPiece> black = captured.stream().filter(x -> x.getColor() == Color.BLACK)
				.collect(Collectors.toList());
		System.out.println("Pecas capturadas :");
		System.out.print("Branco: ");
		System.out.print(ANSI_WHITE);
		Arrays.toString(white.toArray());
		System.out.println();
		System.out.print(ANSI_RESET);
		System.out.print("Preto: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(black.toArray()));
		System.out.print(ANSI_RESET);
	}

}
