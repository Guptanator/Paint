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

/** View for the Paint application
*/
public class View implements EventHandler<ActionEvent> {

	private PaintModel model;

	private PaintPanel paintPanel;
	private TabPanelParent tabParent;
	private FlowPane drawArea;
	private ModelPicker modelBox;
	private VBox drawBox;
	private Stage stage;
	
	/** View Constructor. Sets current Model and
	 * Calls method to initialize UI.
	 * 
	 * @param model Current model
	 * @param stage Paint Stage
	*/
	public View(PaintModel model, Stage stage) {

		this.model = model;
		initUI(stage);
	}

	/** Initializes UI elements on the stage
	 * for the Paint Application
	 * 
	 * @param stage Paint Stage
	*/
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
	
	/** Returns PaintPanel for View
	 * 
	 * @return Current PaintPanel
	*/
	public PaintPanel getPaintPanel() {
		return paintPanel;
	}

	/** Constructs the menu at the top of
	 * the paint Application
	*/
	private MenuBar createMenuBar() {

		MenuBar menuBar = new MenuBar();
		Menu menu;
		MenuItem menuItem;

		// File Menu

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

		// Edit Menu

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

	/** View EventHandler. On menu selection events,
	 * changes current model or opens new window.
	 * 
	 * @param event Menu selected event
	*/
	@Override
	public void handle(ActionEvent event) {
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
	/** Sets the fill icons as filled or unfilled.
	 */
	public void setFilled() {
		this.tabParent.setFilled();
	}
	
}
