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
	protected Color color;
	protected double thickness;
	protected PaintModel model;
	protected boolean fill = false;
	
	public abstract void mouseHandle(MouseEvent e);
	
	public void setModel(PaintModel p) {
		this.model = p;
		this.model.update();
	}
	
	public void setColor(Color c) {
		this.color = c;
	}
	
	public void setThickness(double t) {
		this.thickness = t;
	}
	public void setFill(boolean fill)
	{
		this.fill = fill;
	}
	
}
