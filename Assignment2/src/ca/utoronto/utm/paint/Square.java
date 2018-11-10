package ca.utoronto.utm.paint;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Square extends Drawable {
	
	private int length;
	private boolean fill;
	private Point corner;
	private Color color;
	private Point start;
	private double len;
		
	public Square(Point c, int s, Color color, double len) {
		this.length = s;
		this.corner = c;
		this.start = new Point(c.getX(), c.getY());
		this.color = color;
		this.len = len;
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
	public void draw(GraphicsContext g, double len) {
		g.setStroke(this.color);
		g.strokeRect(this.corner.getX(), this.corner.getY(), this.length, this.length);
		g.setLineWidth(len);
		if(fill)
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

	@Override
	public void setFill(boolean fill) {
		// TODO Auto-generated method stub
		this.fill = fill;
	}

}
