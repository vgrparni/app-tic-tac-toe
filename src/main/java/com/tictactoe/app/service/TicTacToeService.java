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

	public String getPlayerStatus() {
		return playerStatus;
	}

	public void loadDefaultBoard() {
		for (int a = 0; a < 9; a++) {
			squareBoard[a] = String.valueOf(a + 1);
		}
	}

	public String getDisplayBoard() {

		String createBoard = "/---|---|---\\ \n" + "| " + squareBoard[0] + " | " + squareBoard[1] + " | "
				+ squareBoard[2] + " | \n" + "|-----------| \n" + "| " + squareBoard[3] + " | " + squareBoard[4] + " | "
				+ squareBoard[5] + " | \n" + "|-----------| \n" + "| " + squareBoard[6] + " | " + squareBoard[7] + " | "
				+ squareBoard[8] + " | \n" + "/---|---|---\\ \n";
		log.info("Game board : " + createBoard);
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
					winStatusInfo = checkPossibleWaysOfWinningAndGetStatus();
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

	/**
	 * This method used to check the winner as per the move
	 * 
	 * @return
	 */
	public String checkPossibleWaysOfWinningAndGetStatus() {
		for (int a = 0; a < 8; a++) {
			String line = "";
			switch (a) {
			case 0:
				line = squareBoard[0] + squareBoard[1] + squareBoard[2];
				break;
			case 1:
				line = squareBoard[3] + squareBoard[4] + squareBoard[5];
				break;
			case 2:
				line = squareBoard[6] + squareBoard[7] + squareBoard[8];
				break;
			case 3:
				line = squareBoard[0] + squareBoard[3] + squareBoard[6];
				break;
			case 4:
				line = squareBoard[1] + squareBoard[4] + squareBoard[7];
				break;
			case 5:
				line = squareBoard[2] + squareBoard[5] + squareBoard[8];
				break;
			case 6:
				line = squareBoard[0] + squareBoard[4] + squareBoard[8];
				break;
			case 7:
				line = squareBoard[2] + squareBoard[4] + squareBoard[6];
				break;
			}
			if (line != null && line.equals("XXX")) {
				return PLAYER_X;
			} else if (line.equals("OOO")) {
				return PLAYER_O;
			}
		}

		for (int a = 0; a < 9; a++) {
			if (Arrays.asList(squareBoard).contains(String.valueOf(a + 1))) {
				break;
			} else if (a == 8)
				return STATUS_DRAW;
		}

		log.info(turnOfPlayer + "'s turn; enter a slot number to place " + turnOfPlayer + " in:");
		return "";
	}

}
