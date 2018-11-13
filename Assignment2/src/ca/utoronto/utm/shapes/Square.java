package ca.utoronto.utm.shapes;


import java.util.ArrayList;

import ca.utoronto.utm.drawingCommands.ColorCommand;
import ca.utoronto.utm.drawingCommands.DrawingCommands;
import ca.utoronto.utm.drawingCommands.FillCommand;
import ca.utoronto.utm.drawingCommands.ThicknessCommand;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Square extends ClosedShape {
	
	private int length;
	private Point corner;
	private Color color;
	private Point start;
	private double thickness;
	
	private ArrayList<DrawingCommands> commands= new ArrayList<DrawingCommands>();
		
	public Square(Point p, Color color, double thickness) {
		this.corner = p;
		this.start = new Point(p.getX(), p.getY());
		this.color = color;
		this.thickness = thickness;
		this.commands.add(new ColorCommand(this.color));
		this.commands.add(new ThicknessCommand(this.thickness));
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
	
	public int getLength() {
		return this.length;
	}
	
	public void setLength(int s) {
		this.length = s;
	}
	

	@Override
	public void draw(GraphicsContext g) {
		for(DrawingCommands command: this.commands)
		{
			command.executeChange(g);
		}
//		
		if(fill)
		{
			g.fillRect(this.corner.getX(), this.corner.getY(), this.length, this.length);
		}
		g.strokeRect(this.corner.getX(), this.corner.getY(), this.length, this.length);
	}

	@Override
	public String type() {
		return "Square";
	}

	@Override
	public void setColor(Color c) {
		this.color = c;		
		this.commands.add(new ColorCommand(c));
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public void setFill(boolean filled) {
		if(filled)
		{
			this.commands.add(new FillCommand(this.color));
		}
		else
		{
			this.commands.add(new FillCommand(new Color(0,0,0,0)));
		}
		this.fill = filled;
	}

	@Override
	public void setThickness(double thickness) {
		this.commands.add(new ThicknessCommand(thickness));
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
		return true;
	}
}
