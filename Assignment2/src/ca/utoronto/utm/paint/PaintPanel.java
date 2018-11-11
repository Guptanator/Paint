package ca.utoronto.utm.paint;

import javafx.event.EventHandler;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class PaintPanel extends StackPane implements Observer, EventHandler<MouseEvent> {

	private int i = 0;
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view
	private Drawable shape; // the shape we are building
	private ShapeManipulatorStrategy strategy = new CircleStrategy(); // the Strategy for the shape we are building

	private Canvas canvas;

	
	private Color color= Color.BLACK;
	
	private String thick;

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

	public void repaint() {

		GraphicsContext g = this.canvas.getGraphicsContext2D();

		
		// Clear the canvas
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.strokeText("i=" + i, 50, 75);
		i = i + 1;

		
		// Draw Lines

		ArrayList<Drawable> allObjects = this.model.getObjects();
		while (!allObjects.isEmpty()) {
				Drawable current = allObjects.remove(0);
				current.draw(g);
			}
		}


	public void setFill()
	{
		this.model.setFill();
	}
	public PaintModel getModel()
	{
		return this.model;
	}
	
	@Override
	public void update(Observable o, Object arg) {

		// Not exactly how MVC works, but similar.
		this.repaint();
	}

	/**
	 * Controller aspect of this
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
	
	public void setColor(Color c) {
		this.color = c;
		this.strategy.setColor(c);
	}
	public ShapeManipulatorStrategy getStrategy() {
		return this.strategy;
		
	}

	public void setStrategy(ShapeManipulatorStrategy s) {
		this.strategy = s;
		this.strategy.setModel(this.model);
		this.strategy.setColor(this.color);
		setThickness(this.thick);
	}

	@Override
	public void handle(MouseEvent e) {
		this.strategy.mouseHandle(e);
	}
}
