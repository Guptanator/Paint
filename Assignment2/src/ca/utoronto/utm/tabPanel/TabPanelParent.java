package ca.utoronto.utm.tabPanel;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import ca.utoronto.utm.paint.View;

/** Holds Tool Panes for Paint Application.
*/
public class TabPanelParent extends GridPane {
	
	private View view;
	private int currentRow = 0;
	private ShapeChooserPanel scp;
	private ColorAccordions ca;
	private thicknessPopup tp;
	
	private OtherModesPane omp;
	
	/** TabPanelParent constructor. Creates all Sub-panels and adds them 
	 * to the master pane.
	 * 
	 * @param view Paint Application View
	 */
	public TabPanelParent(View view) {
		this.view = view;
		this.setVgap(15);
		this.ca = new ColorAccordions(view);
		this.scp = new ShapeChooserPanel(view);
		this.tp = new thicknessPopup(view);
		this.omp = new OtherModesPane(view);
		this.addPane(this.scp);
		this.addPane(this.ca);
		this.addPane(this.tp);
		this.addPane(this.omp);
		this.setBackground(new Background(new BackgroundFill(Color.web("#7f8c8d"), null, null)));
	}
	
	/** Adds Pane to current row.
	 * 
	 * @param p pane to be added to the panel
	 */
	private void addPane(Pane p) {
		this.add(p, 0, currentRow);
		this.currentRow++;
	}
	
	/** Sets the fill icons in the shapeChooserPanel
	 * as filled or unfilled.
	 */
	public void setFilled() {
		this.scp.setButtonsFill();
	}
}
