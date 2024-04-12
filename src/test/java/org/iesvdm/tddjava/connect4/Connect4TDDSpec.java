package org.iesvdm.tddjava.connect4;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


/*Clase del test de connect4*/

public class Connect4TDDSpec {

    private Connect4TDD tested;

    private OutputStream output;

    @BeforeEach
    public void beforeEachTest() {
        output = new ByteArrayOutputStream();

        //Se instancia el juego modificado para acceder a la salida de consola
        tested = new Connect4TDD(new PrintStream(output));
    }

    /*
     * The board is composed by 7 horizontal and 6 vertical empty positions
     */

    @Test
    public void whenTheGameStartsTheBoardIsEmpty() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Connect4TDD game = new Connect4TDD(printStream);

        String expectedEmptyBoard ="";
        assertEquals(expectedEmptyBoard, outputStream.toString());
    }

    /*
     * Players introduce discs on the top of the columns.
     * Introduced disc drops down the board if the column is empty.
     * Future discs introduced in the same column will stack over previous ones
     */

    @Test
    public void whenDiscOutsideBoardThenRuntimeException() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Connect4TDD game = new Connect4TDD(printStream);

        assertThrows(RuntimeException.class, () -> {
            game.putDiscInColumn(-1);
        });
        assertThrows(RuntimeException.class, () -> {
            game.putDiscInColumn(Connect4TDD.COLUMNS);
        });
    }

    @Test
    public void whenFirstDiscInsertedInColumnThenPositionIsZero() {

        assertThat(tested.putDiscInColumn(0)).isEqualTo(0);
    }

    @Test
    public void whenSecondDiscInsertedInColumnThenPositionIsOne() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Connect4TDD game = new Connect4TDD(printStream);

        game.putDiscInColumn(0);
        int positionOfSecondDisc = game.putDiscInColumn(0);

        assertEquals(1, positionOfSecondDisc);
    }

    @Test
    public void whenDiscInsertedThenNumberOfDiscsIncreases() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Connect4TDD game = new Connect4TDD(printStream);

        int initialNumberOfDiscs = game.getNumberOfDiscs();
        game.putDiscInColumn(0);
        int numberOfDiscsAfterInsertion = game.getNumberOfDiscs();

        assertEquals(initialNumberOfDiscs + 1, numberOfDiscsAfterInsertion);
    }
    @Test
    public void whenNoMoreRoomInColumnThenRuntimeException() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Connect4TDD game = new Connect4TDD(printStream);

        for (int i = 0; i < Connect4TDD.ROWS; i++) {
            game.putDiscInColumn(0);
        }

        assertThrows(RuntimeException.class, () -> {
            game.putDiscInColumn(0);
        });
    }

    /*
     * It is a two-person game so there is one colour for each player.
     * One player uses red ('R'), the other one uses green ('G').
     * Players alternate turns, inserting one disc every time
     */

    @Test
    public void whenFirstPlayerPlaysThenDiscColorIsRed() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Connect4TDD game = new Connect4TDD(printStream);

        String currentPlayer = game.getCurrentPlayer();
        game.putDiscInColumn(0);

        assertEquals(Connect4TDD.RED, currentPlayer);
    }

    @Test
    public void whenSecondPlayerPlaysThenDiscColorIsGreen() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Connect4TDD game = new Connect4TDD(printStream);

        game.putDiscInColumn(0);
        String currentPlayer = game.getCurrentPlayer();
        game.putDiscInColumn(1);

        assertEquals(Connect4TDD.GREEN, currentPlayer);
    }

    /*
     * We want feedback when either, event or error occur within the game.
     * The output shows the status of the board on every move
     */

    @Test
    public void whenAskedForCurrentPlayerTheOutputNotice() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Connect4TDD game = new Connect4TDD(printStream);

        String currentPlayer = game.getCurrentPlayer();

        String output = outputStream.toString();
        assertTrue(output.contains("Player"));
    }

    @Test
    public void whenADiscIsIntroducedTheBoardIsPrinted() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Connect4TDD game = new Connect4TDD(printStream);

        game.putDiscInColumn(0);

        String expectedBoard =
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "| | | | | | | |\n" +
                "|R| | | | | | |\n";
        assertEquals(expectedBoard, outputStream.toString());
    }

    /*
     * When no more discs can be inserted, the game finishes and it is considered a draw
     */

    @Test
    public void whenTheGameStartsItIsNotFinished() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Connect4TDD game = new Connect4TDD(printStream);

        assertFalse(game.isFinished());
    }

    @Test
    public void whenNoDiscCanBeIntroducedTheGamesIsFinished() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Connect4TDD game = new Connect4TDD(printStream);

        for (int col = 0; col < Connect4TDD.COLUMNS; col++) {
            for (int row = 0; row < Connect4TDD.ROWS; row++) {
                game.putDiscInColumn(col);
            }
        }

        assertTrue(game.isFinished());
    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight vertical line then that player wins
     */
        @Test
        public void when4VerticalDiscsAreConnectedThenThatPlayerWins() {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            Connect4TDD game = new Connect4TDD(printStream);

            for (int i = 0; i < 3; i++) {
                game.putDiscInColumn(0);
                game.putDiscInColumn(1);
            }
            game.putDiscInColumn(0);

            assertEquals(Connect4TDD.RED, game.getWinner());
        }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight horizontal line then that player wins
     */

    @Test
    public void when4HorizontalDiscsAreConnectedThenThatPlayerWins() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Connect4TDD game = new Connect4TDD(printStream);

        game.putDiscInColumn(1);
        game.putDiscInColumn(1);
        game.putDiscInColumn(2);
        game.putDiscInColumn(2);
        game.putDiscInColumn(3);
        game.putDiscInColumn(3);
        game.putDiscInColumn(4);

        assertEquals(Connect4TDD.RED, game.getWinner());
    }

    /*
     * If a player inserts a disc and connects more than 3 discs of his colour
     * in a straight diagonal line then that player wins
     */

    @Test
    public void when4Diagonal1DiscsAreConnectedThenThatPlayerWins() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Connect4TDD game = new Connect4TDD(printStream);

        game.putDiscInColumn(0);
        game.putDiscInColumn(1);
        game.putDiscInColumn(1);
        game.putDiscInColumn(2);
        game.putDiscInColumn(2);
        game.putDiscInColumn(3);
        game.putDiscInColumn(2);
        game.putDiscInColumn(3);
        game.putDiscInColumn(3);
        game.putDiscInColumn(6);
        game.putDiscInColumn(3);
        game.putDiscInColumn(6);
        game.putDiscInColumn(6);

        assertEquals(Connect4TDD.RED, game.getWinner());
    }

    @Test
    public void when4Diagonal2DiscsAreConnectedThenThatPlayerWins() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        Connect4TDD game = new Connect4TDD(printStream);

        game.putDiscInColumn(4);
        game.putDiscInColumn(3);
        game.putDiscInColumn(3);
        game.putDiscInColumn(2);
        game.putDiscInColumn(2);
        game.putDiscInColumn(1);
        game.putDiscInColumn(2);
        game.putDiscInColumn(1);
        game.putDiscInColumn(1);
        game.putDiscInColumn(6);
        game.putDiscInColumn(1);
        game.putDiscInColumn(6);
        game.putDiscInColumn(0);

        assertEquals(Connect4TDD.RED, game.getWinner());
    }
}
