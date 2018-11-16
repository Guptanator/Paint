package ca.utoronto.utm.tabPanel;

import ca.utoronto.utm.paint.View;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Accordion;

/** Holds the ColorGrid and handle setting a new color to 
 * PaintPanel's setColor() and setFill() methods, one for 
 * fill color and one to handle border color.
*/
public class ColorAccordions extends GridPane {
	
	private View view;
	private Accordion accordionBorder;
	private Accordion accordionFill;
	private TitledPane cpBorder= new ColorGrid(this.view,"Border",this);
	private TitledPane cpFill= new ColorGrid(this.view,"Fill",this);
	
	/** Constructor for ColorAccordion. Holds the accordions containing 
	 * a ColorGrid. Contains a grid for setting fill color and another to
	 * set the border color.
	 * 
	 * @param view Allows the pane to access the controller.
	 */
	public ColorAccordions(View view) {
		this.view = view;
		this.cpBorder.setMaxWidth(100);
		this.cpFill.setMaxWidth(100);
		
		this.accordionBorder = new Accordion();
		this.accordionBorder.getPanes().add(cpBorder);
		this.add(accordionBorder, 0, 0);
		this.accordionBorder.setMaxWidth(100);
		
		this.accordionFill = new Accordion();
		this.accordionFill.getPanes().add(cpFill);
		this.add(accordionFill, 0, 1);
		this.accordionFill.setMaxWidth(100);
	}
	
	/** Handler method for accordion. Sets the border color via
	 * the PaintPanel setColor() method. Color obtained from the
	 * ColorGrid.
	 * 
	 * @param cp Creates a pane that displays
	 * colors and a transparent button.
	 */
	public void handleMouseClickB (ColorGrid cp) {
        this.view.getPaintPanel().setColor(cp.getColor());
    }
	
	/** Handler method for accordion. Sets the fill color via
	 * the PaintPanel setFill() method. Color obtained from the
	 * ColorGrid.
	 * 
	 * @param cp Creates a pane that displays
	 * colors and a transparent button.
	 */
	public void handleMouseClickF (ColorGrid cp) {
        this.view.getPaintPanel().setFillColor(cp.getColor());
    }
}
