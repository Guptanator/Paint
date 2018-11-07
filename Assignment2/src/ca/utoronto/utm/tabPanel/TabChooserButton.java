package ca.utoronto.utm.tabPanel;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TabChooserButton extends ToggleButton {
	
	private String currentType;

	public TabChooserButton(String name) {
		super();
		this.currentType=name;
		this.setWidth(15);
		this.setHeight(15);
		this.maxWidth(15);
		this.setImages();
	}
	private void setImages() {
		Image currentImage = null;
		if (this.currentType=="shape") {
			currentImage = new Image("resources/polygon.png",15,15,true,true);
		}
		else if (this.currentType=="thick") {
			currentImage = new Image("resources/thickness.png",15,15,true,true);
		}
		else if (this.currentType=="color") {
			currentImage = new Image("resources/color.png",15,15,true,true);
		}
		this.setGraphic(new ImageView(currentImage));
	}
	public String getType() {
		return this.currentType;
	}
	public void setInactive() {
		this.setSelected(false);
	}
	public void setActive() {
		this.setSelected(true);
	}

}
