package ca.utoronto.utm.shapes;

import ca.utoronto.utm.paint.PaintModel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/** 
 * Strategy for making and changing instances of Rectangle
 * on the PaintModel Command Stack
 *  
*/
public class RectangleStrategy extends ShapeManipulatorStrategy {
	
	private Rectangle shape;
	
	/** 
	 * MouseEvent Handler called by the PaintPanel which itself
	 * calls the appropriate method to create or alter
	 * the Rectangle shape.
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
	 * Instantiates Rectangle and adds its reference to the model
	 * and to instance Rectangle shape to further modify.
	 *  
	 * @param e Mouse_Pressed Mouse Event
	*/
	private void makeShape(MouseEvent e) {
		Point corner = new Point((int) e.getX(), (int) e.getY());
		this.shape = new Rectangle(corner, this.color, this.thickness);
		this.shape.setFill(this.fillColor);
		this.model.addDrawable(shape);

	}
	
	/** 
	 * On Mouse Drag, changes the dimension of the current Rectangle shape
	 * in the model based on position of the mouse Cursor after which
	 * it updates the model for repaint.
	 *  
	 * @param e Mouse_Dragged Mouse Event
	*/
	private void changeShape(MouseEvent e) {
		int h = this.shape.getStart().getY()- (int) e.getY();
		int w = this.shape.getStart().getX()- (int) e.getX();
		
		if (h < 0) {
			this.shape.getCorner().setY(this.shape.getStart().getY());
			this.shape.setHeight(Math.abs(h));
		} else if (h >= 0) {
			this.shape.getCorner().setY((int) e.getY());
			this.shape.setHeight(h);
		}
		if (w < 0) {
			this.shape.getCorner().setX(this.shape.getStart().getX());
			this.shape.setWidth(Math.abs(w));
		} else if (w >= 0) {
			this.shape.getCorner().setX((int) e.getX());
			this.shape.setWidth(w);
			
		}
		this.model.update();
	}

}
