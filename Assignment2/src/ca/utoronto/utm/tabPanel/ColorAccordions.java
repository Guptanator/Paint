package ca.utoronto.utm.tabPanel;

import ca.utoronto.utm.paint.View;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.scene.control.Accordion;

public class ColorAccordions extends GridPane {
	private View view;
	private Accordion accordionBorder;
	private Accordion accordionFill;
	private TitledPane CPBorder= new ColorGrid(this.view,"Border",this);
	private TitledPane CPFill= new ColorGrid(this.view,"Fill",this);
	public ColorAccordions(View view) {
		this.view = view;
		this.CPBorder.setMaxWidth(100);
		this.CPFill.setMaxWidth(100);
		
		this.accordionBorder = new Accordion();
		this.accordionBorder.getPanes().add(CPBorder);
		this.add(accordionBorder, 0, 0);
		this.accordionBorder.setMaxWidth(100);
		
		this.accordionFill = new Accordion();
		this.accordionFill.getPanes().add(CPFill);
		this.add(accordionFill, 0, 1);
		this.accordionFill.setMaxWidth(100);
	}
	public void handleMouseClickB (ColorGrid CP) {
        this.view.getPaintPanel().setColor(CP.getColor());
    }
	
	public void handleMouseClickF (ColorGrid CP) {
        this.view.getPaintPanel().setFillColor(CP.getColor());
    }
}
