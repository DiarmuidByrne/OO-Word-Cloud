package ie.gmit.sw;

import java.awt.image.BufferedImage;

public interface Collidable {
	boolean collide(Collidable collidable);
    int getX();
    int getY();
    int getWidth();
    int getHeight();
    BufferedImage getBufferedImage();
}
