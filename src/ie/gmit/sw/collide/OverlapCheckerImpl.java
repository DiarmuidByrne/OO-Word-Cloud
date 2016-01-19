package ie.gmit.sw.collide;

import java.awt.Rectangle;


public class OverlapCheckerImpl implements OverlapChecker{
	
	/**
	 * Creates 2 new rectangle objects 
	 * with the measurements and positions
	 * of the two parameters.
	 * Uses the rectangle intersects() method
	 * to check for collisions.
	 * 
	 * @param c1 CollisionDetector first collider
	 * @param c2 CollisionDetector second collider
	 * @return True if parameters collide.
	 */
	public boolean collide(CollisionDetector c1, CollisionDetector c2) {
        
        Rectangle r1 = null, r2=null;
        
        // Creates two new rectangles based on the metrics of the two parameters
        r1 = new Rectangle(c1.getXPosition(), (c1.getYPosition()-c1.getHeight()), c1.getWidth(), c1.getHeight());
        r2 = new Rectangle(c2.getXPosition(), c2.getYPosition()- c2.getHeight(), c2.getWidth(), c2.getHeight());
        
        // Calls pre-defined Rectangle.intersects() method to check for overlap
        if(r1.intersects(r2)) {
        	return true;
        }
        
        return false;
    }
}
