package ca.utoronto.utm.tabPanel;

import java.util.ArrayList;

import ca.utoronto.utm.paint.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;
import ca.utoronto.utm.shapes.CircleStrategy;
import ca.utoronto.utm.shapes.PolyLineStrategy;
import ca.utoronto.utm.shapes.RectangleStrategy;
import ca.utoronto.utm.shapes.SquareStrategy;
import ca.utoronto.utm.shapes.SquiggleStrategy;

/** The ShapeChooserPanel holds and handles the ShapeChooserButtons
 * Implements Factory Design Pattern for choice of Strategy
*/
public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

	public View view; // So we can talk to our parent or other components of the view
	private shapeChooserButton lastPressed = null;
	private String lastCommand = null;
	private ArrayList<shapeChooserButton> buttonArray = new ArrayList<shapeChooserButton>();
	private boolean isFilled = false;

	/** This constructor initializes the ShapeChooserPanel by creating 5 ShapeChooserButtons based on the
	 * constant buttonLabels string
	 * 
	 * @param view Paint Application view
	 */
	public ShapeChooserPanel(View view) {
	
		this.view = view;

		String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", "PolyLine" };

		int row = 0;
		for (String label : buttonLabels) {
			shapeChooserButton button = new shapeChooserButton(label);
			this.add(button, 0, row,2,1);
			row++;
			button.setOnAction(this);
			buttonArray.add(button);
		}
	}
	
	/** Handles ActionEvents passed from the ShapeChooserButtons.
	 * Sets the strategy based on the button pushed for use in the PaintPanel with all 
	 * commands related to drawing. Also maintains last event pressed.
	 * 
	 * @param event User button press event
	 */
	@Override
	public void handle(ActionEvent event) {
		shapeChooserButton source = (shapeChooserButton)(event.getSource());
		if (lastPressed != null) {
			lastPressed.setInactive();
		}
		if (lastCommand == "PolyLine") {
			((PolyLineStrategy)this.view.getPaintPanel().getStrategy()).terminateShape();
		}
		source.setActive();
		String command = source.currentMode();
		if (command == "Circle") {
			this.view.getPaintPanel().setStrategy(new CircleStrategy());
		} else if (command == "Rectangle") {
			this.view.getPaintPanel().setStrategy(new RectangleStrategy());
		} else if (command == "Squiggle") {
			this.view.getPaintPanel().setStrategy(new SquiggleStrategy());
		} else if (command == "Square") {
			this.view.getPaintPanel().setStrategy(new SquareStrategy());
		} else if (command == "PolyLine") {
			this.view.getPaintPanel().setStrategy(new PolyLineStrategy());
		}
		lastPressed = source;
		lastCommand = command;
		this.view.getPaintPanel().UnsetTransformMode();
	}
	
	/** Sets the buttons icons to be filled or unfilled depending the the state of the toggle
	 * button.
	 */
	public void setButtonsFill() {
		this.isFilled=!this.isFilled;
		if (!this.isFilled) {
			for (shapeChooserButton button : buttonArray) {
				button.unFill();
			}
		} else {
			for (shapeChooserButton button : buttonArray) {
				button.setFill();
			}
		}
	}
}
