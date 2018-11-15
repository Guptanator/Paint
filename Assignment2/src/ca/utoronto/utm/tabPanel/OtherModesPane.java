package ca.utoronto.utm.tabPanel;

import ca.utoronto.utm.paint.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import ca.utoronto.utm.shapes.EraserBrushStrategy;
import ca.utoronto.utm.shapes.MoveShapeStrategy;
import ca.utoronto.utm.shapes.RemoveShapeStrategy;

/**
 * This class is used to hold any transform strategy buttons, it extends vbox since all of the button
 * are placed in vertical order.
*/
public class OtherModesPane extends GridPane implements EventHandler<ActionEvent>{
	private View view;
	/**
	 * This constructor initializes the OtherModesPane with the Move Mode toggle button
	 * @param view which is an instance of the view class which allows for interfacing with the model.
	 */
	public OtherModesPane(View view) {
		this.view = view;
		Image currentImage;
		OtherModeButton modeButton;
		currentImage = new Image("resources/moveIcon.png",20,20,true,true);
		modeButton = new OtherModeButton(new ImageView(currentImage),new MoveShapeStrategy(),100);
		modeButton.setOnAction(this);
		this.add(modeButton, 0, 0,2,1);
		
		currentImage = new Image("resources/eraseIcon.png",20,20,true,true);
		modeButton = new OtherModeButton(new ImageView(currentImage),new RemoveShapeStrategy(),50);
		modeButton.setOnAction(this);
		this.add(modeButton,0, 1,1,1);
		
		currentImage = new Image("resources/eraseIcon.png",20,20,true,true);
		modeButton = new OtherModeButton(new ImageView(currentImage),new EraserBrushStrategy(),50);
		modeButton.setOnAction(this);
		this.add(modeButton,1, 1,1,1);
		
	}
	@Override
	/**
	 * This function handles the Toggle Button and processes it using a subclass of the transform strategy
	 * in this class, we call the MoveShapeStrategy which allows us to move the shapes we can create. 
	 * This function additionally deactivates the transform mode.
	 * @param ActionEvent event passed from the ToggleButton
	 */
	public void handle(ActionEvent event) {
		OtherModeButton button = (OtherModeButton)(event.getSource());
		if (this.view.getPaintPanel().getTStrategy()!=null) {
			this.view.getPaintPanel().UnsetTransformMode();
		}
		if (button.isSelected()) {
			this.view.getPaintPanel().setTransformMode(button.getStrategy(),button);
		}
	}
}