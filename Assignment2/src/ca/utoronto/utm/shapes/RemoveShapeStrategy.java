package ca.utoronto.utm.shapes;

import ca.utoronto.utm.paint.DrawableState;
import javafx.scene.input.MouseEvent;
/**
 * This strategy is used to handle mouse input when the the Move Shapes mode is selected. We utilize
 * the Strategy design pattern since it allows us to add more transformations in a modular format.
*/
public class RemoveShapeStrategy extends TransformStrategy {

	private ClosedShape currentShape;
	double deltaX;
	double deltaY;
	@Override
	/**
	 * This functions overrides the handleMouse function in the transform strategy and is used to decide
	 * how to react to different click types.
	 * @param MouseEvent e
	 */
	public void handleMouse(MouseEvent e) {
		if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
			this.monitor = new DrawableState("remove");
			this.currentShape = (ClosedShape)this.findElement(e);
			if (this.currentShape!=null) {
				this.monitor.setPrevious(this.currentShape);
				this.monitor.setCurrent(null);
				this.removeShape();
			}
		}
	}
	/**
	 * This function is used to move all of the Drawable shapes and is called when the user
	 * drags their mouse. Each shape has slight differences so this function was necessary.
	 * Lastly it calls the model to update so that all of the changes are displayed.
	 * @param MouseEvent e
	 */
	public void removeShape() {
		this.panel.getModel().removeObject(this.currentShape);
		this.terminated();
	}
	@Override
	public void terminated() {
		System.out.println("terminated");
		this.monitor.setCurrent(currentShape);
		this.panel.getModel().undoStates.add(this.monitor);
		
	}
}