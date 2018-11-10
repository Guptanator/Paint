package ca.utoronto.utm.paint;

import java.util.ArrayList;

public class PropertyInvoker {
	private PaintPanel paintPanel;
	private ArrayList<DrawingCommands> commands = new ArrayList<DrawingCommands>();
	public void acceptCommand(DrawingCommands command)
	{
		this.commands.add(command);
	}
	public void applyCommands(PaintPanel panel)
	{
		this.paintPanel = panel;
		for(DrawingCommands command: this.commands)
		{
			command.executeChange(this.paintPanel);
		}
		commands.clear();
	}
}
