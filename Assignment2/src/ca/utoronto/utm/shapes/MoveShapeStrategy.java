package ca.utoronto.utm.shapes;

import javafx.scene.input.MouseEvent;
/**
 * This strategy is used to handle mouse input when the the Move Shapes mode is selected. We utilize
 * the Strategy design pattern since it allows us to add more transformations in a modular format.
*/
public class MoveShapeStrategy extends TransformStrategy {

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
			double newX = e.getX()+deltaX;
			double newY = e.getY()+deltaY;
			c.updateErasables(c.getCentre().getX()-newX,c.getCentre().getY()-newY);
			c.setCentre(new Point((int)newX, (int)newY));
		}
		else if (currentShape.type()=="Rectangle") {
			Rectangle r = ((Rectangle)(currentShape));
			double newX = e.getX()+deltaX;
			double newY = e.getY()+deltaY;
			r.updateErasables(r.getCorner().getX()-newX,r.getCorner().getY()-newY);
			r.setCorner(new Point((int)newX, (int)newY));
		}
		else if (currentShape.type()=="Square") {
			Square s = ((Square)(currentShape));
			double newX = e.getX()+deltaX;
			double newY = e.getY()+deltaY;
			s.updateErasables(s.getCorner().getX()-newX,s.getCorner().getY()-newY);
			s.setCorner(new Point((int)newX, (int)newY));
		}
		this.panel.getModel().update();
	}
}
