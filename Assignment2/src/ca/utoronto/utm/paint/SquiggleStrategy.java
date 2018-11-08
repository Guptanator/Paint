package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SquiggleStrategy extends ShapeManipulatorStrategy {
	

	private Squiggle shape;
	
	@Override
	public void mouseHandle(MouseEvent e) {
		if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			changeShape(e);
		} else if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
			makeShape(e);
		} else if (e.getEventType() == MouseEvent.MOUSE_RELEASED) {
			addShape();
		}
	}
	
	private void makeShape(MouseEvent e) {
		this.shape = new Squiggle((int) e.getX(), (int) e.getY(), this.color, this.thickness);
	}

	private void changeShape(MouseEvent e) {
		this.shape.addPoint((int) e.getX(), (int) e.getY());
		this.model.update();
	}

	private Drawable getShape() {
		return shape;
	}


	private void addShape() {
		this.model.addDrawable(this.shape);
		this.shape = null;
	}

}
