package ca.utoronto.utm.paint;


import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class PaintModel extends Observable {

	private ArrayList<Drawable> allObjects = new ArrayList<Drawable>();
	private LinkedList<Drawable> undone = new LinkedList<Drawable>();
	private int current = 0;
	private boolean fill;
	private LinkedList<PropertyInvoker> properties = new LinkedList<PropertyInvoker>();
	private PropertyInvoker propertyInvoker = new PropertyInvoker();
	private ColorCommand colorCommand = new ColorCommand(Color.BLACK);
	private ThicknessCommand thicknessCommand = new ThicknessCommand(1.0);
	private FillCommand fillCommand = new FillCommand(false);
	
	public void addDrawable(Drawable d) {
		this.propertyInvoker.acceptCommand(fillCommand);
		this.propertyInvoker.acceptCommand(colorCommand);
		this.propertyInvoker.acceptCommand(thicknessCommand);
		this.propertyInvoker.applyCommands(d);
		this.allObjects.add(d);
		this.update();
	}
	public boolean getFill() {
		return this.fill;
	}
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
	public void setColor(Color color)
	{
		this.colorCommand = new ColorCommand(color);
	}
	public void setThickness(String t)
	{
		if (t == "Thin") {
			this.thicknessCommand = new ThicknessCommand(1.0);
		} else if (t == "Normal") {
			this.thicknessCommand = new ThicknessCommand(5.0);
		} else if (t == "Thick") {
		}
		else {
			this.thicknessCommand = new ThicknessCommand(10.0);
		}
	}
	public void Undo() {
		if (!allObjects.isEmpty()) {
			this.undone.addFirst(allObjects.remove(allObjects.size()-1));
			this.update();
		}
	}
	public void Redo() {
		if (!undone.isEmpty()) {
			this.allObjects.add(undone.removeFirst());
			this.update();
		}
	}
	
	public ArrayList<Drawable> getObjects() {
		ArrayList<Drawable> newList = (ArrayList<Drawable>)this.allObjects.clone();
		return newList;
	}
	
	public Drawable removeLast() {
		if (this.allObjects.size() > 0) {
			return this.allObjects.remove(allObjects.size()-1);
		}
		return null;
	}
	
	public int getCurrent() {
		return this.current;
	}
	
	public void setCurrent(int current) {
		this.current = current;
		this.update();
	}
	public void update() {
		this.setChanged();
		this.notifyObservers();
	}
}
