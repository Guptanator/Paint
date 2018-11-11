package ca.utoronto.utm.paint;

import javafx.scene.canvas.GraphicsContext;

public class ThicknessCommand implements DrawingCommands{
	private double thickness;
	public ThicknessCommand(double thick)
	{
		this.thickness = thick;
	}
	@Override
	public void executeChange(Drawable d) {
		d.setThickness(thickness);
	}
}
