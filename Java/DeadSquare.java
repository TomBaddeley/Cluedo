package Java;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * This class represents a dead square. A dead square can not be reached.
 */
public class DeadSquare extends Square {
    public DeadSquare(int x, int y) {
        super(x, y);
    }

    public String toString() {
        return "*";
    }


    @Override
    public void addPlayer(PlayerPiece p) {
        return;
    }


    public void redraw(Graphics g, int top, int left, int height, int width) {
        try {
            BufferedImage image = ImageIO.read(new File("src/images/deadSq.png"));
            g.drawImage(image, left+2, top+2, width, height, null);
        } catch (IOException e) {
            throw new Error();
        }
    }
}
