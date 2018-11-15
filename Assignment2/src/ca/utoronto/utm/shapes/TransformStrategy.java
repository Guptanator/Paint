package ca.utoronto.utm.shapes;

import java.util.ArrayList;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.PaintPanel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
/**
 * This class should be subclasses for all non shape creating strategies, some additional ideas include
 * moving elements, fill bucket, rotation or object scaling. This abstract class provides a frame work which
 * all TransformStrategies can utilize for their operations.
*/
public abstract class TransformStrategy {
	protected PaintPanel panel;
	/**
	 * This function allows the strategy to access the model.
	 * @param PaintModel p sets the model attribute of the strategy to be the passed in model.
	 */
	public void setPanel(PaintPanel p) {
		this.panel = p;
		this.panel.getModel().update();
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
	protected ClosedShape findElement(MouseEvent e) {
		ArrayList<Drawable> allObjects = this.panel.getModel().getObjects();
		for (int i= allObjects.size()-1; i >= 0;i--) {
			if (allObjects.get(i).isClosed()) {
				if(((ClosedShape)(allObjects.get(i))).isClicked(e)) return (ClosedShape)allObjects.get(i);
			}
		}
		return null;
	}
}