package de.uniks.pmws2223.tictactoe.service;

import de.uniks.pmws2223.tictactoe.model.Field;
import de.uniks.pmws2223.tictactoe.model.Game;
import org.junit.Test;
import java.util.List;

import static org.junit.Assert.*;

public class GameServiceTest {
    GameService gameService = new GameService("Alice", "Bob");
    Game game = gameService.getGame();
    List<Field> fields = game.getFields();

    @Test
    public void placeSymbol(){
        // Check if currentPlayer switch works
        assertEquals(game.getCurrentPlayer().getName(), "Alice");
        gameService.placeSymbol(fields.get(0));
        assertEquals(game.getCurrentPlayer().getName(), "Bob");

        // Check if symbol can not be placed at same place by other player
        // currentPlayer should stay the same
        gameService.placeSymbol(fields.get(0));
        assertEquals(game.getCurrentPlayer().getName(), "Bob");
        assertEquals(fields.get(0).getPlayer().getName(), "Alice");

        gameService.placeSymbol(fields.get(1));

        // Check if symbol can not be placed at same place by same player
        assertEquals(game.getCurrentPlayer().getName(), "Alice");
        gameService.placeSymbol(fields.get(0));
        assertEquals(game.getCurrentPlayer().getName(), "Alice");
        assertEquals(fields.get(0).getPlayer().getName(), "Alice");

        // Set Alice as the winner
        gameService.placeSymbol(fields.get(3));
        assertEquals(game.getCurrentPlayer().getName(), "Bob");
        gameService.placeSymbol(fields.get(4));
        assertEquals(game.getCurrentPlayer().getName(), "Alice");
        gameService.placeSymbol(fields.get(6));
        assertNotNull(game.getWinner());
        assertNull(game.getCurrentPlayer());

        // Check if nothing happens when the winner is set
        gameService.placeSymbol(fields.get(8));
        assertNotNull(game.getWinner());
        assertNull(game.getCurrentPlayer());
        assertNull(fields.get(8).getPlayer());
    }

    @Test
    public void checkWinner(){
        // In the beginning there should not be any winner
        assertNull(game.getWinner());

        // Let Alice win with a horizontal row
        gameService.placeSymbol(fields.get(0));
        assertNull(game.getWinner());
        gameService.placeSymbol(fields.get(3));
        assertNull(game.getWinner());
        gameService.placeSymbol(fields.get(1));
        assertNull(game.getWinner());
        gameService.placeSymbol(fields.get(4));
        assertNull(game.getWinner());
        gameService.placeSymbol(fields.get(2));
        assertEquals(game.getWinner().getName(), "Alice");

        // Start new game and let Bob win vertically
        gameService = new GameService("Alice", "Bob");
        game = gameService.getGame();
        fields = game.getFields();

        gameService.placeSymbol(fields.get(0));
        assertNull(game.getWinner());
        gameService.placeSymbol(fields.get(2));
        assertNull(game.getWinner());
        gameService.placeSymbol(fields.get(1));
        assertNull(game.getWinner());
        gameService.placeSymbol(fields.get(5));
        assertNull(game.getWinner());
        gameService.placeSymbol(fields.get(3));
        assertNull(game.getWinner());
        gameService.placeSymbol(fields.get(8));
        assertEquals(game.getWinner().getName(), "Bob");

        // Start new game and let Alice win diagonally
        gameService = new GameService("Alice", "Bob");
        game = gameService.getGame();
        fields = game.getFields();

        gameService.placeSymbol(fields.get(0));
        assertNull(game.getWinner());
        gameService.placeSymbol(fields.get(3));
        assertNull(game.getWinner());
        gameService.placeSymbol(fields.get(4));
        assertNull(game.getWinner());
        gameService.placeSymbol(fields.get(6));
        assertNull(game.getWinner());
        gameService.placeSymbol(fields.get(8));
        assertEquals(game.getWinner().getName(), "Alice");
    }

    @Test
    public void checkDraw(){
        // Call checkDraw by using placeSymbol
        gameService.placeSymbol(fields.get(0));

        // Check if checkDraw detects, that the field is not full
        // If the field is full currentPlayer is set to null
        assertNotNull(game.getCurrentPlayer());

        // Place rest of symbols on the fields without letting anybody win
        gameService.placeSymbol(fields.get(1));
        assertNotNull(game.getCurrentPlayer());
        gameService.placeSymbol(fields.get(3));
        assertNotNull(game.getCurrentPlayer());
        gameService.placeSymbol(fields.get(6));
        assertNotNull(game.getCurrentPlayer());
        gameService.placeSymbol(fields.get(2));
        assertNotNull(game.getCurrentPlayer());
        gameService.placeSymbol(fields.get(5));
        assertNotNull(game.getCurrentPlayer());
        gameService.placeSymbol(fields.get(7));
        assertNotNull(game.getCurrentPlayer());
        gameService.placeSymbol(fields.get(4));
        assertNotNull(game.getCurrentPlayer());

        // Now only one field is empty. If this field gets occupied, then the game should be
        // over with a draw. currentPlayer should be null
        gameService.placeSymbol(fields.get(8));
        assertNull(game.getCurrentPlayer());
    }
}
