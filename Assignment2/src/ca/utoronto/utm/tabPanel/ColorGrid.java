package ca.utoronto.utm.tabPanel;

import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import ca.utoronto.utm.paint.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
/**
 * This class extends the TitledPane and is used to
 * pass a color a color to a ColorAccordion's mouse
 * clicker event handler. Also sets a grid of colors
 * in a grid pane as well as adding a button for a
 * transparent color setting.
 *
*/
public class ColorGrid extends TitledPane implements EventHandler<ActionEvent>{
	private GridPane GP = new GridPane();
	private Color c = Color.BLACK;
	private Button clear;
	public View view;
	public String type;
	private ColorAccordions parent;
	private Rectangle currColor;
	/**
	 * Constructor for ColorGrid. Creates the grid pane for colors
	 * and a button that sets the color to transparent.
	 * @param View view which allows the pane to access the controller.
	 * @param String name to determine where the color is being set to.
	 * @param ColorAccordions parent which calls this grid.
	 */
	public ColorGrid(View view, String name,ColorAccordions parent){
		this.clear = new Button("Empty");
		this.clear.setOnAction(this);
		this.clear.setMaxWidth(100);
		this.view = view;
		this.type=name;
		this.parent=parent;
		Image currentImage = null;
		this.setGraphic(this.configureNode());
		
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
		this.GP.add(this.clear, 0, row+2,3,1);
		this.setContent(GP);
	}
	/**
	 * Handler for mouse clicking events for each cell of
	 * the color grid pane so that they change the the set color
	 * to the grid pane cell's color.
	 * @param MouseEvent e tracks the mouse actions.
	 */
	private void handleMouseClick (MouseEvent e) {
		Node source = (Node)e.getSource() ;
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        for (Node node : GP.getChildren()) {
            if (GridPane.getColumnIndex(node) == colIndex && GridPane.getRowIndex(node) == rowIndex) {
            	this.c = (Color) ((Shape) node).getFill();
            }
        }
        this.currColor.setFill(this.c);
        if (this.type=="Fill") {
        	this.parent.handleMouseClickF(this);
        } else {
        	this.parent.handleMouseClickB(this);
        }
    }
	/**
	 * Getter method for this.c (the stored color).
	 * @return Color this.c is returned, which is the chosen color.
	 */
	public Color getColor() {
		return this.c;
	}
	/**
	 * Handler for the Empty button so that we can set the color to
	 * transparent.
	 * @param ActionEvent event for when the Empty button is clicked.
	 */
	@Override
	public void handle(ActionEvent event) {
		this.c = Color.TRANSPARENT;
		this.currColor.setFill(Color.TRANSPARENT);
		if (this.type=="Fill") {
        	this.parent.handleMouseClickF(this);
        } else {
        	this.parent.handleMouseClickB(this);
        }
	}
	/**
	 * Changes the color of the accordion icon to reflect the current
	 * selected color.
	 * @return HBox out, and HBox which holds the icon that represents the currently selected color.
	 */
	private HBox configureNode() {
		HBox out = new HBox(10);
		Image currentImage = null;
		this.currColor = new Rectangle(0,0,20,20);
		this.currColor.setStroke(Color.BLACK);
		if(this.type=="Border") {
			currentImage = new Image("resources/borderColor.png",20,20,true,true);
		}
		if(this.type=="Fill") {
			currentImage = new Image("resources/fillColor.png",20,20,true,true);
			this.currColor.setFill(Color.TRANSPARENT);
		}
		out.getChildren().add(new ImageView(currentImage));
		out.getChildren().add(this.currColor);
		return out;
	}
}
