package ca.utoronto.utm.paint;

import javafx.application.Application;
import javafx.stage.Stage;

/** Primary Class for Paint Application
*/
public class Paint extends Application {

	PaintModel model; // Model
	View view; // View + Controller

	/** Main Method Launches Application
	*/
	public static void main(String[] args) {
		launch(args);
	}

	/** 
	 * Stages Paint Elements on Application
	 * Start. Instantiates the Application View
	 * and First Model.
	*/
	@Override
	public void start(Stage stage) throws Exception {
		
		this.model = new PaintModel("Default");
		
		// View + Controller
		this.view = new View(model, stage);
	}

}