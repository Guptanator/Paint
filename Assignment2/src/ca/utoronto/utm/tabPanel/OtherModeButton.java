package ca.utoronto.utm.tabPanel;

import ca.utoronto.utm.shapes.TransformStrategy;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;

public class OtherModeButton extends ToggleButton {
	private TransformStrategy strategy;

	/**
	 * Extends a new ToggleButton which allows the user to switch between TransformStrategies
	 * @param ImageView view The image which is displayed by the button
	 * @param TransformStrategy t The strategy that this button holds
	 * @param Int width view The assigned width for this button.
	 */
	public OtherModeButton(ImageView image, TransformStrategy t,int width) {
		super();
		this.setGraphic(image);
		this.strategy=t;
		this.setMinWidth(width);
		this.getStylesheets().add("resources/stylesheet.css");
		this.getStyleClass().add("custom-button");
	}
	/**
	 * Allows the outside world to access the current strategy
	 * @return TransformStrategy the strategy held by this button
	 */
	public TransformStrategy getStrategy() {
		return this.strategy;
	}
}
