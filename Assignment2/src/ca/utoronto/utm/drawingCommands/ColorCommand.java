package ca.utoronto.utm.drawingCommands;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ca.utoronto.utm.shapes.Drawable;
/**
 * Command for choosing the color for shapes.
 *
 * @author Daniel Lee
 */
public class ColorCommand implements DrawingCommands{
	private Color color;
	/** Constructor for ColorCommand, which is used to set
	 * the color of the shape.
	 *  
	 * @param c Color value that denotes the new color to
	 * set to the shapes.
	*/
	public ColorCommand(Color c)
	{
		this.color = c;
	}
	/** Changes the color value for shape d when called.
	*/
	@Override
	public void executeChange(GraphicsContext g) {
		g.setStroke(this.color);
	}
}
