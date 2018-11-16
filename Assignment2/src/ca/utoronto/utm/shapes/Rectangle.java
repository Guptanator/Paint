package ca.utoronto.utm.shapes;


import java.util.ArrayList;

import ca.utoronto.utm.drawingCommands.ColorCommand;
import ca.utoronto.utm.drawingCommands.DrawingCommands;
import ca.utoronto.utm.drawingCommands.FillCommand;
import ca.utoronto.utm.drawingCommands.PropertyInvoker;
import ca.utoronto.utm.drawingCommands.ThicknessCommand;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/** 
 * Rectangle Drawable Command that holds dimensions
 * and draws itself to the GraphicsContext
*/
public class Rectangle extends ClosedShape {
	
	private int height = 0;
	private int width = 0;
	private Point corner;
	private Point start;
	
	/** 
	 * Rectangle Constructor. Makes a Rectangle with a pivot at p,
	 * color c and thickness thickness.
	 * 
	 * @param p Current Point 
	 * @param c Current Color
	 * @param thickness Current Thickness
	*/
	public Rectangle(Point c, Color color, double thickness) {
		super(color,thickness);
		this.corner = c;
		this.start = new Point(c.getX(), c.getY());
	}
	
	/** 
	 * Returns the Rectangle Start Point/Pivot.
	 * @return Point starting point of the rectangle
	*/
	public Point getStart() {
		return this.start;
	}
	
	/** 
	 * Returns the Rectangle current corner
	 * @return Point the current corner of the rectangle
	*/
	public Point getCorner() {
		return this.corner;
	}
	
	/** 
	 * Sets Corner to fit new Dimensions
	 * @param Point p the new corner of the rectangle
	*/
	public void setCorner(Point p) {
		this.corner = p;
	}
	
	/** 
	 * Sets height to fit new Dimensions
	 * @param int h the new height of the rectangles
	*/
	public void setHeight(int h) {
		this.height = h;
	}
	
	/** 
	 * Sets width to fit new Dimensions
	 * @param int w the new width of the rectangle 
	*/
	public void setWidth(int w) {
		this.width = w;
	}
	
	/** 
	 * Draw Command. Draws Rectangle to graphics context
	 * given current Rectangle properties.
	 * 
	 * @param g Current GraphicsContext
	*/
	@Override
	public void draw(GraphicsContext g) {
		this.properties.applyCommands(g);
		g.fillRect(this.corner.getX(), this.corner.getY(), this.width, this.height);
		g.strokeRect(this.corner.getX(), this.corner.getY(), this.width, this.height);
		this.update(g);
	}

	/** 
	 * Type Designation for the Drawable
	 * @return String the type of the Drawable
	*/
	@Override
	public String type() {
		return "Rectangle";
	}

	/** 
	 * Sets color for Color Command
	 *  to Current Color
	 * 
	 * @param c Current Color
	*/
	@Override
	public void setColor(Color c) {
		this.properties.acceptCommand(new ColorCommand(c));
	}
	/** 
	 * Detects if the closedshape has been clicked
	 * @param MouseEvent e an event passed which is used to calculate the distance between the mouse
	 * and the object
	 * @return Boolean indicated if the object has been clicked
	*/
	public boolean isClicked(MouseEvent e) {
		double x = e.getX();double y = e.getY();
		double thick=this.properties.findThickness()/2;
		if (x<=this.getCorner().getX()+this.width+thick && x>this.getCorner().getX()-thick) {
			if (y<=this.getCorner().getY()+this.height+thick && y>this.getCorner().getY()-thick) {
				return true;
			}
		}
		return false;
	}
	
	/** 
	 * Calculates the vertical distance between the object and the double
	 * @param Double d a double passed which is used to calculate the vertical distance between
	 * the double d and the center of the object.
	 * @return Double which is the different between the centre of the object and the parameter d
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
		if ((this.getCorner().getX()+this.width <= x && x <= this.getCorner().getX()+this.width+thick) || (x>=this.getCorner().getX()-thick) && (x<=this.getCorner().getX())) {
			return true;
		}
		if ((y>=this.getCorner().getY()+this.height  && y<=this.getCorner().getY()+this.height+thick) || (y>=this.getCorner().getY()-thick && y<=this.getCorner().getY())) {
			return true;
		}
		return false;
	}
}
