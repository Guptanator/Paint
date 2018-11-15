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
	