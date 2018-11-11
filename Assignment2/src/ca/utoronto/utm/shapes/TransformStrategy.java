package ca.utoronto.utm.shapes;

import java.util.ArrayList;

import ca.utoronto.utm.paint.PaintModel;
import javafx.scene.input.MouseEvent;
/**
 * This class should be subclasses for all non shape creating strategies, some additional ideas include
 * moving elements, fill bucket, rotation or object scaling. This abstract class provides a frame work which
 * all TransformStrategies can utilize for their operations.
*/
public abstract class TransformStrategy {
	protected PaintModel model;
	/**
	 * This functions allows the PaintPanel to set the model of the strategy on a running when required
	 * @param PaintModel p sets the model attribute of the strategy to be the passed in model.
	 */
	public void setModel(PaintModel p) {
		this.model = p;
		this.model.update();
	}
	/**
	 * This functions allows the strategy to handle any passed in mouse events from the PaintPanel
	 * @param MouseEvent e the event passed from the canvas.
	 */
	public abstract void handleMouse(MouseEvent e);
	/**
	 * This functions allows you to detect what element you are clicking on based on the MouseEvent 
	 * passed in. It looks through the allObjects array list if the event was in a shape.
	 * @param MouseEvent e is used to parse the x,y coordinates clicked.
	 * @return Drawable based on the event that was clicked by the MouseEvent.
	 */
	protected Drawable findElement(MouseEvent e) {
		ArrayList<Drawable> allObjects = model.getObjects();
		for (int i= allObjects.size()-1; i >= 0;i--) {
			if(allObjects.get(i).isClicked(e)) return allObjects.get(i);
		}
		return null;
	}
}