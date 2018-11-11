package ca.utoronto.utm.paint;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;

public class PropertyInvoker {
	private ArrayList<DrawingCommands> commands;
	public PropertyInvoker()
	{
		this.commands = new ArrayList<DrawingCommands>();
	}
	public void acceptCommand(DrawingCommands command)
	{
		this.commands.add(command);
	}
	public void applyCommands(Drawable d)
	{
		for(DrawingCommands command: this.commands)
		{
			command.executeChange(d);
		}
		this.commands.clear();
	}
}
