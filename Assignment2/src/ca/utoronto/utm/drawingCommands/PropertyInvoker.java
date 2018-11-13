package ca.utoronto.utm.drawingCommands;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import ca.utoronto.utm.shapes.Drawable;

/**
 * Class for storing and executing drawing commands to apply
 * to shapes.
 *
 * @author Daniel Lee
 */
public class PropertyInvoker {
	private ArrayList<DrawingCommands> commands;
	/** Constructor for PropertyInvoker, which all it does is 
	 * set a new list of commands.
	*/
	public PropertyInvoker()
	{
		this.commands = new ArrayList<DrawingCommands>();
	}
	/** Method for storing drawing commands to apply to new shapes.
	 * @param command DrawingCommands that dictate a certain change
	 * that is going to be made to be applied to new shapes.
	*/
	public void acceptCommand(DrawingCommands command)
	{
		this.commands.add(command);
	}
	/** Method for applying all stored changes to the given drawable d,
	 * after the list of commands is cleared to prevent list size overflow.
	 * @param d Drawable that the stored changes will be applied to.
	*/
	public void applyCommands(GraphicsContext g)
	{
		for(DrawingCommands command: this.commands)
		{
			command.executeChange(g);
		}
		this.commands.clear();
	}
}
