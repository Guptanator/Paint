package ca.utoronto.utm.shapes;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseButton;

public class PolyLineStrategy extends ShapeManipulatorStrategy {
	
	private Line shape;
	private boolean isFirst = true;
	private boolean isEnd = false;
	private boolean isTerminated;
	
	@Override
	public void mouseHandle(MouseEvent e) {
		if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			changeShape(e);
		} else if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
			if (e.getButton() == MouseButton.PRIMARY) {
				makeShape(e);
				this.isTerminated = false;
			} else {
				terminateShape();
			}
		} else if (e.getEventType() == MouseEvent.MOUSE_MOVED) {
			moveFeedback(e);
		} else if (e.getEventType() == MouseEvent.MOUSE_RELEASED) {
			addShape();
		}
	}
	
	private void makeShape(MouseEvent e) {
		Point p = new Point((int) e.getX(),(int) e.getY(), this.color, this.thickness);
		if (this.isFirst == true) {
			this.shape = new Line(p, p, this.color);
			this.model.addDrawable(this.shape);
		} else {
			this.shape.setLast(p);
		}
	}
	
	public void terminateShape() {
		if (!this.isTerminated) {
			this.model.removeLast();
			this.shape = null;
			this.isFirst = true;
			this.isEnd = true;
			this.isTerminated = true;
			this.model.update();
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
				this.shape = new Line(shape.getLast(), shape.getLast(), this.color);
				this.model.addDrawable(this.shape);
			} 
		} else {
			this.isEnd = false;
			this.model.update();
		}
	}

	public void moveFeedback(MouseEvent e) {
		if (!this.isFirst) {
			this.shape.setLast(new Point((int) e.getX(), (int) e.getY()));
			this.model.update();
		}
	}



}
