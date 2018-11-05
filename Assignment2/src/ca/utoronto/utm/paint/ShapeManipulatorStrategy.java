package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;
import java.awt.Color;

public interface ShapeManipulatorStrategy {
	public abstract void makeShape(MouseEvent e, Color c);
	public abstract void changeShape(MouseEvent e, PaintModel p);
	public abstract Drawable getShape();
	public abstract void setNull();
}
