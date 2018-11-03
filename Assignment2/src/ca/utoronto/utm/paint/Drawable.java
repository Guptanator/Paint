package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

public abstract class Drawable {
	public abstract void draw(GraphicsContext g, double thickness);
	public abstract String type();
	}
