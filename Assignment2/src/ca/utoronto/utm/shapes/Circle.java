package ca.utoronto.utm.shapes;


import java.awt.Graphics;
import java.util.ArrayList;

import ca.utoronto.utm.drawingCommands.ColorCommand;
import ca.utoronto.utm.drawingCommands.DrawingCommands;
import ca.utoronto.utm.drawingCommands.FillCommand;
import ca.utoronto.utm.drawingCommands.PropertyInvoker;
import ca.utoronto.utm.drawingCommands.ThicknessCommand;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
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
	private float radius;
	
	/** 
	 * Circle Constructor. Makes a circle with a centre at p
	 * color c and thickness thickness.
	 *  
	 * @param p Current Point 
	 * @param c Current Color
	 * @param thickness Current Thickness
	*/
	public Circle(Point p, Color c, double thickness) {
		super(c,thickness);
		this.centre = p;
		this.radius = 0;
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
		int x = this.getCentre().getX()-((int)this.radius);
		int y = this.getCentre().getY()-((int)this.radius);
		this.properties.applyCommands(g);
		g.fillOval(x, y, this.radius*2, this.radius*2);
		g.strokeOval(x, y, this.radius*2, this.radius*2);
		this.update(g);
	}

	/** 
	 * Type Designation for moving Circle
	*/
	@Override
	public String type() {
		return "Circle";
	}

	@Override
	public boolean isClicked(MouseEvent e) {
		double thick=this.properties.findThickness()/2;
		double distance = Math.sqrt(Math.pow(e.getX() - this.getCentre().getX(), 2) + Math.pow(e.getY() - this.getCentre().getY(), 2));
		if (distance < this.radius+thick) {
			return true;
		}
		return false;
	}
	public double yDifferent(double d) {
		if (d>this.centre.getY()) {
			return -(d-this.centre.getY());
		} else {
			return this.centre.getY()-d;
		}
	}
	public double xDifferent(double d) {
		if (d<this.centre.getX()) {
			return this.centre.getX()-d;
		} else {
			return -(d-this.centre.getX());
		}
	}

}
