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
	
	/** 
	 * This sets the current stroke to be Color color
	 * @param GraphicsContext g is used to set the stroke color
	*/
	@Override
	public void executeChange(GraphicsContext g) {
		g.setStroke(this.color);
	}
	
	/** 
	 * This returns the type of the command
	 * @return the string detailing the type of the button
	*/
	@Override
	public String type() {
		return "color";
	}
	/** 
	 * This returns the current color of the command
	 * @return the current color
	*/
	public Color getColor() {
		return this.color;
	}
}
