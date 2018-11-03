package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends Drawable{
	
	private int height;
	private int width;
	private Point corner;
	
	public Rectangle(Point c, int h, int w) {
		this.height = h;
		this.width = w;
		this.corner = c;
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

	@Override
	public void draw(GraphicsContext g, double thickness) {
		// TODO Auto-generated method stub
		g.strokeRect(this.corner.getX(), this.corner.getY(), this.width, this.height);
		g.setLineWidth(thickness);
		
	}

	@Override
	public String type() {
		return "Rectangle";
	}

}
