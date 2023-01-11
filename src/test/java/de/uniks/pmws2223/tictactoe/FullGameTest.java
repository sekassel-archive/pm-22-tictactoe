package de.uniks.pmws2223.tictactoe;

import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

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
        // TODO
    }
}