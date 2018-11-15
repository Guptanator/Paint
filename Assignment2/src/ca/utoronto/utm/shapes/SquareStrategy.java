package ca.utoronto.utm.shapes;

import ca.utoronto.utm.paint.PaintModel;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/** 
 * Strategy for making and changing instances of Square
 * on the PaintModel Command Stack
 *  
*/
public class SquareStrategy extends ShapeManipulatorStrategy {
	
	private Square shape;
	
	/** 
	 * MouseEvent Handler called by the PaintPanel which itself
	 * calls the appropriate method to create or alter
	 * the Square shape.
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
	 * Instantiates Square and adds its reference to the model
	 * and to instance Square shape to further modify.
	 *  
	 * @param e Mouse_Pressed Mouse Event
	*/
	private void makeShape(MouseEvent e) {
		Point corner = new Point((int) e.getX(), (int) e.getY());
		this.shape = new Square(corner, this.color, thickness);
		this.shape.setFill(this.fillColor);
		this.model.addDrawable(this.shape);
	}
	
	/** 
	 * On Mouse Drag, changes the dimension of the current Square shape
	 * in the model based on position of the mouse Cursor after which
	 * it updates the model for repaint.
	 *  
	 * @param e Mouse_Dragged Mouse Event
	*/
	private void changeShape(MouseEvent e) {
		int h = this.shape.getStart().getY()- (int) e.getY();
		int w = this.shape.getStart().getX()- (int) e.getX();
		if (h <= 0) {
			if (w <= 0) {
				this.shape.getCorner().setX(this.shape.getStart().getX());
				this.shape.getCorner().setY(this.shape.getStart().getY());
				if (h >= w) {
					this.shape.setLength(Math.abs(h));
				} else {this.shape.setLength(Math.abs(w));}
			} else {
				this.shape.getCorner().setY(this.shape.getStart().getY());
				if (Math.abs(h) >= w) {
					this.shape.getCorner().setX(this.shape.getStart().getX() - w);
					this.shape.setLength(w);
				} else {
					this.shape.getCorner().setX(this.shape.getStart().getX() + h);
					this.shape.setLength(Math.abs(h));
				}
			}
		} else {
			if (w <= 0) {
				this.shape.getCorner().setX(this.shape.getStart().getX());
				if (h >= Math.abs(w)) {
					this.shape.getCorner().setY(this.shape.getStart().getY() + w);
					this.shape.setLength(Math.abs(w));
				} else {
					this.shape.getCorner().setY(this.shape.getStart().getY() - h);
					this.shape.setLength(h);
				}
			} else {
				if (h > w) {
					this.shape.getCorner().setX(this.shape.getStart().getX() - w);
					this.shape.getCorner().setY(this.shape.getStart().getY() - w);
					this.shape.setLength(w);
					
				} else {
					this.shape.getCorner().setX(this.shape.getStart().getX() - h);
					this.shape.getCorner().setY(this.shape.getStart().getY() - h);	
					this.shape.setLength(h);
				}
			}
			
		} 	
		this.model.update();
	}
}
