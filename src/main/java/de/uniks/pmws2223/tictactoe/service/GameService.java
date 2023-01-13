package de.uniks.pmws2223.tictactoe.service;

import de.uniks.pmws2223.tictactoe.model.Field;
import de.uniks.pmws2223.tictactoe.model.Game;
import de.uniks.pmws2223.tictactoe.model.Player;

public class GameService {
    private final Game game;

    public GameService(String playerNameX, String playerNameO){
        // Setup of player
        game = new Game().withPlayers(
                new Player().setName(playerNameX).setSymbol("X"),
                new Player().setName(playerNameO).setSymbol("O")
        );

        game.setCurrentPlayer(game.getPlayers().get(0));

        // Setup of field
        for(int i = 0; i < 9; i++) game.withFields(new Field());

        for(int i = 0; i < 9; i++){
            if(i%3 != 2){
                game.getFields().get(i).setRight(game.getFields().get(i+1));
            }

            if(i < 6){
                game.getFields().get(i).setBottom(game.getFields().get(i+3));
            }
        }
    }

    public Game getGame() {
        return game;
    }

    public void placeSymbol(Field field){
        if(field.getPlayer() != null || game.getWinner() != null) return;

        // Set Player of field
        field.setPlayer(game.getCurrentPlayer());

        checkWinner();

        // Set next player if there is no winner
        if(game.getWinner() == null && game.getCurrentPlayer() != null){
            for(int i = 0; i < game.getPlayers().size(); i++){
                if(game.getPlayers().get(i) != game.getCurrentPlayer()){
                    game.setCurrentPlayer(game.getPlayers().get(i));
                    break;
                }
            }
        }
    }

    private void checkWinner(){
        // Check if there is a winner
        // Check all rows
        checkField(game.getFields().get(0), 1, 0);
        checkField(game.getFields().get(3), 1, 0);
        checkField(game.getFields().get(6), 1, 0);

        // Check all columns
        checkField(game.getFields().get(0), 0, -1);
        checkField(game.getFields().get(1), 0, -1);
        checkField(game.getFields().get(2), 0, -1);

        // Check all diagonals
        checkField(game.getFields().get(0), 1, -1);
        checkField(game.getFields().get(2), -1, -1);

        // Check if the field is full and no symbol can be placed anymore
        if(game.getWinner() == null){
            checkDraw();
        }
        else{
            game.setCurrentPlayer(null);
        }
    }

    private void checkField(Field field, int horizontalDir, int verticalDir){
        // Checks if there is a winning row in a direction
        // horizontalDir = -1 => move left  |  horizontalDir = 0 => don't move  |  horizontalDir = 1 => move right
        // verticalDir = -1 => move down  |  verticalDir = 0 => don't move  |  verticalDir = 1 => move up

        int countSymbols = 1;
        Player playerOfField = field.getPlayer();

        while(field != null){
            // Get next field to check
            Field nextCheck = field;
            if(horizontalDir == -1){
                nextCheck = nextCheck.getLeft();
            }
            else if(horizontalDir == 1){
                nextCheck = nextCheck.getRight();
            }

            if(nextCheck != null){
                if(verticalDir == -1){
                    nextCheck = nextCheck.getBottom();
                }
                else if(verticalDir == 1){
                    nextCheck = nextCheck.getTop();
                }
            }

            if(nextCheck != null && nextCheck.getPlayer() != null && field.getPlayer() == nextCheck.getPlayer()){
                countSymbols++;
            }
            else{
                nextCheck = null;
            }

            field = nextCheck;
        }

        if(countSymbols == 3){
            game.setWinner(playerOfField);
        }
    }

    private void checkDraw(){
        boolean noEmptyField = true;
        for(Field field : game.getFields()){
            if(field.getPlayer() == null){
                noEmptyField = false;
                break;
            }
        }

        if(noEmptyField){
            game.setCurrentPlayer(null);
        }
    }
}
