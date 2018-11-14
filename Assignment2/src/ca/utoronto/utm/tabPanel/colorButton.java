package ca.utoronto.utm.tabPanel;

import ca.utoronto.utm.paint.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class colorButton extends Button {
	private Color color;
	public colorButton(View view, Color c)
	{
		Button colorB = new Button();
		this.color = c;
		String red = Integer.toHexString((int)(this.color.getRed()*255));
		String green = Integer.toHexString((int)(this.color.getGreen()*255));
		String blue = Integer.toHexString((int) (this.color.getBlue()*255));
		String hex = "#" + red + green + blue;
		colorB.setStyle("-fx-background-color: "+ hex +";");
		colorB.setOnAction(new EventHandler<ActionEvent>() {
	      public void handle(ActionEvent event) {
	    	  view.getPaintPanel().setColor(color);
	      }
		});
	}
}
