package ca.utoronto.utm.paint;

import java.awt.Color;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

public class Rectangle extends Drawable{
	
	private int height;
	private int width;
	private boolean fill;
	private Point corner;
	private Color color;
	private Point start;
	
	public Rectangle(Point c, int h, int w) {
		this.color = new Color(0, 0, 0);
		this.fill = false;
		this.height = h;
		this.width = w;
		this.corner = c;
		this.start = new Point(c.getX(), c.getY());
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
	
	public void update(MouseEvent e) {
		int h = start.getY()- (int) e.getY();
		int w = start.getX()- (int) e.getX();
		if (h < 0) {
			this.height = Math.abs(h);
		} else if (h >= 0) {
			this.corner.setY((int) e.getY());
			this.height = h;
			
		}
		if (w < 0) {
			this.width = Math.abs(w);
		} else if (w >= 0) {
			this.corner.setX((int) e.getX());
			this.width = w;
			
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		// TODO Auto-generated method stub
		g.setStroke(Paint.valueOf("#"+Integer.toHexString(this.color.getRGB()).substring(2)));
		g.strokeRect(this.corner.getX(), this.corner.getY(), this.width, this.height);
		if(fill)
		{
			g.setFill(Paint.valueOf("#"+Integer.toHexString(this.color.getRGB()).substring(2)));
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
	public void toFill(boolean shouldFill)
	{
		this.fill = shouldFill;
	}
}
