package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SquiggleStrategy implements ShapeManipulatorStrategy {
	

	private Squiggle shape;
	
	@Override
	public void makeShape(MouseEvent e, Color c, double l) {
		this.shape = new Squiggle((int) e.getX(), (int) e.getY(), c);
	}

	@Override
	public void changeShape(MouseEvent e, PaintModel p) {
		this.shape.addPoint((int) e.getX(), (int) e.getY());
		p.update();
	}

	@Override
	public Drawable getShape() {
		return shape;
	}


	@Override
	public void addShape(PaintModel p) {
		p.addDrawable(this.shape);
		this.shape = null;
	}

	@Override
	public void moveFeedback(PaintModel g, MouseEvent e) {
		
	}


}
