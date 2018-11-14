package ca.utoronto.utm.shapes;


import java.util.ArrayList;

import ca.utoronto.utm.drawingCommands.ColorCommand;
import ca.utoronto.utm.drawingCommands.DrawingCommands;
import ca.utoronto.utm.drawingCommands.ThicknessCommand;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Point extends Drawable {
	private int x, y;
	private boolean segment;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
		this.segment= false;
	}
	Point(int x, int y, Color c, double thickness){
		this(x,y);
		this.properties.acceptCommand(new ThicknessCommand(thickness));
		this.properties.acceptCommand(new ColorCommand(c));
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setIsFinal() {
		this.segment = true;
	}
	public boolean isFinal() {
		return segment;
	}
	
	@Override
	public void draw(GraphicsContext g) {
		this.properties.applyCommands(g);
		g.strokeLine(this.getX(), this.getY(), this.getX(), this.getY());
	}
	public void draw(GraphicsContext g,Point p2, double thickness) {
		if (this.isFinal()) {
			g.setLineWidth(this.properties.findThickness());
			return;
		}
		this.properties.applyCommands(g);
		g.strokeLine(this.getX(), this.getY(), p2.getX(), p2.getY());
	}

	@Override
	public String type() {
		return "Point";
	}
	public void update(MouseEvent e) {
	}
}
