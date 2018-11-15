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
 * This class extends the GridPane and is used hold the colorPicker. Additionally it passes the color
 * the the PaintPanel controller.
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
	 * 
	 * @param View view which allows the pane to access the controller.
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
	private void handleMouseClick (MouseEvent e) {
		Node source = (Node)e.getSource() ;
        Integer colIndex = GridPane.getColumnIndex(source);
        Integer rowIndex = GridPane.getRowIndex(source);
        for (Node node : GP.getChildren()) {
            if (GridPane.getColumnIndex(node) == colIndex && GridPane.getRowIndex(node) == rowIndex) {
            	this.c = (Color) ((Shape) node).getFill();
            	//this.setExpanded(false);this is controlling the panes closing/opening
            }
        }
        this.currColor.setFill(this.c);
        if (this.type=="Fill") {
        	this.parent.handleMouseClickF(this);
        } else {
        	this.parent.handleMouseClickB(this);
        }
    }
	public Color getColor() {
		return this.c;
	}

	@Override
	public void handle(ActionEvent event) {
		this.c = Color.TRANSPARENT;
		this.currColor.setFill(Color.TRANSPARENT);
	}
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
