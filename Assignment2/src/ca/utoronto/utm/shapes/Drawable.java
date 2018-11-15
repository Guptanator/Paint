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
	
	public abstract void draw(GraphicsContext g);
	public abstract String type();
	
	public Drawable(Color color, double thickness) {
		this.properties.acceptCommand(new ColorCommand(color));
		this.properties.acceptCommand(new ThicknessCommand(thickness));
	}
	
	/** 
	 * Returns Current instance Color
	*/
	public Color getColor() {
		return this.properties.findColor();
	}
	
	/** 
	 * Sets Thickness command for instance of Drawable
	 * @param thickness Current Thickness
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
	 * Sets Thickness command for instance of Drawable
	 * @param c Current Color
	*/
	public void setColor(Color c) {
		this.properties.acceptCommand(new ColorCommand(c));
	}
}
	