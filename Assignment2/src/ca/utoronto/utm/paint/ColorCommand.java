package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ColorCommand implements DrawingCommands{
	private Color color;
	public ColorCommand(Color c)
	{
		this.color = c;
	}
	@Override
	public void executeChange(Drawable d) {
		d.setColor(this.color);
	}
}
