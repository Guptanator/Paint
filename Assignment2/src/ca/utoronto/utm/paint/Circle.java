package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public class Circle extends Drawable {
	
	private Point centre;
	private int radius;

	public Circle(Point centre, int radius) {
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
		g.strokeOval(x, y, radius*2, radius*2);
	}

	@Override
	public String type() {
		// TODO Auto-generated method stub
		return "Circle";
	}

}
