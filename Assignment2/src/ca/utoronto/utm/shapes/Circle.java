package ca.utoronto.utm.shapes;


import java.awt.Graphics;
import java.util.ArrayList;

import ca.utoronto.utm.drawingCommands.ColorCommand;
import ca.utoronto.utm.drawingCommands.DrawingCommands;
import ca.utoronto.utm.drawingCommands.FillCommand;
import ca.utoronto.utm.drawingCommands.PropertyInvoker;
import ca.utoronto.utm.drawingCommands.ThicknessCommand;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.input.MouseEvent;

/** 
 * Circle Drawable Command that holds dimensions
 * and draws itself to the GraphicsContext
*/
public class Circle extends ClosedShape {
	
	private Point centre;
	private int radius;
	private boolean fill = false;
	
	/** 
	 * Sets PaintModel model in instance
	 * to current PaintModel
	 *  
	 *  @param p Current Point 
	*/
	public Circle(Point p, int r, Color c, double thickness) {

		this.centre = p;
		this.radius = r;
		this.properties.acceptCommand(new ColorCommand(c));
		this.properties.acceptCommand(new ThicknessCommand(thickness));
	}

	public Point getCentre() {
		return centre;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	

	@Override
	public void draw(GraphicsContext g) {
		int radius = this.getRadius();
		int x = this.getCentre().getX()-(radius);
		int y = this.getCentre().getY()-(radius);
		this.properties.applyCommands(g);
		if(this.fill)
		{
			g.fillOval(x, y, radius*2, radius*2);
		}
		g.strokeOval(x, y, radius*2, radius*2);
	}

	@Override
	public String type() {
		return "Circle";
	}

	@Override
	public void setFill(boolean filled)
	{
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

	@Override
	public boolean isClicked(MouseEvent e) {
		double distance = Math.sqrt(Math.pow(e.getX() - this.getCentre().getX(), 2) + Math.pow(e.getY() - this.getCentre().getY(), 2));
		if (distance <= this.radius) {
			return true;
		}
		return false;
	}
	public double yDifferent(double d) {
		if (d>this.getCentre().getY()) {
			return -(d-this.getCentre().getY());
		} else {
			return this.getCentre().getY()-d;
		}
	}
	public double xDifferent(double d) {
		if (d<this.getCentre().getX()) {
			return this.getCentre().getX()-d;
		} else {
			return -(d-this.getCentre().getX());
		}
	}
}
