package ca.utoronto.utm.shapes;

import javafx.scene.input.MouseEvent;

/** 
 * Abstract Drawable for closed shapes
*/
public abstract class ClosedShape extends Drawable {
	protected boolean fill;
	
	public abstract boolean isClicked(MouseEvent e);
	public abstract double xDifferent(double d);
	public abstract double yDifferent(double d);
	public abstract void setFill(boolean fill);
	
	/** 
	 * Returns that current Drawable
	 * is a closed shape
	*/
	public boolean isClosed() {
		return true;
	}
}
