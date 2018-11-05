package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;
import java.awt.Color;

public class RectangleStrategy implements ShapeManipulatorStrategy {
	
	private Rectangle shape;
	
	@Override
	public void makeShape(MouseEvent e, Color c) {
		Point corner = new Point((int) e.getX(), (int) e.getY());
		int length = 0;
		this.shape = new Rectangle(corner, length, length, c);
	}

	@Override
	public void changeShape(MouseEvent e, PaintModel p) {
		this.shape.toFill(p.IwillFill());
		int h = this.shape.getStart().getY()- (int) e.getY();
		int w = this.shape.getStart().getX()- (int) e.getX();
		if (h < 0) {
			this.shape.setHeight(Math.abs(h));
		} else if (h >= 0) {
			this.shape.getCorner().setY((int) e.getY());
			this.shape.setHeight(h);
			
		}
		if (w < 0) {
			this.shape.setWidth(Math.abs(w));
		} else if (w >= 0) {
			this.shape.getCorner().setX((int) e.getX());
			this.shape.setWidth(w);
			
		}
		p.update();
	}

	@Override
	public Drawable getShape() {
		// TODO Auto-generated method stub
		return shape;
	}
	

}
