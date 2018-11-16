package ca.utoronto.utm.drawingCommands;

import ca.utoronto.utm.shapes.ClosedShape;
import ca.utoronto.utm.shapes.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Command for activating the fill option for shapes.
 *
 * @author Daniel Lee
 */
public class FillCommand implements DrawingCommands{
	private Color fillColor;
	/** Constructor for FillCommand, which is used to set
	 *  whether or not the shape should be filled.
	 * @param filled Boolean value that denotes whether the
	 * shapes should be filled or not.
	*/
	public FillCommand(Color color)
	{
		this.fillColor = color;
	}
	/** Changes the fill value for shape d when called.
	*/
	@Override
	public void executeChange(GraphicsContext g) {
		g.setFill(this.fillColor);
	}
	
	/** 
	 * This returns the type of the command
	 * @return the string detailing the type of the button
	*/
	@Override
	public String type() {
		return "fill";
	}
}
