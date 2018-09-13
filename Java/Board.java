package Java;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4148.608b7c78e modeling language!*/


import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

/**
 * The board stores the location of all the parts of the board, the weapons, the player pieces, the rooms and all the
 * squares.
 */
public class Board
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Board Attributes
  private Square[][] squares = new Square[25][24];

  //Board Associations
  private Map<String,Room> rooms;
  private Map<String,Weapon> weapons;
  private Map<String,PlayerPiece> playerPieces = new HashMap<>();


    public Map<String, PlayerPiece> getPlayerPieces() {
        return playerPieces;
    }

    public Map<String, Room> getRooms() {
        return rooms;
    }

    public Map<String, Weapon> getWeapons() {
        return weapons;
    }

    public void addPlayerPieces(String name,PlayerPiece pp){
        playerPieces.put(name,pp);
    }
//------------------------
  // CONSTRUCTOR
  //------------------------

  public Board()
  {
    rooms = new HashMap<>();
    weapons = new HashMap<>();
  }

    /**
     * Sets up the board so a game of cluedo can be played.
     */
  public void initializeBoard() {
      for (int row = 0; row < 25; row++) {
          for (int col = 0; col < 24; col++) {
              squares[row][col] = new Square(row, col);
          }
      }
      //add dead squares
      for (int i = 10; i < 15; i++) {
          for (int j = 10; j < 17; j++) squares[j][i] = new DeadSquare(j, i);
      }
      for (int i = 0; i < 9; i++) squares[0][i] = new DeadSquare(0, i);
      for (int i = 10; i < 14; i++) squares[0][i] = new DeadSquare(0, i);
      for (int i = 15; i < 24; i++) squares[0][i] = new DeadSquare(0, i);
      squares[1][6] = new DeadSquare(1, 6);
      squares[1][17] = new DeadSquare(1, 17);
      squares[5][23] = new DeadSquare(5, 23);
      squares[7][23] = new DeadSquare(7, 23);
      squares[13][23] = new DeadSquare(13, 23);
      squares[14][23] = new DeadSquare(14, 23);
      squares[18][23] = new DeadSquare(18, 23);
      squares[20][23] = new DeadSquare(20, 23);
      squares[6][0] = new DeadSquare(6, 0);
      squares[8][0] = new DeadSquare(8, 0);
      squares[16][0] = new DeadSquare(16, 0);
      squares[18][0] = new DeadSquare(18, 0);
      squares[24][6] = new DeadSquare(24, 6);
      squares[24][8] = new DeadSquare(24, 8);
      squares[24][15] = new DeadSquare(24, 15);
      squares[24][17] = new DeadSquare(24, 17);

      //add kitchen
      List<RoomSquare> kitchenSquares = new ArrayList<>();
      for (int i = 1; i < 6; i++) {
          for (int j = 0; j < 6; j++) {
              squares[i][j] = new RoomSquare(i, j);
              kitchenSquares.add((RoomSquare) squares[i][j]);
          }
      }
      for (int i = 1; i < 6; i++) {
          squares[6][i] = new RoomSquare(6, i);
          kitchenSquares.add((RoomSquare) squares[6][i]);
      }
      rooms.put("Kitchen", new Room(kitchenSquares, "Kitchen"));
      //add dining room
      List<RoomSquare> dinRoomSquares = new ArrayList<>();
      for (int i = 10; i < 16; i++) {
          for (int j = 0; j < 8; j++) {
              squares[i][j] = new RoomSquare(i, j);
              dinRoomSquares.add((RoomSquare) squares[i][j]);
          }
      }
      for (int i = 0; i < 5; i++) {
          squares[9][i] = new RoomSquare(9, i);
          dinRoomSquares.add((RoomSquare) squares[9][i]);
      }
      rooms.put("Dining Room", new Room(dinRoomSquares, "Dining Room"));
      //add lounge
      List<RoomSquare> loungeSquares = new ArrayList<>();
      for (int i = 19; i < 24; i++) {
          for (int j = 0; j < 7; j++) {
              squares[i][j] = new RoomSquare(i, j);
              loungeSquares.add((RoomSquare) squares[i][j]);
          }
      }
      for (int i = 0; i < 6; i++) {
          squares[24][i] = new RoomSquare(24, i);
          loungeSquares.add((RoomSquare) squares[24][i]);
      }
      rooms.put("Lounge", new Room(loungeSquares, "Lounge"));
      //add hall
      List<RoomSquare> hallSquares = new ArrayList<>();
      for (int i = 18; i < 25; i++) {
          for (int j = 9; j < 15; j++) {
              squares[i][j] = new RoomSquare(i, j);
              hallSquares.add((RoomSquare) squares[i][j]);
          }
      }
      rooms.put("Hall", new Room(hallSquares, "Hall"));
      //add study
      List<RoomSquare> studySquares = new ArrayList<>();
      for (int i = 21; i < 24; i++) {
          for (int j = 17; j < 24; j++) {
              squares[i][j] = new RoomSquare(i, j);
              studySquares.add((RoomSquare) squares[i][j]);
          }
      }
      for (int i = 18; i < 24; i++) {
          squares[24][i] = new RoomSquare(24, i);
          studySquares.add((RoomSquare) squares[24][i]);
      }
      rooms.put("Study", new Room(studySquares, "Study"));
      //add library
      List<RoomSquare> librarySquares = new ArrayList<>();
      for (int i = 15; i < 18; i++) {
          for (int j = 17; j < 24; j++) {
              squares[i][j] = new RoomSquare(i, j);
              librarySquares.add((RoomSquare) squares[i][j]);
          }
      }
      for (int i = 18; i < 23; i++) {
          squares[18][i] = new RoomSquare(18, i);
          librarySquares.add((RoomSquare) squares[18][i]);
          squares[14][i] = new RoomSquare(14, i);
          librarySquares.add((RoomSquare) squares[14][i]);
      }
      rooms.put("Library", new Room(librarySquares, "Library"));
      //add billiard room
      List<RoomSquare> billiardSquares = new ArrayList<>();
      for (int i = 8; i < 13; i++) {
          for (int j = 18; j < 24; j++) {
              squares[i][j] = new RoomSquare(i, j);
              billiardSquares.add((RoomSquare) squares[i][j]);
          }
      }
      rooms.put("Billiard Room", new Room(billiardSquares, "Billiard Room"));

      //add conservatory
      List<RoomSquare> conservatorySquares = new ArrayList<>();
      for (int i = 1; i < 5; i++) {
          for (int j = 18; j < 24; j++) {
              squares[i][j] = new RoomSquare(i, j);
              conservatorySquares.add((RoomSquare) squares[i][j]);
          }
      }
      for (int i = 19; i < 23; i++) {
          squares[5][i] = new RoomSquare(5, i);
          conservatorySquares.add((RoomSquare) squares[5][i]);
      }
      rooms.put("Conservatory", new Room(conservatorySquares, "Conservatory"));

      //add ball room
      List<RoomSquare> ballroomSquares = new ArrayList<>();
      for (int i = 2; i < 8; i++) {
          for (int j = 8; j < 16; j++) {
              squares[i][j] = new RoomSquare(i, j);
              ballroomSquares.add((RoomSquare) squares[i][j]);
          }
      }
      for (int i = 10; i < 14; i++) {
          squares[1][i] = new RoomSquare(1, i);
          ballroomSquares.add((RoomSquare) squares[1][i]);
      }
      rooms.put("Ball Room", new Room(ballroomSquares, "Ball Room"));


      //set entry squares
      squares[5][16].setEntrySquare(true);
      squares[5][7].setEntrySquare(true);
      squares[5][18].setEntrySquare(true);
      squares[7][4].setEntrySquare(true);
      squares[8][9].setEntrySquare(true);
      squares[8][14].setEntrySquare(true);
      squares[9][17].setEntrySquare(true);
      squares[12][8].setEntrySquare(true);
      squares[13][20].setEntrySquare(true);
      squares[13][22].setEntrySquare(true);
      squares[16][6].setEntrySquare(true);
      squares[16][16].setEntrySquare(true);
      squares[17][12].setEntrySquare(true);
      squares[18][6].setEntrySquare(true);
      squares[17][11].setEntrySquare(true);
      squares[20][15].setEntrySquare(true);
      squares[20][17].setEntrySquare(true);
      //add entry squares as room exits
      rooms.get("Ball Room").addExit(squares[5][7]);
      rooms.get("Ball Room").addExit(squares[5][16]);
      rooms.get("Ball Room").addExit(squares[8][9]);
      rooms.get("Ball Room").addExit(squares[8][14]);
      rooms.get("Kitchen").addExit(squares[7][4]);
      rooms.get("Conservatory").addExit(squares[5][18]);
      rooms.get("Billiard Room").addExit(squares[9][17]);
      rooms.get("Billiard Room").addExit(squares[13][22]);
      rooms.get("Dining Room").addExit(squares[12][8]);
      rooms.get("Dining Room").addExit(squares[16][6]);
      rooms.get("Lounge").addExit(squares[18][6]);
      rooms.get("Hall").addExit(squares[17][12]);
      rooms.get("Hall").addExit(squares[17][11]);
      rooms.get("Hall").addExit(squares[20][15]);
      rooms.get("Study").addExit(squares[20][17]);
      rooms.get("Library").addExit(squares[13][20]);
      rooms.get("Library").addExit(squares[16][16]);

      //create weapons
      weapons.put("Candlestick", new Weapon("Candlestick"));
      weapons.put("Dagger", new Weapon("Dagger"));
      weapons.put("Lead Pipe", new Weapon("Lead Pipe"));
      weapons.put("revolver", new Weapon("revolver"));
      weapons.put("Rope", new Weapon("Rope"));
      weapons.put("Spanner", new Weapon("Spanner"));

      //place weapons in a random room by creating two lists to represent the keys, shuffling them then adding each weapon to a room.
      List<Object> weaponKey = Arrays.asList(weapons.keySet().toArray());
      List<Object> roomKey = Arrays.asList(rooms.keySet().toArray());
      Collections.shuffle(roomKey);
      for (int i = 0; i < weapons.keySet().size(); i++) {
          rooms.get(roomKey.get(i).toString()).addWeapon(weapons.get(weaponKey.get(i).toString()));
      }

      playerPieces.get("Miss Scarlett").setLocation(squares[24][7]);
      playerPieces.get("Colonel Mustard").setLocation(squares[17][0]);
      playerPieces.get("Mrs White").setLocation(squares[0][9]);
      playerPieces.get("Mr Green").setLocation(squares[0][14]);
      playerPieces.get("Mrs Peacock").setLocation(squares[6][23]);
      playerPieces.get("Professor Plum").setLocation(squares[19][23]);
  }

    /**
     * Tests if an attempted move is valid
     * @param p      The player attempting to move
     * @param s The moves the player is attempting
     * @return Is the move valid
     */
  public boolean checkValidMove(PlayerPiece p, Square s){

      return !((s instanceof DeadSquare) || s.getPlayer()== null);//a player may not move on a dead square or another player's square

  }

    /**
     * converts the square the player is moving to into a string that can be used with other methods.
     *
     * @param p  player attempting to move
     * @param s the square the palyer wishes to move to
     * @return
     */

  public String[] moveToString(PlayerPiece p,Square s){
      int dy = s.getY() - p.getLocation().getY();
      int dx = s.getX() - p.getLocation().getX();
      String[] moves = new String[0];
      int numOfMoves = 0;
      ArrayList<String> temp = new ArrayList<>();
      //move one step at a time towards the goal making sure each step is valid
      while(!(dy == 0 && dx ==0)){
          if(dx > 0 ){
              temp = new ArrayList<String>(Arrays.asList(moves));
              temp.add("1");
              temp.add("r");
              if(checkValidMove(p,temp.toArray(new String[0]))){
                  moves = temp.toArray(new String[0]);
                  dx--;
                  continue;
              }
              temp.clear();
          }
          if(dx < 0 ){
              temp = new ArrayList<String>(Arrays.asList(moves));
              temp.add("1");
              temp.add("l");
              if(checkValidMove(p,temp.toArray(new String[0]))){
                  moves = temp.toArray(new String[0]);
                  dx++;
                  continue;
              }
              temp.clear();
          }
          if(dy < 0 ){
              temp = new ArrayList<String>(Arrays.asList(moves));
              temp.add("1");
              temp.add("u");
              if(checkValidMove(p,temp.toArray(new String[0]))){
                  moves = temp.toArray(new String[0]);
                  dy++;
                  continue;
              }
              temp.clear();
          }
          if(dy > 0 ){
              temp = new ArrayList<String>(Arrays.asList(moves));
              temp.add("1");
              temp.add("d");
              if(checkValidMove(p,(temp.toArray(new String[0])))){
                  moves = temp.toArray(new String[0]);
                  dy--;
                  continue;
              }
              temp.clear();
          }
          return null;//stuck by a wall
      }

      return moves;


  }

    /**
     * tests if a player is blocked in a room by an opponent standing in the doorway
     * @param p
     * @return
     */

  public Boolean isPlayerBlocked(PlayerPiece p){
        if(p.getLocation() instanceof RoomSquare) {
            for(Square s:((RoomSquare) p.getLocation()).getRoom().getExits()){
                if(s.getPlayer()== null) return false;
            }
            return true;
        }
        return false;

  }

    /**
     * checks if the player is attempting a valid move
     * @param p
     * @param moves
     * @return
     */

    public boolean checkValidMove(PlayerPiece p, String[] moves){
        try {
            int playerX = p.getLocation().getX();
            int playerY = p.getLocation().getY();
            int totalDx = 0, totalDy = 0;
            List<Square> visitiedSquares = new ArrayList<>();
            Room initialRoom = null;
            Boolean leftRoom = false;

            if (p.getLocation() instanceof RoomSquare) initialRoom = ((RoomSquare) p.getLocation()).getRoom();

            for (int i = 0; i < moves.length; i = i + 2) {
                if(moves[i]==null) break;
                int dx = 0, dy = 0;
                if (moves[i + 1].equalsIgnoreCase("L")) dx = -Integer.parseInt(moves[i]);
                if (moves[i + 1].equalsIgnoreCase("R")) dx = Integer.parseInt(moves[i]);
                if (moves[i + 1].equalsIgnoreCase("U")) dy = -Integer.parseInt(moves[i]);
                if (moves[i + 1].equalsIgnoreCase("D")) dy = Integer.parseInt(moves[i]);

                //player can not move out of bounds
                if (dx + playerX + totalDx > 23 || dx + playerX + totalDx < 0) throw new IllegalMoveException("Player can not Move out of bounds.");
                if (dy + playerY + totalDy > 24 || dy + playerY + totalDy < 0) throw new IllegalMoveException("Player can not Move out of bounds.");

                //check if a horizontal move is ok
                if (dx != 0) {
                    for (int j = (dx / Math.abs(dx)); Math.abs(j) <= Math.abs(dx); j = j + (dx / Math.abs(dx))) {
                        Square curSq = squares[playerY + totalDy][playerX + j - (dx / Math.abs(dx)) + totalDx];
                        Square nextSq = squares[playerY + totalDy][playerX + j + totalDx];
                        visitiedSquares.add(curSq);

                        if(visitiedSquares.contains(nextSq))throw new IllegalMoveException("Cannot move on the same square twice in a single turn");
                        if (!(nextSq instanceof RoomSquare)) leftRoom = true;
                        if (leftRoom && nextSq instanceof RoomSquare && ((RoomSquare) nextSq).getRoom().equals(initialRoom))
                            throw new IllegalMoveException("Cannot re-enter a room in a single turn."); //cannot re-enter a room in a single turn
                        //can not move on another player's square
                        if (nextSq.getPlayer() != null) throw new IllegalMoveException("Cannot move onto another player's square");
                        if (nextSq instanceof DeadSquare) throw new IllegalMoveException("Cannot move onto a dead square");  //cant move onto a dead square
                        if (nextSq instanceof RoomSquare && !(curSq.isEntrySquare() || curSq instanceof RoomSquare))
                            throw new IllegalMoveException("Must enter room through entryway");//can only move to room sq from entry sq or other room sq
                        if (!(nextSq.isEntrySquare() || nextSq instanceof RoomSquare) && (curSq instanceof RoomSquare))
                            throw new IllegalMoveException("Must exit room through exit");//can only move from room sq to entry sq or room sq
                    }
                }
                //check if a vertical move is ok
                if (dy != 0) {
                    for (int j = (dy / Math.abs(dy)); Math.abs(j) <= Math.abs(dy); j = j + (dy / Math.abs(dy))) {
                        Square curSq = squares[playerY + totalDy + j - (dy / Math.abs(dy))][playerX + totalDx];
                        Square nextSq = squares[playerY + totalDy + j][playerX + totalDx];
                        visitiedSquares.add(curSq);

                        if(visitiedSquares.contains(nextSq))throw new IllegalMoveException("Cannot move on the same square twice in a single turn");
                        if (!(nextSq instanceof RoomSquare)) leftRoom = true;
                        if (leftRoom && nextSq instanceof RoomSquare && ((RoomSquare) nextSq).getRoom().equals(initialRoom))
                            throw new IllegalMoveException("Cannot re-enter a room in a single turn."); //cannot re-enter a room in a single turn
                        //can not move on another player's square
                        if (nextSq.getPlayer() != null) throw new IllegalMoveException("Cannot move onto another player's square");
                        if (nextSq instanceof DeadSquare) throw new IllegalMoveException("Cannot move onto a dead square");  //cant move onto a dead square
                        if (nextSq instanceof RoomSquare && !(curSq.isEntrySquare() || curSq instanceof RoomSquare))
                            throw new IllegalMoveException("Must enter room through entryway");//can only move to room sq from entry sq or other room sq
                        if (!(nextSq.isEntrySquare() || nextSq instanceof RoomSquare) && (curSq instanceof RoomSquare))
                            throw new IllegalMoveException("Must exit room through exit");//can only move from room sq to entry sq or room sq
                    }
                }
                totalDx += dx;
                totalDy += dy;

            }

            return true;
        }
        catch(IllegalMoveException e){
            return false;
        }
    }
    /**
     * moves a player piece to a new square
     *
     * @param p  player attempting to move
     * @param moves the square the player is attempting to move to
     */


    public void movePlayer(PlayerPiece p,String[] moves){
        int dy =0,dx =0;
        for(int i = 0; i < moves.length; i=i+2) {
            if(moves[i] == null) break;
            if (moves[i + 1].equalsIgnoreCase("L")) dx += -Integer.parseInt(moves[i]);
            if (moves[i + 1].equalsIgnoreCase("R")) dx += Integer.parseInt(moves[i]);
            if (moves[i + 1].equalsIgnoreCase("U")) dy += -Integer.parseInt(moves[i]);
            if (moves[i + 1].equalsIgnoreCase("D")) dy += Integer.parseInt(moves[i]);
        }
        p.setLocation(squares[p.getLocation().getY()+dy][p.getLocation().getX()+dx]);

    }

    /**
     * Calculates the number of moves the player is attempting to move
     * @param p
     * @param moves
     * @return
     */

    public int numOfMoves(PlayerPiece p,String[] moves){
        int playerX = p.getLocation().getX();
        int playerY = p.getLocation().getY();
        int numOfMoves = 0;
        int totalDx =0, totalDy = 0;
        for(int i = 0; i < moves.length; i=i+2){
            int dx = 0, dy =0;
            if(moves[i+1].equalsIgnoreCase("L")) dx = -Integer.parseInt(moves[i]);
            if(moves[i+1].equalsIgnoreCase("R")) dx = Integer.parseInt(moves[i]);
            if(moves[i+1].equalsIgnoreCase("U")) dy = -Integer.parseInt(moves[i]);
            if(moves[i+1].equalsIgnoreCase("D")) dy = Integer.parseInt(moves[i]);


            //count horizontal moves
            if(dx!= 0) {
                for (int j = (dx / Math.abs(dx)); Math.abs(j) <= Math.abs(dx); j = j + (dx / Math.abs(dx))) {
                    Square curSq = squares[playerY + totalDy][playerX + j - (dx / Math.abs(dx)) + totalDx];
                    Square nextSq = squares[playerY + totalDy][playerX + j + totalDx];
                    if (!(curSq instanceof RoomSquare && nextSq instanceof RoomSquare)) numOfMoves++;


                }
            }
            //count vertical moves
            if(dy!= 0) {
                for (int j = (dy / Math.abs(dy)); Math.abs(j) <= Math.abs(dy); j = j + (dy / Math.abs(dy))) {
                    Square curSq = squares[playerY + totalDy + j - (dy / Math.abs(dy))][playerX + totalDx];
                    Square nextSq = squares[playerY + totalDy + j][playerX + totalDx];
                    if (!(curSq instanceof RoomSquare && nextSq instanceof RoomSquare)) numOfMoves++;

                }
            }
            totalDx += dx;
            totalDy += dy;
        }
        return numOfMoves;
    }

    /**
     * Draws the board and all its contents
     * @param g  graphics to draw on
     * @param height height of drawing pane
     * @param width width of drawing pane
     */

    public void redraw(Graphics g, int height, int width){
      height = (height/squares.length);
      width = (width/squares[0].length);
      for(int i = 0; i < squares.length; i++){
          for(int j=0; j < squares[0].length;j++){
              squares[i][j].redraw(g,i*height,j*width,height,width);
          }
      }


      for(Weapon w:weapons.values()) w.redraw(g,height,width);
      for(PlayerPiece p:playerPieces.values()) p.redraw(g,height,width);
    }

    /**
     * Gets square given the row and col on the board
     * @param row
     * @param col
     * @return square
     */
  public Square getSquare(int row,int col)
  {
    return squares[row][col];
  }

    /**
     * Given the mouse position relative to the board boundary this method calculates the square clicked
     * @param maxHeight board height
     * @param maxWidth board width
     * @param height board height
     * @param width board width
     * @return Square
     */
  public Square getSquareFromMousePosition(double maxHeight,double maxWidth,double height,double width){
      int y = (int)Math.round(25*height/maxHeight);
      int x = (int)(24*width/maxWidth);
      y = y > 24? 24:y;
      return squares[y][x];


  }





  public String toString() {
      String res = "";
      for(int i = 0;i < squares.length; i++){
        for(int j =0; j < squares[0].length; j++){
            res+= squares[i][j].toString()+" ";
        }
        res+="\n";
    }
    return res;
  }
}