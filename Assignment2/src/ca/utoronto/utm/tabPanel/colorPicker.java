package ca.utoronto.utm.tabPanel;

import ca.utoronto.utm.paint.View;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Accordion;

public class colorPicker extends GridPane {
	private View view;
	private Accordion accordion;
	private TitledPane CP= new colorPane(this.view);
	public colorPicker(View view)
	{
		this.view = view;
		this.accordion = new Accordion();
		this.CP.setMaxWidth(100);
		this.accordion.getPanes().add(CP);
		this.accordion.setMaxWidth(100);
		this.add(accordion, 0, 0);
	}
}
