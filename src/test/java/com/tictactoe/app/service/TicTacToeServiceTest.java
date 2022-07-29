package com.tictactoe.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TicTacToeServiceTest {

	@Spy
	private TicTacToeService ticTacToeService;

	@Test
	public void displayDefaultGameBoard() {
		String expectedResult = "/---|---|---\\ \n" + "| 1 | 2 | 3 | \n" + "|-----------| \n" + "| 4 | 5 | 6 | \n"
				+ "|-----------| \n" + "| 7 | 8 | 9 | \n" + "/---|---|---\\ \n";
		ticTacToeService.loadDefaultBoard();
		assertEquals(expectedResult, ticTacToeService.getDisplayBoard());
	}
}
