package shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class RectangleStrategy extends ShapeManipulatorStrategy {
	
	private Rectangle shape;
	
	@Override
	public void mouseHandle(MouseEvent e) {
		if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			changeShape(e);
		} else if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
			makeShape(e);
		}
	}
	
	private void makeShape(MouseEvent e) {
		Point corner = new Point((int) e.getX(), (int) e.getY());
		this.shape = new Rectangle(corner, this.color, this.thickness);
		this.model.addDrawable(shape);

	}

	private void changeShape(MouseEvent e) {
		this.shape.toFill(this.model.getFill());
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
