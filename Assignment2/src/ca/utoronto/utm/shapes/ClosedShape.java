package ca.utoronto.utm.shapes;

import java.util.ArrayList;
import java.util.function.Function;

import ca.utoronto.utm.drawingCommands.FillCommand;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/** 
 * Abstract Drawable for closed shapes
*/
public abstract class ClosedShape extends Drawable {
	/** 
	 * Constructs the ClosedShape by calling super()
	 * @param Color color
	 * @param Double thickness
	*/
	public ClosedShape(Color color, double thickness) {
		super(color, thickness);
	}
	private boolean isTransparent = false;
	public ArrayList<Eraseable> clearers = new ArrayList<Eraseable>(); 
	
	public abstract boolean isClicked(MouseEvent e);
	public abstract boolean isHallowClicked(MouseEvent e);
	public abstract double xDifferent(double d);
	public abstract double yDifferent(double d);
	
	/** 
	 * Adds a new listener to the shape
	 * @param Eraseable e the new erase we want to add as a listener
	*/
	public void addListener(Eraseable e) {
		this.clearers.add(e);
	}
	/** 
	 * Updates the listeners for the shape
	 * @param GraphicsContext g redraws all of listeners for the shape.
	*/
	public void update(GraphicsContext g) {
		for (Eraseable e: clearers) {
			e.execute(g);
		}
	}
	/** 
	 * Updates all listeners to have the new x,y coordinates.
	 * @param Double x the change in x
	 * @param Double y the change in y
	*/
	public void updateErasables(double x, double y) {
		for (Eraseable e: clearers) {
			e.x=e.x-x;
			e.y=e.y-y;
		}
	}
	
	/** 
	 * Sets a new value in the property invoker regarding fill
	 * @param Color filled the new value for the color of the shape
	*/
	public void setFill(Color filled) {
		if (filled == Color.TRANSPARENT) {
			this.isTransparent=true;
		} else {
			this.isTransparent=false;
		}
		this.properties.acceptCommand(new FillCommand(filled));
	}
	
	/** 
	 * Returns true is the shape is a closed shape
	*/
	public boolean isClosed() {
		return true;
	}
	/** 
	 * Returns the boolean value of isTransparent
	*/
	public boolean isHallow() {
		return this.isTransparent;
	}
}
