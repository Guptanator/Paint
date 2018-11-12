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
 * of drawable objects, as well as handles the setting of
 * drawing commands.
 */
public class PaintModel extends Observable {

	private ArrayList<Drawable> allObjects = new ArrayList<Drawable>();
	private LinkedList<Drawable> undone = new LinkedList<Drawable>();
	private int current = 0;
	private boolean fill;
	private PropertyInvoker propertyInvoker = new PropertyInvoker();
	private ColorCommand colorCommand = new ColorCommand(Color.BLACK);
	private ThicknessCommand thicknessCommand = new ThicknessCommand(1.0);
	private FillCommand fillCommand = new FillCommand(false);
	
	/** Handles setting the properties to d and adding it
	 * to the list of drawable objects (allObjects) to draw on canvas.
	 * @param d Drawable that will be added to the canvas.
	*/
	public void addDrawable(Drawable d) {
		this.propertyInvoker.acceptCommand(fillCommand);
		this.propertyInvoker.acceptCommand(colorCommand);
		this.propertyInvoker.acceptCommand(thicknessCommand);
		this.propertyInvoker.applyCommands(d);
		this.allObjects.add(d);
		this.update();
	}
	/** Creates the FillCommand which defines whether the next drawable objects
	 * will be filled in or not.
	*/
	public void setFill() {
		if(this.fill)
		{
			this.fillCommand = new FillCommand(false);
			this.fill = false;
		}
		else
		{
			this.fillCommand = new FillCommand(true);
			this.fill = true;
		}
	}
	/** Returns the PaintModel's boolean fill value.
	*/
	public boolean getFill()
	{
		return this.fill;
	}
	/** Creates the ColorCommand which defines the color to be set to the
	 * next drawable objects.
	 * @param color Color to be set to the next drawable objects.
	*/
	public void setColor(Color color)
	{
		this.colorCommand = new ColorCommand(color);
	}
	/** Creates the ThicknessCommand which defines the line thickness value
	 *  to be set to the next drawable objects.
	 * @param t String that dictates certain line thickness values,
	 * which can be either: Thin, Normal, or Thick.
	*/
	public void setThickness(String t)
	{
		if (t == "Thin") {
			this.thicknessCommand = new ThicknessCommand(1.0);
		} else if (t == "Normal") {
			this.thicknessCommand = new ThicknessCommand(5.0);
		} else if (t == "Thick") {
			this.thicknessCommand = new ThicknessCommand(10.0);
		}
		else {
			this.thicknessCommand = new ThicknessCommand(1.0);
		}
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
	
	public void update() {
		this.setChanged();
		this.notifyObservers();
	}
	
	public void removeObject(Drawable d) {
		int i=this.allObjects.indexOf(d);
		if (i>=0) {
			this.allObjects.remove(i);
			this.update();
		}
	}
}
