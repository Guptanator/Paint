package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class CircleStrategy extends ShapeManipulatorStrategy{
	
	private Circle shape;
	
	public void mouseHandle(MouseEvent e) {
		if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			changeCircle(e);
		} else if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
			makeCircle(e);
		}  
	}
	
	private void makeCircle(MouseEvent e) {
		Point centre = new Point((int) e.getX(), (int) e.getY());
		this.shape = new Circle(centre, 0, this.color, this.thickness);
		this.shape.toFill(model.getFill());
		model.addDrawable(this.shape);
	}

	private void changeCircle(MouseEvent e) {
		
		int horizontal = Math.abs((int) this.shape.getCentre().getX() - (int) e.getX());
		int vertical = Math.abs((int) this.shape.getCentre().getY() - (int) e.getY());
		int radius = (int)Math.sqrt(Math.pow(horizontal,2) + Math.pow(vertical,2));
		this.shape.setRadius(radius);
		model.update();
	}
}
