package ca.utoronto.utm.tabPanel;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class fillButton extends GridPane{
	private View view;
	public fillButton(View view)
	{
		this.view = view;
		
		Button button = new Button("Fill?");
		button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	view.getPaintPanel().setFill();
            }
        });
		button.setMinWidth(100);
		this.add(button, 0, 0);
	}

}
