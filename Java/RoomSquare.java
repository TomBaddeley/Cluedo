package Java;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/** A room square is a type of square in a room. Unlike other squares a room
 * may hold a weapon.
 */

public class RoomSquare extends Square{
    private Weapon w;
    private Room room;


    public RoomSquare(int y, int x) {
        super(y, x);
    }


    public Room getRoom() {
        return room;
    }

    public void addWeapon(Weapon w){
        this.w = w;
    }

    public Weapon getWeapon() {
        return w;
    }

    public void setRoom(Room r){
        room = r;
    }


    public void removeWeapon(){
        this.w = null;
    }

    public String toString(){
        if(super.getPlayer()!=null) return super.getPlayer().displayName();
        if(w!= null) return "\u001B[31m"+w.toString().substring(0,1)+"\u001B[0m";
        else return room.getRoomName().substring(0,1);
    }

    public void redraw(Graphics g, int top, int left, int height, int width){
        try {
            BufferedImage image = ImageIO.read(new File("src/images/roomSq.png"));
            g.drawImage(image,left+2,top+2,width,height,null);
        }
        catch(IOException e){throw new Error();}
    }

}
