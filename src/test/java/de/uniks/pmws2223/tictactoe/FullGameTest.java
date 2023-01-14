package de.uniks.pmws2223.tictactoe;

import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import static org.junit.Assert.*;
import static de.uniks.pmws2223.tictactoe.Constants.*;

public class FullGameTest extends ApplicationTest {
    private App app;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        app = new App();
        app.start(stage);
    }

    @Test
    public void fullGame() {
        // Setup Screen
        assertEquals(SETUP_TITLE, stage.getTitle());

        // Check that game can not be started with empty names
        clickOn("#startButton");
        assertEquals(SETUP_TITLE, stage.getTitle());

        clickOn("#playerNameX");
        write("Alice");
        clickOn("#startButton");
        assertEquals(SETUP_TITLE, stage.getTitle());

        clickOn("#playerNameO");
        write("Bob");
        clickOn("#startButton");

        // Ingame Screen
        assertEquals(INGAME_TITLE, stage.getTitle());

        // Check that Alice starts the game
        Label currentPlayerLabel = lookup("#currentPlayerLabel").query();
        assertEquals(currentPlayerLabel.getText(), CURRENT_PLAYER_TEXT + "Alice");

        // Let Alice win the game
        clickOn("#r1c1");

        // Check that nothing happens when player clicks on occupied field
        assertEquals(currentPlayerLabel.getText(), CURRENT_PLAYER_TEXT + "Bob");
        clickOn("#r1c1");
        assertEquals(currentPlayerLabel.getText(), CURRENT_PLAYER_TEXT + "Bob");
        clickOn("#r2c1");

        assertEquals(currentPlayerLabel.getText(), CURRENT_PLAYER_TEXT + "Alice");
        clickOn("#r2c2");

        assertEquals(currentPlayerLabel.getText(), CURRENT_PLAYER_TEXT + "Bob");
        clickOn("#r3c1");

        assertEquals(currentPlayerLabel.getText(), CURRENT_PLAYER_TEXT + "Alice");
        clickOn("#r3c3");

        assertEquals(currentPlayerLabel.getText(), WINNER_TEXT + "Alice");

        clickOn("#leaveButton");

        // Setup Screen
        assertEquals(SETUP_TITLE, stage.getTitle());
    }
}