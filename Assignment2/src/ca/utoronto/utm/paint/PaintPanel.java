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

import java.awt.Color;

class PaintPanel extends StackPane implements Observer, EventHandler<MouseEvent> {

	private int i = 0;
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view
	private String mode; // modifies how we interpret input (could be better?)
	private Drawable shape; // the circle we are building

	private Canvas canvas;
	
	private Color color= new Color(0, 0, 0);

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
		
		g.strokeText("i=" + i, 50, 75);
		i = i + 1;

		// Draw Lines

		LinkedList<Drawable> allObjects = this.model.getObjects();
		Point previousPoint = null;
		if (this.shape != null) {
			this.shape.draw(g);
		}
		while (!allObjects.isEmpty()) {
			Drawable current = allObjects.removeFirst();
			//color = current.getColor();
			//g.setStroke(Paint.valueOf("#"+Integer.toHexString(color.getRGB()).substring(2)));
			if (current.type()=="Point") {
				Point p1 = (Point)(current);
				if (previousPoint != null) {
					p1.draw(g, previousPoint);
				}
				previousPoint = (Point)current;
			}
			if (current.type()=="Circle") {
				current.draw(g);
			}
			if (current.type()=="Rectangle") {
				current.draw(g);
			}
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
		if (this.mode == "Squiggle") {
			this.model.addDrawable(new Point((int) e.getX(), (int) e.getY()));
		} else if (this.mode == "Circle") {
			if (this.shape != null) {
				this.shape.update(e);
				this.model.update();
			}
		} else if (this.mode == "Rectangle") {
			if (this.shape != null) {
				this.shape.update(e);
				this.model.update();
			}
		}
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
			this.shape = new Circle(centre, radius);
			this.shape.setColor(this.color);
		} else if (this.mode == "Rectangle") {
			Point corner = new Point((int) e.getX(), (int) e.getY());
			int length = 0;
			this.shape = new Rectangle(corner, length, length);
			this.shape.setColor(this.color);
		}
	}

	private void mouseReleased(MouseEvent e) {
		if (this.mode == "Squiggle") {
			this.model.addDrawable(new Point((int) e.getX(), (int) e.getY(),true));
		} else if (this.mode == "Circle") {
			if (this.shape != null) {
				this.shape.update(e);
				this.model.addDrawable(this.shape);
				this.shape = null;
			}
		} else if (this.mode == "Rectangle") {
			if (this.shape != null) {
				this.shape.update(e);
				this.model.addDrawable(this.shape);
				this.shape = null;
			}
	}
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
