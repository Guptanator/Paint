package ca.utoronto.utm.tabPanel;

import javafx.scene.paint.Color;

import ca.utoronto.utm.paint.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
/**
 * This class extends the GridPane and is used hold the colorPicker. Additionally it passes the color
 * the the PaintPanel controller.
 *
*/
public class colorPane extends TitledPane implements EventHandler<ActionEvent>{
	private GridPane GP = new GridPane();
	public View view;
	private ColorPicker borderPicker;
	private ColorPicker fillPicker;
	/**
	 * This constructor initializes the colorPane, prepares the color picker and creates the anonymous
	 * function to handle the ActionEvent passed from the colorPicker.
	 * @param View view which allows the pane to access the controller.
	 */
	public colorPane(View view){
		
		this.view = view;
		this.borderPicker = new ColorPicker();
		this.borderPicker.setValue(Color.BLACK);
		this.fillPicker = new ColorPicker();
		this.fillPicker.setValue(Color.TRANSPARENT);
		borderPicker.setMaxWidth(100);
		fillPicker.setMaxWidth(100);
		this.GP.add(borderPicker, 0, 0);
		this.GP.add(fillPicker, 0, 1);
		borderPicker.setOnAction(this);
		fillPicker.setOnAction(this);
		borderPicker.getStylesheets().add("resources/stylesheet.css");
		borderPicker.getStyleClass().add("custom-button");
		fillPicker.getStylesheets().add("resources/stylesheet.css");
		fillPicker.getStyleClass().add("custom-button");
	}
	/**
	 * This function sets the current color in the PaintPanel controller.
	 * @param ActionEvent event, the event passed by the colorPicker
	 */
	public void handle(ActionEvent event) {
		this.view.getPaintPanel().setColor(borderPicker.getValue());
		this.view.getPaintPanel().setFillColor(fillPicker.getValue());
	}
}
