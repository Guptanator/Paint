package ca.utoronto.utm.tabPanel;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

import ca.utoronto.utm.paint.View;


public class TabPanelParent extends GridPane {
	
	private View view;
	private int currentRow = 0;
	private ShapeChooserPanel scp;
	private colorPane cp;
	private thicknessPopup tp;
	private fillButton fB;
	
	public TabPanelParent(View view) {
		this.view = view;
		this.setVgap(20);
		this.cp = new colorPane(view);
		this.scp = new ShapeChooserPanel(view);
		this.tp = new thicknessPopup(view);
		this.fB = new fillButton(view);
		this.addPane(this.scp);
		this.addPane(this.cp);
		this.addPane(this.tp);
		this.addPane(this.fB);
	}
	public void addPane(Pane p) {
		this.add(p, 0, currentRow);
		this.currentRow++;
	}
}
