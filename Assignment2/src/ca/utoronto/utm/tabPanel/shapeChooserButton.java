package ca.utoronto.utm.tabPanel;

import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/** Allows for additionally functionality and contained image loading.
 * Adds additional user input Feedback for Toggle Buttons.
*/
public class shapeChooserButton extends ToggleButton  {
	
	String currentType;
	private Node currentIcon;
	
	/** ShapeChooserButton Constructor. Sets currentType, image and width 
	 * of the button
	 * 
	 * @param name Name of the current button
	 */
	public shapeChooserButton(String name) {
		super();
		this.currentType=name;
		this.setMinWidth(100);
		this.setImages();
		this.getStylesheets().add("resources/stylesheet.css");
		this.getStyleClass().add("custom-button");
	}
	
	/** Returns the currentType of the button
	 * 
	 * @return Button Type
	 */
	public String currentMode() {
		return this.currentType;
	}
	/** Sets the image of the button based on the button type.
	 */
	private void setImages() {
		Image currentImage;
		if (this.currentType=="Circle") {
			this.currentIcon = new Circle();
			((Circle)(this.currentIcon)).setRadius(10);
			((Circle)(this.currentIcon)).setFill(Color.TRANSPARENT);
			((Circle)(this.currentIcon)).setStroke(Color.BLACK);
		}
		else if (this.currentType=="Rectangle") {
			currentImage = new Image("resources/rectangle.png",20,20,true,true);
			this.currentIcon = new Rectangle();
			((Rectangle)(this.currentIcon)).setHeight(20);
			((Rectangle)(this.currentIcon)).setWidth(30);
			((Rectangle)(this.currentIcon)).setFill(Color.TRANSPARENT);
			((Rectangle)(this.currentIcon)).setStroke(Color.BLACK);

		}
		else if (this.currentType=="Square") {
			currentImage = new Image("resources/square.png",20,20,true,true);
			this.currentIcon = new Rectangle();
			((Rectangle)(this.currentIcon)).setHeight(20);
			((Rectangle)(this.currentIcon)).setWidth(20);
			((Rectangle)(this.currentIcon)).setFill(Color.TRANSPARENT);
			((Rectangle)(this.currentIcon)).setStroke(Color.BLACK);
		}
		else if (this.currentType=="Squiggle") {
			currentImage = new Image("resources/squiggle.png",20,20,true,true);
			this.currentIcon =  new ImageView(currentImage);
		}
		else if (this.currentType=="PolyLine") {
			currentImage = new Image("resources/polygon.png",20,20,true,true);
			this.currentIcon =  new ImageView(currentImage);
		}
		this.setGraphic(this.currentIcon);
	}
	
	/** Sets the button to be inactive (used for UX purposes)
	 */
	public void setInactive() {
		this.setSelected(false);
	}
	
	/** Sets the button to be active (used for UX purposes)
	 */
	public void setActive() {
		this.setSelected(true);
	}
	
	/** Sets the button icon to be filled (used for UX purposes)
	 */
	public void setFill() {
		if (this.currentType=="Rectangle" || this.currentType=="Square"||this.currentType=="Circle") {
			((Shape)(this.currentIcon)).setFill(Color.BLACK);
		}
	}
	
	/** Sets the button icon to be unfilled (used for UX purposes)
	 */
	public void unFill() {
		if (this.currentType=="Rectangle" || this.currentType=="Square"||this.currentType=="Circle") {
			((Shape)(this.currentIcon)).setFill(Color.TRANSPARENT);
		}
	}
}
