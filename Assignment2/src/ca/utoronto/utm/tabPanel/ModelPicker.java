package ca.utoronto.utm.tabPanel;

import ca.utoronto.utm.paint.Paint;
import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.PaintPanel;
import ca.utoronto.utm.paint.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/** 
 * Tab for Holding, Selecting and creating Different Canvases
*/
public class ModelPicker extends HBox implements EventHandler<ActionEvent> {
	
	private PaintPanel panel;
	private View view;
	private Paint paint;
	private ModelButton [] buttons;
	private int current;
	
	/** 
	 * ModelPicker Constructor. Makes Button Array,
	 * stores panel and sets default model
	 * to the first button.
	 * 
	 * @param model Initial Default Model
	 * @param panel Current PaintPanel
	*/
	public ModelPicker(PaintModel model, PaintPanel panel){
		this.panel = panel;
		current = 1;
		buttons = new ModelButton[5];
		buttons[0] = new ModelButton(model);
		buttons[0].setOnAction(this);
		buttons[0].setSelected(true);
		this.getChildren().add(buttons[0]);
	}
	
	/** 
	 * Constructs new Buttons for the buttons
	 * array with associated new models.
	 * Returns true upon construction of new Model
	 * else returns false.
	 * 
	 * @param name Name for new Model
	*/
	public boolean newModel(String name) {
		if (current < 5) {
			buttons[current] = new ModelButton(new PaintModel(name));
			this.getChildren().add(buttons[current]);
			for (int i = 0; i < current; i++) {
				buttons[i].setSelected(false);
			}
			buttons[current].setOnAction(this);
			buttons[current].setSelected(true);
			buttons[current].changeModel(panel);
			current++;
			return true;
		}
		return false;
	}

	/** 
	 * EventHandler for ModelButtons array.
	 * Switches current model upon button press.
	 * 
	 * @param event ModelButton press event
	*/
	@Override
	public void handle(ActionEvent event) {
		for (int i = 0; i < current; i++) {
			buttons[i].setSelected(false);
		}
		ModelButton source = (ModelButton) event.getSource();
		source.setSelected(true);
		source.changeModel(panel);
	
		
	}
	
	
	

}
