package ca.utoronto.utm.tabPanel;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
	private colorPicker cp;
	private thicknessPopup tp;
	
	private OtherModesPane omp;
	/**
	 * This constructor creates all of the Sub-panels and adds them to the master pane. 
	 * @param View view used for passing to the independent views which all require the View.
	 */
	public TabPanelParent(View view) {
		this.view = view;
		this.setVgap(15);
		this.cp = new colorPicker(view);
		this.scp = new ShapeChooserPanel(view);
		this.tp = new thicknessPopup(view);
		this.omp = new OtherModesPane(view);
		this.addPane(this.scp);
		this.addPane(this.cp);
		this.addPane(this.tp);
		this.addPane(this.omp);
		this.setBackground(new Background(new BackgroundFill(Color.web("#7f8c8d"), null, null)));
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
	/**
	 * This function allows the outside world to set the fill icons in the shapeChooserPanel
	 * as filled or unfilled.
	 */
	public void setFilled() {
		this.scp.setButtonsFill();
	}
}
