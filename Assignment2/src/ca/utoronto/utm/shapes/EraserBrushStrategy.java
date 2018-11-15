package ca.utoronto.utm.shapes;

import javafx.scene.input.MouseEvent;

public class EraserBrushStrategy extends TransformStrategy {
	private double thickness;
	@Override
	public void handleMouse(MouseEvent e) {
		if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			if (this.thickness<5)this.thickness=5;
			this.thickness = model.thick*3;
			this.g.clearRect(e.getX()-(this.thickness/2), e.getY()-(this.thickness/2), this.thickness, this.thickness);
		}
	}
}