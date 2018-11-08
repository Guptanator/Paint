package ca.utoronto.utm.paint;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Rectangle extends Drawable{
	
	private int height;
	private int width;
	private boolean fill;
	private Point corner;
	private Point start;
	
	public Rectangle(Point c, int h, int w, Color color, double thickness) {
		this.height = h;
		this.width = w;
		this.corner = c;
		this.color = color;
		this.start = new Point(c.getX(), c.getY());
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
	
	public int getHeight() {
		return this.height;
	}
	
	public void setHeight(int h) {
		this.height = h;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public void setWidth(int w) {
		this.width = w;
	}
	

	@Override
	public void draw(GraphicsContext g) {
		// TODO Auto-generated method stub
		g.setStroke(this.color);
		g.setLineWidth(this.thickness);
		g.strokeRect(this.corner.getX(), this.corner.getY(), this.width, this.height);
		if(fill)
		{
			g.setFill(this.color);
			g.fillRect(this.corner.getX(), this.corner.getY(), this.width, this.height);
		}
	}

	@Override
	public String type() {
		return "Rectangle";
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
	public void toFill(boolean fill)
	{
		this.fill = fill;
	}

}
