package ca.utoronto.utm.tabPanel;

import ca.utoronto.utm.paint.CircleStrategy;
import ca.utoronto.utm.paint.PolyLineStrategy;
import ca.utoronto.utm.paint.RectangleStrategy;
import ca.utoronto.utm.paint.SquareStrategy;
import ca.utoronto.utm.paint.SquiggleStrategy;
import ca.utoronto.utm.paint.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

	public View view; // So we can talk to our parent or other components of the view
	private shapeChooserButton lastPressed = null;
	private RectangleStrategy rectangleStrat;
	private CircleStrategy circleStrat;
	private SquiggleStrategy squiggleStrat;
	private SquareStrategy squareStrat;
	private PolyLineStrategy polyStrat;
	
	public ShapeChooserPanel(View view) {
	
		this.view = view;

		String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", "PolyLine" };

		int row = 0;
		for (String label : buttonLabels) {
			shapeChooserButton button = new shapeChooserButton(label);
			this.add(button, 0, row);
			row++;
			button.setOnAction(this);
		}
		rectangleStrat = new RectangleStrategy();
		circleStrat = new CircleStrategy();
		squiggleStrat = new SquiggleStrategy();
		squareStrat = new SquareStrategy();
		polyStrat = new PolyLineStrategy();
	}

	@Override
	public void handle(ActionEvent event) {
		shapeChooserButton source = (shapeChooserButton)(event.getSource());
		if (lastPressed != null) {
			lastPressed.setInactive();
		}
		source.setActive();
		String command = source.currentMode();
		///this.view.getPaintPanel().setMode(command);
		if (command == "Circle") {
			this.view.getPaintPanel().setStrategy(this.circleStrat);
		} else if (command == "Rectangle") {
			this.view.getPaintPanel().setStrategy(this.rectangleStrat);
		} else if (command == "Squiggle") {
			this.view.getPaintPanel().setStrategy(this.squiggleStrat);
		} else if (command == "Square") {
			this.view.getPaintPanel().setStrategy(this.squareStrat);
		} else if (command == "PolyLine") {
			this.view.getPaintPanel().setStrategy(this.polyStrat);
		}
		lastPressed = source;
	}
}
