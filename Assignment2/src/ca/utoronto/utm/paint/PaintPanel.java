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
/**
 * Class that handles the actual drawing of drawable objects
 * onto the canvas as well as the drawing strategies.
 * 
 */
public class PaintPanel extends StackPane implements Observer, EventHandler<MouseEvent> {

	private int i = 0;
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view
	private Drawable shape; // the shape we are building
	private ShapeManipulatorStrategy strategy = new CircleStrategy(); // the Strategy for the shape we are building
	private TransformStrategy TStrategy;
	private Canvas canvas;
	private OtherModeButton currentModeButton=null;
	
	private Color color= Color.BLACK;
	
	private String thick;
	public boolean shapeMode=true;
	/** Constructor for PaintPanel that sets up and initializes the canvas.
	 * @param model PaintModel that handles the creation and destruction
	 * of drawable objects.
	 * @param view View 
	*/
	public PaintPanel(PaintModel model, View view) {
		
		GraphicsDevice gdevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int width = gdevice.getDisplayMode().getWidth();
		int height = gdevice.getDisplayMode().getHeight();
		
		this.canvas = new Canvas(400, 400);
		this.getChildren().add(this.canvas);
		// The canvas is transparent, so the background color of the
		// containing pane serves as the background color of the canvas.
		this.setStyle("-fx-background-color: white");

		this.addEventHandler(MouseEvent.ANY, this);
		
		this.model = model;
		
		this.model.addObserver(this);
		
		this.view = view;
		
		this.strategy.setModel(this.model);
		this.strategy.setColor(this.color);
		this.strategy.setThickness(1.0);
	}
	/** The method that handles the constant redrawing of
	 * all stored drawable objects and putting them back on
	 * the canvas.
	*/
	public void repaint() {

		GraphicsContext g = this.canvas.getGraphicsContext2D();

		
		// Clear the canvas
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.setLineWidth(1.0);
		g.strokeText("i=" + i, 50, 75);
		i = i + 1;

		
		// Draw Lines

		ArrayList<Drawable> allObjects = this.model.getObjects();
		while (!allObjects.isEmpty()) {
				Drawable current = allObjects.remove(0);
				current.draw(g);
			}
		}

	/** Activates PaintModel's setfill() function, which changes
	 * whether or not the next shapes are going to be filled or not.
	*/
	public void setFill() {
		this.view.setFilled();
		this.model.setFill();
	}
	/** Returns the stored PaintModel.
	 * @return getModel PaintModel
	*/
	public PaintModel getModel()
	{
		return this.model;
	}
	/** Activates this class' repaint() function,
	 * which updates the canvas to show all drawn
	 * objects.
	*/
	@Override
	public void update(Observable o, Object arg) {

		// Not exactly how MVC works, but similar.
		this.repaint();
	}

	/**
	 * Changes the thickness value and stores it in the
	 * ShapeManipulatorStrategy strategy based on a String (t)
	 * to be either Thin (1.0 thickness), Normal (5.0 thickness),
	 * or Thick (10.0 thickness).
	 * @param t String value that will either be Thin, Normal, or
	 * Thick.
	 */
	
	public void setThickness(String t) {
		if (t == "Thin") {
			this.strategy.setThickness(1.0);
		} else if (t == "Normal") {
			this.strategy.setThickness(5.0);
		} else if (t == "Thick") {
			this.strategy.setThickness(10.0);
		}
		else {
			this.setThickness("Thin");
		}
	}
	/**
	 * Changes the color value in the model
	 * @param c Color that will change the drawable
	 * color.
	 */
	public void setColor(Color c) {
		this.model.setColor(c);
	}
	/**
	 * Returns the current ShapeManipulatorStrategy (strategy).
	 * @param this.strategy ShapeManipulatorStrategy
	 */
	public ShapeManipulatorStrategy getStrategy() {
		return this.strategy;
		
	}

	public void setStrategy(ShapeManipulatorStrategy s) {
		this.strategy = s;
		this.strategy.setModel(this.model);
		this.strategy.setColor(this.color);
		setThickness(this.thick);
	}
	
	public void setTransformMode(TransformStrategy t, OtherModeButton modeButton) {
		this.TStrategy = t;
		t.setModel(this.model);
		this.shapeMode = false;
		this.currentModeButton = modeButton;
	}
	public void UnsetTransformMode() {
		this.TStrategy = null;
		this.shapeMode = true;
		if (this.currentModeButton!=null)this.currentModeButton.setSelected(false);
	}
	
	@Override
	public void handle(MouseEvent e) {
		if (this.shapeMode) {
			this.strategy.mouseHandle(e);
		} else {
			this.TStrategy.handleMouse(e);
		}
	}
}
