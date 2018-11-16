package ca.utoronto.utm.drawingCommands;

import ca.utoronto.utm.shapes.ClosedShape;
import ca.utoronto.utm.shapes.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/** Command for activating the fill option for shapes.
 *
 * @author Daniel Lee
 */
public class FillCommand implements DrawingCommands{
	
	private Color fillColor;
	
	/** Constructor for FillCommand, which is used to set
	 *  the fill color
	 *  
	 * @param c Current Fill Color
	*/
	public FillCommand(Color c) {
		this.fillColor = c;
	}
	
	/** Sets the current fill setting to change in the
	 * GraphicsContext
	 * 
	 * @param g GraphicsContext used to set fill
	*/
	@Override
	public void executeChange(GraphicsContext g) {
		g.setFill(this.fillColor);
	}
	
	@Override
	public String type() {
		return "fill";
	}
}
