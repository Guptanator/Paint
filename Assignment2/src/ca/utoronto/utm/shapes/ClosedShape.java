package ca.utoronto.utm.shapes;

import ca.utoronto.utm.drawingCommands.FillCommand;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/** 
 * Abstract Drawable for closed shapes
*/
public abstract class ClosedShape extends Drawable {
	
	public abstract boolean isClicked(MouseEvent e);
	public abstract double xDifferent(double d);
	public abstract double yDifferent(double d);
	
	public void setFill(Color filled) {
		this.properties.acceptCommand(new FillCommand(filled));
	}
	
	/** 
	 * Returns that current Drawable
	 * is a closed shape
	*/
	public boolean isClosed() {
		return true;
	}
}
