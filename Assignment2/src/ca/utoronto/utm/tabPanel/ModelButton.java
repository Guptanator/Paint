package ca.utoronto.utm.tabPanel;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.PaintPanel;
import javafx.scene.control.ToggleButton;

public class ModelButton extends ToggleButton {
	
	private PaintModel model;
	
	public ModelButton(String name){
		this.model = new PaintModel(name);
		this.setText(name);
	}
	
	public void changeModel(PaintPanel panel) {
		panel.setModel(this.model);
		panel.getView().setModel(this.model);
		panel.getView().getPaint().setModel(this.model);
	}
	
	public PaintModel getModel() {
		return this.model;
	}
}
