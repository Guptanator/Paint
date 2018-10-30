package ca.utoronto.utm.paint;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Stack;

class PaintPanel extends StackPane implements Observer, EventHandler<MouseEvent> {

	private int i = 0;
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view

	private String mode; // modifies how we interpret input (could be better?)
	private Circle circle; // the circle we are building

	private Canvas canvas;

	public PaintPanel(PaintModel model, View view) {

		this.canvas = new Canvas(300, 300);
		this.getChildren().add(this.canvas);
		// The canvas is transparent, so the background color of the
		// containing pane serves as the background color of the canvas.
		this.setStyle("-fx-background-color: blue");

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

		g.setStroke(Color.WHITE);
		g.strokeText("i=" + i, 50, 75);
		i = i + 1;

		// Draw Lines

		Stack<Drawable> allObjects = this.model.getObjects();
		Point previousPoint = null;
		while (!allObjects.empty()) {
			Drawable current = allObjects.pop();
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
			if (this.circle != null) {
				int horizontal = Math.abs((int) this.circle.getCentre().getX() - (int) e.getX());
				int vertical = Math.abs((int) this.circle.getCentre().getY() - (int) e.getY());
				int radius = (int)Math.sqrt(Math.pow(horizontal,2) + Math.pow(vertical,2));
				this.circle.setRadius(radius);
			}
		}
	}

	private void mouseClicked(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {
				

		}
	}

	private void mousePressed(MouseEvent e) {
		if (this.mode == "Squiggle") {

		} else if (this.mode == "Circle") {
			// Problematic notion of radius and centre!!
			Point centre = new Point((int) e.getX(), (int) e.getY());
			int radius = 0;
			this.circle = new Circle(centre, radius);
		}
	}

	private void mouseReleased(MouseEvent e) {
		if (this.mode == "Squiggle") {
			this.model.addDrawable(new Point((int) e.getX(), (int) e.getY(),true));
		} else if (this.mode == "Circle") {
			if (this.circle != null) {
				// Problematic notion of radius and centre!!
				int horizontal = Math.abs((int) this.circle.getCentre().getX() - (int) e.getX());
				int vertical = Math.abs((int) this.circle.getCentre().getY() - (int) e.getY());
				int radius = (int)Math.sqrt(Math.pow(horizontal,2) + Math.pow(vertical,2));
				this.circle.setRadius(radius);
				this.model.addDrawable(this.circle);
				this.circle = null;
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
