package ca.utoronto.utm.drawingCommands;

import ca.utoronto.utm.shapes.ClosedShape;
import ca.utoronto.utm.shapes.Drawable;

/**
 * Command for activating the fill option for shapes.
 *
 * @author Daniel Lee
 */
public class FillCommand implements DrawingCommands{
	public boolean fill;
	/** Constructor for FillCommand, which is used to set
	 *  whether or not the shape should be filled.
	 * @param filled Boolean value that denotes whether the
	 * shapes should be filled or not.
	*/
	public FillCommand(boolean filled)
	{
		this.fill = filled;
	}
	/** Changes the fill value for shape d when called.
	*/
	@Override
	public void executeChange(Drawable d) {
		if (d.isClosed()) {
			((ClosedShape)d).setFill(this.fill);
		}
	}
}
