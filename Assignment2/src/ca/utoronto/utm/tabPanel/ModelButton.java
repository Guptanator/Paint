package ca.utoronto.utm.tabPanel;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.PaintPanel;
import javafx.scene.control.ToggleButton;

public class ModelButton extends ToggleButton {
	
	private PaintModel model;
	
	public ModelButton(PaintModel model){
		super(model.getName());
		this.model = model;
	}
	
	public void changeModel(PaintPanel panel) {
		panel.setModel(this.model);
		panel.changeCanvas(this.model.getHeight(), this.model.getWidth());
		this.model.update();
	}
	
	public PaintModel getModel() {
		return this.model;
	}
}
