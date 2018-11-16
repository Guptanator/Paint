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

/** This class is used to hold any transform strategy buttons, it extends vbox since all of the button
 * are placed in vertical order.
*/
public class OtherModesPane extends GridPane implements EventHandler<ActionEvent>{
	
	private View view;
	
	/** This constructor initializes the OtherModesPane with the Move Mode toggle button
	 * 
	 * @param view Paint Application View.
	 */
	public OtherModesPane(View view) {
		this.view = view;
		Image currentImage;
		OtherModeButton modeButton;
		currentImage = new Image("resources/moveIcon.png",20,20,true,true);
		modeButton = new OtherModeButton(new ImageView(currentImage),new MoveShapeStrategy(),100);
		modeButton.setOnAction(this);
		this.add(modeButton, 0, 0,2,1);
		
		currentImage = new Image("resources/shapeErase.png",30,30,true,true);
		modeButton = new OtherModeButton(new ImageView(currentImage),new RemoveShapeStrategy(),50);
		modeButton.setOnAction(this);
		this.add(modeButton,0, 1,1,1);
		
		currentImage = new Image("resources/eraseIcon.png",30,30,true,true);
		modeButton = new OtherModeButton(new ImageView(currentImage),new EraserBrushStrategy(),50);
		modeButton.setOnAction(this);
		this.add(modeButton,1, 1,1,1);
		
	}
	
	/** Handles the Toggle Button and allows movement for shapes.
	 * Also, deactivates the transform mode.
	 * 
	 * @param event ButtonEvent triggered by User.
	 */
	@Override
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