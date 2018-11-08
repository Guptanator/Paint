package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseButton;

public class PolyLineStrategy implements ShapeManipulatorStrategy {
	
	private Line shape;
	private boolean isFirst = true;
	private boolean isEnd = false;
	
	@Override
	public void makeShape(MouseEvent e, Color c, double l) {
		if (e.getButton() == MouseButton.PRIMARY) {
			if (this.isFirst == true) {
				this.shape = new Line(new Point((int) e.getX(),(int) e.getY()), c);
			} else {
				Point p = new Point((int) e.getX(),(int) e.getY(), c);
				this.shape.setLast(p);
			} 
		} else {
			this.shape = null;
			this.isFirst = true;
			this.isEnd = true;
		}
	}

	@Override
	public void changeShape(MouseEvent e, PaintModel p) {
		if (!this.isFirst) {
			this.shape.setLast(new Point((int) e.getX(), (int) e.getY()));
			p.update();
		}
	}

	@Override
	public Drawable getShape() {
		// TODO Auto-generated method stub
		return this.shape;
	}

	@Override
	public void addShape(PaintModel p) {
		if (!this.isEnd) {
			if (this.isFirst) {
				this.isFirst = false;
			} else {
				p.addDrawable(this.shape);
				this.shape = new Line(shape.getLast(), shape.getColor());
			} 
		} else {
			this.isEnd = false;
			p.update();
		}
	}

	@Override
	public void moveFeedback(PaintModel p, MouseEvent e) {
		this.shape.setLast(new Point((int) e.getX(), (int) e.getY()));
		p.update();
	}

}
