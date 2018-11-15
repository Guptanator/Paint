package ca.utoronto.utm.paint;

import ca.utoronto.utm.tabPanel.CanvasPopup;
import ca.utoronto.utm.tabPanel.ModelPicker;
import ca.utoronto.utm.tabPanel.NewCanvasWindow;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View implements EventHandler<ActionEvent> {

	private PaintModel model;
	private Paint paint;

	private PaintPanel paintPanel;
	private TabPanelParent tabParent;
	private FlowPane drawArea;
	private ModelPicker modelBox;
	private VBox drawBox;
	private Stage stage;
	public View(PaintModel model, Stage stage, Paint app) {

		this.paint = app;
		this.model = model;
		initUI(stage);
	}

	private void initUI(Stage stage) {
		
		this.stage = stage;
		this.paintPanel = new PaintPanel(this.model, this);
		this.tabParent = new TabPanelParent(this);
		this.drawArea = new FlowPane();
		this.modelBox = new ModelPicker(this.model, paintPanel);
		this.drawBox = new VBox();
		
		drawBox.getChildren().add(this.modelBox);
		drawBox.getChildren().add(this.paintPanel);
		drawArea.getChildren().add(this.drawBox);

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
		else if (((MenuItem)event.getSource()).getText()=="Exit") {
			System.exit(0);
		} else if (((MenuItem)event.getSource()).getText()=="Resize Canvas") {
			CanvasPopup canvasPop = new CanvasPopup(this.paintPanel);
		} else if (((MenuItem)event.getSource()).getText()=="New") {
			NewCanvasWindow newCanvas = new NewCanvasWindow(this.paintPanel, modelBox);
		}
	}
	/**
	 * This function allows the outside world to set the fill icons as filled or unfilled.
	 */
	public void setFilled() {
		this.tabParent.setFilled();
	}
	
	/**
	 * Sets model to Current Model
	 */	
	public void setModel(PaintModel model) {
		this.model = model;
	}
	
	public Paint getPaint() {
		return this.paint;
	}
}
