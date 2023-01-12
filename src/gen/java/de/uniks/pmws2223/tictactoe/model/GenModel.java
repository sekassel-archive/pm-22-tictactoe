package de.uniks.pmws2223.tictactoe.model;

import org.fulib.builder.ClassModelDecorator;
import org.fulib.builder.ClassModelManager;
import org.fulib.builder.reflect.Link;

import java.util.List;

public class GenModel implements ClassModelDecorator {

    class Game {
        Player currentPlayer;
        Player winner;

        @Link("game")
        List<Player> players;

        @Link("game")
        List<Field> fields;
    }

    class Player {
        String name;
        String symbol;

        @Link("players")
        Game game;

        @Link("player")
        List<Field> fields;
    }

    class Field {
        @Link("fields")
        Game game;

        @Link("fields")
        Player player;

        @Link("left")
        Field right;

        @Link("right")
        Field left;

        @Link("bottom")
        Field top;

        @Link("top")
        Field bottom;
    }

    @Override
    public void decorate(ClassModelManager mm) {
        mm.haveNestedClasses(GenModel.class);
    }
}
