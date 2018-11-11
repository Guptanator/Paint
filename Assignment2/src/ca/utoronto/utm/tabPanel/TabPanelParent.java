package ca.utoronto.utm.tabPanel;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

import ca.utoronto.utm.paint.View;

/**
 * This class is used to hold all of the other Panes and allows for easier scaling in terms additional
 * features. Furthermore this class extends GridPane since it has the the easiest scaling and allows
 * automatic width scaling depends on the largest item.
*/
public class TabPanelParent extends GridPane {
	
	private View view;
	private int currentRow = 0;
	private ShapeChooserPanel scp;
	private colorPane cp;
	private thicknessPopup tp;
	private OtherModesPane omp;
	/**
	 * This constructor creates all of the Sub-panels and adds them to the master pane. 
	 * @param View view used for passing to the independent views which all require the View.
	 */
	public TabPanelParent(View view) {
		this.view = view;
		this.setVgap(15);
		this.cp = new colorPane(view);
		this.scp = new ShapeChooserPanel(view);
		this.tp = new thicknessPopup(view);
		this.omp = new OtherModesPane(view);
		this.addPane(this.scp);
		this.addPane(this.cp);
		this.addPane(this.tp);
		this.addPane(this.omp);
	}
	/**
	 * This function allows for adding the panes in an abstracted manner, ensuring there 
	 * are fewer places for things to break.
	 * @param Pane p the pane you want to add to the master pane.
	 */
	private void addPane(Pane p) {
		this.add(p, 0, currentRow);
		this.currentRow++;
	}
}
