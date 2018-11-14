package ca.utoronto.utm.shapes;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Squiggle extends Drawable {
	
	private ArrayList<Point> points;
	
	public Squiggle(int x, int y, Color c, double thickness) {
		this.points = new ArrayList<Point>();
		this.thickness = thickness;
		this.points.add(new Point (x,y,c, this.thickness));
		this.color = c;
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
	public void setColor(Color c) {
		this.color = c;
	}

	@Override
	public Color getColor() {
		return this.color;
	}
	@Override
	public void setThickness(double thickness) {
		this.thickness = thickness;
	}

}
