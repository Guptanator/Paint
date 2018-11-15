package ca.utoronto.utm.tabPanel;

import ca.utoronto.utm.paint.Paint;
import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.PaintPanel;
import ca.utoronto.utm.paint.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ModelPicker extends HBox implements EventHandler<ActionEvent> {
	
	private PaintPanel panel;
	private View view;
	private Paint paint;
	private ModelButton [] buttons;
	private int current;
	
	
	public ModelPicker(PaintModel model, PaintPanel panel){
		this.panel = panel;
		current = 1;
		buttons = new ModelButton[5];
		buttons[0] = new ModelButton(model.getName());
		buttons[0].setOnAction(this);
		buttons[0].setSelected(true);
		this.getChildren().add(buttons[0]);
	}
	
	public boolean newModel(String name) {
		if (current < 6) {
			buttons[current] = new ModelButton(name);
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


	@Override
	public void handle(ActionEvent event) {
		for (int i = 0; i < current; i++) {
			buttons[i].setSelected(false);
			System.out.print(buttons[i].getModel().getName());
		}
		ModelButton source = (ModelButton) event.getSource();
		source.setSelected(true);
		source.changeModel(panel);
	
		
	}
	
	
	

}
