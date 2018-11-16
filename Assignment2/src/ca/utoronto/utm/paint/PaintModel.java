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

/** The class that handles the creation and destruction
 * of drawable objects.
 */
public class PaintModel extends Observable {

	private ArrayList<Drawable> allObjects = new ArrayList<Drawable>();
	public LinkedList<DrawableState> undoStates = new LinkedList<DrawableState>();
	public LinkedList<DrawableState> redoStates = new LinkedList<DrawableState>();
	public double thick = 1.0;
	private String name;
	private int height;
	private int width;
	
	/** PaintModel Constructor. Sets it's name to be
	 * the inputed String
	 * 
	 * @param name The name of the PaintModel.
	*/
	public PaintModel(String name) {
		this.name = name;
		this.setChanged();
		this.notifyObservers();
	}
	
	/** Adds a drawable to the list of drawable objects 
	 * (allObjects) to draw on canvas.
	 * 
	 * @param d Drawable that will be added to the canvas.
	*/
	public void addDrawable(Drawable d) {
		this.undoStates.addLast(new DrawableState(null,d,"add"));
		this.allObjects.add(d);
		this.update();
	}

	/** Removes the latest state object from the undoStates list
	 * executes it's operation and then adds it to the redo list
	*/
	public void Undo() {
		if (!this.undoStates.isEmpty()) {
			DrawableState current = this.undoStates.removeLast();
			current.setModel(this);
			current.execute();
			redoStates.addFirst(current);
		}
	}
	
	/** Removes the first drawable state from the redoStates list
	 * and executes it's undo function.
	*/
	public void Redo() {
		if (!this.redoStates.isEmpty()) {
			DrawableState current = this.redoStates.removeLast();
			current.executeRedo();
		}
	}
	
	/** Returns an ArrayList of drawable objects that are drawn to the
	 * Canvas
	 * 
	 * @return getObjects ArrayList<Drawable> of objects
	*/
	public ArrayList<Drawable> getObjects() {
		return this.allObjects;
	}
	
	/** Returns the topmost value of the stack of the list
	 * (allObjects) or null if the list is empty.
	 * 
	 * @return Drawable Object or Null if allObjects
	 * is empty.
	*/
	public Drawable removeLast() {
		if (this.allObjects.size() > 0) {
			return this.allObjects.remove(allObjects.size()-1);
		}
		return null;
	}
	
	/** Utility function to update Observers
	*/
	public void update() {
		this.setChanged();
		this.notifyObservers();
	}
	
	/** Removes Specified Drawable from the allObjects ArrayList and then updates the model
	 * 
	 * @param d Selected Drawable
	*/
	public void removeObject(Drawable d) {
		this.allObjects.remove(d);
		this.update();
	}
	
	/** Finds the topmost not closed element, and adds the Drawable
	 * above that element in the list, this is used to avoid issues with 
	 * the z-level of the eraser Squiggle
	 * 
	 * @param d Drawable to be added
	*/
	public void addAbove(Drawable d) {
		int i;
		for (i=allObjects.size()-1;i>=0;i=i-1) {
			if (!allObjects.get(i).isClosed()) {
				break;
			}
		}
		this.undoStates.addLast(new DrawableState(null,d,"add"));
		allObjects.add(i+1, d);
	}
	
	/** Returns the name of the model
	 * 
	 * @return The name of the model
	*/
	public String getName() {
		return this.name;
	}
	
	/** Set height to current height.
	 * 
	 * @param h New height
	*/
	public void setHeight(int h) {
		this.height = h;
	}
	
	/** Set Width to current width
	 * 
	 * @param w New width
	*/
	public void setWidth(int w) {
		this.width = w;
	}
	
	/**  Returns height.
	 * 
	 * @return The current height
	*/
	public int getHeight() {
		return this.height;
	}
	
	/** Returns Width.
	 * 
	 * @return the current width
	*/
	public int getWidth() {
		return this.width;
	}
}
