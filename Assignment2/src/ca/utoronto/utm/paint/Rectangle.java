package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class Rectangle extends Drawable{
	
	private int height;
	private int width;
	private Point corner;
	private Point start;
	
	public Rectangle(Point c, int h, int w) {
		this.height = h;
		this.width = w;
		this.corner = c;
		this.start = new Point(c.getX(), c.getY());
	}
	
	public Point getCorner() {
		return this.corner;
	}
	
	public void setCorner(Point c) {
		this.corner = c;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setHeight(int h) {
		this.height = h;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setWidth(int w) {
		this.width = w;
	}
	
	public void update(MouseEvent e) {
		int h = start.getY()- (int) e.getY();
		int w = start.getX()- (int) e.getX();
		if (h < 0) {
			this.height = Math.abs(h);
		} else if (h >= 0) {
			this.corner.setY((int) e.getY());
			this.height = h;
			
		}
		if (w < 0) {
			this.width = Math.abs(w);
		} else if (w >= 0) {
			this.corner.setX((int) e.getX());
			this.width = w;
			
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		// TODO Auto-generated method stub
		g.strokeRect(this.corner.getX(), this.corner.getY(), this.width, this.height);
		
	}

	@Override
	public String type() {
		return "Rectangle";
	}

}
