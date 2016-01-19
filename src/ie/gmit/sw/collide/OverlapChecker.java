package ie.gmit.sw.collide;

public interface OverlapChecker {
	/**
	 * Any class that implements this interface 
	 * will be able to compare two objects for
	 * collision detection.
	 * Method taken from Kumo word cloud.
	 * 
	 * @param c1 CollisionDetector First Colliding object
	 * @param c2 CollisionDetector Second Colliding object
	 * @return true boolean if objects collide
	 */
	public boolean collide(CollisionDetector c1, CollisionDetector c2);
}
