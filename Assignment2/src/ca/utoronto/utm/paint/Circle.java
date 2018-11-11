package ca.utoronto.utm.paint;


import java.awt.Graphics;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.input.MouseEvent;

public class Circle extends Drawable{
	
	private Point centre;
	private int radius;
	private boolean fill;
	private double len; 
	private double thickness;
	private Color color;
	
	public Circle(Point p, int r, Color c, double thickness) {

		this.centre = p;
		this.radius = r;
		this.color = c;
		this.thickness = thickness;

	}

	public Point getCentre() {
		return centre;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	

	@Override
	public void draw(GraphicsContext g) {
		int radius = this.getRadius();
		int x = this.getCentre().getX()-(radius);
		int y = this.getCentre().getY()-(radius);
		g.setLineWidth(this.thickness);
		g.setStroke(this.color);
		g.strokeOval(x, y, radius*2, radius*2);
		if(fill)
		{
			g.setFill(this.color);
			g.fillOval(x, y, radius*2, radius*2);
		}
	}

	@Override
	public String type() {
		return "Circle";
	}

	@Override
	public void setColor(Color c) {
		this.color = c;
	}

	@Override
	public Color getColor() {
		return this.color;
	}
	public void setFill(boolean fill)
	{
		this.fill = fill;
	}

	@Override
	public void setThickness(double thickness) {
		this.thickness = thickness;
	}
}
