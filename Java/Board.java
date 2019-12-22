package Java;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4148.608b7c78e modeling language!*/


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.WindowStateListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
      for (int i = 10; i < 14; i++) {
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
     * @param s1 Square the player is moving from.
     * @param s2 The square the playing is moving to.
     * @return Is the move valid
     */
  public boolean checkValidMove(Square s1, Square s2){

      if((s2 instanceof DeadSquare) || s2.getPlayer()!= null) return false;//a player may not move on a dead square or another player's square
      if(s2 instanceof RoomSquare && (!s1.isEntrySquare() && !(s1 instanceof RoomSquare)))return false;
      if(s1 instanceof RoomSquare && !(s2 instanceof RoomSquare) && !s2.isEntrySquare()) return false;
      return true;
  }

    /**
     * converts the square the player is moving to into a string that can be used with other methods.
     *
     * @param p  player attempting to move
     * @param s the square the palyer wishes to move to
     * @return
     */

  public List<Square> moveToString(PlayerPiece p,Square s){
        if((s instanceof DeadSquare) || s.getPlayer()!= null) return  null;

        Queue<Square> bfs = new ArrayDeque<>();
        HashMap<Square,Square> parentMap = new HashMap<>();
        bfs.add(p.getLocation());
        boolean found = false;
        while(!bfs.isEmpty()&&!found){
            Square cur = bfs.poll();
            int x = cur.getX();
            int y = cur.getY();
            if(x < 23){
                Square next = squares[y][x+1];
                if(checkValidMove(cur,next) && !parentMap.containsKey(next)) {
                    parentMap.put(next,cur);
                    bfs.add(next);
                }
                if(next.equals(s)) found = true;
            }
            if(y < 24){
                Square next = squares[y+1][x];
                if(checkValidMove(cur,next) && !parentMap.containsKey(next)) {
                    parentMap.put(next,cur);
                    bfs.add(next);
                }
                if(next.equals(s)) found = true;
            }
            if(x > 0){
                Square next = squares[y][x-1];
                if(checkValidMove(cur,next) && !parentMap.containsKey(next)) {
                    parentMap.put(next,cur);
                    bfs.add(next);
                }
                if(next.equals(s)) found = true;
            }
            if(y > 0){
                Square next = squares[y-1][x];
                if(checkValidMove(cur,next) && !parentMap.containsKey(next)) {
                    parentMap.put(next,cur);
                    bfs.add(next);
                }
                if(next.equals(s)) found = true;
            }
        }
        if(!found) return null;

        List<Square> sol = new ArrayList<>();
        sol.add(s);
        Square cur = s;
        while(parentMap.get(cur) != null){
            cur = parentMap.get(cur);
            sol.add(0,cur);
        }
        return sol;


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
     * moves a player piece to a new square
     *
     * @param p  player attempting to move
     * @param s the square the player is attempting to move to
     */


    public void movePlayer(PlayerPiece p,Square s){

        p.setLocation(s);

    }

    /**
     * Calculates the number of moves the player is attempting to move
     * @param p
     * @param moves
     * @return
     */

    public int numOfMoves(PlayerPiece p,List<Square> moves){
        int numOfMoves = 0;
        for(int i = 0; i < moves.size() - 1;i++){
            if(!(moves.get(i) instanceof RoomSquare && moves.get(i+1) instanceof RoomSquare)) numOfMoves++;
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
      //draws walls around the rooms
        for(int i = 0; i < squares.length; i++){
            for(int j=0; j < squares[0].length;j++){
                if(i + 1 < squares.length && squares[i][j] instanceof RoomSquare && !(squares[i+1][j] instanceof RoomSquare)
                && !squares[i+1][j].isEntrySquare()){
                    ((Graphics2D)g).setStroke(new BasicStroke(2));
                    g.drawLine(j*width+2,(i+1)*height+2,(j+1)*width+2,(i+1)*height+2);
                }
                if(i - 1 > 0 && squares[i][j] instanceof RoomSquare && !(squares[i-1][j] instanceof RoomSquare)
                && !squares[i-1][j].isEntrySquare()){
                    ((Graphics2D)g).setStroke(new BasicStroke(2));
                    g.drawLine(j*width+2,(i)*height+2,(j+1)*width+2,(i)*height+2);
                }
                if(j - 1 > 0 && squares[i][j] instanceof RoomSquare && !(squares[i][j -1] instanceof RoomSquare) &&
                        !squares[i][j-1].isEntrySquare()){
                    ((Graphics2D)g).setStroke(new BasicStroke(2));
                    g.drawLine(j*width+2,(i+1)*height+2,j*width+2,(i)*height+2);
                }
                if(j + 1 < squares[0].length && squares[i][j] instanceof RoomSquare && !(squares[i][j +1] instanceof RoomSquare)&&
                        !squares[i][j+1].isEntrySquare()){
                    ((Graphics2D)g).setStroke(new BasicStroke(2));
                    g.drawLine((j+1)*width+2,(i+1)*height+2,(j+1)*width+2,(i)*height+2);
                }
            }
        }
        //draw center of the board
        try {
            BufferedImage image = ImageIO.read(new File("src/images/Center.png"));
            g.drawImage(image,10*width + 2,10*height+2,4*width,7*height,null);
        } catch(IOException e) {
            e.printStackTrace();
        }
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
      int y = (int)Math.floor(25*height/maxHeight);
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