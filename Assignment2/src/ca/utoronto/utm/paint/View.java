package ca.utoronto.utm.paint;

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
import javafx.stage.Stage;

public class View implements EventHandler<ActionEvent> {

	private PaintModel model;

	private PaintPanel paintPanel;
	private ShapeChooserPanel shapeChooserPanel;
	private thicknessPopup thicknessPopup;
	private TabPanelParent tabParent;
	
	public View(PaintModel model, Stage stage) {

		this.model = model;
		initUI(stage);
	}

	private void initUI(Stage stage) {

		this.paintPanel = new PaintPanel(this.model, this);
		this.tabParent = new TabPanelParent(this);
		this.thicknessPopup = new thicknessPopup(this);

		BorderPane root = new BorderPane();
		root.setTop(createMenuBar());
		root.setCenter(this.paintPanel);
		root.setLeft(this.tabParent);
		root.setRight(this.thicknessPopup);

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Paint");
		stage.show();
	}

	public PaintPanel getPaintPanel() {
		return paintPanel;
	}

	public ShapeChooserPanel getShapeChooserPanel() {
		return shapeChooserPanel;
	}
	
	public thicknessPopup getthicknessPopup() {
		return thicknessPopup;
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
		
		menuItem = new MenuItem("Color");
		menuItem.setOnAction(this);
		menu.getItems().add(menuItem);
		
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
		else if (((MenuItem)event.getSource()).getText()=="Color") {
			model.createColorWindow("line");
		}
		else if (((MenuItem)event.getSource()).getText()=="Fill?") {
			model.shouldFill();
		}
		else if (((MenuItem)event.getSource()).getText()=="Exit") {
			System.exit(0);
		} 
	}
}
