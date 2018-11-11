package ca.utoronto.utm.paint;

import java.awt.Color;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.GridPane;

public class colorPane extends GridPane
{

	public View view;
	public colorPane(View view){
		
		this.view = view;
		final ColorPicker colorPicker = new ColorPicker();
		this.add(colorPicker, 0, 0);
		colorPicker.setOnAction(new EventHandler<ActionEvent>() {
			 
            @Override
            public void handle(ActionEvent event) {
                setNewColor(colorPicker.getValue());
            }
        });
	}
	public void setNewColor(javafx.scene.paint.Color color)
	{
		this.view.getPaintPanel().setColor(color);
	}

}
