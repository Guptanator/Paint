package ca.utoronto.utm.tabPanel;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

public class fillButton extends GridPane implements EventHandler<ActionEvent> {
	private View view;
	private ToggleButton button;
	public fillButton(View view)
	{
		this.view = view;
		
		button = new ToggleButton("Fill?");
		button.setOnAction(this);
		button.setMinWidth(100);
		this.add(button, 0, 0);
	}
	@Override
	public void handle(ActionEvent arg0) {
		if (this.button.isSelected()) {
			view.getPaintPanel().setFill();
			view.getPaintPanel().UnsetTransformMode();
		}
	}
}
