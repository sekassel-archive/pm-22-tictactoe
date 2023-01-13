package de.uniks.pmws2223.tictactoe.controller;

import de.uniks.pmws2223.tictactoe.App;
import de.uniks.pmws2223.tictactoe.Main;
import de.uniks.pmws2223.tictactoe.model.Game;
import de.uniks.pmws2223.tictactoe.model.Player;
import de.uniks.pmws2223.tictactoe.service.GameService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static de.uniks.pmws2223.tictactoe.Constants.*;

public class IngameController implements Controller {
    private final App app;
    private final GameService gameService;
    private final ArrayList<FieldController> fieldControllers = new ArrayList<>();
    private PropertyChangeListener currentPlayerListener;

    public IngameController(App app, GameService gameService) {
        this.app = app;
        this.gameService = gameService;
    }

    @Override
    public String getTitle() {
        return INGAME_TITLE;
    }

    @Override
    public void init() {
    }

    @Override
    public Parent render() throws IOException {
        // Get FXML Objects
        final Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("Ingame.fxml")));
        final Button leaveButton = (Button) parent.lookup("#leaveButton");
        final Label currentPlayerLabel = (Label) parent.lookup("#currentPlayerLabel");

        Game game = gameService.getGame();

        // Setup of currentPlayerLabel
        currentPlayerListener = evt -> setCurrentPLayer(currentPlayerLabel, (Player) evt.getNewValue());
        game.listeners().addPropertyChangeListener(Game.PROPERTY_CURRENT_PLAYER, currentPlayerListener);
        setCurrentPLayer(currentPlayerLabel, game.getCurrentPlayer());

        // Setup of the field
        for(int row = 1; row <= 3; row++){
            for(int column = 1; column <= 3; column++){
                final Pane fieldPane = (Pane) parent.lookup("#r" + row + "c" + column);
                FieldController fieldController = new FieldController(game.getFields().get((row-1) * 3 + (column - 1)), fieldPane, gameService);
                fieldController.init();
                fieldControllers.add(fieldController);
            }
        }

        leaveButton.setOnMouseClicked(event -> app.show(new SetupController(app)));

        return parent;
    }

    @Override
    public void destroy() {
        gameService.getGame().listeners().removePropertyChangeListener(Game.PROPERTY_CURRENT_PLAYER, currentPlayerListener);
        for(FieldController fieldController : fieldControllers) fieldController.destroy();
    }

    private void setCurrentPLayer(Label label, Player player){
        if(player != null){
            label.setText(CURRENT_PLAYER_TEXT + player.getName());
        }
        else if(gameService.getGame().getWinner() != null){
            label.setText(WINNER_TEXT + gameService.getGame().getWinner().getName());
        }
        else{
            label.setText(DRAW_TEXT);
        }
    }
}