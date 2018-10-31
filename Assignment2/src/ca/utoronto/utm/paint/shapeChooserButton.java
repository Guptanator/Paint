package ca.utoronto.utm.paint;

import javafx.scene.control.Button;

public class shapeChooserButton extends Button {
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
		
	}
	
}
