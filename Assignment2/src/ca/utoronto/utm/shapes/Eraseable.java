package ca.utoronto.utm.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Eraseable {
	public double x,y;
	public int w;
	public Eraseable(double x,double y,int w) {
		this.x=x;
		this.y=y;
		this.w=w;
	}
	
	public void execute(GraphicsContext g) {
		g.setFill(Color.WHITE);
		g.fillRect(x-(w/2), y-(w/2), w, w);
	}
	
}
