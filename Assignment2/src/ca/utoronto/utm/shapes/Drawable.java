package ca.utoronto.utm.shapes;


import ca.utoronto.utm.drawingCommands.ColorCommand;
import ca.utoronto.utm.drawingCommands.PropertyInvoker;
import ca.utoronto.utm.drawingCommands.ThicknessCommand;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public abstract class Drawable {
	
	protected PropertyInvoker properties = new PropertyInvoker();
	
	public abstract void draw(GraphicsContext g);
	public abstract String type();
	
	public Color getColor() {
		return this.properties.findColor();
	}
	
	public void setThickness(double thickness) {
		this.properties.acceptCommand((new ThicknessCommand(thickness)));
	}
	public boolean isClosed() {
		return false;
	}
	public void setColor(Color c) {
		this.properties.acceptCommand(new ColorCommand(c));
	}
}
	