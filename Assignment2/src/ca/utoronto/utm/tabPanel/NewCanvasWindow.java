package ca.utoronto.utm.tabPanel;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.EmptyStackException;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.PaintPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewCanvasWindow extends Stage implements EventHandler<ActionEvent>{
	
	private PaintPanel panel;
	private ModelPicker modelPicker;
	private Button ok = new Button("OK");
	private Button cancel = new Button("CANCEL");
	private Button screenSize = new Button("SET SCREEN DIMENSIONS");
	private PaintPanel paintPanel;
	private TextField name;
	private TextField ySize;
	private TextField xSize;
	private TextField error;
	private final int MAXWIDTH = 1920;
	private final int MAXHEIGHT = 1080;
	
	public NewCanvasWindow(PaintPanel p, ModelPicker modelPicker) {
		this.setTitle("New Canvas");
		
		this.modelPicker = modelPicker;
		this.paintPanel = p;
		
		VBox window = new VBox();
		GridPane grid = new GridPane();
		grid.setVgap(8);
		grid.setHgap(10);
		grid.setPadding(new Insets(10,10,10,10));
		this.initModality(Modality.APPLICATION_MODAL);
		this.setResizable(false);
		
		HBox nameArea = new HBox();
		nameArea.setPadding(new Insets(10, 10, 10, 10));

		window.getChildren().addAll(nameArea, grid);
	
		Label nameLabel = new Label("Name:  ");
		nameArea.getChildren().add(nameLabel);
		
		name = new TextField();
		name.setPromptText("Canvas Name");
		nameArea.getChildren().add(name);
		
		ok.setOnAction(this);
		grid.setConstraints(ok, 0, 3);
		
		cancel.setOnAction(this);
		grid.setConstraints(cancel, 1, 3);
		
		screenSize.setOnAction(this);
		grid.setConstraints(screenSize, 0, 4);
	
		
		Label pixel1 = new Label("px");
		grid.setConstraints(pixel1, 1, 1);
		
		Label pixel2 = new Label("px");
		grid.setConstraints(pixel2, 1, 2);
		
		
		ySize = new TextField();
		ySize.setPromptText("Height");
		grid.setConstraints(ySize, 0, 1);
		
		xSize = new TextField();
		xSize.setPromptText("Width");
		grid.setConstraints(xSize, 0, 2);
		
		error = new TextField("");
		error.setEditable(false);
		error.setBackground(null);
		grid.setConstraints(error, 0, 5);
		
		
		grid.getChildren().addAll(pixel1, pixel2, ySize, xSize, ok, cancel, screenSize, error);
		
		Scene scene = new Scene(window, 300, 300);
		
		this.setScene(scene);
		this.showAndWait();
			
	}

	/** 
	 * Detects Current Display device and populates
	 * canvas height and width TextFields with device resolution.
	 * If display is higher resolution than the max dimension constants,
	 * will populate with max values instead.
	 * 
	*/
	private void setScreenSize() {
		GraphicsDevice gdevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gdevice.getDisplayMode().getWidth();
		int height = gdevice.getDisplayMode().getHeight();
		if (width > this.MAXWIDTH) {
			width = this.MAXWIDTH;
		}
		if (height > this.MAXHEIGHT) {
			height = this.MAXHEIGHT;
		}
		
		this.ySize.setText(Integer.toString(height));
		this.xSize.setText(Integer.toString(width));
		
	}
	


	/** 
	 * Button Event Handler. Calls methods based on button
	 * press and will either set canvas size, populate dimension
	 * TextFields or closes window.
	 *  
	 * @param event Button pressed event on Resize Canvas
	 * Window
	*/
	@Override
	public void handle(ActionEvent event) {
		if (((Button) event.getSource()).getText()=="OK") {
			try {
				int height = Integer.parseInt(ySize.getText());
				int width = Integer.parseInt(xSize.getText());
				String nameInput = this.name.getText();
				if (nameInput.length() == 0 | nameInput.length() >= 10) {
					throw new EmptyStackException();
				}
				if (width > this.MAXWIDTH) {
					width = this.MAXWIDTH;
				}
				if (height > this.MAXHEIGHT) {
					height = this.MAXHEIGHT;
				}
				this.modelPicker.newModel(nameInput);
				this.paintPanel.changeCanvas(height, width);
				System.out.println("Hello");
				this.hide();
			} catch(IllegalArgumentException e){
				this.error.setText("Must Input two integers");
			} catch (EmptyStackException e) {
				this.error.setText("Invalid Name");
			}
		} else if (((Button) event.getSource()).getText() =="CANCEL") {
			this.hide();
		} else if (((Button) event.getSource()).getText() =="SET SCREEN DIMENSIONS") {
			this.setScreenSize();
		}
		
	}

}
