package ca.utoronto.utm.tabPanel;

import java.util.Observer;

import ca.utoronto.utm.paint.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import ca.utoronto.utm.shapes.MoveShapeStrategy;

public class OtherModesPane extends VBox implements EventHandler<ActionEvent>{
	private View view;
	private ToggleButton modeButton;
	public OtherModesPane(View view) {
		this.view = view;
		this.modeButton = new ToggleButton("Move Mode");
		this.modeButton.setMinWidth(100);
		this.getChildren().add(modeButton);
		modeButton.setOnAction(this);
	}
	@Override
	public void handle(ActionEvent event) {
		this.view.getPaintPanel().shapeMode = !this.view.getPaintPanel().shapeMode;
		if (!this.view.getPaintPanel().shapeMode) {
			this.view.getPaintPanel().setTransformMode(new MoveShapeStrategy(),this.modeButton);
		} else {
			this.view.getPaintPanel().UnsetTransformMode();
		}
	}
	
}