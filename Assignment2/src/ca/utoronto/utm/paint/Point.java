package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

public class Point extends Drawable {
	int x, y;
	boolean segment;
	Point(int x, int y) {
		this.x = x;
		this.y = y;
		this.segment= false;
	}
	Point(int x, int y, boolean segment) {
		this.x = x;
		this.y = y;
		this.segment = segment;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	public boolean isFinal() {
		return segment;
	}
	@Override
	public void draw(GraphicsContext g) {
		g.strokeLine(this.getX(), this.getY(), this.getX(), this.getY());
	}
	public void draw(GraphicsContext g,Point p2) {
		if (this.isFinal()) {
			return;
		}
		g.strokeLine(this.getX(), this.getY(), p2.getX(), p2.getY());
	}

	@Override
	public String type() {
		// TODO Auto-generated method stub
		return "Point";
	}
}
