package ca.utoronto.utm.drawingCommands;

import ca.utoronto.utm.shapes.Drawable;
import javafx.scene.canvas.GraphicsContext;

/** Abstract class for commands that change the
 * properties of Drawable objects.
 * The shared interface for drawing style commands
 * implementing the Command Design Pattern.
 * 
 * @author Daniel Lee
*/
public interface DrawingCommands {
	
	/** Sets the current property to change in the
	 * GraphicsContext
	 * 
	 * @param g GraphicsContext used to set command property
	*/
	public abstract void executeChange(GraphicsContext g);
	
	/** This returns the type of the command
	 * 
	 * @return the string detailing the type
	*/
	public abstract String type();
}
