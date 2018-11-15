package ca.utoronto.utm.shapes;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/** 
 * Strategy for making and changing instances of Squiggle
 * on the PaintModel Command Stack
 *  
*/
public class SquiggleStrategy extends ShapeManipulatorStrategy {
	

	private Squiggle shape;
	
	
	/** 
	 * MouseEvent Handler called by the PaintPanel which itself
	 * calls the appropriate method to create or alter
	 * the Squiggle shape.
	 *  
	 * @param e MouseEvent passed by PaintPanel to denote
	 * user action.
	*/
	@Override
	public void mouseHandle(MouseEvent e) {
		if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			changeShape(e);
		} else if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
			makeShape(e);
		}
	}
	
	/** 
	 * Instantiates Squiggle and adds its reference to the model
	 * and to instance Squiggle shape to further modify.
	 *  
	 * @param e Mouse_Pressed Mouse Event
	*/
	private void makeShape(MouseEvent e) {
		this.shape = new Squiggle((int) e.getX(), (int) e.getY(), this.color, this.thickness);
		this.model.addDrawable(this.shape);
	}
	
	/** 
	 * Instantiates Squiggle and adds its reference to the model
	 * and to instance Squiggle shape to further modify.
	 *  
	 * @param e Mouse_Pressed Mouse Event
	*/
	public void makeShapeAbove(MouseEvent e) {
		this.shape = new Squiggle((int) e.getX(), (int) e.getY(), this.color, this.thickness);
		this.model.addAbove(this.shape);
	}

	/** 
	 * On Mouse Drag, adds points to current Squiggle shape
	 * in the model based on position of the mouse Cursor after which
	 * it updates the model for repaint.
	 *  
	 * @param e Mouse_Dragged Mouse Event
	*/
	private void changeShape(MouseEvent e) {
		this.shape.addPoint((int) e.getX(), (int) e.getY());
		this.model.update();
	}

}
