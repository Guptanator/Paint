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
	
	public ClosedShape(Color color, double thickness) {
		super(color, thickness);
	}
	private boolean isTransparent = false;
	public ArrayList<Eraseable> clearers = new ArrayList<Eraseable>(); 
	
	public abstract boolean isClicked(MouseEvent e);
	public abstract boolean isHallowClicked(MouseEvent e);
	public abstract double xDifferent(double d);
	public abstract double yDifferent(double d);
	
	public void addListener(Eraseable e) {
		this.clearers.add(e);
	}
	
	public void update(GraphicsContext g) {
		for (Eraseable e: clearers) {
			e.execute(g);
		}
	}
	
	public void updateErasables(double x, double y) {
		for (Eraseable e: clearers) {
			e.x=e.x-x;
			e.y=e.y-y;
		}
	}
	
	public void setFill(Color filled) {
		if (filled == Color.TRANSPARENT) {
			this.isTransparent=true;
		} else {
			this.isTransparent=false;
		}
		this.properties.acceptCommand(new FillCommand(filled));
	}
	
	/** 
	 * Returns that current Drawable
	 * is a closed shape
	*/
	public boolean isClosed() {
		return true;
	}
	public boolean isHallow() {
		return this.isTransparent;
	}
}
