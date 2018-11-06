package ca.utoronto.utm.paint;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class shapeChooserButton extends ToggleButton  {
	String currentType;
	public shapeChooserButton(String name) {
		super(name);
		this.currentType=name;
		this.setWidth(100);
		this.handle();
	}
	public String currentMode() {
		return this.currentType;
	}
	public void handle() {
		Image currentImage = null;
		if (this.currentType=="Circle") {
			currentImage = new Image("file:circle.png",20,20,true,true);
		}
		else if (this.currentType=="Rectangle") {
			currentImage = new Image("file:rectangle.png",20,20,true,true);
		}
		else if (this.currentType=="Square") {
			currentImage = new Image("file:square.png",20,20,true,true);
		}
		else if (this.currentType=="Squiggle") {
			currentImage = new Image("file:squiggle.png",20,20,true,true);
		}
		else if (this.currentType=="PolyLine") {
			currentImage = new Image("file:polygon.png",20,20,true,true);
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
