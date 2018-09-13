package Java;/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.28.0.4148.608b7c78e modeling language!*/


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// line 75 "model.ump"
// line 133 "model.ump"
public class Weapon
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Weapon Attributes
  private String name;
  private RoomSquare location;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Weapon(String aName)
  {
    name = aName;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public void delete()
  {}

  public void updateLocation(RoomSquare s){
    if(this.location!= null) this.location.removeWeapon();
    location = s;
    s.addWeapon(this);


  }
  public void redraw(Graphics g, int height, int width) {
    String fileName = name.replace(" ","_");
    try {
      BufferedImage weapImage = ImageIO.read(new File("src/images/WeaponTokens/" + fileName + ".png"));
      g.drawImage(weapImage,location.getX()*width+2,location.getY()*height+2,width,height,null);
    }catch(IOException e){}

  }



  public String toString()
  {
    return name.substring(0,1);
  }
}