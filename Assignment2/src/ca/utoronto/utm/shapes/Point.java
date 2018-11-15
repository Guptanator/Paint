package ca.utoronto.utm.shapes;


import java.util.ArrayList;

import ca.utoronto.utm.drawingCommands.ColorCommand;
import ca.utoronto.utm.drawingCommands.DrawingCommands;
import ca.utoronto.utm.drawingCommands.ThicknessCommand;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/** 
 * Point Drawable Command that holds a position
 * and draws itself to the GraphicsContext
*/
public class Point extends Drawable {
	private int x, y;
	private boolean segment;

	/** 
	 * Point Constructor. Makes a Point with the specified
	 * x,y coordinates
	 * 
	 * @param x Input x position
	 * @param y Input y position
	*/
	Point(int x, int y) {
		super(Color.BLACK,1.0);
		this.x = x;
		this.y = y;
		this.segment= false;
	}
	
	/** 
	 * Point Constructor. Makes a Point with the specified
	 * x,y coordinates with color c and thickness thickness.
	 * 
	 * @param x Input x position
	 * @param y Input y position
	 * @param c Current Color
	 * @param thickness Current Thickness
	*/
	Point(int x, int y, Color c, double thickness){
		this(x,y);
		this.properties.acceptCommand(new ThicknessCommand(thickness));
		this.properties.acceptCommand(new ColorCommand(c));
	}

	/** 
	 * Returns the X coordinate
	*/
	public int getX() {
		return x;
	}

	/** 
	 * Sets the X coordinate
	 * 
	 * @param x Input x position
	*/
	public void setX(int x) {
		this.x = x;
	}

	/** 
	 * Returns the Y coordinate
	*/
	public int getY() {
		return y;
	}

	/** 
	 * Sets the Y coordinate
	 * 
	 * @param y Input y position
	*/
	public void setY(int y) {
		this.y = y;
	}
	
	/** 
	 * Sets segment to true
	*/
	public void setIsFinal() {
		this.segment = true;
	}
	
	/** 
	 * Returns segment
	*/
	public boolean isFinal() {
		return segment;
	}
	
	/** 
	 * Draw Command. Draws Point to graphics context
	 * given current point properties.
	 * 
	 * @param g Current GraphicsContext
	*/
	@Override
	public void draw(GraphicsContext g) {
		this.properties.applyCommands(g);
		g.strokeLine(this.getX(), this.getY(), this.getX(), this.getY());
	}
	
	/** 
	 * Draw Command. Draws Line from this point
	 * to input point 2 to graphics context
	 * given current point properties.
	 * 
	 * @param g Current GraphicsContext
	 * @param p2 Second Point for line segment
	 * @param thickness Current thickness
	*/
	public void draw(GraphicsContext g,Point p2, double thickness) {
		if (this.isFinal()) {
			g.setLineWidth(this.properties.findThickness());
			return;
		}
		this.properties.applyCommands(g);
		g.strokeLine(this.getX(), this.getY(), p2.getX(), p2.getY());
	}

	/** 
	 * Type Designation for moving Points
	*/
	@Override
	public String type() {
		return "Point";
	}
}
