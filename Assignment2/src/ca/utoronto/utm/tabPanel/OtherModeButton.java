package ca.utoronto.utm.tabPanel;

import ca.utoronto.utm.shapes.TransformStrategy;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;

/** Button for switching to TransformStrategies.
 */
public class OtherModeButton extends ToggleButton {
	
	private TransformStrategy strategy;

	/**Extends a new ToggleButton which allows the user to switch between TransformStrategies
	 * 
	 * @param image The image which is displayed by the button
	 * @param t The strategy that this button holds
	 * @param width The assigned width for this button.
	 */
	public OtherModeButton(ImageView image, TransformStrategy t,int width) {
		super();
		this.setGraphic(image);
		this.strategy=t;
		this.setMinWidth(width);
		this.getStylesheets().add("resources/stylesheet.css");
		this.getStyleClass().add("custom-button");
	}
	/** Returns current strategy.
	 * 
	 * @return Stored TransformStrategy
	 */
	public TransformStrategy getStrategy() {
		return this.strategy;
	}
}
