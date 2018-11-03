package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Stack;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PaintModel extends Observable {

	private Stack<Drawable> allObjects = new Stack<Drawable>();
	private LinkedList<Drawable> undone = new LinkedList<Drawable>();
	private int current = 0;
	
	public void addDrawable(Drawable d) {
		this.allObjects.push(d);
		this.setChanged();
		this.notifyObservers();
	}
	
	public void Undo() {
		this.undone.addFirst(allObjects.pop());
		this.setChanged();
		this.notifyObservers();
	}
	public void Redo() {
		this.allObjects.push(undone.removeFirst());
		this.setChanged();
		this.notifyObservers();
	}

	public Stack<Drawable> getObjects() {
		Stack<Drawable> newStack = (Stack<Drawable>)this.allObjects.clone();
		return newStack;
	}
	
	public int getCurrent() {
		return this.current;
	}
	
	public void setCurrent(int current) {
		this.current = current;
		this.setChanged();
		this.notifyObservers();
	}
	
}
