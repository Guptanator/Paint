package ca.utoronto.utm.tabPanel;

import ca.utoronto.utm.shapes.TransformStrategy;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;

public class OtherModeButton extends ToggleButton {
	private TransformStrategy strategy;
	public OtherModeButton(ImageView image, TransformStrategy t) {
		super();
		this.setGraphic(image);
		this.strategy=t;
		this.setMinWidth(100);
		this.getStylesheets().add("resources/stylesheet.css");
		this.getStyleClass().add("custom-button");
	}
	public TransformStrategy getStrategy() {
		return this.strategy;
	}
}
