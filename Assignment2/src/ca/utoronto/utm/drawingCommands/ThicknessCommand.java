package ca.utoronto.utm.drawingCommands;

import ca.utoronto.utm.shapes.Drawable;
import javafx.scene.canvas.GraphicsContext;

/** Command for choosing the line thickness for shapes.
 *
 * @author Daniel Lee
 */
public class ThicknessCommand implements DrawingCommands{
	
	private double thickness;
	
	/** Constructor for ThicknessCommand. Sets
	 * thickness.
	 *  
	 * @param thickness Double that denotes the line
	 * thickness of the new shapes.
	*/
	public ThicknessCommand(double thickness) {
		this.thickness = thickness;
	}
	
	/** Sets the current thickness to change in the
	 * GraphicsContext
	 * 
	 * @param g GraphicsContext used to set thickness
	*/
	@Override
	public void executeChange(GraphicsContext g) {
		g.setLineWidth(this.thickness);
	}
	
	@Override
	public String type() {
		return "thick";
	}
	
	/** This returns the current thickness of the command
	 * 
	 * @return the current thickness
	*/
	public double getThickness() {
		return thickness;
	}
}
