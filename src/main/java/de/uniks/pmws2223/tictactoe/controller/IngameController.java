package de.uniks.pmws2223.tictactoe.controller;

import de.uniks.pmws2223.tictactoe.App;
import de.uniks.pmws2223.tictactoe.Main;
import de.uniks.pmws2223.tictactoe.service.GameService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class IngameController implements Controller {
    private final App app;
    private final GameService gameService;

    public IngameController(App app, GameService gameService) {
        this.app = app;
        this.gameService = gameService;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void init() {
    }

    @Override
    public Parent render() throws IOException {
        final Parent parent = FXMLLoader.load(Main.class.getResource("Ingame.fxml"));
        return parent;
    }

    @Override
    public void destroy() {
    }
}