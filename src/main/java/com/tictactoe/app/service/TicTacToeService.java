package com.tictactoe.app.service;

import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TicTacToeService {
	 private static final Logger log = LoggerFactory.getLogger(TicTacToeService.class);

    private String[] boardOfGame = new String[9];
	private String turn = "X";
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
		log.info("Game board : "+createBoard);
		return createBoard;
	}

	public void runGame() {
		log.info("*********Game started!*********");
		String winner = "";
		loadDefaultBoard();
		log.info("Welcoming to 2 Players(X and O) to play Tic Tac Toe.");

		getDisplayBoard();
		log.info("X's will play first. Enter a slot number to place X in:");

		try (Scanner in = new Scanner(System.in)) {
			while ( winner.isEmpty()) {
				int numInput = in.nextInt();
				if (boardOfGame[numInput - 1].equals(String.valueOf(numInput))) {
					boardOfGame[numInput - 1] = turn;
					if (turn.equals("X")) {
						turn = "O";
					}else {
						turn = "X";
					}
					getDisplayBoard();
					winner = checkWinner();
				}
			}
			if (!winner.isEmpty()) {
				playerStatus = "Congratulations! " + winner + "'s have won the Game";
				log.info("Congratulations! " + winner + "'s have won! Thanks for playing.");
			}
		}
		log.info("*********Game Finished!*********");
	}

	/**
	 * This method used to check the winner as per the move
	 * @return
	 */
	public String checkWinner() {
		for (int a = 0; a < 6; a++) {
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
			}
			if (line.equals("XXX")) {
				return "X";
			} else if (line.equals("OOO")) {
				return "O";
			}
		}

		for (int a = 0; a < 9; a++) {
			if (Arrays.asList(boardOfGame).contains(String.valueOf(a + 1))) {
				break;
			}
		}

		log.info(turn + "'s turn; enter a slot number to place " + turn + " in:");
		return "";
	}

}
