package ca.utoronto.utm.shapes;


import java.util.ArrayList;

import ca.utoronto.utm.drawingCommands.ColorCommand;
import ca.utoronto.utm.drawingCommands.DrawingCommands;
import ca.utoronto.utm.drawingCommands.FillCommand;
import ca.utoronto.utm.drawingCommands.ThicknessCommand;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/** 
 * Square Drawable Command that holds dimensions
 * and draws itself to the GraphicsContext
*/
public class Square extends ClosedShape {
	
	private int length;
	private Point corner;
	private Point start;
		
	/** 
	 * Square Constructor. Makes a Square with a pivot at p,
	 * color c and thickness thickness.
	 * 
	 * @param p Current Point 
	 * @param c Current Color
	 * @param thickness Current Thickness
	*/
	public Square(Point p, Color color, double thickness) {
		super(color,thickness);
		this.corner = p;
		this.start = new Point(p.getX(), p.getY());
	}
	
	/** 
	 * Returns the Square Start Point/Pivot.
	*/
	public Point getStart() {
		return this.start;
	}
	
	/** 
	 * Returns the Square current corner
	 * Point
	*/
	public Point getCorner() {
		return this.corner;
	}
	
	/** 
	 * Sets Corner to fit new Dimensions
	*/
	public void setCorner(Point c) {
		this.corner = c;
	}
	
	/** 
	 * Returns the Square Length
	*/
	public int getLength() {
		return this.length;
	}
	
	/** 
	 * Sets length to fit new Dimensions
	*/
	public void setLength(int s) {
		this.length = s;
	}
	
	/** 
	 * Draw Command. Draws Square to graphics context
	 * given current Squares properties.
	 * 
	 * @param g Current GraphicsContext
	*/
	@Override
	public void draw(GraphicsContext g) {
		this.properties.applyCommands(g);
		g.fillRect(this.corner.getX(), this.corner.getY(), this.length, this.length);
		g.strokeRect(this.corner.getX(), this.corner.getY(), this.length, this.length);
		this.update(g);
	}

	/** 
	 * Type Designation for moving Rectangle
	*/
	@Override
	public String type() {
		return "Square";
	}
	/** 
	 * Detects if the closedshape has been clicked
	 * @param MouseEvent e an event passed which is used to calculate the distance between the mouse
	 * and the object
	 * @return Boolean indicated if the object has been clicked
	*/
	@Override
	public boolean isClicked(MouseEvent e) {
		double x = e.getX();double y = e.getY();
		double thick=this.properties.findThickness()/2;
		if (x<=this.getCorner().getX()+this.length+thick && x>this.getCorner().getX()-thick) {
			if (y<=this.getCorner().getY()+this.length+thick && y>this.getCorner().getY()-thick) {
				return true;
			}
		}
		return false;
	}
	
	/** 
	 * Calculates the vertical distance between the object and the double
	 * @param Double d a double passed which is used to calculate the vertical distance between
	 * the double d and the center of the object.
	 * @return Double which is the difference between the centre of the object and the parameter d
	*/
	@Override
	public double yDifferent(double d) {
		if (d>this.getCorner().getY()) {
			return -(d-this.getCorner().getY());
		} else {
			return this.getCorner().getY()-d;
		}
	}
	
	/** 
	 * Calculates the horizontal distance between the object and the double
	 * @param Double d a double passed which is used to calculate the horizontal distance between
	 * the double d and the center of the object.
	 * @return Double which is the difference between the centre of the object and the parameter d
	*/
	@Override
	public double xDifferent(double d) {
		if (d<this.getCorner().getX()) {
			return this.getCorner().getX()-d;
		} else {
			return -(d-this.getCorner().getX());
		}
	}

	/** 
	 * Detects if the edges of the closed shape have been clicked
	 * @param MouseEvent e an event passed which is used to calculate the distance between the mouse
	 * and the object
	 * @return Boolean which indicates if the object's border has been clicked
	*/
	@Override
	public boolean isHallowClicked(MouseEvent e) {
		double x = e.getX();double y = e.getY();
		double thick=this.properties.findThickness()/2;
		if ((this.getCorner().getX()+this.length <= x && x <= this.getCorner().getX()+this.length+thick) || (x>=this.getCorner().getX()-thick) && (x<=this.getCorner().getX())) {
			return true;
		}
		if ((y>=this.getCorner().getY()+this.length  && y<=this.getCorner().getY()+this.length+thick) || (y>=this.getCorner().getY()-thick && y<=this.getCorner().getY())) {
			return true;
		}
		return false;
	}
}
