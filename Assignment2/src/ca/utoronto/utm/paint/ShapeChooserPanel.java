package ca.utoronto.utm.paint;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.GridPane;

public class ShapeChooserPanel extends GridPane implements EventHandler<ActionEvent> {

	private View view; // So we can talk to our parent or other components of the view
	private shapeChooserButton lastPressed = null;
	public ShapeChooserPanel(View view) {

		this.view = view;

		String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", "Polyline" };

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
		source.setActive();
		String command = source.currentMode();
		this.view.getPaintPanel().setMode(command);
		if (command == "Circle") {
			this.view.getPaintPanel().setStrategy(new CircleStrategy());
		} else if (command == "Rectangle") {
			this.view.getPaintPanel().setStrategy(new RectangleStrategy());
		} else if (command == "Squiggle") {
			this.view.getPaintPanel().setStrategy(new SquiggleStrategy());
		}
		System.out.println(command);
		lastPressed = source;
	}
}
