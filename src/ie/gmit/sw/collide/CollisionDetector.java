package ie.gmit.sw.collide;
/**
 * @author Diarmuid
 * Defines a collidable object. Any object that 
 * implements this interface can be compared with 
 * another collidable object for collision detection
 */
public interface CollisionDetector {
    int getXPosition();
    int getYPosition();
    int getWidth();
    int getHeight();
}