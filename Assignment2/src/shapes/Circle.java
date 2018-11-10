package shapes;


import java.awt.Graphics;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.input.MouseEvent;

public class Circle extends Drawable {
	
	private Point centre;
	private int radius;
	private boolean fill;
	
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
	public void toFill(boolean shouldFill)
	{
		this.fill = shouldFill;
	}

	@Override
	public boolean isClicked(MouseEvent e) {
		double distance = Math.sqrt(Math.pow(e.getX() - this.getCentre().getX(), 2) + Math.pow(e.getY() - this.getCentre().getY(), 2));
		if (distance <= this.radius) {
			return true;
		}
		return false;
	}
	public double yDifferent(double d) {
		if (d>this.getCentre().getY()) {
			return -(d-this.getCentre().getY());
		} else {
			return this.getCentre().getY()-d;
		}
	}
	public double xDifferent(double d) {
		if (d<this.getCentre().getX()) {
			return this.getCentre().getX()-d;
		} else {
			return -(d-this.getCentre().getX());
		}		
	}
}
