package ca.utoronto.utm.tabPanel;


import ca.utoronto.utm.paint.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class thicknessPopup extends GridPane implements EventHandler<ActionEvent>{
	
	private View view; 
	
	public thicknessPopup(View view){
		
		this.view = view;

		String[] buttonLabels = {"Thin", "Normal", "Thick"};

		int row = 0;
		for (String label : buttonLabels) {
			Button button = new Button(label);
			button.setMinWidth(100);
			this.add(button, 0, row);
			row++;
			button.setOnAction(this);
		}
	}
	@Override
	public void handle(ActionEvent event) {
		String command = ((Button) event.getSource()).getText();
		this.view.getPaintPanel().getModel().setThickness(command);
		System.out.println(command);
	}
	
	
	
	}
