package ca.utoronto.utm.tabPanel;

import ca.utoronto.utm.paint.View;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * This call is used to hold and display elements relating to the thickness manipulating elements
*/
public class thicknessPopup extends VBox implements ChangeListener<Number>{
	
	/**
	 * Hold and initializes elements relating the thickness options, this is current 3 buttons
	 * but this will be changing to a slider soon.
	 * @param View view used to communicating with the controller
	 */
	final Slider thickness;
	final Label  thicknessCaption;
	double thicknessValue;
	View view;
	
	public thicknessPopup(View view){
	
		this.view = view;
		
		this.thickness = new Slider(0, 10, 1.0);
		this.thickness.setMaxWidth(100);
		this.thickness.setMin(0.5);
		this.thickness.setMax(15.0);
		this.thickness.setValue(1.0);
		this.thickness.setShowTickLabels(true);
		this.thickness.setShowTickMarks(true);
	
		this.thickness.setBlockIncrement(0.5);
	
		this.thicknessCaption = new Label("Thickness:1.0");
		this.thicknessCaption.setMaxWidth(100);
		this.thicknessCaption.setTextFill(Color.web("#ecf0f1"));
		this.getChildren().addAll(this.thicknessCaption,this.thickness);
		this.thickness.valueProperty().addListener(this);
	}
	/**
	 * This handles the changing the of the thickness from the slider
	 * @param ObservableValue observable the observable value passed from the handler
	 * @param Number oldValue the new value
	 * @param Number newValue the new value
	 */
	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		double thickNum = (double)observable.getValue();
		this.view.getPaintPanel().setThickness(thickNum);
		try {
			this.thicknessCaption.setText("Thickness: " + Double.toString(thickNum).substring(0, 4));
		} catch (StringIndexOutOfBoundsException e) {
			this.thicknessCaption.setText("Thickness: " + Double.toString(thickNum).substring(0, 3));
		}
	}
}
