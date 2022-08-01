package com.tictactoe.app.service;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TicTacToeService {
	private static final Logger log = LoggerFactory.getLogger(TicTacToeService.class);

	private String[] boardOfGame = new String[9];
	private String turnOfPlayer = "X";
	private String playerStatus;

	public String getPlayerStatus() {
		return playerStatus;
	}

	public void loadDefaultBoard() {
		for (int a = 0; a < 9; a++) {
			boardOfGame[a] = String.valueOf(a + 1);
		}
	}

	public String getDisplayBoard() {

		String createBoard = "/---|---|---\\ \n" + "| " + boardOfGame[0] + " | " + boardOfGame[1] + " | "
				+ boardOfGame[2] + " | \n" + "|-----------| \n" + "| " + boardOfGame[3] + " | " + boardOfGame[4] + " | "
				+ boardOfGame[5] + " | \n" + "|-----------| \n" + "| " + boardOfGame[6] + " | " + boardOfGame[7] + " | "
				+ boardOfGame[8] + " | \n" + "/---|---|---\\ \n";
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
				if (boardOfGame[numInput - 1].equals(String.valueOf(numInput))) {
					boardOfGame[numInput - 1] = turnOfPlayer;
					if (turnOfPlayer.equals("X")) {
						turnOfPlayer = "O";
					} else {
						turnOfPlayer = "X";
					}
					getDisplayBoard();
					winStatusInfo = checkPossibleWaysOfWinningAndGetStatus();
				} else {
					log.info("Slot already taken; re-enter slot number:");
					continue;
				}
			}
			if (winStatusInfo.equalsIgnoreCase("draw")) {
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
				line = boardOfGame[0] + boardOfGame[1] + boardOfGame[2];
				break;
			case 1:
				line = boardOfGame[3] + boardOfGame[4] + boardOfGame[5];
				break;
			case 2:
				line = boardOfGame[6] + boardOfGame[7] + boardOfGame[8];
				break;
			case 3:
				line = boardOfGame[0] + boardOfGame[3] + boardOfGame[6];
				break;
			case 4:
				line = boardOfGame[1] + boardOfGame[4] + boardOfGame[7];
				break;
			case 5:
				line = boardOfGame[2] + boardOfGame[5] + boardOfGame[8];
				break;
			case 6:
				line = boardOfGame[0] + boardOfGame[4] + boardOfGame[8];
				break;
			case 7:
				line = boardOfGame[2] + boardOfGame[4] + boardOfGame[6];
				break;
			}
			if (line != null && line.equals("XXX")) {
				return "X";
			} else if (line.equals("OOO")) {
				return "O";
			}
		}

		for (int a = 0; a < 9; a++) {
			if (Arrays.asList(boardOfGame).contains(String.valueOf(a + 1))) {
				break;
			} else if (a == 8)
				return "draw";
		}

		log.info(turnOfPlayer + "'s turn; enter a slot number to place " + turnOfPlayer + " in:");
		return "";
	}

}
