package ca.utoronto.utm.paint;

import java.awt.Color;

import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionListener;
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
import javafx.stage.Stage;

public class PaintModel extends Observable {

	private LinkedList<Drawable> allObjects = new LinkedList<Drawable>();
	private LinkedList<Drawable> undone = new LinkedList<Drawable>();
	private int current = 0;
	private boolean fill = false;
	private Color color = new Color(0, 0, 0);
	
	public void addDrawable(Drawable d) {
		d.setColor(color);
		this.allObjects.addLast(d);
		d.toFill(fill);
		this.allObjects.push(d);
		this.setChanged();
		this.notifyObservers();
	}
	public Color getColor()
	{
		return color;
	}
	public boolean IwillFill()
	{
		return this.fill;
	}
	public void shouldFill()
	{
		if(this.fill)
		{
			this.fill = false;
		}
		else
		{
			this.fill = true;
		}
	}
	public void Undo() {
		if (!allObjects.isEmpty()) {
			this.undone.addFirst(allObjects.removeLast());
			this.setChanged();
			this.notifyObservers();
		}
	}
	public void Redo() {
		if (!undone.isEmpty()) {
			this.allObjects.addLast(undone.removeFirst());
			this.setChanged();
			this.notifyObservers();
		}
	}

	public LinkedList<Drawable> getObjects() {
		LinkedList<Drawable> newList = (LinkedList<Drawable>)this.allObjects.clone();
		return newList;
	}
	
	public int getCurrent() {
		return this.current;
	}
	
	public void setCurrent(int current) {
		this.current = current;
		this.setChanged();
		this.notifyObservers();
	}
	
	public void createColorWindow(String colorFor)
	{
		JFrame colorFrame = new JFrame("Choose Color!");
		JButton choose_color = new JButton("Choose this color!");
		JSlider red = new JSlider(0, 255);
		JSlider green = new JSlider(0, 255);
		JSlider blue = new JSlider(0, 255);
		choose_color.setBackground(new Color(red.getValue(), green.getValue(), blue.getValue()));
		colorFrame.getContentPane().add(choose_color);
		ChangeListener listener = new ChangeListener()
	    {
			public void stateChanged(ChangeEvent event)
			{
				choose_color.setBackground(new Color(red.getValue(), green.getValue(), blue.getValue()));
		    	colorFrame.getContentPane().add(choose_color);
			}
	    };
		red.addChangeListener(listener);
		TextField textred = new TextField("Red value");
		textred.setEnabled(false);
		colorFrame.getContentPane().add(textred);
		colorFrame.getContentPane().add(red);
		green.addChangeListener(listener);
		TextField textgreen = new TextField("Green value");
		textgreen.setEnabled(false);
		colorFrame.getContentPane().add(textgreen);
		colorFrame.getContentPane().add(green);
		blue.addChangeListener(listener);
		TextField textblue = new TextField("Blue value");
		textblue.setEnabled(false);
		colorFrame.getContentPane().add(textblue);
		colorFrame.getContentPane().add(blue);
		colorFrame.setLayout(new GridLayout(7,16));
		colorFrame.pack();
		choose_color.addActionListener(new ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent e)
			{
				if (colorFor.equals("line"))
				{
					color = new Color(red.getValue(), green.getValue(), blue.getValue());
				}
				colorFrame.dispose();
			}
		});
		colorFrame.getContentPane().add(choose_color);
		colorFrame.pack();
		colorFrame.setVisible(true);
	}
	public void update() {
		this.setChanged();
		this.notifyObservers();
	}
}
