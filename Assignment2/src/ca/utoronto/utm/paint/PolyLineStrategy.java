package ca.utoronto.utm.paint;

import java.awt.Color;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class PolyLineStrategy implements ShapeManipulatorStrategy {
	
	private Line shape;
	private boolean isFirst = true;
	
	@Override
	public void makeShape(MouseEvent e, Color c, double l) {
		if (this.isFirst == true) {
			Point p = new Point((int) e.getX(),(int) e.getY(), c);
			this.shape = new Line(p, c);
		} else {
			Point p = new Point((int) e.getX(),(int) e.getY(), c);
			this.shape.setLast(p);
		}
	}

	@Override
	public void changeShape(MouseEvent e, PaintModel p) {
		if (!this.isFirst) {
			
		}
	}

	@Override
	public Drawable getShape() {
		// TODO Auto-generated method stub
		return this.shape;
	}

	@Override
	public void addShape(PaintModel p) {
		if (this.isFirst) {
			this.isFirst = false;
		} else {
			p.addDrawable(this.shape);
			this.shape = new Line(shape.getLast(), shape.getColor());
		}
	}

	@Override
	public void moveFeedback(PaintModel p, MouseEvent e) {
		this.shape.setLast(new Point((int) e.getX(), (int) e.getY()));
		p.update();
	}

}
