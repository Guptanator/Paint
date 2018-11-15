package ca.utoronto.utm.tabPanel;

import ca.utoronto.utm.paint.View;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.beans.Observable;
import javafx.scene.control.Accordion;

public class ColorAccordions extends GridPane {
	private View view;
	private Accordion accordionBorder;
	private Accordion accordionFill;
	private TitledPane CPBorder= new ColorGrid(this.view);
	private TitledPane CPFill= new ColorGrid(this.view);
	public ColorAccordions(View view)
	{
		this.view = view;
		this.CPBorder.setMaxWidth(100);
		this.CPFill.setMaxWidth(100);
		this.CPBorder.setText("Border Color");
		this.CPFill.setText("Fill Color");
		this.accordionBorder = new Accordion();
		this.accordionBorder.getPanes().add(CPBorder);
		this.accordionBorder.expandedPaneProperty().addListener(e -> handleMouseClickB(e, (ColorGrid)CPBorder));
		this.add(accordionBorder, 0, 0);
		this.accordionFill = new Accordion();
		this.accordionFill.getPanes().add(CPFill);
		this.accordionFill.expandedPaneProperty().addListener(e -> handleMouseClickF(e, (ColorGrid)CPFill));
		this.add(accordionFill, 0, 1);
	}
	private void handleMouseClickB (Observable e, ColorGrid CP) {
        this.view.getPaintPanel().setColor(CP.getColor());
        CP.setStyle("-fx-text-fill: #" + Color.web(CP.getColor().toString()).toString().substring(2)+";");
    }
	
	private void handleMouseClickF (Observable e, ColorGrid CP) {
        this.view.getPaintPanel().setFillColor(CP.getColor());
        CP.setStyle("-fx-text-fill: #" + Color.web(CP.getColor().toString()).toString().substring(2)+";");
    }
}
