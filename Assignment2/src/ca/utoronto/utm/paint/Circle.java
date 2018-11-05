package ca.utoronto.utm.paint;

import java.awt.Color;

import java.awt.Graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.input.MouseEvent;

public class Circle extends Drawable {
	private Point centre;
	private int radius;
	private boolean fill;
	private double len; 
	private Color color;
	
	public Circle(Point centre, int radius, Color color, double len) {
		this.color = color;
		this.fill = false;
		this.centre = centre;
		this.radius = radius;
		this.len = len;

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
	public void draw(GraphicsContext g, double thickness) {
		int radius = this.getRadius();
		int x = this.getCentre().getX()-(radius);
		int y = this.getCentre().getY()-(radius);
		g.setLineWidth(len);
		g.setStroke(Paint.valueOf("#"+Integer.toHexString(this.color.getRGB()).substring(2)));
		g.strokeOval(x, y, radius*2, radius*2);
		if(fill)
		{
			g.setFill(Paint.valueOf("#"+Integer.toHexString(this.color.getRGB()).substring(2)));
			g.fillOval(x, y, radius*2, radius*2);
		}
		g.setStroke(Paint.valueOf("#"+Integer.toHexString(this.color.getRGB()).substring(2)));
		g.strokeOval(x, y, radius*2, radius*2);
		g.setLineWidth(thickness);
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
	public void toFill(boolean shouldFill)
	{
		this.fill = shouldFill;
	}
}
