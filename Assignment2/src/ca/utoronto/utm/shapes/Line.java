package ca.utoronto.utm.shapes;


import java.util.ArrayList;

import ca.utoronto.utm.drawingCommands.ColorCommand;
import ca.utoronto.utm.drawingCommands.DrawingCommands;
import ca.utoronto.utm.drawingCommands.ThicknessCommand;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/** 
 * Line Drawable Command that holds dimensions
 * and draws itself to the GraphicsContext
*/
public class Line extends Drawable {
	
	private Point first;
	private Point last;
	private double thickness;	
	
	/** 
	 * Line Constructor. Makes a line with first point at p
	 * color c and thickness thickness.
	 *  
	 * @param p Current Point 
	 * @param c Current Color
	 * @param thickness Current Thickness
	*/
	public Line(Point p, Color c, double thickness) {
		super(c,thickness);
		this.first = p;
	}
	
	/** 
	 * Line Constructor. Makes a line with first point at p1
	 * and a second point at p2. Line Color is also set to c.
	 *  
	 * @param p1 Current Point 
	 * @param p2 Current Point 
	 * @param c Current Color
	*/
	public Line(Point p1, Point p2, Color c,double thickness) {
		super(c,thickness);
		this.first = p1;
		this.last = p2;
	}
	
	/** 
	 * Gets last point of the line.
	 * return Point the last corner of the line
	*/
	public Point getLast() {
		return this.last;
	}
	
	/** 
	 * Sets Last Point to p.
	 * 
	 * @param p Current Point 
	*/
	public void setLast(Point p) {
		this.last = p;
	}	
	
	/** 
	 * Draw Command. Draws Line to graphics context
	 * given current Line properties.
	 * 
	 * @param g Current GraphicsContext
	*/
	@Override
	public void draw(GraphicsContext g) {
		this.properties.applyCommands(g);
		this.first.setColor(this.properties.findColor());
		this.first.draw(g, last, this.thickness);
	}

	/** 
	 * Type Designation for the Drawable
	 * @return String the type of the Drawable
	*/
	@Override
	public String type() {
		return "Line";
	}
}
