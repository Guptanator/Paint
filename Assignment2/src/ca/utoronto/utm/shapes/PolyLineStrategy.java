package ca.utoronto.utm.shapes;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseButton;

/** 
 * Strategy for making and changing instances of Lines to 
 * Form Poly-Lines on the PaintModel Command Stack
 *  
*/
public class PolyLineStrategy extends ShapeManipulatorStrategy {
	
	private Line shape;
	private boolean isFirst = true;
	private boolean isEnd = false;
	private boolean isTerminated = true;
	
	/** 
	 * MouseEvent Handler called by the PaintPanel which itself
	 * calls the appropriate method to create or alter
	 * the Line shape in PolyLine.
	 *  
	 * @param e MouseEvent passed by PaintPanel to denote
	 * user action.
	*/
	@Override
	public void mouseHandle(MouseEvent e) {
		if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			changeShape(e);
		} else if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
			if (e.getButton() == MouseButton.PRIMARY) {
				makeShape(e);
				this.isTerminated = false;
			} else {
				terminateShape();
			}
		} else if (e.getEventType() == MouseEvent.MOUSE_MOVED) {
			moveFeedback(e);
		} else if (e.getEventType() == MouseEvent.MOUSE_RELEASED) {
			if (!this.isTerminated) {
				addShape();
			}
		}
	}
	
	/** 
	 * Instantiates Line and adds its reference to the model
	 * and to instance Line shape to further modify.
	 * If it is the second Line of a polyline drawing action
	 * it will instead set the end point of the current 
	 * poly-line
	 *  
	 * @param e Mouse_Pressed Mouse Event
	*/
	private void makeShape(MouseEvent e) {
		Point p = new Point((int) e.getX(),(int) e.getY(), this.color, this.thickness);
		if (this.isFirst == true) {
			this.shape = new Line(p, p, this.color);
			this.model.addDrawable(this.shape);
		} else {
			this.shape.setLast(p);
		}
	}
	
	/** 
	 * Terminates Line in PaintModel currently being
	 * drawn.
	 * 
	*/
	public void terminateShape() {
		if (!this.isTerminated) {
			this.model.removeLast();
			this.shape = null;
			this.isFirst = true;
			this.isEnd = false;
			this.isTerminated = true;
			this.model.update();
		}
		
	}
	
	/** 
	 * Changes last point on current line in model on drag.
	 * 
	 * @param e Mouse_Dragged Mouse Event
	*/
	private void changeShape(MouseEvent e) {
		if (!this.isFirst) {
			this.shape.setLast(new Point((int) e.getX(), (int) e.getY()));
			this.model.update();
		}
	}

	/** 
	 * Completes the formation of the first line
	 * in a poly=line drawing action and adds the 
	 * next line to the model.
	 * 
	 * @param e Mouse_Released Mouse Event
	*/
	private void addShape() {
		if (!this.isEnd) {
			if (this.isFirst) {
				this.isFirst = false;
			} else {
				this.shape = new Line(shape.getLast(), shape.getLast(), this.color);
				this.model.addDrawable(this.shape);
			} 
		} else {
			this.isEnd = false;
			this.model.update();
		}
	}
	
	/** 
	 * Changes last point on current line in model on move.
	 * 
	 * @param e Mouse_Moved Mouse Event
	*/
	public void moveFeedback(MouseEvent e) {
		if (!this.isFirst && !this.isTerminated) {
			this.shape.setLast(new Point((int) e.getX(), (int) e.getY()));
			this.model.update();
		}
	}



}
