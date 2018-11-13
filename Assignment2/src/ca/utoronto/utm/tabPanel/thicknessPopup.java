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

/**
 * This call is used to hold and display elements relating to the thickness manipulating elements
*/
public class thicknessPopup extends GridPane implements EventHandler<ActionEvent>{
	
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
		
		this.thickness = new Slider(0, 10, 0);
	
		this.add(thickness, 10, 10);
		this.thickness.setMin(0.5);
		this.thickness.setMax(10.0);
	
		this.thickness.setValue(1.0);
	
		this.thickness.setShowTickLabels(true);
		this.thickness.setShowTickMarks(true);
	
		this.thickness.setBlockIncrement(0.5);
	
		this.thicknessCaption = new Label("Thickness: ");
	
	
		this.thickness.valueProperty().addListener(new ChangeListener<Number>(){
    
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue) {
				thicknessValue = ((double)newValue);
		}
    });
}


@Override
	/**
	 * This function handles any action on the thickness buttons and changes the thickness values 
	 * in the PaintPanel
	 * @param ActionEvent event passed by the thickness buttons and used to decide which thickness
	 * to set
	 */
	public void handle(ActionEvent event) {
		double command = thickness.getValue();
		this.view.getPaintPanel().getModel().setThickness(command);
	}
}
