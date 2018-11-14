package ca.utoronto.utm.shapes;


import java.util.ArrayList;

import ca.utoronto.utm.drawingCommands.ColorCommand;
import ca.utoronto.utm.drawingCommands.DrawingCommands;
import ca.utoronto.utm.drawingCommands.FillCommand;
import ca.utoronto.utm.drawingCommands.PropertyInvoker;
import ca.utoronto.utm.drawingCommands.ThicknessCommand;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Rectangle extends ClosedShape {
	
	private int height = 0;
	private int width = 0;
	private Point corner;
	private Point start;
	
	public Rectangle(Point c, Color color, double thickness) {
		this.corner = c;
		this.start = new Point(c.getX(), c.getY());
		this.properties.acceptCommand(new ColorCommand(color));
		this.properties.acceptCommand(new ThicknessCommand(thickness));
	}
	
	public Point getStart() {
		return this.start;
	}
	
	public Point getCorner() {
		return this.corner;
	}
	public void setCorner(Point p) {
		this.corner = p;
	}
	
	public void setHeight(int h) {
		this.height = h;
	}
	
	public void setWidth(int w) {
		this.width = w;
	}
	

	@Override
	public void draw(GraphicsContext g) {
		this.properties.applyCommands(g);
		if(fill)
		{
			g.fillRect(this.corner.getX(), this.corner.getY(), this.width, this.height);
		}
		g.strokeRect(this.corner.getX(), this.corner.getY(), this.width, this.height);
	}

	@Override
	public String type() {
		return "Rectangle";
	}

	@Override
	public void setColor(Color c) {
		this.properties.acceptCommand(new ColorCommand(c));
	}
	
	public void setFill(boolean filled) {
		Color c = this.properties.findColor();
		if(filled)
		{
			this.properties.acceptCommand(new FillCommand(c));
		}
		else
		{
			this.properties.acceptCommand(new FillCommand(new Color(0,0,0,0)));
		}
		this.fill = filled;
	}

	public boolean isClicked(MouseEvent e) {
		double x = e.getX();double y = e.getY();
		if (x<=this.getCorner().getX()+this.width && x>this.getCorner().getX()) {
			if (y<=this.getCorner().getY()+this.height && y>this.getCorner().getY()) {
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

}
