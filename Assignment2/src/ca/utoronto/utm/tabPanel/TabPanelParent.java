package ca.utoronto.utm.tabPanel;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

import ca.utoronto.utm.paint.View;


public class TabPanelParent extends GridPane {
	
	private View view;
	private headerPanel header = new headerPanel();
	private tabButtonHandler handleTabButtons;
	private Pane oldPane = null;
	public TabPanelParent(View view) {
		this.view = view;
		handleTabButtons = new tabButtonHandler(this,view);
		this.add(header,0,0);
		for (int i=0;i<header.getAllTabs().size();i++) {
			header.getAllTabs().get(i).setOnAction(handleTabButtons);
		}
		
	    this.getColumnConstraints().add(new ColumnConstraints(200)); // column 0 is 100 wide
	    header.getAllTabs().get(0).fire();
	}
	
	public void updateCurrentTab(Pane newPane) {
		if (oldPane!=null) {
			this.getChildren().remove(oldPane);
		}
		this.add(newPane, 0, 1);
		oldPane = newPane;
	}
	public void removeChild(Pane p) {
		this.getChildren().remove(p);
	}
	public void clearAllButtons(TabChooserButton selected) {
		for (int i=0; i<this.header.getAllTabs().size();i++) {
			if (selected != this.header.getAllTabs().get(i)) {
				this.header.getAllTabs().get(i).setSelected(false);
			}
		}
	}
}
