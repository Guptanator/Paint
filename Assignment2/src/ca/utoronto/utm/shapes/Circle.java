package ca.utoronto.utm.shapes;


import java.awt.Graphics;
import java.util.ArrayList;

import ca.utoronto.utm.drawingCommands.ColorCommand;
import ca.utoronto.utm.drawingCommands.DrawingCommands;
import ca.utoronto.utm.drawingCommands.FillCommand;
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
	private double thickness;
	private Color color;
	
	private ArrayList<DrawingCommands> commands = new ArrayList<DrawingCommands>();
	
	/** 
	 * Sets PaintModel model in instance
	 * to current PaintModel
	 *  
	 *  @param p Current Point 
	*/
	public Circle(Point p, int r, Color c, double thickness) {

		this.centre = p;
		this.radius = r;
		this.color = c;
		this.thickness = thickness;
		this.commands.add(new ColorCommand(this.color));
		this.commands.add(new ThicknessCommand(this.thickness));
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
		for(DrawingCommands command: this.commands)
		{
			command.executeChange(g);
		}
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
	public void setColor(Color c) {
		this.color = c;
		this.commands.add(new ColorCommand(c));
	}

	@Override
	public Color getColor() {
		return this.color;
	}
	@Override
	public void setFill(boolean filled)
	{
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
		this.thickness = thickness;
		this.commands.add(new ThicknessCommand(this.thickness));
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
