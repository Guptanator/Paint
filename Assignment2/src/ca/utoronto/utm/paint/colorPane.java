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
		
//		this.red = new Slider(0, 255, 0);
//		this.green = new Slider(0, 255, 0);
//		this.blue = new Slider(0, 255, 0);
//		this.add(red, 0, 0);
//		this.add(green, 0, 1);
//		this.add(blue, 0, 2);
//		red.valueProperty().addListener(new ChangeListener<Number>(){
//			@Override
//			public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue) {
//				redValue = (double) newValue;
//			}
//        });
//		green.valueProperty().addListener(new ChangeListener<Number>(){
//			@Override
//			public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue) {
//				greenValue = (double) newValue;
//			}
//        });
//		blue.valueProperty().addListener(new ChangeListener<Number>(){
//			@Override
//			public void changed(ObservableValue<? extends Number> arg0, Number oldValue, Number newValue) {
//				blueValue = (double) newValue;
//			}
//        });
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
