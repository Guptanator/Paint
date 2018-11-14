package ca.utoronto.utm.shapes;


import java.util.ArrayList;

import ca.utoronto.utm.drawingCommands.ColorCommand;
import ca.utoronto.utm.drawingCommands.DrawingCommands;
import ca.utoronto.utm.drawingCommands.ThicknessCommand;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Line extends Drawable {
	
	private Point first;
	private Point last;
	private double thickness;
	
	private ArrayList<DrawingCommands> commands= new ArrayList<DrawingCommands>();
	
	
	public Line(Point p, Color c, double thickness) {
		this.first = p;
		this.color = c;
		this.thickness = thickness;
		this.commands.add(new ColorCommand(this.color));
		this.commands.add(new ThicknessCommand(this.thickness));
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
		for(DrawingCommands command: this.commands)
		{
			command.executeChange(g);
		}
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
		this.commands.add(new ColorCommand(c));
	}

	@Override
	public Color getColor() {
		return this.color;
	}

	@Override
	public void setThickness(double thickness) {
		this.commands.add(new ThicknessCommand(thickness));
	}
}
