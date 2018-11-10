package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.input.MouseEvent;

public abstract class TransformStrategy {
	protected PaintModel model;
	
	public void setModel(PaintModel p) {
		this.model = p;
		this.model.update();
	}
	public abstract void handleMouse(MouseEvent e);
	
	public Drawable findElement(MouseEvent e) {
		ArrayList<Drawable> allObjects = model.getObjects();
		for (int i= allObjects.size()-1; i >= 0;i--) {
			if(allObjects.get(i).isClicked(e)) return allObjects.get(i);
		}
		return null;
	}
}