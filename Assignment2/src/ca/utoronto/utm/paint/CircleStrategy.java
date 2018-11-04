package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import java.awt.Color;

public class CircleStrategy implements ShapeManipulatorStrategy{
	
	private Circle shape;
	
	@Override
	public void makeShape(MouseEvent e, Color c, double l) {
		Point centre = new Point((int) e.getX(), (int) e.getY());
		this.shape = new Circle(centre, 0, c, l);
		
	}
	@Override
	public void changeShape(MouseEvent e, PaintModel p) {
		this.shape.toFill(p.IwillFill());
		int horizontal = Math.abs((int) this.shape.getCentre().getX() - (int) e.getX());
		int vertical = Math.abs((int) this.shape.getCentre().getY() - (int) e.getY());
		int radius = (int)Math.sqrt(Math.pow(horizontal,2) + Math.pow(vertical,2));
		this.shape.setRadius(radius);
		p.update();
	}
	
	@Override
	public Drawable getShape() {
		return shape;
	}
	
}
