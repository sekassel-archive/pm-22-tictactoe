package de.uniks.pmws2223.tictactoe.controller;

import de.uniks.pmws2223.tictactoe.App;
import de.uniks.pmws2223.tictactoe.Main;
import de.uniks.pmws2223.tictactoe.service.GameService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SetupController implements Controller {
    private final App app;

    public SetupController(App app) {
        this.app = app;
    }

    @Override
    public String getTitle() {
        return "Setup Screen";
    }

    @Override
    public void init() {
    }

    @Override
    public Parent render() throws IOException {
        final Parent parent = FXMLLoader.load(Main.class.getResource("Setup.fxml"));

        final Button startButton = (Button) parent.lookup("#startButton");
        final TextField playerNameX = (TextField) parent.lookup("#playerNameX");
        final TextField playerNameO = (TextField) parent.lookup("#playerNameO");

        startButton.setOnMouseClicked(event -> {
            if(!(playerNameX.getText().isBlank() || playerNameO.getText().isBlank())){
                app.show(new IngameController(app, new GameService(playerNameX.getText(), playerNameO.getText())));
            }
        });

        return parent;
    }

    @Override
    public void destroy() {
    }
}