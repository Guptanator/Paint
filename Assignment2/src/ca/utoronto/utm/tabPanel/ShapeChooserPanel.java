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
	private String lastCommand = null;
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
	}

	@Override
	public void handle(ActionEvent event) {
		shapeChooserButton source = (shapeChooserButton)(event.getSource());
		if (lastPressed != null) {
			lastPressed.setInactive();
		}
		if (lastCommand == "PolyLine") {
			((PolyLineStrategy)this.view.getPaintPanel().getStrategy()).terminateShape(true);
		}
		source.setActive();
		String command = source.currentMode();
		///this.view.getPaintPanel().setMode(command);
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
	}
}
