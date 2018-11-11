package shapes;

import javafx.scene.input.MouseEvent;
/**
 * This strategy is used to handle mouse input when the the Move Shapes mode is selected. We utilize
 * the Strategy design pattern since it allows us to add more transformations in a modular format.
*/
public class MoveShapeStrategy extends TransformStrategy {

	private Drawable currentShape;
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
			currentShape = this.findElement(e);
			if (currentShape!=null)PrepareDeltas(e);
		} else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			MoveDrawable(e);
		}
	}
	/**
	 * This function is used to calculate the difference in X and Y coordinates when a shape is 
	 * Initially clicked. This means that when you click the corner of a shape, it moves with respect to
	 * that clicked corner.
	 * @param MouseEvent e 
	 */
	public void PrepareDeltas(MouseEvent e) {
		this.deltaX = currentShape.xDifferent(e.getX());
		this.deltaY = currentShape.yDifferent(e.getY());
	}
	/**
	 * This function is used to move all of the Drawable shapes and is called when the user
	 * drags their mouse. Each shape has slight differences so this function was necessary.
	 * Lastly it calls the model to update so that all of the changes are displayed.
	 * @param MouseEvent e
	 */
	public void MoveDrawable(MouseEvent e) {
		if (currentShape==null) {
			return;
		}
		else if (currentShape.type()=="Circle") {
			Circle c = ((Circle)(currentShape));
			c.setCentre(new Point((int) (e.getX()+deltaX), (int) (e.getY()+deltaY)));
		}
		else if (currentShape.type()=="Rectangle") {
			Rectangle r = ((Rectangle)(currentShape));
			r.setCorner(new Point((int) (e.getX()+deltaX), (int) (e.getY()+deltaY)));
		}
		else if (currentShape.type()=="Square") {
			Square s = ((Square)(currentShape));
			s.setCorner(new Point((int) (e.getX()+deltaX), (int) (e.getY()+deltaY)));
		}
		this.model.update();
	}
}
