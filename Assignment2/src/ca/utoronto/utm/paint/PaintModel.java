package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;

import ca.utoronto.utm.drawingCommands.ColorCommand;
import ca.utoronto.utm.drawingCommands.FillCommand;
import ca.utoronto.utm.drawingCommands.PropertyInvoker;
import ca.utoronto.utm.drawingCommands.ThicknessCommand;
import javafx.scene.paint.Color;
import ca.utoronto.utm.shapes.Drawable;

/**
 * The class that handles the creation and destruction
 * of drawable objects.
 */
public class PaintModel extends Observable {

	private ArrayList<Drawable> allObjects = new ArrayList<Drawable>();
	private LinkedList<Drawable> undone = new LinkedList<Drawable>();
	private int current = 0;
	private String name;
	private int height;
	private int width;
	
	public PaintModel(String name) {
		this.name = name;
		this.setChanged();
		this.notifyObservers();
	}
	
	/** Handles setting the properties to d and adding it
	 * to the list of drawable objects (allObjects) to draw on canvas.
	 * @param d Drawable that will be added to the canvas.
	*/
	public void addDrawable(Drawable d) {
		this.allObjects.add(d);
		this.update();
	}
	/** Removes the latest drawn object from the (allObjects) list
	 * and saves the value into a different list (undone) in case you
	 * want to undo the undo.
	*/
	public void Undo() {
		if (!allObjects.isEmpty()) {
			this.undone.addFirst(allObjects.remove(allObjects.size()-1));
			this.update();
		}
	}
	/** Removes the first drawable object in the (undone) list
	 * and places it back to the (allObjects) list of objects 
	 * to display on canvas.
	*/
	public void Redo() {
		if (!undone.isEmpty()) {
			this.allObjects.add(undone.removeFirst());
			this.update();
		}
	}
	/** Returns an ArrayList of drawable objects that are to be added to
	 * the canvas.
	 * @return getObjects ArrayList<Drawable> of objects to put onto
	 * the canvas.
	*/
	public ArrayList<Drawable> getObjects() {
		ArrayList<Drawable> newList = (ArrayList<Drawable>)this.allObjects.clone();
		return newList;
	}
	/** Returns the topmost value of the stack of the list
	 * (allObjects) or null if the list is empty.
	 * @return   top most value of the list (allObjects) or null
	 * if the list is empty.
	*/
	public Drawable removeLast() {
		if (this.allObjects.size() > 0) {
			return this.allObjects.remove(allObjects.size()-1);
		}
		return null;
	}
	
	/** 
	 * Upon call Updates Observers
	*/
	public void update() {
		this.setChanged();
		this.notifyObservers();
	}
	
	/** 
	 * Removes objects in allOjects
	*/
	public void removeObject(Drawable d) {
		int i=this.allObjects.indexOf(d);
		if (i>=0) {
			this.allObjects.remove(i);
			this.update();
		}
	}
	
	/** 
	 * Returns the name of the model
	*/
	public String getName() {
		return this.name;
	}
	
	/** 
	 * Set height to current height
	*/
	public void setHeight(int h) {
		this.height = h;
	}
	
	/** 
	 * Set Width to current width
	*/
	public void setWidth(int w) {
		this.width = w;
	}
	
	/** 
	 * Return height
	*/
	public int getHeight() {
		return this.height;
	}
	
	/** 
	 * Return Width
	*/
	public int getWidth() {
		return this.width;
	}
}
