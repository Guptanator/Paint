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
	 * Circle Constructor. Makes a circle with a centre at p
	 * color c and thickness thickness.
	 *  
	 * @param p Current Point 
	 * @param c Current Color
	 * @param thickness Current Thickness
	*/
	public Circle(Point p, Color c, double thickness) {

		this.centre = p;
		this.radius = 0;
		this.properties.acceptCommand(new ColorCommand(c));
		this.properties.acceptCommand(new ThicknessCommand(thickness));
	}

	/** 
	 * Returns the centre point of the Circle
	*/
	public Point getCentre() {
		return centre;
	}

	/** 
	 * Sets the centre point of the Circle
	 * 
	 * @param centre New Centre point
	*/
	public void setCentre(Point centre) {
		this.centre = centre;
	}
	
	/** 
	 * Sets the radius of the Circle
	*/
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/** 
	 * Draw Command. Draws circle to graphics context
	 * given current circle properties.
	 * 
	 * @param g Current GraphicsContext
	*/
	@Override
	public void draw(GraphicsContext g) {
		int x = this.getCentre().getX()-(this.radius);
		int y = this.getCentre().getY()-(this.radius);
		this.properties.applyCommands(g);
		if(this.fill)
		{
			g.fillOval(x, y, this.radius*2, this.radius*2);
		}
		g.strokeOval(x, y, this.radius*2, this.radius*2);
	}

	/** 
	 * Type Designation for moving Circle
	*/
	@Override
	public String type() {
		return "Circle";
	}

	/** 
	 * Sets Circle's fill Command
	 * 
	 * @param filled PaintPanel fill boolean
	*/
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
