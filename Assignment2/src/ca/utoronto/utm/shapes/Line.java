package ca.utoronto.utm.shapes;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Line extends Drawable {
	
	private Point first;
	private Point last;
	private double thickness;
	
	
	public Line(Point p, Color c) {
		this.first = p;
		this.color = c;
		this.thickness = thickness;
	}
	
	public Line(Point p1, Point p2, Color c) {
		this.first = p1;
		this.last = p2;
		this.color = c;
	}
	
	public Point getLast() {
		return this.last;
	}
	
	public void setLast(Point p) {
		this.last = p;
	}	
	
	@Override
	public void draw(GraphicsContext g) {
		g.setLineWidth(this.thickness);
		this.first.setColor(color);
		this.first.draw(g, last, this.thickness);
	}

	@Override
	public String type() {
		return "Line";
	}

	@Override
	public void setColor(Color c) {
		this.color = c;
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public void setThickness(double thickness) {
		this.thickness = thickness;
	}

	@Override
	public boolean isClosed() {
		// TODO Auto-generated method stub
		return false;
	}
}
