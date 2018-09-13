package Java;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4148.608b7c78e modeling language!*/


import java.util.*;
/**
 * This class represents a playing card. A Card object holds a string.
 *
 * @author Tom Baddeley
 * @version 1.0
 */
// line 61 "model.ump"
// line 121 "model.ump"
public class Card
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Card Attributes
  private String name;


  //------------------------
  // CONSTRUCTOR
  //------------------------
/**
 * Creates a Card object
 * @param aName The name of the card.
 */
  public Card(String aName)
  {
    name = aName;

  }
  /**
   * @return returns the card's name
   */

  public String getName() {
    return name;
  }

  // line 67 "model.ump"
   public Boolean equals(Card c){
    return c.name.equals(this.name);
  }

@Override
  public String toString()
  {
    return "["+
           name+ "]";
  }
}