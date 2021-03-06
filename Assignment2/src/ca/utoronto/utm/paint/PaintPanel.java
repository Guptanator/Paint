package ca.utoronto.utm.paint;

import javafx.event.EventHandler;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import ca.utoronto.utm.shapes.CircleStrategy;
import ca.utoronto.utm.shapes.Drawable;
import ca.utoronto.utm.shapes.ShapeManipulatorStrategy;
import ca.utoronto.utm.shapes.TransformStrategy;
import ca.utoronto.utm.tabPanel.OtherModeButton;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
/** Class that handles the actual drawing of drawable objects
 * onto the canvas as well as the drawing and transforming strategies.
 */
public class PaintPanel extends StackPane implements Observer, EventHandler<MouseEvent> {

	private int i = 0;
	private boolean fill = false;
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view
	private Drawable shape; // the shape we are building
	private ShapeManipulatorStrategy strategy = new CircleStrategy(); // the Strategy for the shape we are building
	private TransformStrategy TStrategy;
	private Canvas canvas;
	private OtherModeButton currentModeButton=null;
	
	private Color borderColor = Color.BLACK;
	private Color fillColor = Color.TRANSPARENT;
	
	private double thick = 1.0;
	public boolean shapeMode=true;
	public boolean override = false;
	
	/** Constructor for PaintPanel. Sets up and initializes the canvas.
	 * 
	 * @param model PaintModel that handles the creation and destruction
	 * of drawable objects.
	 * @param view View of Paint Application
	*/
	public PaintPanel(PaintModel model, View view) {
		
		this.canvas = new Canvas(400, 400);
		this.getChildren().add(this.canvas);
		// The canvas is transparent, so the background color of the
		// containing pane serves as the background color of the canvas.
		this.setStyle("-fx-background-color: white");
		this.addEventHandler(MouseEvent.ANY, this);
		
		this.model = model;
		this.model.setHeight((int)this.canvas.getHeight());
		this.model.setWidth((int)this.canvas.getWidth());
		
		this.model.addObserver(this);
		
		this.view = view;
		
		this.strategy.setModel(this.model);
		this.strategy.setColor(this.borderColor);
		this.strategy.setFill(this.fillColor);
		this.strategy.setThickness(1.0);
	}
	
	/** Handles the constant redrawing of all stored drawable 
	 * objects and putting them back on the canvas.
	*/
	public void repaint() {
		GraphicsContext g = this.canvas.getGraphicsContext2D();

		g.clearRect(0, 0, this.getWidth(), this.getHeight());

		for (Drawable current: this.model.getObjects()) {
			current.draw(g);
		}
	}
	
	/** Returns the stored PaintModel.
	 * 
	 * @return Current PaintModel
	*/
	public PaintModel getModel() {
		return this.model;
	}
	
	/** Updates the canvas to show all drawn objects.
	*/	
	@Override
	public void update(Observable o, Object arg) {

		// Not exactly how MVC works, but similar.
		this.repaint();
	}

	/** Changes the thickness value in panel, current model
	 * and current Strategy.
	 * 
	 * @param t Selected Thickness
	 */
	public void setThickness(double t) {
		this.thick = t;
		this.model.thick = t;
		this.strategy.setThickness(t);
	}
	/** Changes the color value in the model
	 * 
	 * @param c Color that will change the drawable
	 * color.
	 */
	public void setColor(Color c) {
		this.borderColor = c;
		this.strategy.setColor(c);
	}
	
	/** Changes the fill color value in the model
	 * 
	 * @param c Color that will change the drawable
	 * color.
	 */
	public void setFillColor(Color c) {
		this.fillColor = c;
		this.strategy.setFill(c);
	}
	
	/** Returns the current ShapeManipulatorStrategy.
	 * 
	 * @return Current Strategy
	 */
	public ShapeManipulatorStrategy getStrategy() {
		return this.strategy;
	}
	
	/** Returns the current TStrategy
	 * 
	 * @return The current active TransformStrategy
	*/
	public TransformStrategy getTStrategy() {
		return this.TStrategy;
	}
	
	/** Sets the current strategy and initializes it's key attributes 
	 * required for drawing/adding shapes
	 * 
	 * @param s New Strategy
	*/
	public void setStrategy(ShapeManipulatorStrategy s) {
		this.strategy = s;
		this.strategy.setModel(this.model);
		this.strategy.setColor(this.borderColor);
		this.strategy.setFill(this.fillColor);
		this.strategy.setThickness(this.thick);
	}
	
	/** Sets the current transform strategy and initializes it's key attributes 
	 * which are required for any transformation. Also manages the display of
	 * some UI elements
	 * 
	 * @param t New TransformStrategy
	 * @param modeButton the last button pressed
	*/
	public void setTransformMode(TransformStrategy t, OtherModeButton modeButton) {
		this.TStrategy = t;
		t.setPanel(this);
		this.shapeMode = false;
		this.currentModeButton = modeButton;
	}
	
	/** Deactivates the transform strategy functionality and reloads the 
	 * ShapeManipulatorStrategy attributes. Also manages the display of some UI elements.
	*/
	public void UnsetTransformMode() {
		this.TStrategy = null;
		this.shapeMode = true;
		this.override = false;
		if (this.currentModeButton!=null)this.currentModeButton.setSelected(false);
	}
	
	/** Activates the current strategy mode, either transform or shape manipulator.
	 * Ensures that no two strategies are active.
	 * 
	 * @param MouseEvent e the mouse event passed from the user
	*/
	@Override
	public void handle(MouseEvent e) {
		if (this.shapeMode) {
			this.strategy.mouseHandle(e);
		} else {
			this.TStrategy.handleMouse(e);
		}
	}
	
	/** Sets canvas dimensions to the input length
	 * 
	 * @param h Input Height
	 * @param w Input Width
	 */
	public void changeCanvas(int h, int w) {
		this.canvas.setHeight(h);
		this.canvas.setWidth(w);
		this.model.setHeight(h);
		this.model.setWidth(w);
	}
	
	/** Returns the current Canvas
	 * 
	 * @return The current canvas
	 */
	public Canvas getCanvas() {
		return this.canvas;
	}
	
	/** Returns the current View
	 * 
	 * @return The current View
	 */
	public View getView() {
		return this.view;
	}
	
	/** Sets Input model to Current Model and adds Panel
	 * as Observer.
	 * 
	 * @param model New PaintModel
	 */	
	public void setModel(PaintModel model) {
		this.model = model;
		this.strategy.setModel(model);
		this.model.addObserver(this);
		this.model.update();
	}
}
