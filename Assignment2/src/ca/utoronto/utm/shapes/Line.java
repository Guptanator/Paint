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
	
	public Line(Point p, Color c, double thickness) {
		this.first = p;
		this.properties.acceptCommand(new ThicknessCommand(thickness));
		this.properties.acceptCommand(new ColorCommand(c));
	}
	
	public Line(Point p1, Point p2, Color c) {
		this.first = p1;
		this.last = p2;
		this.properties.acceptCommand(new ColorCommand(c));
	}
	
	public Point getLast() {
		return this.last;
	}
	
	public void setLast(Point p) {
		this.last = p;
	}	
	
	@Override
	public void draw(GraphicsContext g) {
		this.properties.applyCommands(g);
		this.first.setColor(this.properties.findColor());
		this.first.draw(g, last, this.thickness);
	}

	@Override
	public String type() {
		return "Line";
	}
}
