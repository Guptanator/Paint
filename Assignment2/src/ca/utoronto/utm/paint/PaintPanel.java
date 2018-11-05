package ca.utoronto.utm.paint;

import javafx.event.EventHandler;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

import java.awt.Color;

class PaintPanel extends StackPane implements Observer, EventHandler<MouseEvent> {

	private int i = 0;
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view
	private String mode; // modifies how we interpret input (could be better?)
	private Drawable shape; // the shape we are building
	private ShapeManipulatorStrategy strategy = new CircleStrategy(); // the Strategy for the shape we are building

	private Canvas canvas;

	
	private Color color= new Color(0, 0, 0);
	
	private double thickness = 1.0;
	private String thick;

	public PaintPanel(PaintModel model, View view) {

		this.canvas = new Canvas(300, 300);
		this.getChildren().add(this.canvas);
		// The canvas is transparent, so the background color of the
		// containing pane serves as the background color of the canvas.
		this.setStyle("-fx-background-color: white");

		this.addEventHandler(MouseEvent.ANY, this);

		this.mode = "Circle"; // bad code here?
		
		this.model = model;
		
		this.model.addObserver(this);
		
		this.view = view;
	}

	public void repaint() {

		GraphicsContext g = this.canvas.getGraphicsContext2D();

		
		// Clear the canvas
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		g.setStroke(Paint.valueOf("#"+Integer.toHexString(this.color.getRGB()).substring(2)));
		g.strokeText("i=" + i, 50, 75);
		i = i + 1;

		
		// Draw Lines
		
		

		LinkedList<Drawable> allObjects = this.model.getObjects();
		Point previousPoint = null;
		if (this.strategy.getShape() != null) {
			this.strategy.getShape().draw(g,this.thickness);
		}
		if (this.strategy.getShape()!= null) {this.shape.draw(g, this.thickness);}
		while (!allObjects.isEmpty()) {
			Drawable current = allObjects.removeFirst();
			if (current.type()=="Point") {
				Point p1 = (Point)(current);
				if (previousPoint != null) {
					p1.draw(g, previousPoint, this.thickness);
				}
				previousPoint = (Point)current;
			} else {current.draw(g, this.thickness);}

		}
	}



	
	@Override
	public void update(Observable o, Object arg) {

		// Not exactly how MVC works, but similar.
		this.repaint();
	}

	/**
	 * Controller aspect of this
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}
	

	public void setStrategy(ShapeManipulatorStrategy s) {
		this.strategy = s;
	}

	@Override
	public void handle(MouseEvent event) {

		if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			mouseDragged(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
			mousePressed(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_MOVED) {
			mouseMoved(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
			mouseClicked(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
			mouseReleased(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_ENTERED) {
			mouseEntered(event);
		} else if (event.getEventType() == MouseEvent.MOUSE_EXITED) {
			mouseExited(event);
		}
	}

	private void mouseMoved(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {

		}
	}

	private void mouseDragged(MouseEvent e) {
		this.strategy.changeShape(e, this.model);
	}

	private void mouseClicked(MouseEvent e) {
		if (this.mode == "Squiggle") {
		} else if (this.mode == "Circle") {
		} else if (this.mode == "Rectangle");
	}

	private void mousePressed(MouseEvent e) {

		this.color = this.model.getColor();
		
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {
			// Problematic notion of radius and centre!!
			Point centre = new Point((int) e.getX(), (int) e.getY());
			int radius = 0;
			this.shape = new Circle(centre, radius, this.color, this.thickness);
		} else if (this.mode == "Rectangle") {
			Point corner = new Point((int) e.getX(), (int) e.getY());
			int length = 0;
			this.shape = new Rectangle(corner, length, length, this.color, this.thickness);
		}		

		if (this.thick == "Normal") {
			this.thickness = 5.0;
			
		} else if (this.thick == "Thin") {
			this.thickness = 1.0;
		
		} else if (this.thick == "Thick") {
			this.thickness = 10.0;
		}
		
		this.strategy.makeShape(e, this.color, this.thickness);


	}
	public void setThickness(String command) {
		this.thick = command;
	}
	
	private void mouseReleased(MouseEvent e) {
		this.model.addDrawable(this.strategy.getShape());
		this.strategy.setNull();
	}

	private void mouseEntered(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {

		}
	}

	private void mouseExited(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {

		}
	}
}
