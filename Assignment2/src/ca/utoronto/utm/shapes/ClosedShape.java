package ca.utoronto.utm.shapes;

import javafx.scene.input.MouseEvent;

public abstract class ClosedShape extends Drawable {
	public abstract boolean isClicked(MouseEvent e);
	public abstract double xDifferent(double d);
	public abstract double yDifferent(double d);
	public abstract void setFill(boolean fill);
}
