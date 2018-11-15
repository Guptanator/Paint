package ca.utoronto.utm.tabPanel;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import ca.utoronto.utm.paint.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
/**
 * This class extends the GridPane and is used hold the colorPicker. Additionally it passes the color
 * the the PaintPanel controller.
 *
*/
public class ColorGrid extends TitledPane {
	private GridPane GP = new GridPane();
	private Color c = Color.BLACK;
	private Button clear;
	public View view;
	/**
	 * 
	 * @param View view which allows the pane to access the controller.
	 */
	public ColorGrid(View view){
		this.clear = new Button("E");
		this.clear.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	c = Color.TRANSPARENT;
		    }
		});
		this.view = view;
		int col = 0;
		int row = 0;
		for(int hue = 0; hue <=270; hue+=18)
		{
			this.c = Color.hsb(hue, 1.0, 1.0, 1.0);
			Rectangle rect = new Rectangle(0, 0, 26, 20);
			rect.setFill(this.c);
			this.GP.add(rect, col, row);
			row ++;
			if(row == 6)
			{
				row = 0;
				col ++;
			}
		}
		Rectangle rect = new Rectangle(0, 0, 26, 20);
		this.c = Color.hsb(0, 0, 0, 1.0);
		rect.setFill(this.c);
		this.GP.add(rect, col, row);
		rect = new Rectangle(0, 0, 26, 20);
		this.c = Color.hsb(270, 0, 1.0, 1.0);
		rect.setFill(this.c);
		this.GP.add(rect, col, row+1);
		for (Node node : GP.getChildren()) {
			node.setOnMouseClicked(e -> handleMouseClick(e));
		}
		this.GP.add(this.clear, 0, row+2);
		this.setContent(GP);
	}
	private void handleMouseClick (javafx.scene.input.MouseEvent e) {
		Node source = (Node)e.getSource() ;
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        for (Node node : GP.getChildren()) {
            if (GridPane.getColumnIndex(node) == colIndex && GridPane.getRowIndex(node) == rowIndex) {
            	this.c = (Color) ((Shape) node).getFill();
            	this.setExpanded(false);
            }
        }
    }
	public Color getColor()
	{
		return this.c;
	}
}
