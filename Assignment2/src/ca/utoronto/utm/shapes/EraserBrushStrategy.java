package ca.utoronto.utm.shapes;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class EraserBrushStrategy extends TransformStrategy {
	private int thickness;
	@Override
	public void handleMouse(MouseEvent e) {
		ClosedShape c = findElement(e);
		if (this.thickness<5)this.thickness=5;
		this.thickness = (int)model.thick*3;
		if ((e.getEventType() == MouseEvent.MOUSE_PRESSED) && (c != null)) {
			c.addListener(new Eraseable(e.getX(), e.getY(), this.thickness));
			g.setFill(Color.WHITE);
			g.fillRect(e.getX()-(thickness/2), e.getY()-(thickness/2), this.thickness, this.thickness);
		}
		else if ((e.getEventType() == MouseEvent.MOUSE_DRAGGED) && (c != null)) {
			c.addListener(new Eraseable(e.getX(), e.getY(), this.thickness));
			g.setFill(Color.WHITE);
			g.fillRect(e.getX()-(thickness/2), e.getY()-(thickness/2), this.thickness, this.thickness);
		}
		else if ((e.getEventType() == MouseEvent.MOUSE_DRAGGED) && (c == null)) {
			g.setFill(Color.WHITE);
			g.fillRect(e.getX()-(thickness/2), e.getY()-(thickness/2), this.thickness, this.thickness);
		}
	}
}