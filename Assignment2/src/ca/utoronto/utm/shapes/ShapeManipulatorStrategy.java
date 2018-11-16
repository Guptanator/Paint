package ca.utoronto.utm.shapes;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import ca.utoronto.utm.paint.PaintModel;
import javafx.scene.canvas.GraphicsContext;

/** Abstract Strategy class for implementation of
 * different shape formation strategies
 * Abstract Class for the shape construction strategies
 * implementing the Strategy Design Pattern
*/
public abstract class ShapeManipulatorStrategy {
	
	protected Color color = Color.BLACK;
	protected double thickness;
	protected PaintModel model;
	protected Color fillColor = Color.TRANSPARENT;
	
	/** MouseEvent Handler called by the PaintPanel which itself
	 * calls the appropriate method to create or alter
	 * the stored shape.
	 *  
	 * @param e MouseEvent passed by PaintPanel to denote
	 * user action.
	*/
	public abstract void mouseHandle(MouseEvent e);
	
	/** Sets PaintModel model in instance
	 * to current PaintModel
	 *  
	 *  @param PaintModel p sets the model attribute 
	*/
	public void setModel(PaintModel p) {
		this.model = p;
		this.model.update();
	}
	
	/** Sets Color color in instance
	 * to the passed in Color
	 *  
	 *  @param Color c the new color for the strategy
	*/
	public void setColor(Color c) {
		this.color = c;
	}
	
	/** Sets double thickness in instance
	 * to new thickness
	 *  
	 *  @param Double t the new thickness for the strategy
	*/
	public void setThickness(double t) {
		this.thickness = t;
	}
	
	/** Sets fillColor to be the new passed in color
	 *  
	 *  @param Color fillColor the new color for the fill of the shape
	*/
	public void setFill(Color fillColor) {
			this.fillColor = fillColor;
	}
}
