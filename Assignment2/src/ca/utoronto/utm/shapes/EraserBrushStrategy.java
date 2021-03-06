package ca.utoronto.utm.shapes;

import ca.utoronto.utm.paint.DrawableState;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class EraserBrushStrategy extends TransformStrategy {
	private int thickness;
	
	/** 
	 * Handles all erase brush functionality
	 * @param MouseEvent e the MouseEvent passed by the handler and used to find the closedshape
	*/
	@Override
	public void handleMouse(MouseEvent e) {
		ClosedShape c = findElement(e);
		if (this.thickness<5)this.thickness=5;
		this.thickness = (int)this.panel.getModel().thick*3;
		GraphicsContext g = this.panel.getCanvas().getGraphicsContext2D();
		if ((e.getEventType() == MouseEvent.MOUSE_PRESSED) && (c != null)) {
			this.monitor = new DrawableState("BrushShape");
			this.monitor.setPrevious(c);
		}
		if ((e.getEventType() == MouseEvent.MOUSE_DRAGGED) && (c != null)) {
			onShapeDrag(e,c,g);
		}
		else if ((e.getEventType() == MouseEvent.MOUSE_DRAGGED) && (c == null)) {
			onNullDrag(e);
		}
		if (e.getEventType() == MouseEvent.MOUSE_RELEASED) {
			if (c!=null) {
				this.monitor.setCurrent(c);
				this.terminated();
			}
			if (c==null) {
				this.terminatedNull();
			}
		}
	}
	/** 
	 * Handles the creation of a Squiggle when the mouse is dragged but it is not dragged on a shape
	 * @param MouseEvent e the MouseEvent passed by the handler.
	*/
	private void onNullDrag(MouseEvent e) {
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
	/** 
	 * Handles the creation listeners for each shape, additionally reduces overhead by removing unnecessary
	 * Erasables for transparent areas.
	 * @param MouseEvent e the MouseEvent passed by the handler
	 * @param ClosedShape c the ClosedShape found while dragging
	 * @param GraphicsContext g the GraphicsContext we use to draw the erased zones.
	*/
	private void onShapeDrag(MouseEvent e, ClosedShape c,GraphicsContext g) {
		this.panel.override = false;
		c.addListener(new Eraseable(e.getX(), e.getY(), this.thickness));
		g.setFill(Color.WHITE);
		if (c.isHallow()) {
			if (c.isHallowClicked(e)) {
				g.fillRect(e.getX()-(thickness/2), e.getY()-(thickness/2), this.thickness, this.thickness);
			}
		} else {
			g.fillRect(e.getX()-(thickness/2), e.getY()-(thickness/2), this.thickness, this.thickness);
		}
	}
	/** 
	 * This is used to handle the termination of the current moving period
	 * It primary pushes the state change to the undostates list in the model.
	*/
	@Override
	public void terminated() {
		this.panel.getModel().undoStates.add(this.monitor);
	}
	/** 
	 * This is used to handle the termination of the current moving period
	 * It primary pushes the state change to the undostates list in the model.
	*/
	public void terminatedNull() {
		//TODO
	}
}