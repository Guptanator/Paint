package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

public interface DrawingCommands {
	public abstract void executeChange(Drawable d);
}
