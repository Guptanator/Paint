package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

public class FillCommand implements DrawingCommands{
	public boolean fill;
	public FillCommand(boolean filled)
	{
		this.fill = filled;
	}
	@Override
	public void executeChange(Drawable d) {
		d.setFill(this.fill);
	}
}
