package ie.gmit.sw.collide;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import ie.gmit.sw.Word;

public class RectCollisionChecker implements CollisionChecker {
	@Override
	public boolean collide(final Collidable c1, Collidable c2) {
//        final Vector2D position = c1.getPosition();
//        final Vector2D position2 = c2.getPosition();
        
        final Rectangle r1, r2;

        r1 = new Rectangle(c1.getX(), c1.getY()- c1.getHeight(), c1.getWidth(), c1.getHeight());
        r2 = new Rectangle(c2.getX(), c2.getY()- c2.getHeight(), c2.getWidth(), c2.getHeight());
                
        if(!r1.intersects(r2)) {
        	return false;
        }

//        if((position.getX() + c1.getWidth() < position2.getX()) || (position2.getX() + c2.getWidth() < position.getX())) {
//            return false;
//        }
//        if((position.getY() + c1.getHeight() < position2.getY()) || (position2.getY() + c2.getHeight() < position.getY())) {
//            return false;
//        }
        return true;
    }
}
