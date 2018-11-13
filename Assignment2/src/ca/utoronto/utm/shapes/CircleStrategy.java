package ca.utoronto.utm.shapes;

import javafx.scene.input.MouseEvent;
import ca.utoronto.utm.paint.PaintModel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/** 
 * Strategy for making and changing instances of Circle
 * On the PaintModel Command Stack
 *  
*/
public class CircleStrategy extends ShapeManipulatorStrategy{
	
	private Circle shape;
	
	/** 
	 * MouseEvent Handler called by the PaintPanel which itself
	 * calls the appropriate method to create or alter
	 * the circle shape.
	 *  
	 * @param e MouseEvent passed by PaintPanel to denote
	 * user action.
	*/
	public void mouseHandle(MouseEvent e) {
		if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			changeCircle(e);
		} else if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
			makeCircle(e);
		}  
	}
	
	/** 
	 * Instantiates Circle and adds its reference to the model
	 * and to instance Circle shape to further modify.
	 *  
	 * @param e Mouse_Pressed Mouse Event
	*/
	private void makeCircle(MouseEvent e) {
		Point centre = new Point((int) e.getX(), (int) e.getY());
		this.shape = new Circle(centre, 0, this.color, this.thickness);
		this.shape.setFill(model.getFill());
		model.addDrawable(this.shape);
	}
	
	/** 
	 * Sets Strategy Fill setting to True
	 *  
	 * @param e Mouse Event
	 * @param p Current PaintModel
	*/
	public void changeShape(MouseEvent e, PaintModel p) {
		this.shape.setFill(p.getFill());
	}
	
	/** 
	 * On Mouse Drag, changes the dimension of the current Circle Shape
	 * in the model based on position of the mouse Cursor after which
	 * it updates the model for repaint.
	 *  
	 * @param e Mouse_Dragged Mouse Event
	*/
	private void changeCircle(MouseEvent e) {
		int horizontal = Math.abs((int) this.shape.getCentre().getX() - (int) e.getX());
		int vertical = Math.abs((int) this.shape.getCentre().getY() - (int) e.getY());
		int radius = (int)Math.sqrt(Math.pow(horizontal,2) + Math.pow(vertical,2));
		this.shape.setRadius(radius);
		model.update();
	}
}
