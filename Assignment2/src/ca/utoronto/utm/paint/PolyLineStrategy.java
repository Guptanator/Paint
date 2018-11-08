package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseButton;

public class PolyLineStrategy extends ShapeManipulatorStrategy {
	
	private Line shape;
	private boolean isFirst = true;
	private boolean isEnd = false;
	
	@Override
	public void mouseHandle(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			changeShape(e);
		} else if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
			makeShape(e);
		} else if (e.getEventType() == MouseEvent.MOUSE_MOVED) {
			moveFeedback(e);
		} else if (e.getEventType() == MouseEvent.MOUSE_RELEASED) {
			addShape();
		}
	}
	
	private void makeShape(MouseEvent e) {
		if (e.getButton() == MouseButton.PRIMARY) {
			if (this.isFirst == true) {
				this.shape = new Line(new Point((int) e.getX(),(int) e.getY()), this.color);
			} else {
				Point p = new Point((int) e.getX(),(int) e.getY(), this.color, this.thickness);
				this.shape.setLast(p);
			} 
		} else {
			this.shape = null;
			this.isFirst = true;
			this.isEnd = true;
		}
	}

	private void changeShape(MouseEvent e) {
		if (!this.isFirst) {
			this.shape.setLast(new Point((int) e.getX(), (int) e.getY()));
			this.model.update();
		}
	}


	private void addShape() {
		if (!this.isEnd) {
			if (this.isFirst) {
				this.isFirst = false;
			} else {
				this.model.addDrawable(this.shape);
				this.shape = new Line(shape.getLast(), shape.getColor());
			} 
		} else {
			this.isEnd = false;
			this.model.update();
		}
	}

	public void moveFeedback(MouseEvent e) {
		this.shape.setLast(new Point((int) e.getX(), (int) e.getY()));
		this.model.update();
	}



}
