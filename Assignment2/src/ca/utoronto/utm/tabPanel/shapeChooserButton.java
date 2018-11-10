package ca.utoronto.utm.tabPanel;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class shapeChooserButton extends ToggleButton  {
	String currentType;
	public shapeChooserButton(String name) {
		super();
		this.currentType=name;
		this.setMinWidth(100);
		this.handle();
	}
	public String currentMode() {
		return this.currentType;
	}
	public void handle() {
		Image currentImage = null;
		if (this.currentType=="Circle") {
			currentImage = new Image("resources/circle.png",20,20,true,true);
		}
		else if (this.currentType=="Rectangle") {
			currentImage = new Image("resources/rectangle.png",20,20,true,true);
		}
		else if (this.currentType=="Square") {
			currentImage = new Image("resources/square.png",20,20,true,true);
		}
		else if (this.currentType=="Squiggle") {
			currentImage = new Image("resources/squiggle.png",20,20,true,true);
		}
		else if (this.currentType=="PolyLine") {
			currentImage = new Image("resources/polygon.png",20,20,true,true);
		}
		this.setGraphic(new ImageView(currentImage));
	}
	public void setInactive() {
		this.setSelected(false);
	}
	public void setActive() {
		this.setSelected(true);
	}
}
