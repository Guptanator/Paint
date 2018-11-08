package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class CircleStrategy implements ShapeManipulatorStrategy{
	
	private Circle shape;
	private PaintModel model;
	
	public void mouseHandle(MouseEvent e) {
		
	}
	
	@Override
	public void makeShape(MouseEvent e, Color c, double l) {
		Point centre = new Point((int) e.getX(), (int) e.getY());
		this.shape = new Circle(centre, 0, c, l);
		model.addDrawable(this.shape);
		
	}
	@Override
	public void changeShape(MouseEvent e, PaintModel p) {
		this.shape.toFill(p.getFill());
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
	
	@Override
	public void addShape(PaintModel p) {
		p.addDrawable(shape);
		this.shape = null;
	}
	
	@Override
	public void setModel(PaintModel p) {
		this.model = p;
	}
	
	@Override
	public void moveFeedback(PaintModel g, MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
