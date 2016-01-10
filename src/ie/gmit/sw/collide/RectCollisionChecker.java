package ie.gmit.sw.collide;

import java.awt.Rectangle;


public class RectCollisionChecker implements CollisionChecker {
	public boolean collide(final Collidable c1, Collidable c2) {
        
        Rectangle r1 = null, r2=null;
                
        r1 = new Rectangle(c1.getX(), (c1.getY()-c1.getHeight()), c1.getWidth(), c1.getHeight());
        r2 = new Rectangle(c2.getX(), c2.getY()- c2.getHeight(), c2.getWidth(), c2.getHeight());
        
        if(!r1.intersects(r2)) {
        	return false;
        }
        return true;
    }
}
