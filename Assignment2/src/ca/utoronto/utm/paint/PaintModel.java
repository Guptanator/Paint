package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Stack;

public class PaintModel extends Observable {

	private Stack<Drawable> allObjects = new Stack<Drawable>();
	
	public void addDrawable(Drawable d) {
		this.allObjects.push(d);
		this.setChanged();
		this.notifyObservers();
	}

	public Stack<Drawable> getObjects() {
		Stack<Drawable> newStack = (Stack<Drawable>)this.allObjects.clone();
		return newStack;
	}
}
