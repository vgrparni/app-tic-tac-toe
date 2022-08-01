package com.tictactoe.app.service;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TicTacToeService {
	private static final Logger log = LoggerFactory.getLogger(TicTacToeService.class);

	private final String[] squareBoard = new String[9];
	private final static String  PLAYER_X = "X";
	private final static String  PLAYER_O = "O";
	private String turnOfPlayer = PLAYER_X;
	private final static String STATUS_DRAW = "draw";
	private String playerStatus;
	private final String FRAME_HF = "/---|---|---\\ \n";
	private final String BOARD_LINE = "|-----------| \n";
	private final String CELL_SEPARATOR = " | ";
	private final String CELL_LIFT_SEPARATOR = "| ";
	private final String CELL_RIGHT_SEPARATOR = " |";
	private final String NEXT_LINE = "\n";

	public String getPlayerStatus() {
		return playerStatus;
	}

	public void loadDefaultBoard() {
		for (int a = 0; a < 9; a++) {
			squareBoard[a] = String.valueOf(a + 1);
		}
	}

	public String getDisplayBoard() {
		String createBoard = new StringBuffer().append(FRAME_HF).append(CELL_LIFT_SEPARATOR).append(squareBoard[0])
				.append(CELL_SEPARATOR).append(squareBoard[1]).append(CELL_SEPARATOR).append(squareBoard[2])
				.append(CELL_RIGHT_SEPARATOR).append(NEXT_LINE).append(BOARD_LINE).append(CELL_LIFT_SEPARATOR)
				.append(squareBoard[3]).append(CELL_SEPARATOR).append(squareBoard[4]).append(CELL_SEPARATOR)
				.append(squareBoard[5]).append(CELL_RIGHT_SEPARATOR).append(NEXT_LINE).append(BOARD_LINE)
				.append(CELL_LIFT_SEPARATOR).append(squareBoard[6]).append(CELL_SEPARATOR).append(squareBoard[7])
				.append(CELL_SEPARATOR).append(squareBoard[8]).append(CELL_RIGHT_SEPARATOR).append(NEXT_LINE)
				.append(FRAME_HF).toString();
		log.info("Game squareBoard :" + NEXT_LINE + createBoard);
		return createBoard;
	}


	public void runTicTacToeGame() {
		log.info("*********Game started!*********");
		loadDefaultBoard();
		log.info("Welcoming to 2 Players(X and O) to play Tic Tac Toe.");
		getDisplayBoard();
		log.info("X's will play first. Enter a slot number to place X in:");
		String winStatusInfo = "";
		try (Scanner in = new Scanner(System.in)) {
			while (winStatusInfo.isEmpty()) {
				int numInput;
				try {
					numInput = in.nextInt();
					if (!(numInput > 0 && numInput <= 9)) {
						log.error("Invalid input; re-enter slot number within 0-9 range:");
						continue;
					}
				} catch (InputMismatchException e) {
					log.error("Invalid input; re-enter slot number within 0-9 range:");
					in.next();
					continue;
				}
				if (squareBoard[numInput - 1].equals(String.valueOf(numInput))) {
					squareBoard[numInput - 1] = turnOfPlayer;
					if (turnOfPlayer.equals(PLAYER_X)) {
						turnOfPlayer = PLAYER_O;
					} else {
						turnOfPlayer = PLAYER_X;
					}
					getDisplayBoard();
					winStatusInfo = getWinningStatus();
				} else {
					log.info("Slot already taken; re-enter slot number:");
					continue;
				}
			}
			if (winStatusInfo.equalsIgnoreCase(STATUS_DRAW)) {
				playerStatus = "Hi Guys, Its a draw!, Enjoy the game and try again.";
				log.info("Hi Guys, Its a draw!, Enjoy the game and try again.");
			} else {
				playerStatus = "Congratulations! " + winStatusInfo + "'s have won the Game";
				log.info("Congratulations! " + winStatusInfo + "'s have won! Thanks for playing.");
			}
		}
		log.info("*********Game Finished!*********");
	}

	
	public String getThePossibleWayOfWinning(int possibleCaseId) {
		String possibleWinLine ="";
			switch (possibleCaseId) {
			case 0:
				possibleWinLine = squareBoard[0] + squareBoard[1] + squareBoard[2];
				break;
			case 1:
				possibleWinLine = squareBoard[3] + squareBoard[4] + squareBoard[5];
				break;
			case 2:
				possibleWinLine = squareBoard[6] + squareBoard[7] + squareBoard[8];
				break;
			case 3:
				possibleWinLine = squareBoard[0] + squareBoard[3] + squareBoard[6];
				break;
			case 4:
				possibleWinLine = squareBoard[1] + squareBoard[4] + squareBoard[7];
				break;
			case 5:
				possibleWinLine = squareBoard[2] + squareBoard[5] + squareBoard[8];
				break;
			case 6:
				possibleWinLine = squareBoard[0] + squareBoard[4] + squareBoard[8];
				break;
			case 7:
				possibleWinLine = squareBoard[2] + squareBoard[4] + squareBoard[6];
				break;
			default : break;
			}
		return possibleWinLine;
		
	}
	
	public String getWinningStatus() {
		String winningStatus="";
		for (int a = 0; a < 8; a++) {
			String possibleline= getThePossibleWayOfWinning(a);
			if (possibleline.equals("XXX")) {
				winningStatus = PLAYER_X;
				break;
			} else if (possibleline.equals("OOO")) {
				winningStatus = PLAYER_O;
				break;
			}
		}
		for (int a = 0; a < 9; a++) {
			if (Arrays.asList(squareBoard).contains(String.valueOf(a + 1))) {
				break;
			} else if (a == 8) {
				winningStatus = STATUS_DRAW;
				break;
			}
		}
		log.info(turnOfPlayer + "'s turn; enter a slot number to place " + turnOfPlayer + " in:");
		return winningStatus;
	}

}
