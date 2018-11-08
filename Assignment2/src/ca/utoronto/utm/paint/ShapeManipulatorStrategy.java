package ca.utoronto.utm.paint;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public abstract class ShapeManipulatorStrategy {
	protected Color color;
	protected double thickness;
	protected PaintModel model;
	
	public abstract void mouseHandle(MouseEvent e);
	
	public void setModel(PaintModel p) {
		this.model = p;
		this.model.update();
	}
	
	public void setColor(Color c) {
		this.color = c;
	}
	
	public void setThickness(double t) {
		this.thickness = t;
	}
	
}
