package ca.utoronto.utm.paint;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Point extends Drawable {
	int x, y;
	boolean segment;

	Point(int x, int y) {
		this.x = x;
		this.y = y;
		this.segment= false;
	}
	Point(int x, int y, Color c, double thickness){
		this(x,y);
		this.color = c;
		this.thickness = thickness;
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
	
	public void setIsFinal() {
		this.segment = true;
	}
	public boolean isFinal() {
		return segment;
	}
	
	@Override
	public void draw(GraphicsContext g) {
		g.setStroke(this.color);
		g.strokeLine(this.getX(), this.getY(), this.getX(), this.getY());
		g.setLineWidth(thickness);
	}
	public void draw(GraphicsContext g,Point p2, double thickness) {
		g.setLineWidth(this.thickness);
		if (this.isFinal()) {
			return;
		}
		g.setStroke(this.color);
		g.strokeLine(this.getX(), this.getY(), p2.getX(), p2.getY());
		
	}

	@Override
	public String type() {
		// TODO Auto-generated method stub
		return "Point";
	}
	@Override
	public void setColor(Color c) {
		// TODO Auto-generated method stub
		this.color = c;
	}
	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}
	public void update(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void setFill(boolean fill) {
		return;
	}
	
}
