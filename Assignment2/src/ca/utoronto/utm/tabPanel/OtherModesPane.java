package ca.utoronto.utm.tabPanel;

import ca.utoronto.utm.paint.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import ca.utoronto.utm.shapes.MoveShapeStrategy;

/**
 * This class is used to hold any transform strategy buttons, it extends vbox since all of the button
 * are placed in vertical order.
*/
public class OtherModesPane extends VBox implements EventHandler<ActionEvent>{
	private View view;
	private ToggleButton modeButton;
	/**
	 * This constructor initializes the OtherModesPane with the Move Mode toggle button
	 * @param view which is an instance of the view class which allows for interfacing with the model.
	 */
	public OtherModesPane(View view) {
		this.view = view;
		this.modeButton = new ToggleButton();
		Image currentImage = new Image("resources/moveIcon.png",20,20,true,true);
		this.modeButton.setGraphic(new ImageView(currentImage));
		this.modeButton.setMinWidth(100);
		this.getChildren().add(modeButton);
		modeButton.setOnAction(this);
		modeButton.getStylesheets().add("resources/stylesheet.css");
		modeButton.getStyleClass().add("custom-button");
	}
	@Override
	/**
	 * This function handles the Toggle Button and processes it using a subclass of the transform strategy
	 * in this class, we call the MoveShapeStrategy which allows us to move the shapes we can create. 
	 * This function additionally deactivates the transform mode.
	 * @param ActionEvent event passed from the ToggleButton
	 */
	public void handle(ActionEvent event) {
		this.view.getPaintPanel().shapeMode = !this.view.getPaintPanel().shapeMode;
		if (!this.view.getPaintPanel().shapeMode) {
			this.view.getPaintPanel().setTransformMode(new MoveShapeStrategy(),this.modeButton);
		} else {
			this.view.getPaintPanel().UnsetTransformMode();
		}
	}
	
}