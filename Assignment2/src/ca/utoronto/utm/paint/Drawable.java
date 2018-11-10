package ca.utoronto.utm.paint;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public abstract class Drawable {
	
	protected Color color;
	protected boolean fill;
	
	public abstract void draw(GraphicsContext g, double thickness);
	public abstract String type();
	public abstract void setColor(Color c);
	public abstract Color getColor();
	public abstract void setFill(boolean fill);
}
	