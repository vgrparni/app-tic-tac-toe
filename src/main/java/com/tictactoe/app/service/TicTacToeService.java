package com.tictactoe.app.service;

public class TicTacToeService {

	private String[] boardOfGame = new String[9];

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
		return createBoard;
	}
}
