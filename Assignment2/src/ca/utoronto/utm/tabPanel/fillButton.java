package ca.utoronto.utm.tabPanel;

import ca.utoronto.utm.paint.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
/**
 * This constructor holds the fillButton which dictates if we want to be using fill mode
 */
public class fillButton extends GridPane implements EventHandler<ActionEvent> {
	private View view;
	private ToggleButton button;
	/**
	 * This constructor initializes the fillButton and creates the main ToggleButton which will be used
	 * @param View view which is an instance of the view class which allows for interfacing with the controller
	 */
	public fillButton(View view) {
		this.view = view;
		
		button = new ToggleButton("Fill?");
		button.setOnAction(this);
		button.setMinWidth(100);
		this.add(button, 0, 0);
		button.getStylesheets().add("resources/stylesheet.css");
		button.getStyleClass().add("custom-button");
	}
	@Override
	/**
	 * This function handles the event passed by the ToggleButton and triggers the setFill function
	 * in the paintPanel controller
	 * @param ActionEvent e this argument is passed by the ToggleButton
	 */
	public void handle(ActionEvent e) {
		view.getPaintPanel().setFill();
		view.getPaintPanel().UnsetTransformMode();
	}
}
