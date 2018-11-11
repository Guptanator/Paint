package ca.utoronto.utm.tabPanel;

import javafx.scene.paint.Color;

import ca.utoronto.utm.paint.View;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.GridPane;
/**
 * This class extends the GridPane and is used hold the colorPicker. Additionally it passes the color
 * the the PaintPanel controller.
 *
*/
public class colorPane extends GridPane {

	public View view;
	final ColorPicker colorPicker;
	/**
	 * This constructor initializes the colorPane, prepares the color picker and creates the anonymous
	 * function to handle the ActionEvent passed from the colorPicker.
	 * @param View view which allows the pane to access the controller.
	 */
	public colorPane(View view){
		
		this.view = view;
		this.colorPicker = new ColorPicker();
		colorPicker.setMaxWidth(100);
		this.add(colorPicker, 0, 0);
		colorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setNewColor(colorPicker.getValue());
            }
        });
		colorPicker.getStylesheets().add("resources/stylesheet.css");
		colorPicker.getStyleClass().add("custom-button");
	}
	/**
	 * This function sets the current color in the PaintPanel controller.
	 * @param Color color, the color passed from the colorPicker.
	 */
	public void setNewColor(javafx.scene.paint.Color color){
		this.view.getPaintPanel().getModel().setColor(color);
	}

}
