package Java;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4148.608b7c78e modeling language!*/

/**
 * This class represents a Room. A room knows all its squares and exits. When a suggestion is made a weapon and
 * player is moved to the corresponding room.
 */

import java.util.ArrayList;
import java.util.List;

// line 19 "model.ump"
// line 100 "model.ump"
public class Room
{


  private List<RoomSquare> roomSquares;
  private String roomName;
  private List<Square> exits = new ArrayList<>();

    public Room(List<RoomSquare> roomSquares, String roomName) {
        this.roomSquares = roomSquares;
        this.roomName = roomName;
        for(RoomSquare r: roomSquares){
            r.setRoom(this);
        }
    }

    public List<RoomSquare> getRoomSquares() {
        return roomSquares;
    }
/**
 * Adds a weapon to a random square inside the room
 * @Param w weapon to add
 */
    // Adds the weapon to a random square in the room
   public void addWeapon(Weapon w){
        w.updateLocation(roomSquares.get((int)(Math.random()*roomSquares.size())));
    
  }
    /**
     * Adds a playerpiece to a random square inside the room
     * @param p playerpiece to add
     */
    public void addPlayer(PlayerPiece p){
        int index = (int)(Math.random()*roomSquares.size());
        while(roomSquares.get(index).getPlayer()!= null) index = (int)(Math.random()*roomSquares.size());
        p.setLocation(roomSquares.get(index));

    }

    public void addExit(Square s){
        exits.add(s);
    }

    /**
     * returns the exits of the rooms
     * @ exits
     */
    public List<Square> getExits() {
        return exits;
    }

    public String getRoomName() {
        return roomName;
    }



  public String toString()
  {
    return roomName;
  }
}