package ca.utoronto.utm.tabPanel;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class headerPanel extends BorderPane implements EventHandler<ActionEvent> {
	GridPane buttonBox = new GridPane();
	Button leftButton = new Button();
	Button rightButton = new Button();
	int bottom = 0;
	int top = 4;
	int totalSize = 200;
	private ArrayList<TabChooserButton> allTabsButtons = new ArrayList<TabChooserButton>();
	
	public headerPanel() {
		leftButton.setOnAction(this);
		rightButton.setOnAction(this);
		addTabButtons();
		configureButtons();

		buttonBox.setMaxWidth(200);
		buttonBox.setPadding(new Insets(0,1, 0,1));
		this.setLeft(leftButton);
		this.setRight(rightButton);
		configureHeader();
		this.setCenter(buttonBox);
	}
	
	public ArrayList<TabChooserButton> getAllTabs() {
		return allTabsButtons;
	}
	
	@Override
	public void handle(ActionEvent event) {
		Button source = (Button)(event.getSource());
		if (source==this.leftButton) {
			System.out.println("move left");
			this.top--;
			this.bottom--;
			if (this.bottom<0) {
				this.bottom=this.allTabsButtons.size()-1;
			}
			if (this.top<0) {
				this.top=this.allTabsButtons.size()-1;
			}
		}
		else if (source==this.rightButton) {
			this.top++;
			this.bottom++;
			if (this.top==this.allTabsButtons.size()) {
				this.top=0;
			}
			if (this.bottom==this.allTabsButtons.size()) {
				this.bottom=0;
			}
			System.out.println("move right");
		}
		configureHeader();
	}
	private	ArrayList<TabChooserButton> defineCurrentTabs() {
		ArrayList<TabChooserButton> currentTabs = new ArrayList<TabChooserButton>();
		if (this.top>this.bottom) {
			for (int i = this.bottom; i<this.top;i++) {
				currentTabs.add(this.allTabsButtons.get(i));
			}
		}
		if (this.bottom>this.top) {
			for (int i = this.bottom; i<this.allTabsButtons.size();i++) {
				currentTabs.add(this.allTabsButtons.get(i));
			}
			for (int i = 0; i<this.top;i++) {
				currentTabs.add(this.allTabsButtons.get(i));
			}
		}
		return currentTabs;
		
	}
	private void configureHeader() {
		
		ArrayList<TabChooserButton> currentButtons = defineCurrentTabs();
		
		buttonBox.getChildren().clear();
		for (int i = 0; i < currentButtons.size();i++) {
			buttonBox.add(currentButtons.get(i), i, 0);
		}
	}
	private void addTabButtons() {
		allTabsButtons.add(new TabChooserButton("shape"));
		allTabsButtons.add(new TabChooserButton("color"));
		allTabsButtons.add(new TabChooserButton("thick"));
		allTabsButtons.add(new TabChooserButton("thick"));
		allTabsButtons.add(new TabChooserButton("thick"));
	}
	private void configureButtons() {
		Image currentImage;
		currentImage = new Image("resources/back.png",15,15,true,true);
		this.leftButton.setGraphic((new ImageView(currentImage)));
		currentImage = new Image("resources/next.png",15,15,true,true);
		this.rightButton.setGraphic((new ImageView(currentImage)));
	}
}
