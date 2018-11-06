package ca.utoronto.utm.paint;

import java.awt.Color;
import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class Squiggle extends Drawable {
	
	private ArrayList<Line> lines;
	
	public Squiggle() {
		this.lines = new ArrayList<Line>();
		
	}

	@Override
	public void draw(GraphicsContext g, double thickness) {
		for (Line line: lines) {
			line.draw(g, thickness);
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
