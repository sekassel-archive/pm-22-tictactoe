package de.uniks.pmws2223.tictactoe.controller;

import de.uniks.pmws2223.tictactoe.model.Field;
import de.uniks.pmws2223.tictactoe.service.GameService;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class FieldController implements Controller {

    private final Field field;
    private final Pane pane;
    private final GameService gameService;
    private final Label label;

    public FieldController(Field field, Pane pane, GameService gameService) {
        this.field = field;
        this.pane = pane;
        this.gameService = gameService;
        this.label = (Label) this.pane.getChildren().get(0);
    }
    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void init() {
        this.label.setText("");

        this.pane.setOnMouseClicked(event -> {
            gameService.placeSymbol(this.field);
            if(this.field.getPlayer() != null) this.label.setText(this.field.getPlayer().getSymbol());
        });
    }

    @Override
    public Parent render() throws IOException {
        return null;
    }

    @Override
    public void destroy() {

    }
}
