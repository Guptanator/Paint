package ca.utoronto.utm.paint;

public class ThicknessCommand implements DrawingCommands{
	private String thickness;
	public void setThickness(String thick)
	{
		this.thickness = thick;
	}
	@Override
	public void executeChange(PaintPanel panel) {
		panel.setThickness(thickness);
	}

}
