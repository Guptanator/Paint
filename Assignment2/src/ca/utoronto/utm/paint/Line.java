package ca.utoronto.utm.paint;

import java.awt.Color;

import javafx.scene.canvas.GraphicsContext;

public class Line extends Drawable {
	
	private Point first;
	private Point last;
	
	public Line(Point p, Color c) {
		this.first = p;
		this.color = c;
	}
	
	public Point getLast() {
		return this.last;
	}
	
	public void setLast(Point p) {
		this.last = p;
	}	
	
	@Override
	public void draw(GraphicsContext g, double thickness) {
		this.first.setColor(color);
		this.first.draw(g, last, thickness);
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
	public void toFill(boolean fill) {
		this.fill = fill;
	}

}
