package ca.utoronto.utm.paint;

import javafx.scene.paint.Color;

public class ColorCommand implements DrawingCommands{
	private Color color;
	public void setColor(Color c)
	{
		this.color = c;
	}
	@Override
	public void executeChange(PaintPanel panel) {
		panel.setColor(color);
	}
}
