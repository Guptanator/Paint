package ca.utoronto.utm.paint;

import java.awt.Color;

import javafx.scene.input.MouseEvent;

public class SquiggleStrategy implements ShapeManipulatorStrategy {
	
	Point shape;
	@Override
	public void makeShape(MouseEvent e, Color c) {
		this.shape = new Point((int) e.getX(), (int) e.getY(), c);
	}

	@Override
	public void changeShape(MouseEvent e, PaintModel p) {
		this.shape.setX((int) e.getX());
		this.shape.setY((int) e.getY());
		p.addDrawable(new Point(this.shape.getX(), this.shape.getY(), this.shape.getColor()));
	}

	@Override
	public Drawable getShape() {
		this.shape.setIsFinal();
		return shape;
	}

	@Override
	public void setNull() {
		this.shape = null;
		
	}

	
}
