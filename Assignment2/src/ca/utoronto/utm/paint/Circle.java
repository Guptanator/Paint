package ca.utoronto.utm.paint;

import java.awt.Color;

import java.awt.Graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.input.MouseEvent;

public class Circle extends Drawable {
	
	private Point centre;
	private int radius;
	private Color color;

	public Circle(Point centre, int radius) {
		this.color = new Color(0, 0, 0);
		this.centre = centre;
		this.radius = radius;
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
	
	public void update(MouseEvent e) {
		int horizontal = Math.abs((int) this.centre.getX() - (int) e.getX());
		int vertical = Math.abs((int) this.centre.getY() - (int) e.getY());
		int radius = (int)Math.sqrt(Math.pow(horizontal,2) + Math.pow(vertical,2));
		this.radius = radius;
	}

	@Override
	public void draw(GraphicsContext g) {
		int radius = this.getRadius();
		int x = this.getCentre().getX()-(radius);
		int y = this.getCentre().getY()-(radius);
		g.setStroke(Paint.valueOf("#"+Integer.toHexString(this.color.getRGB()).substring(2)));
		g.strokeOval(x, y, radius*2, radius*2);
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
}
