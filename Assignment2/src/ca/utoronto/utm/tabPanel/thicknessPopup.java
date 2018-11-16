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

/** Holds and displays elements relating to the thickness manipulation
*/
public class thicknessPopup extends VBox implements ChangeListener<Number>{
	
	private final Slider thickness;
	private final Label  thicknessCaption;
	private double thicknessValue;
	private View view;
	
	/** Hold and initializes elements relating the thickness options.
	 * 
	 * @param view Paint Application View
	 */
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
	
	/** Handles the changes to the thickness from the slider
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
