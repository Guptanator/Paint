package ca.utoronto.utm.shapes;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import ca.utoronto.utm.paint.PaintModel;
import javafx.scene.canvas.GraphicsContext;

/** 
 * Abstract Strategy class for implementation of
 * different shape formation strategies
 *  
*/
public abstract class ShapeManipulatorStrategy {
	
	protected Color color = Color.BLACK;
	protected double thickness;
	protected PaintModel model;
	protected Color fillColor = Color.TRANSPARENT;
	
	/** 
	 * MouseEvent Handler called by the PaintPanel which itself
	 * calls the appropriate method to create or alter
	 * the stored shape.
	 *  
	 * @param e MouseEvent passed by PaintPanel to denote
	 * user action.
	*/
	public abstract void mouseHandle(MouseEvent e);
	
	/** 
	 * Sets PaintModel model in instance
	 * to current PaintModel
	 *  
	 *  @param p Current PaintModel
	*/
	public void setModel(PaintModel p) {
		this.model = p;
		this.model.update();
	}
	
	/** 
	 * Sets Color color in instance
	 * to current color
	 *  
	 *  @param c Current color
	*/
	public void setColor(Color c) {
		this.color = c;
	}
	
	/** 
	 * Sets double thickness in instance
	 * to current thickness
	 *  
	 *  @param t Current thickness
	*/
	public void setThickness(double t) {
		this.thickness = t;
	}
	
	/** 
	 * Sets boolean fill in instance
	 * to current fill setting
	 *  
	 *  @param fillColor 
	*/
	public void setFill(Color fillColor) {
			this.fillColor = fillColor;
	}
}
