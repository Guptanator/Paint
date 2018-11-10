package ca.utoronto.utm.paint;

public class FillCommand implements DrawingCommands{
	@Override
	public void executeChange(PaintPanel panel) {
		panel.getModel().setFill();
	}
}
