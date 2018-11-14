package ca.utoronto.utm.shapes;
import java.util.ArrayList;
import java.util.Arrays;

import ca.utoronto.utm.drawingCommands.ColorCommand;
import ca.utoronto.utm.drawingCommands.PropertyInvoker;
import ca.utoronto.utm.drawingCommands.ThicknessCommand;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Squiggle extends Drawable {
	
	private ArrayList<Point> points;
	private double thickness;
	private Color color;
	
	public Squiggle(int x, int y, Color c, double thickness) {
		this.points = new ArrayList<Point>();
		this.properties.acceptCommand(new ThicknessCommand(thickness));
		this.properties.acceptCommand(new ColorCommand(c));
		this.thickness = properties.findThickness();
		this.color = properties.findColor();
		this.points.add(new Point (x,y,c, this.thickness));
	}
	
	public void addPoint(int x, int y) {
		this.points.add(new Point (x,y,color, this.thickness));
	}
	
	@Override
	public void draw(GraphicsContext g) {
		g.setLineWidth(this.thickness);
		for (int i = 0; i < this.points.size() - 1; i++) {
			points.get(i).draw(g, points.get(i+1), this.thickness);
		}
	}

	@Override
	public String type() {
		return "Squiggle";
	}
	@Override
	public void setThickness(double thickness) {
		this.thickness = thickness;
	}

}
