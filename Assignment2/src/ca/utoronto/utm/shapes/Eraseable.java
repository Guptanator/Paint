package ca.utoronto.utm.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Eraseable {
	public double x,y;
	public int w;
	/** 
	 * Sets a new value in the property invoker regarding fill
	 * @param Double x the x coordinate of the erasers
	 * @param Double y the y coordinate of the erasers
	 * @param Double w the width of the erasers
	*/
	public Eraseable(double x,double y,int w) {
		this.x=x;
		this.y=y;
		this.w=w;
	}
	/** 
	 * Redraws all of the Erasables
	 * @param GraphicsContext g the current context
	*/
	public void execute(GraphicsContext g) {
		g.setFill(Color.WHITE);
		g.fillRect(x-(w/2), y-(w/2), w, w);
	}
	public Eraseable copy() {
		Eraseable e = new Eraseable(this.x,this.y,this.w);
		return e;
	}
}
