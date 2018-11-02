package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public abstract class Drawable {
	public abstract void draw(GraphicsContext g);
	public abstract String type();
	public abstract void update(MouseEvent e);
}