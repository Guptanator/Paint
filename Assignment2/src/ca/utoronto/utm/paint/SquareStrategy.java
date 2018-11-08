package ca.utoronto.utm.paint;


import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class SquareStrategy implements ShapeManipulatorStrategy {
	
	private Square shape;
	
	@Override
	public void makeShape(MouseEvent e, Color c, double len) {
		Point corner = new Point((int) e.getX(), (int) e.getY());
		this.shape = new Square(corner, 0, c, len);
	}

	@Override
	public void changeShape(MouseEvent e, PaintModel p) {
		this.shape.toFill(p.getFill());
		int h = this.shape.getStart().getY()- (int) e.getY();
		int w = this.shape.getStart().getX()- (int) e.getX();
		if (h <= 0) {
			if (w <= 0) {
				if (h >= w) {
					this.shape.setLength(Math.abs(h));
				} else {this.shape.setLength(Math.abs(w));}
			} else {
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
				if (h >= Math.abs(w)) {
					this.shape.getCorner().setY(this.shape.getStart().getY() + w);
					this.shape.setLength(Math.abs(w));
				} else {
					this.shape.getCorner().setY(this.shape.getStart().getY() - h);
					this.shape.setLength(h);
				}
			} else {
				if (h > w) {
					this.shape.getCorner().setX((int) e.getX());
					this.shape.getCorner().setY(this.shape.getStart().getY() - w);
					this.shape.setLength(w);
					
				} else {
					this.shape.getCorner().setX(this.shape.getStart().getX() - h);
					this.shape.getCorner().setY(this.shape.getStart().getY() - h);	
					this.shape.setLength(h);
				}
			}
			
		} 
		
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
		// TODO Auto-generated method stub
		
	}


}
