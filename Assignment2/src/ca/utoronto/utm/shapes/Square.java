package ca.utoronto.utm.shapes;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Square extends ClosedShape {
	
	private int length;
	private boolean fill;
	private Point corner;
	private Color color;
	private Point start;
	private double thickness;
		
	public Square(Point p, Color color, double thickness) {
		this.corner = p;
		this.start = new Point(p.getX(), p.getY());
		this.color = color;
		this.thickness = thickness;
	}
	
	public Point getStart() {
		return this.start;
	}
	
	public Point getCorner() {
		return this.corner;
	}
	
	public void setCorner(Point c) {
		this.corner = c;
	}
	
	public int geLength() {
		return this.length;
	}
	
	public void setLength(int s) {
		this.length = s;
	}
	

	@Override
	public void draw(GraphicsContext g) {
		g.setStroke(this.color);
		g.setLineWidth(this.thickness);
		g.strokeRect(this.corner.getX(), this.corner.getY(), this.length, this.length);
		
		if(this.fill)
		{
			g.fillRect(this.corner.getX(), this.corner.getY(), this.length, this.length);
		}
	}

	@Override
	public String type() {
		// TODO Auto-generated method stub
		return "Square";
	}

	@Override
	public void setColor(Color c) {
		this.color = c;		
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return this.color;
	}

	public void toFill(boolean fill) {
		this.fill = fill;
	}

	@Override
	public void setFill(boolean fill) {
		this.fill = fill;
	}

	@Override
	public void setThickness(double thickness) {
		this.thickness = thickness;
	}

	public boolean isClicked(MouseEvent e) {
		double x = e.getX();double y = e.getY();
		if (x<=this.getCorner().getX()+this.length && x>this.getCorner().getX()) {
			if (y<=this.getCorner().getY()+this.length && y>this.getCorner().getY()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public double yDifferent(double d) {
		if (d>this.getCorner().getY()) {
			return -(d-this.getCorner().getY());
		} else {
			return this.getCorner().getY()-d;
		}
	}

	@Override
	public double xDifferent(double d) {
		if (d<this.getCorner().getX()) {
			return this.getCorner().getX()-d;
		} else {
			return -(d-this.getCorner().getX());
		}
	}

	@Override
	public boolean isClosed() {
		// TODO Auto-generated method stub
		return true;
	}
}
