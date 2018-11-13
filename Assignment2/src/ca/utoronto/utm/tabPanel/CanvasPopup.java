package ca.utoronto.utm.tabPanel;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import ca.utoronto.utm.paint.PaintPanel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CanvasPopup extends Stage implements EventHandler<ActionEvent>{
	
	private Button ok = new Button("OK");
	private Button cancel = new Button("CANCEL");
	private Button screenSize = new Button("SET SCREEN DIMENSIONS");
	private PaintPanel paintPanel;
	private TextField ySize;
	private TextField xSize;
	private TextField error;
	private final int MAXWIDTH = 1920;
	private final int MAXHEIGHT = 1080;
	
	public CanvasPopup(PaintPanel p) {
		this.setTitle("Resize Canvas");

		this.paintPanel = p;
		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(8);
		grid.setHgap(10);
		this.initModality(Modality.APPLICATION_MODAL);
		this.setResizable(false);
		
		ok.setOnAction(this);
		grid.setConstraints(ok, 0, 2);
		
		cancel.setOnAction(this);
		grid.setConstraints(cancel, 1, 2);
		
		screenSize.setOnAction(this);
		grid.setConstraints(screenSize, 0, 3);
		
		Label pixel1 = new Label("px");
		grid.setConstraints(pixel1, 1, 0);
		
		Label pixel2 = new Label("px");
		grid.setConstraints(pixel2, 1, 1);
		
		ySize = new TextField();
		ySize.setPromptText("Height");
		grid.setConstraints(ySize, 0, 0);
		
		xSize = new TextField();
		xSize.setPromptText("Width");
		grid.setConstraints(xSize, 0, 1);
		
		error = new TextField("");
		error.setEditable(false);
		error.setBackground(null);
		grid.setConstraints(error, 0, 4);
		
		
		grid.getChildren().addAll(pixel1, pixel2, ySize, xSize, ok, cancel, screenSize, error);
		
		Scene scene = new Scene(grid, 300, 200);
		
		this.setScene(scene);
		this.showAndWait();
	}

	
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
	@Override
	public void handle(ActionEvent event) {
		if (((Button) event.getSource()).getText()=="OK") {
			try {
				int height = Integer.parseInt(ySize.getText());
				int width = Integer.parseInt(xSize.getText());
				if (width > this.MAXWIDTH) {
					width = this.MAXWIDTH;
				}
				if (height > this.MAXHEIGHT) {
					height = this.MAXHEIGHT;
				}
				this.paintPanel.changeCanvas(height, width);
				this.hide();
			} catch(IllegalArgumentException e){
				this.error.setText("Must Input two integers");
			}
		} else if (((Button) event.getSource()).getText() =="CANCEL") {
			this.hide();
		} else if (((Button) event.getSource()).getText() =="SET SCREEN DIMENSIONS") {
			this.setScreenSize();
		}
		
	}
	
}
