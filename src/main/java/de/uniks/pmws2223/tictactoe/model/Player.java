package de.uniks.pmws2223.tictactoe.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Collections;
import java.util.Collection;
import java.beans.PropertyChangeSupport;

public class Player
{
   public static final String PROPERTY_NAME = "name";
   public static final String PROPERTY_SYMBOL = "symbol";
   public static final String PROPERTY_FIELDS = "fields";
   public static final String PROPERTY_GAME = "game";
   private String name;
   private String symbol;
   private List<Field> fields;
   private Game game;
   protected PropertyChangeSupport listeners;

   public String getName()
   {
      return this.name;
   }

   public Player setName(String value)
   {
      if (Objects.equals(value, this.name))
      {
         return this;
      }

      final String oldValue = this.name;
      this.name = value;
      this.firePropertyChange(PROPERTY_NAME, oldValue, value);
      return this;
   }

   public String getSymbol()
   {
      return this.symbol;
   }

   public Player setSymbol(String value)
   {
      if (Objects.equals(value, this.symbol))
      {
         return this;
      }

      final String oldValue = this.symbol;
      this.symbol = value;
      this.firePropertyChange(PROPERTY_SYMBOL, oldValue, value);
      return this;
   }

   public List<Field> getFields()
   {
      return this.fields != null ? Collections.unmodifiableList(this.fields) : Collections.emptyList();
   }

   public Player withFields(Field value)
   {
      if (this.fields == null)
      {
         this.fields = new ArrayList<>();
      }
      if (!this.fields.contains(value))
      {
         this.fields.add(value);
         value.setPlayer(this);
         this.firePropertyChange(PROPERTY_FIELDS, null, value);
      }
      return this;
   }

   public Player withFields(Field... value)
   {
      for (final Field item : value)
      {
         this.withFields(item);
      }
      return this;
   }

   public Player withFields(Collection<? extends Field> value)
   {
      for (final Field item : value)
      {
         this.withFields(item);
      }
      return this;
   }

   public Player withoutFields(Field value)
   {
      if (this.fields != null && this.fields.remove(value))
      {
         value.setPlayer(null);
         this.firePropertyChange(PROPERTY_FIELDS, value, null);
      }
      return this;
   }

   public Player withoutFields(Field... value)
   {
      for (final Field item : value)
      {
         this.withoutFields(item);
      }
      return this;
   }

   public Player withoutFields(Collection<? extends Field> value)
   {
      for (final Field item : value)
      {
         this.withoutFields(item);
      }
      return this;
   }

   public Game getGame()
   {
      return this.game;
   }

   public Player setGame(Game value)
   {
      if (this.game == value)
      {
         return this;
      }

      final Game oldValue = this.game;
      if (this.game != null)
      {
         this.game = null;
         oldValue.withoutPlayers(this);
      }
      this.game = value;
      if (value != null)
      {
         value.withPlayers(this);
      }
      this.firePropertyChange(PROPERTY_GAME, oldValue, value);
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

   @Override
   public String toString()
   {
      final StringBuilder result = new StringBuilder();
      result.append(' ').append(this.getName());
      result.append(' ').append(this.getSymbol());
      return result.substring(1);
   }

   public void removeYou()
   {
      this.withoutFields(new ArrayList<>(this.getFields()));
      this.setGame(null);
   }
}
