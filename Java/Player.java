package Java;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4148.608b7c78e modeling language!*/


import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * This class represents the player. The player has a playerpiece to represent their character. A player may refute suggestions
 * with cards in their hand.
 */
public class Player
{


  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Player Attributes
  private boolean excluded =false;

  //Player Associations
  private List<Card> hand = new ArrayList<>();
  private PlayerPiece piece;
  private String name;


  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Player(String name, PlayerPiece piece)
  {
    this.piece = piece;
    this.name = name;
    piece.setOwner(name);

  }

  //------------------------
  // INTERFACE
  //------------------------

    /**
     * When a player is excluded they may no longer move, make suggestions or make accusations however they are still free to refute
     * and when suggested of murder their playerpiece will still move to the room
     * @param aExcluded
     */
  public void setExcluded(boolean aExcluded)
  {
       excluded = aExcluded;
  }


  public boolean isExcluded()
  {
    return excluded;
  }
  /* Code from template association_GetMany */


    public void addCard(Card c){
      hand.add(c);
  }


  /* Code from template association_GetMany */
  public PlayerPiece getPiece()
  {
    return piece;
  }

    public String getName() {
        return name;
    }

    public List<Card> getHand(){
      return hand;
  }

    /**
     * When a suggestion is made a player may refute the claim by showing a card that matches the scenario. If the
     * player has two matching cards in their hand they may decide which one to show.
     * @param killer
     * @param room
     * @param weapon
     * @return Card The card the player shows his/her opponents
     */


  // line 16 "model.ump"
   public Card refute(Card killer,Card room, Card weapon){
       List<Card> matches = new ArrayList<>();
      for(Card c:hand) {
          if (c.equals(killer)) matches.add(c);
          if (c.equals(room)) matches.add(c);
          if (c.equals(weapon)) matches.add(c);
      }
      if (matches.size()<10&& matches.size()>0) return matches.get(0);
      else if (matches.size() > 10){//change 10 to 1 for the text based version
          Scanner sc2 = new Scanner(System.in);
          while(1>0) {
              System.out.print("Enter a number to choose the card you would like to show. ");
              for (int i = 0; i < matches.size(); i++) {
                  System.out.print((i+1) + ". " + matches.get(i).toString() + " ");
              }
              String choice = sc2.nextLine();
              if(choice.equals("1")) {
                  sc2.close();
                  return matches.get(0);
              }
              if(choice.equals("2")){
                  sc2.close();
                  return matches.get(1);
              }
              if(choice.equals("3")&&matches.size()==3){
                  sc2.close();
                  return matches.get(2);
              }
          }

      }
      return null;
  }

    /**
     * Draws the players hand to the GUI
     * @param g
     * @param width
     * @param height
     */

  public void drawHand(Graphics g,int width,int height){
       Graphics2D g2d = (Graphics2D)g;
      g2d.setStroke(new BasicStroke(3));
       for(int i =0;i <hand.size();i++){
          g2d.drawRect(i*width/7,0,width/7,height);
          g2d.drawString(hand.get(i).toString(),i*width/7+width/35,height/2);
      }

  }


  public String toString()
  {
    return piece.getName();
  }


}