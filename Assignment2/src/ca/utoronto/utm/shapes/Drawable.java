package ca.utoronto.utm.shapes;


import ca.utoronto.utm.drawingCommands.ColorCommand;
import ca.utoronto.utm.drawingCommands.PropertyInvoker;
import ca.utoronto.utm.drawingCommands.ThicknessCommand;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/** 
 * Drawable abstract class for drawable commands for all objects 
 * drawable to canvas.
*/
public abstract class Drawable {
	
	protected PropertyInvoker properties = new PropertyInvoker();
	/** 
	 * This function draws the current drawable shape
	 * 
	 * @param GraphicsContext g the current context
	*/
	public abstract void draw(GraphicsContext g);
	/** 
	 * This function returns the type of drawable an instance is
	 * 
	 * @return String which informs the program what kind of instance this is
	*/
	public abstract String type();
	
	/** 
	 * This constructor builds the Drawable by define key properties
	 * 
	 * @param Color color the starting color of the shape
	 * @param Double thickness the starting thickness of the shape
	*/
	public Drawable(Color color, double thickness) {
		this.properties.acceptCommand(new ColorCommand(color));
		this.properties.acceptCommand(new ThicknessCommand(thickness));
	}
	
	/** 
	 * Returns current instance color
	 * 
	 * @return Color the current Color of the shape
	*/
	public Color getColor() {
		return this.properties.findColor();
	}
	
	/** 
	 * Sets Thickness command for this instance's propertyInvoker
	 * 
	 * @param Double thickness the new thickness for the shape
	*/
	public void setThickness(double thickness) {
		this.properties.acceptCommand((new ThicknessCommand(thickness)));
	}
	/** 
	 * By default all drawables are not closed shapes
	 * @return boolean indicated if the shape is closed.
	*/
	public boolean isClosed() {
		return false;
	}
	
	/** 
	 * Sets Color command for this instance's propertyInvoker
	 * 
	 * @param Color c the new Color for the shape
	*/
	public void setColor(Color c) {
		this.properties.acceptCommand(new ColorCommand(c));
	}
}
	