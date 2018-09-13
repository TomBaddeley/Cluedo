package Java;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4148.608b7c78e modeling language!*/


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Squares are what build up the board. A player may move on a square given it isn't already occupied.
 */

// line 82 "model.ump"
// line 138 "model.ump"
public class Square
{
    private int x;
    private int y;
    private PlayerPiece p;
    private boolean entrySquare = false;

    public Square(int y, int x) {
        this.x = x;
        this.y = y;
    }

    public void addPlayer(PlayerPiece p){
        this.p = p;
    }

    public void removePlayer(){
        this.p = null;
    }

    public PlayerPiece getPlayer() {
        return p;
    }

    public String toString(){
        if(p!=null) return p.displayName();
        else if(entrySquare) return "E";
        else return "-";
    }

    public void redraw(Graphics g,int top,int left, int height, int width){
        try {
            if(!entrySquare) {
                BufferedImage image = ImageIO.read(new File("src/images/square.png"));
                g.drawImage(image, left + 2, top + 2, width, height, null);
            }
            else{
                g.setColor(Color.GREEN);
                g.fillRect(left + 2, top + 2, width, height);
                g.setColor(Color.BLACK);
            }
        }
        catch(IOException e){throw new Error();}
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isEntrySquare() {
        return entrySquare;
    }

    public void setEntrySquare(boolean entrySquare) {
        this.entrySquare = entrySquare;
    }
}