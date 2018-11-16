package ca.utoronto.utm.drawingCommands;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
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
		//this.commands.clear();
	}
	/** 
	 * This finds the topmost color command and returns the it's color,
	 * this is important since it drawn as the color
	 * @return Color the topmost color in the stack
	*/
	public Color findColor() {
		for (int i=(this.commands.size())-1; i>=0;i=i-1) {
			if (this.commands.get(i).type()=="color") {
				return ((ColorCommand)(this.commands.get(i))).getColor();
			}
		}
		return Color.BLACK;
	}
	/** 
	 * This finds the topmost thickness command and returns the it's value,
	 * this is important since it drawn as the thickness
	 * @return Double the topmost thickness attribute
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
