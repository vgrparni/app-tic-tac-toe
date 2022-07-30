package com.tictactoe.app.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TicTacToeServiceTest {

	@Spy
	private TicTacToeService ticTacToeService;

	@BeforeEach
	public void beforeEach()
	{
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void displayDefaultGameBoard() {
		String expectedResult = "/---|---|---\\ \n" + "| 1 | 2 | 3 | \n" + "|-----------| \n" + "| 4 | 5 | 6 | \n"
				+ "|-----------| \n" + "| 7 | 8 | 9 | \n" + "/---|---|---\\ \n";
		ticTacToeService.loadDefaultBoard();
		assertEquals(expectedResult, ticTacToeService.getDisplayBoard());
	}
	
	@Test
	public void checkthePlayerWinningHarizontal() {
		List<String> playersMoves = Arrays.asList("1","4","2","5","3","6","7","8","9");
        String delim = System.getProperty("line.separator");
        String playerMovesTogether = String.join(delim, playersMoves);
	    InputStream in = new ByteArrayInputStream(playerMovesTogether.getBytes());
	    System.setIn(in);
	    ticTacToeService.runGame();
	    assertEquals("Congratulations! X's have won the Game", ticTacToeService.getPlayerStatus());
	}
}
