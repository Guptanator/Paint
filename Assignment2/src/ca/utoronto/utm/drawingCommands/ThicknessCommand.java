package ca.utoronto.utm.drawingCommands;

import ca.utoronto.utm.shapes.Drawable;
import javafx.scene.canvas.GraphicsContext;
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
	public void executeChange(GraphicsContext g) {
		g.setLineWidth(this.thickness);
	}
	@Override
	public String type() {
		return "thick";
	}
	public double getThickness() {
		return thickness;
	}
}
