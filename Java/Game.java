package Java;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4148.608b7c78e modeling language!*/


import java.awt.*;
import java.util.*;
import java.util.List;

// line 44 "model.ump"
// line 114 "model.ump"

/**
 * This class controls the overall state and flow of the game. The bulk of the workload is delegated to other classes.
 *
 * @author Tom Baddeley
 * @version 1.0
 */
public class Game {

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Game Attributes


  //Game Associations
  private List<Card> solution;
  private List<Player> players;
  private Board board;
  private HashMap<String,Card> validCards = new HashMap<>();

  public boolean gameIsWon;
  private int turn;
  private int numOfPlayers;

  //------------------------
  // CONSTRUCTOR
  //------------------------
  /**
   * Creates a game object
   */
  public Game()
  {
    solution = new ArrayList<Card>();
    players = new ArrayList<Player>();
    board = new Board();
  }



  public boolean isGameWon() {
    return gameIsWon;
  }

  public Player getTurn() {
    Player a =  players.get(turn%numOfPlayers);
    while(a.isExcluded())a =  players.get(++turn%numOfPlayers);
    return a;
  }

  /**
   * Simulates the rolling of a dice
   * @return int value of dice
   */

  // simulates the rolling of two die
   public int rollDie(){
    return (int)(Math.random()*6)+1;
    
  }

  /**
   * Creates player objects and adds them to the list of players
   * @param numOfPlayers The number of players participating
   */

  public void initializePlayers(int numOfPlayers) {
    this.numOfPlayers = numOfPlayers;
    List<PlayerPiece> playerPieces = new ArrayList<>();
    playerPieces.add(new PlayerPiece("Miss Scarlett"));
    playerPieces.add(new PlayerPiece("Colonel Mustard"));
    playerPieces.add(new PlayerPiece("Mrs White"));
    playerPieces.add(new PlayerPiece("Mr Green"));
    playerPieces.add(new PlayerPiece("Mrs Peacock"));
    playerPieces.add(new PlayerPiece("Professor Plum"));
    //assigns player pieces to players and sets the pieces as active
    for(int i =0; i <numOfPlayers;i++){
      players.add(new Player("",playerPieces.get(i)));
    }
    //add player pieces to the board
    for(PlayerPiece p:playerPieces){
      board.addPlayerPieces(p.getName(),p);
    }

  }

  /**
   * Creates player objects using the selected characters and names.
   * @param names
   * @param tokens
   */

  public void initializePlayers(ArrayList<String> names, ArrayList<String> tokens) {
    this.numOfPlayers = names.size();

    List<PlayerPiece> playerPieces = new ArrayList<>();
    playerPieces.add(new PlayerPiece("Miss Scarlett"));
    playerPieces.add(new PlayerPiece("Colonel Mustard"));
    playerPieces.add(new PlayerPiece("Mrs White"));
    playerPieces.add(new PlayerPiece("Mr Green"));
    playerPieces.add(new PlayerPiece("Mrs Peacock"));
    playerPieces.add(new PlayerPiece("Professor Plum"));
    //add player pieces to the board
    for(PlayerPiece p:playerPieces){
      board.addPlayerPieces(p.getName(),p);
    }
    //assigns player pieces to players and sets the pieces as active
    for(int i =0; i <numOfPlayers;i++){
      players.add(new Player(names.get(i),board.getPlayerPieces().get(tokens.get(i))));
    }


  }

  /**
   * Starts the next turn
   */

   public void nextTurn() {
     getTurn().getPiece().setTurn(false);
     turn++;
     getTurn().getPiece().setTurn(true);
   }




  /**
   * Checks if the user enters a valid character, room and weapon then lets the opponents refute the suggestion by showing
   * cards that match suggested scenario.
   *
   * @param player The player making the suggestion.
   * @param killer The suspect.
   * @param room The suspected place of the murder.
   * @param weapon The suspected weapon of the murder
   *
   * @return false The user entered an invalid character, room or weapon.
   * @return true A valid suggestion was made.
   */

  public Boolean makeSuggestion(Player player,String killer,String room,String weapon) {
      PlayerPiece suspect = board.getPlayerPieces().get(killer);

      if (!validCards.containsKey(killer)) return false;
      if (!validCards.containsKey(room)) return false;
      if (!validCards.containsKey(weapon)) return false;

      if (!((RoomSquare) player.getPiece().getLocation()).getRoom().toString().equals(room))
        return false; //make sure players suggestion includes current room

      //opponents take turns refuting the suggestion in a clockwise fashion

      if(suspect != null)board.getRooms().get(room).addPlayer(suspect);
      board.getRooms().get(room).addWeapon(board.getWeapons().get(weapon));
      for (int i = players.indexOf(player) + 1; i < players.size(); i++) {
        Card c = players.get(i).refute(validCards.get(killer), validCards.get(room), validCards.get(weapon));
        if(c!=null)System.out.println(players.get(i).toString() + " shows " + c.toString());
      }
      for (int i = 0; i < players.indexOf(player); i++) {
        Card c = players.get(i).refute(validCards.get(killer), validCards.get(room), validCards.get(weapon));
        if(c!=null)System.out.println(players.get(i).toString() + " shows " + c.toString());
      }



    return true;
  }

  /**
   * Checks if the user enters a valid character, room and weapon then compares the accusation to the solution. If the
   * player guesses right he wins and the game ends otherwise that player is excluded from the game and may no longer
   * move.
   *
   * @param player The player making the accusation.
   * @param killer The accused.
   * @param room The accused place of the murder.
   * @param weapon The accused weapon of the murder
   *
   * @return false The user entered an invalid character, room or weapon.
   * @return true A valid accusation was made.
   */

  public Boolean makeAccusation(Player player,String killer,String room,String weapon) {

    //if user enters an invalid name/room/weapon return false so they can try again
    if (!validCards.containsKey(killer)) return false;
    if (!validCards.containsKey(room)) return false;
    if (!validCards.containsKey(weapon)) return false;

    if(!solution.get(0).getName().equals(weapon)) player.setExcluded(true);
    else if(!solution.get(1).getName().equals(room)) player.setExcluded(true);
    else if(!solution.get(2).getName().equals(killer)) player.setExcluded(true);
    else gameIsWon = true;

    return true;
  }

  /**
   * Creates the Card objects and splits them into 3 lists to represent each type of card. One card is selected from each
   * list to form the solution. The remaining cards are dealt to the players.
   */

  public void dealCards(){
    List<Card> weaponCard = new ArrayList<>();
    List<Card> roomCard = new ArrayList<>();
    List<Card> playerCard = new ArrayList<>();

    weaponCard.add(new Card("Candlestick"));
    weaponCard.add(new Card("Dagger"));
    weaponCard.add(new Card("Lead Pipe"));
    weaponCard.add(new Card("Revolver"));
    weaponCard.add(new Card("Rope"));
    weaponCard.add(new Card("Spanner"));

    playerCard.add(new Card("Colonel Mustard"));
    playerCard.add(new Card("Miss Scarlett"));
    playerCard.add(new Card("Mrs White"));
    playerCard.add(new Card("Mr Green"));
    playerCard.add(new Card("Mrs Peacock"));
    playerCard.add(new Card("Professor Plum"));

    roomCard.add(new Card("Dining Room"));
    roomCard.add(new Card("Ball Room"));
    roomCard.add(new Card("Billiard Room"));
    roomCard.add(new Card("Library"));
    roomCard.add(new Card("Lounge"));
    roomCard.add(new Card("Hall"));
    roomCard.add(new Card("Kitchen"));
    roomCard.add(new Card("Conservatory"));
    roomCard.add(new Card("Study"));

    for(Card c: playerCard)validCards.put(c.getName(),c);
    for(Card c: weaponCard)validCards.put(c.getName(),c);
    for(Card c: roomCard)validCards.put(c.getName(),c);

    //take a random player,room and weapon Card and add it to the solution while removing them
    solution.add(weaponCard.remove((int)(Math.random()*weaponCard.size())));
    solution.add(roomCard.remove((int)(Math.random()*roomCard.size())));
    solution.add(playerCard.remove((int)(Math.random()*playerCard.size())));

    List<Card> allCards = new ArrayList<>(weaponCard);
    allCards.addAll(roomCard);
    allCards.addAll(playerCard);
    //deal out the remaining cards to the players

    if(players.isEmpty()) return; //this is to avoid looping when testing.
    while(!allCards.isEmpty()){
      for(Player p:players){
        if(!allCards.isEmpty()){
          Card card = allCards.remove((int)(Math.random()*allCards.size()));
          if(p.getHand().size() < 4) p.addCard(card);
        }
      }
    }
  }

  /**
   * Gets the board associated with this game object
   * @return board
   */

  public Board getBoard() {
    return board;
  }

  /**
   * Sets the solution
   */

  public void setSolution(List<Card> solution) {
    this.solution = solution;
  }

  /**
   * returns a list of the players in the game
   * @return players
   */


  public List<Player> getPlayers() {
    return players;
  }




  public String toString()
  {
    return super.toString() + "["+ "]";
  }
}