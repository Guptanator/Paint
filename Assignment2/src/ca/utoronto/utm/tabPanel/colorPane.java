package ca.utoronto.utm.tabPanel;

import javafx.scene.paint.Color;

import ca.utoronto.utm.paint.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
/**
 * This class extends the GridPane and is used hold the colorPicker. Additionally it passes the color
 * the the PaintPanel controller.
 *
*/
public class colorPane extends TitledPane {
	private GridPane gP = new GridPane();
	/**
	 * This constructor initializes the colorPane, prepares the color picker and creates the anonymous
	 * function to handle the ActionEvent passed from the colorPicker.
	 * @param View view which allows the pane to access the controller.
	 */
	public colorPane(View view){
		int row = 0;
		int col = 0;
		for(double r = 0; r<=1; r = r+0.25)
		{
			for(double g = 0; g<=1; g = g+0.25)
			{
				for(double b = 0; b<=1; b = b+0.25)
				{
					colorButton cB = new colorButton(view, new Color(r, g, b, 1.0));
					gP.add(cB, col, row);
				}
				col += 1;
			}
			col = 0;
			row += 1;
		}
		this.setContent(gP);
//		for (String label : buttonLabels) {
//			shapeChooserButton button = new shapeChooserButton(label);
//			this.add(button, 0, row);
//			row++;
//			button.setOnAction(this);
//			buttonArray.add(button);
//		}
		
		
	}
//		this.colorPicker = new ColorPicker();
//		colorPicker.setMaxWidth(100);
//		this.add(colorPicker, 0, 0);
//		colorPicker.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                setNewColor(colorPicker.getValue());
//            }
//        });
//		colorPicker.getStylesheets().add("resources/stylesheet.css");
//		colorPicker.getStyleClass().add("custom-button");
//	/**
//	 * This function sets the current color in the PaintPanel controller.
//	 * @param Color color, the color passed from the colorPicker.
//	 */
//	public void setNewColor(Color color){
//		this.view.getPaintPanel().setColor(color);
//	}

}
