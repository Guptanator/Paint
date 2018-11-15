package ca.utoronto.utm.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class EraserBrushStrategy extends TransformStrategy {
	private int thickness;
	@Override
	public void handleMouse(MouseEvent e) {
		ClosedShape c = findElement(e);
		if (this.thickness<5)this.thickness=5;
		this.thickness = (int)this.panel.getModel().thick*3;
		GraphicsContext g = this.panel.getCanvas().getGraphicsContext2D();
		if ((e.getEventType() == MouseEvent.MOUSE_PRESSED) && (c != null)) {
			this.panel.override = false;
			c.addListener(new Eraseable(e.getX(), e.getY(), this.thickness));
			g.setFill(Color.WHITE);
			g.fillRect(e.getX()-(thickness/2), e.getY()-(thickness/2), this.thickness, this.thickness);
		}
		else if ((e.getEventType() == MouseEvent.MOUSE_DRAGGED) && (c != null)) {
			this.panel.override = false;
			c.addListener(new Eraseable(e.getX(), e.getY(), this.thickness));
			g.setFill(Color.WHITE);
			g.fillRect(e.getX()-(thickness/2), e.getY()-(thickness/2), this.thickness, this.thickness);
		}
		else if ((e.getEventType() == MouseEvent.MOUSE_DRAGGED) && (c == null)) {
			if (!this.panel.override) {
				this.panel.setStrategy(new SquiggleStrategy());
				this.panel.getStrategy().color=Color.WHITE;
				((SquiggleStrategy)this.panel.getStrategy()).makeShapeAbove(e);
				this.panel.override = true;
			}
			else {
				this.panel.getStrategy().mouseHandle(e);
			}
		}
	}
}