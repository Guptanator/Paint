package ca.utoronto.utm.drawingCommands;

import ca.utoronto.utm.shapes.Drawable;
import javafx.scene.canvas.GraphicsContext;
/** Abstract class for commands that change the
 * properties of Drawable objects.
 * 
 * @author Daniel Lee
*/
public interface DrawingCommands {
	/** Executes the command to change the properties
	 * of the given drawable object d.
	 * @param d Drawable object change the properties of.
	*/
	public abstract void executeChange(GraphicsContext g);
	public abstract String type();
}
