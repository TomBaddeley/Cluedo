package Java;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class represents the player pieces, a piece can update its location on the board.
 */

public class PlayerPiece {
    private Square location;
    private String name;
    private String owner = "N/A";
    private Boolean turn = false;


    public PlayerPiece(String name){
        this.name = name;
    }

    /**
     * Sets the location of the player piece
     * @param s
     */
    public void setLocation(Square s){
        if(location!= null) location.removePlayer();
        location = s;
        s.addPlayer(this);
    }

    /**
     * Returns the location of the player piece
     * @return square
     */

    public Square getLocation() {
        return location;
    }

    /**
     * returns the name of the player piece
     * @return
     */

    public String getName() {
        return name;
    }

    /**
     * This method returns a one character display name that is used to represent the characters location on the
     * board.
     * @return
     */
    public String displayName(){
        if(name.equals("Mrs Peacock"))  return "\u001B[32m"+"p"+"\u001B[0m";
        return "\u001B[32m"+name.split(" ")[1].substring(0,1)+"\u001B[0m";
    }

    /**
     * returns the player associated with this player piece
     * @return owner name
     */

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Boolean isTurn() {
        return turn;
    }

    public void setTurn(Boolean turn) {
        this.turn = turn;
    }

    /**
     * Draws the player piece to the GUI
     * @param g
     * @param height
     * @param width
     */

    public void redraw(Graphics g, int height, int width){
        String fileName = name.replace(" ","_");
        if(isTurn()) fileName = fileName + "_turn";
        try {
            BufferedImage charImage = ImageIO.read(new File("src/images/CharacterTokens/" + fileName + ".png"));
            g.drawImage(charImage,location.getX()*width+2,location.getY()*height+2,width,height,null);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
