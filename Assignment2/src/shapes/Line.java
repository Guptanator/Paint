package shapes;


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
	public void toFill(boolean fill) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double xDifferent(double d) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double yDifferent(double d) {
		// TODO Auto-generated method stub
		return 0;
	}

}
