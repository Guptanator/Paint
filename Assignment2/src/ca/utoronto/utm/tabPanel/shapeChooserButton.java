package ca.utoronto.utm.tabPanel;

import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
/**
 * This class extends the ToggleButton and allows for additionally functionality and contained image loading.
 * Additionally it adds key features which create a better user experience. 
*/
public class shapeChooserButton extends ToggleButton  {
	String currentType;
	private Node currentIcon;
	/**
	 * This constructor initializes the ToggleButton by calling the default empty constructor and
	 * setting the variable currentType, image and width of the button
	 * @param String name the name of the current button
	 */
	public shapeChooserButton(String name) {
		super();
		this.currentType=name;
		this.setMinWidth(100);
		this.setImages();
	}
	/**
	 * This function returns the string contains the currentType of the button
	 * @return String of the current type of the button
	 */
	public String currentMode() {
		return this.currentType;
	}
	/**
	 * This function sets the image of the button based on the current type of the button.
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
	/**
	 * This function sets the button to be inactive (used for UX purposes)
	 */
	public void setInactive() {
		this.setSelected(false);
	}
	/**
	 * This function sets the button to be active (used for UX purposes)
	 */
	public void setActive() {
		this.setSelected(true);
	}
	/**
	 * This function sets the button to be filled (used for UX purposes)
	 */
	public void setFill() {
		if (this.currentType=="Rectangle" || this.currentType=="Square"||this.currentType=="Circle") {
			((Shape)(this.currentIcon)).setFill(Color.BLACK);
		}
	}
	public void unFill() {
		if (this.currentType=="Rectangle" || this.currentType=="Square"||this.currentType=="Circle") {
			((Shape)(this.currentIcon)).setFill(Color.TRANSPARENT);
		}
	}
}
