package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public interface ShapeManipulatorStrategy {
	public abstract void makeShape(MouseEvent e, Color color, double l);
	public abstract void changeShape(MouseEvent e, PaintModel p);
	public abstract Drawable getShape();
	public abstract void addShape(PaintModel p);
	public abstract void moveFeedback(PaintModel g, MouseEvent e);
}
