package ca.utoronto.utm.tabPanel;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.PaintPanel;
import javafx.scene.control.ToggleButton;

/** 
 * Window for Resizing the Current Canvas in the PaintPanel
*/
public class ModelButton extends ToggleButton {
	
	private PaintModel model;
	
	/** 
	 * ModelButton Constructor. Button Text
	 * is set to the model's name and the model
	 * is stored.
	 * 
	 * @param model New Model
	*/
	public ModelButton(PaintModel model){
		super(model.getName());
		this.model = model;
	}

	/** 
	 * Changes model and canvas size in PaintPanel.
	 * Updates the model.
	 * 
	 * @param panel Current PaintPanel
	*/
	public void changeModel(PaintPanel panel) {
		panel.setModel(this.model);
		panel.changeCanvas(this.model.getHeight(), this.model.getWidth());
		this.model.update();
	}
}
