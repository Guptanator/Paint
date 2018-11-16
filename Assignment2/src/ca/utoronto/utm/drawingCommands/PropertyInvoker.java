package ca.utoronto.utm.drawingCommands;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import ca.utoronto.utm.shapes.Drawable;

/** Class for storing and executing drawing commands to apply
 * to shapes.
 *
 * @author Daniel Lee
 */
public class PropertyInvoker {
	
	private ArrayList<DrawingCommands> commands;
	
	/** Constructor for PropertyInvoker. 
	 * Sets a new list of commands.
	*/
	public PropertyInvoker() {
		this.commands = new ArrayList<DrawingCommands>();
	}
	
	/** Method for storing drawing commands to apply to new shapes.
	 * 
	 * @param command DrawingCommands that dictates a certain change
	 * that is going to be made to be applied to new shapes.
	*/
	public void acceptCommand(DrawingCommands command) {
		this.commands.add(command);
	}
	
	/** Method for applying all stored changes to the Drawables.
	 * 
	 * @param g GraphicsContext to which commands will be applied
	*/
	public void applyCommands(GraphicsContext g) {
		for(DrawingCommands command: this.commands) {
			command.executeChange(g);
		}
	}
	
	/** Finds the topmost color command and returns the it's color.
	 * 
	 * @return Color the topmost color in the stack. If there
	 * are no colors, it returns Black.
	*/
	public Color findColor() {
		for (int i=(this.commands.size())-1; i>=0;i=i-1) {
			if (this.commands.get(i).type()=="color") {
				return ((ColorCommand)(this.commands.get(i))).getColor();
			}
		}
		return Color.BLACK;
	}
	
	/** Finds the topmost thickness command and returns the it's value.
	 * 
	 * @return The topmost thickness attribute as a double. 
	 * If none are found, returns Null.
	*/
	public Double findThickness() {
		for (int i=(this.commands.size())-1; i>=0;i=i-1) {
			if (this.commands.get(i).type()=="thick") {
				return ((ThicknessCommand)(this.commands.get(i))).getThickness();
			}
		}
		return null;
	}
}
