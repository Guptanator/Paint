package ca.utoronto.utm.shapes;
import java.util.ArrayList;
import java.util.Arrays;

import ca.utoronto.utm.drawingCommands.ColorCommand;
import ca.utoronto.utm.drawingCommands.PropertyInvoker;
import ca.utoronto.utm.drawingCommands.ThicknessCommand;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/** 
 * Squiggle Drawable Command that holds Points
 * and draws itself to the GraphicsContext
*/
public class Squiggle extends Drawable {
	
	private ArrayList<Point> points;
	private double thickness;
	private Color color;
	
	/** 
	 * Squiggle Constructor. Makes a Squiggle with a point
	 * the specified x,y coordinates with color c and 
	 * thickness thickness.
	 * 
	 * @param x Input x position
	 * @param y Input y position
	 * @param c Current Color
	 * @param thickness Current Thickness
	*/
	public Squiggle(int x, int y, Color c, double thickness) {
		super(c,thickness);
		this.points = new ArrayList<Point>();
		this.thickness = thickness;
		this.color = c;
		this.points.add(new Point (x,y,c, this.thickness));
	}

	/** 
	 * Adds point to Squiggle's points.
	 * 
	 * @param x Input x position
	 * @param y Input y position
	*/
	public void addPoint(int x, int y) {
		this.points.add(new Point (x,y,color, this.thickness));
	}
	
	/** 
	 * Draw Command. Draws Squiggle to graphics context
	 * given current Squiggle properties.
	 * 
	 * @param g Current GraphicsContext
	*/
	@Override
	public void draw(GraphicsContext g) {
		g.setLineWidth(this.thickness);
		for (int i = 0; i < this.points.size() - 1; i++) {
			points.get(i).draw(g, points.get(i+1), this.thickness);
		}
	}

	/** 
	 * Type Designation for moving Points
	*/
	@Override
	public String type() {
		return "Squiggle";
	}
	
	/** 
	 * Set thickness to current thickness
	*/
	@Override
	public void setThickness(double thickness) {
		this.properties.acceptCommand(new ThicknessCommand(thickness));
		this.thickness = thickness;
	}
}
