package ca.utoronto.utm.paint;


import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SquareStrategy extends ShapeManipulatorStrategy {
	
	private Square shape;
	
	@Override
	public void mouseHandle(MouseEvent e) {
		if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			changeShape(e);
		} else if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
			makeShape(e);
		}
	}
	
	private void makeShape(MouseEvent e) {
		Point corner = new Point((int) e.getX(), (int) e.getY());
		this.shape = new Square(corner, 0, this.color, this.thickness);
		this.model.addDrawable(this.shape);
	}

	private void changeShape(MouseEvent e) {
		this.shape.toFill(this.model.getFill());
		int h = this.shape.getStart().getY()- (int) e.getY();
		int w = this.shape.getStart().getX()- (int) e.getX();
		if (h <= 0) {
			if (w <= 0) {
				this.shape.getCorner().setX(this.shape.getStart().getX());
				this.shape.getCorner().setY(this.shape.getStart().getY());
				if (h >= w) {
					this.shape.setLength(Math.abs(h));
				} else {this.shape.setLength(Math.abs(w));}
			} else {
				this.shape.getCorner().setY(this.shape.getStart().getY());
				if (Math.abs(h) >= w) {
					this.shape.getCorner().setX(this.shape.getStart().getX() - w);
					this.shape.setLength(w);
				} else {
					this.shape.getCorner().setX(this.shape.getStart().getX() + h);
					this.shape.setLength(Math.abs(h));
				}
			}
		} else {
			if (w <= 0) {
				this.shape.getCorner().setX(this.shape.getStart().getX());
				if (h >= Math.abs(w)) {
					this.shape.getCorner().setY(this.shape.getStart().getY() + w);
					this.shape.setLength(Math.abs(w));
				} else {
					this.shape.getCorner().setY(this.shape.getStart().getY() - h);
					this.shape.setLength(h);
				}
			} else {
				if (h > w) {
					this.shape.getCorner().setX(this.shape.getStart().getX() - w);
					this.shape.getCorner().setY(this.shape.getStart().getY() - w);
					this.shape.setLength(w);
					
				} else {
					this.shape.getCorner().setX(this.shape.getStart().getX() - h);
					this.shape.getCorner().setY(this.shape.getStart().getY() - h);	
					this.shape.setLength(h);
				}
			}
			
		} 	
		this.model.update();
	}

}
