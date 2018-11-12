package ca.utoronto.utm.paint;

import ca.utoronto.utm.tabPanel.CanvasPopup;
import ca.utoronto.utm.tabPanel.ShapeChooserPanel;
import ca.utoronto.utm.tabPanel.TabPanelParent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class View implements EventHandler<ActionEvent> {

	private PaintModel model;

	private PaintPanel paintPanel;
	private TabPanelParent tabParent;
	private FlowPane drawArea;
	private Stage stage;
	public View(PaintModel model, Stage stage) {

		this.model = model;
		initUI(stage);
	}

	private void initUI(Stage stage) {
		
		this.stage = stage;
		this.paintPanel = new PaintPanel(this.model, this);
		this.tabParent = new TabPanelParent(this);
		drawArea = new FlowPane();
		drawArea.getChildren().add(this.paintPanel);

		BorderPane root = new BorderPane();
		root.setTop(createMenuBar());
		root.setLeft(this.tabParent);
		root.setCenter(drawArea);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Paint");
		stage.show();
	}

	public PaintPanel getPaintPanel() {
		return paintPanel;
	}

	private MenuBar createMenuBar() {

		MenuBar menuBar = new MenuBar();
		Menu menu;
		MenuItem menuItem;

		// A menu for File

		menu = new Menu("File");

		menuItem = new MenuItem("New");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Open");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Save");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);
		
		menuItem = new MenuItem("Resize Canvas");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menu.getItems().add(new SeparatorMenuItem());

		menuItem = new MenuItem("Exit");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuBar.getMenus().add(menu);

		// Another menu for Edit

		menu = new Menu("Edit");

		menuItem = new MenuItem("Cut");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Copy");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Paste");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menu.getItems().add(new SeparatorMenuItem());

		menuItem = new MenuItem("Undo");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuItem = new MenuItem("Redo");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);

		menuBar.getMenus().add(menu);

		//New menu for color stuff
		
		menu = new Menu("Change Properties");
		
		menuItem = new MenuItem("Fill?");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);
		
		menuBar.getMenus().add(menu);
		
		return menuBar;
	}

	@Override
	public void handle(ActionEvent event) {
		System.out.println(((MenuItem)event.getSource()).getText());
		if (((MenuItem)event.getSource()).getText()=="Undo") {
			model.Undo();
		}
		else if (((MenuItem)event.getSource()).getText()=="Redo") {
			model.Redo();
		}
		else if (((MenuItem)event.getSource()).getText()=="Fill?") {
			model.setFill();
		}
		else if (((MenuItem)event.getSource()).getText()=="Exit") {
			System.exit(0);
		} else if (((MenuItem)event.getSource()).getText()=="Resize Canvas") {
			CanvasPopup canvasPop = new CanvasPopup(this.paintPanel);
		}
	}
	/**
	 * This function allows the outside world to set the fill icons as filled or unfilled.
	 */
	public void setFilled() {
		this.tabParent.setFilled();
	}
}
