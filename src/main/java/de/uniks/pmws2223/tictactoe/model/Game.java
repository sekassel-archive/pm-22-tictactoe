package de.uniks.pmws2223.tictactoe.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Collections;
import java.util.Collection;
import java.beans.PropertyChangeSupport;

public class Game
{
   public static final String PROPERTY_CURRENT_PLAYER = "currentPlayer";
   public static final String PROPERTY_WINNER = "winner";
   public static final String PROPERTY_FIELDS = "fields";
   public static final String PROPERTY_PLAYERS = "players";
   private Player currentPlayer;
   private Player winner;
   private List<Field> fields;
   private List<Player> players;
   protected PropertyChangeSupport listeners;

   public Player getCurrentPlayer()
   {
      return this.currentPlayer;
   }

   public Game setCurrentPlayer(Player value)
   {
      if (Objects.equals(value, this.currentPlayer))
      {
         return this;
      }

      final Player oldValue = this.currentPlayer;
      this.currentPlayer = value;
      this.firePropertyChange(PROPERTY_CURRENT_PLAYER, oldValue, value);
      return this;
   }

   public Player getWinner()
   {
      return this.winner;
   }

   public Game setWinner(Player value)
   {
      if (Objects.equals(value, this.winner))
      {
         return this;
      }

      final Player oldValue = this.winner;
      this.winner = value;
      this.firePropertyChange(PROPERTY_WINNER, oldValue, value);
      return this;
   }

   public List<Field> getFields()
   {
      return this.fields != null ? Collections.unmodifiableList(this.fields) : Collections.emptyList();
   }

   public Game withFields(Field value)
   {
      if (this.fields == null)
      {
         this.fields = new ArrayList<>();
      }
      if (!this.fields.contains(value))
      {
         this.fields.add(value);
         value.setGame(this);
         this.firePropertyChange(PROPERTY_FIELDS, null, value);
      }
      return this;
   }

   public Game withFields(Field... value)
   {
      for (final Field item : value)
      {
         this.withFields(item);
      }
      return this;
   }

   public Game withFields(Collection<? extends Field> value)
   {
      for (final Field item : value)
      {
         this.withFields(item);
      }
      return this;
   }

   public Game withoutFields(Field value)
   {
      if (this.fields != null && this.fields.remove(value))
      {
         value.setGame(null);
         this.firePropertyChange(PROPERTY_FIELDS, value, null);
      }
      return this;
   }

   public Game withoutFields(Field... value)
   {
      for (final Field item : value)
      {
         this.withoutFields(item);
      }
      return this;
   }

   public Game withoutFields(Collection<? extends Field> value)
   {
      for (final Field item : value)
      {
         this.withoutFields(item);
      }
      return this;
   }

   public List<Player> getPlayers()
   {
      return this.players != null ? Collections.unmodifiableList(this.players) : Collections.emptyList();
   }

   public Game withPlayers(Player value)
   {
      if (this.players == null)
      {
         this.players = new ArrayList<>();
      }
      if (!this.players.contains(value))
      {
         this.players.add(value);
         value.setGame(this);
         this.firePropertyChange(PROPERTY_PLAYERS, null, value);
      }
      return this;
   }

   public Game withPlayers(Player... value)
   {
      for (final Player item : value)
      {
         this.withPlayers(item);
      }
      return this;
   }

   public Game withPlayers(Collection<? extends Player> value)
   {
      for (final Player item : value)
      {
         this.withPlayers(item);
      }
      return this;
   }

   public Game withoutPlayers(Player value)
   {
      if (this.players != null && this.players.remove(value))
      {
         value.setGame(null);
         this.firePropertyChange(PROPERTY_PLAYERS, value, null);
      }
      return this;
   }

   public Game withoutPlayers(Player... value)
   {
      for (final Player item : value)
      {
         this.withoutPlayers(item);
      }
      return this;
   }

   public Game withoutPlayers(Collection<? extends Player> value)
   {
      for (final Player item : value)
      {
         this.withoutPlayers(item);
      }
      return this;
   }

   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (this.listeners != null)
      {
         this.listeners.firePropertyChange(propertyName, oldValue, newValue);
         return true;
      }
      return false;
   }

   public PropertyChangeSupport listeners()
   {
      if (this.listeners == null)
      {
         this.listeners = new PropertyChangeSupport(this);
      }
      return this.listeners;
   }

   public void removeYou()
   {
      this.withoutFields(new ArrayList<>(this.getFields()));
      this.withoutPlayers(new ArrayList<>(this.getPlayers()));
   }
}
