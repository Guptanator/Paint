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
	private Color color;
	private Point start;
	private double thickness;
	
	private ArrayList<DrawingCommands> commands= new ArrayList<DrawingCommands>();
	
	/** 
	 * Square Constructor. Makes a Square with a pivot at p,
	 * color c and thickness thickness.
	 * 
	 * @param p Current Point 
	 * @param c Current Color
	 * @param thickness Current Thickness
	*/
	public Square(Point p, Color color, double thickness) {
		this.corner = p;
		this.start = new Point(p.getX(), p.getY());
		this.color = color;
		this.thickness = thickness;
		this.commands.add(new ColorCommand(this.color));
		this.commands.add(new ThicknessCommand(this.thickness));
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
		for(DrawingCommands command: this.commands)
		{
			command.executeChange(g);
		}
//		
		if(fill)
		{
			g.fillRect(this.corner.getX(), this.corner.getY(), this.length, this.length);
		}
		g.strokeRect(this.corner.getX(), this.corner.getY(), this.length, this.length);
	}

	/** 
	 * Type Designation for moving Rectangle
	*/
	@Override
	public String type() {
		return "Square";
	}

	/** 
	 * Sets color for Color Command
	 *  to Current Color
	 * 
	 * @param c Current Color
	*/
	@Override
	public void setColor(Color c) {
		this.color = c;		
		this.commands.add(new ColorCommand(c));
	}

	/** 
	 * Returns Square's color
	*/
	@Override
	public Color getColor() {
		return this.color;
	}

	/** 
	 * Sets Fill setting for fill
	 * command to current setting
	 * 
	 * @param filled Current fill settings
	*/
	@Override
	public void setFill(boolean filled) {
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

	/** 
	 * Sets thickness for thickness command
	 * 
	 * @param thickness Current thickness
	*/
	@Override
	public void setThickness(double thickness) {
		this.commands.add(new ThicknessCommand(thickness));
	}

	public boolean isClicked(MouseEvent e) {
		double x = e.getX();double y = e.getY();
		if (x<=this.getCorner().getX()+this.length && x>this.getCorner().getX()) {
			if (y<=this.getCorner().getY()+this.length && y>this.getCorner().getY()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public double yDifferent(double d) {
		if (d>this.getCorner().getY()) {
			return -(d-this.getCorner().getY());
		} else {
			return this.getCorner().getY()-d;
		}
	}

	@Override
	public double xDifferent(double d) {
		if (d<this.getCorner().getX()) {
			return this.getCorner().getX()-d;
		} else {
			return -(d-this.getCorner().getX());
		}
	}

}
