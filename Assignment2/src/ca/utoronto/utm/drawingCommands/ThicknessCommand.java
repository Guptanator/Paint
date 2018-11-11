package ca.utoronto.utm.drawingCommands;

import ca.utoronto.utm.shapes.Drawable;
/**
 * Command for choosing the line thickness for shapes.
 *
 * @author Daniel Lee
 */
public class ThicknessCommand implements DrawingCommands{
	private double thickness;
	/** Constructor for ThicknessCommand, which is used to set
	 * the line thickness for shapes.
	 *  
	 * @param thick Double value that denotes the line
	 * thickness value of the new shapes.
	*/
	public ThicknessCommand(double thick)
	{
		this.thickness = thick;
	}
	/** Changes the line thickness value for shape d when called.
	*/
	@Override
	public void executeChange(Drawable d) {
		d.setThickness(thickness);
	}
}
