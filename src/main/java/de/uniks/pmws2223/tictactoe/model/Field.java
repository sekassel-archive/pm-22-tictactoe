package de.uniks.pmws2223.tictactoe.model;
import java.beans.PropertyChangeSupport;

public class Field
{
   public static final String PROPERTY_GAME = "game";
   public static final String PROPERTY_PLAYER = "player";
   public static final String PROPERTY_RIGHT = "right";
   public static final String PROPERTY_LEFT = "left";
   public static final String PROPERTY_TOP = "top";
   public static final String PROPERTY_BOTTOM = "bottom";
   private Game game;
   private Player player;
   private Field right;
   private Field left;
   private Field top;
   private Field bottom;
   protected PropertyChangeSupport listeners;

   public Game getGame()
   {
      return this.game;
   }

   public Field setGame(Game value)
   {
      if (this.game == value)
      {
         return this;
      }

      final Game oldValue = this.game;
      if (this.game != null)
      {
         this.game = null;
         oldValue.withoutFields(this);
      }
      this.game = value;
      if (value != null)
      {
         value.withFields(this);
      }
      this.firePropertyChange(PROPERTY_GAME, oldValue, value);
      return this;
   }

   public Player getPlayer()
   {
      return this.player;
   }

   public Field setPlayer(Player value)
   {
      if (this.player == value)
      {
         return this;
      }

      final Player oldValue = this.player;
      if (this.player != null)
      {
         this.player = null;
         oldValue.withoutFields(this);
      }
      this.player = value;
      if (value != null)
      {
         value.withFields(this);
      }
      this.firePropertyChange(PROPERTY_PLAYER, oldValue, value);
      return this;
   }

   public Field getRight()
   {
      return this.right;
   }

   public Field setRight(Field value)
   {
      if (this.right == value)
      {
         return this;
      }

      final Field oldValue = this.right;
      if (this.right != null)
      {
         this.right = null;
         oldValue.setLeft(null);
      }
      this.right = value;
      if (value != null)
      {
         value.setLeft(this);
      }
      this.firePropertyChange(PROPERTY_RIGHT, oldValue, value);
      return this;
   }

   public Field getLeft()
   {
      return this.left;
   }

   public Field setLeft(Field value)
   {
      if (this.left == value)
      {
         return this;
      }

      final Field oldValue = this.left;
      if (this.left != null)
      {
         this.left = null;
         oldValue.setRight(null);
      }
      this.left = value;
      if (value != null)
      {
         value.setRight(this);
      }
      this.firePropertyChange(PROPERTY_LEFT, oldValue, value);
      return this;
   }

   public Field getTop()
   {
      return this.top;
   }

   public Field setTop(Field value)
   {
      if (this.top == value)
      {
         return this;
      }

      final Field oldValue = this.top;
      if (this.top != null)
      {
         this.top = null;
         oldValue.setBottom(null);
      }
      this.top = value;
      if (value != null)
      {
         value.setBottom(this);
      }
      this.firePropertyChange(PROPERTY_TOP, oldValue, value);
      return this;
   }

   public Field getBottom()
   {
      return this.bottom;
   }

   public Field setBottom(Field value)
   {
      if (this.bottom == value)
      {
         return this;
      }

      final Field oldValue = this.bottom;
      if (this.bottom != null)
      {
         this.bottom = null;
         oldValue.setTop(null);
      }
      this.bottom = value;
      if (value != null)
      {
         value.setTop(this);
      }
      this.firePropertyChange(PROPERTY_BOTTOM, oldValue, value);
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
      this.setGame(null);
      this.setPlayer(null);
      this.setRight(null);
      this.setLeft(null);
      this.setTop(null);
      this.setBottom(null);
   }
}
