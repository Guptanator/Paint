package ca.utoronto.utm.tabPanel;

import ca.utoronto.utm.paint.View;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Accordion;
/**
 * This class is used to hold the ColorGrid and handle
 * setting a new color to PaintPanel's setColor() and
 * setFill() methods, one for fill color and one to handle
 * border color.
 *
*/
public class ColorAccordions extends GridPane {
	private View view;
	private Accordion accordionBorder;
	private Accordion accordionFill;
	private TitledPane CPBorder= new ColorGrid(this.view,"Border",this);
	private TitledPane CPFill= new ColorGrid(this.view,"Fill",this);
	/**
	 * Constructor for ColorAccordion, which is used to hold the accordions
	 * that contain a ColorGrid, one for setting fill color and another to
	 * set the border color.
	 * @param View view which allows the pane to access the controller.
	 */
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
	/**
	 * Handler method for accordion to set the border color via
	 * the PaintPanel setColor() method. Color obtained from the
	 * ColorGrid.
	 * @param ColorGrid CP which creates a pane that displays
	 * colors and a transparent button.
	 */
	public void handleMouseClickB (ColorGrid CP) {
        this.view.getPaintPanel().setColor(CP.getColor());
    }
	/**
	 * Handler method for accordion to set the fill color via
	 * the PaintPanel setFill() method. Color obtained from the
	 * ColorGrid.
	 * @param ColorGrid CP which creates a pane that displays
	 * colors and a transparent button.
	 */
	public void handleMouseClickF (ColorGrid CP) {
        this.view.getPaintPanel().setFillColor(CP.getColor());
    }
}
