package ca.utoronto.utm.tabPanel;


import ca.utoronto.utm.paint.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
/**
 * This call is used to hold and display elements relating to the thickness manipulating elements
*/
public class thicknessPopup extends GridPane implements EventHandler<ActionEvent>{
	
	/**
	 * Hold and initializes elements relating the thickness options, this is current 3 buttons
	 * but this will be changing to a slider soon.
	 * @param View view used to communicating with the controller
	 */
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
	/**
	 * This function handles any action on the thickness buttons and changes the thickness values 
	 * in the PaintPanel
	 * @param ActionEvent event passed by the thickness buttons and used to decide which thickness
	 * to set
	 */
	public void handle(ActionEvent event) {
		String command = ((Button) event.getSource()).getText();
		this.view.getPaintPanel().getModel().setThickness(command);
	}
}
