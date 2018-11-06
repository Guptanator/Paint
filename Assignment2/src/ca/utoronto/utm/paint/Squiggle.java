package ca.utoronto.utm.paint;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.canvas.GraphicsContext;

public class Squiggle extends Drawable {
	
	private ArrayList<Point> points;
	
	public Squiggle(int x, int y, Color c) {
		this.points = new ArrayList<Point>();
		this.points.add(new Point (x,y,c));
		this.color = c;
	}
	
	public void addPoint(int x, int y) {
		this.points.add(new Point (x,y,color));
	}
	
	@Override
	public void draw(GraphicsContext g, double thickness) {
		for (int i = 0; i < this.points.size() - 1; i++) {
			points.get(i).draw(g, points.get(i+1), thickness);
		}
	}

	@Override
	public String type() {
		// TODO Auto-generated method stub
		return "Squiggle";
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
	public void toFill(boolean fill) {
		
	}

}
