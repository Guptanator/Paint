package ca.utoronto.utm.paint;

import ca.utoronto.utm.shapes.Drawable;
import ca.utoronto.utm.shapes.Point;
import ca.utoronto.utm.shapes.Rectangle;
import ca.utoronto.utm.shapes.Circle;
import ca.utoronto.utm.shapes.Square;

public class DrawableState {
	private Drawable previous;
	private Drawable current;
	private String tag;
	private PaintModel model;
	private Point previousMove;
	
	/** 
	 * This constructor allow you to create the DrawableState
	 * with only a string and then you can define the other attributes
	 * after initialization
	 *  
	 * @param String type the type of operation which is state change represents
	*/
	public DrawableState(String type) {
		this.tag = type;
	}
	/** 
	 * This constructor allow you to create the DrawableState
	 * with all parameters initially defined
	 *  
	 * @param String type the type of operation which is state change represents
	 * @param Drawable previous the previous state of the drawable
	 * @param Drawable current the current state of the drawable
	*/
	public DrawableState(Drawable previous, Drawable current,String type) {
		this.tag=type;
		this.setPrevious(previous);
		this.setCurrent(current);
	}
	/** 
	 * This allows you to the set the model attribute of the DrawableState
	 * when you need to rather than creating a connection by default
	 *  
	 * @param PaintModel m the current PaintModel
	*/
	public void setModel(PaintModel m) {
		this.model=m;
	}
	/** 
	 * This functions allows you to set what the previous iteration of the 
	 * drawable looked like so that you can transform the current version
	 * when required.
	 *  
	 * @param Drawable p the previous state
	*/
	public void setPrevious(Drawable p) {
		if (this.tag=="move") {
			if (p.type()=="Circle") {
				this.previousMove = ((Circle)(p)).getCentre().copy();
			}
			if (p.type()=="Rectangle") {
				this.previousMove = ((Rectangle)(p)).getCorner().copy();
			}
			if (p.type()=="Square") {
				this.previousMove = ((Square)(p)).getCorner().copy();
			}
		}
		this.previous=p;
	}
	/** 
	 * This functions allows you to set what the current iteration of the 
	 * drawable looks like so that you can transform to the previous version
	 * when required
	 *  
	 * @param Drawable c the current state
	*/
	public void setCurrent(Drawable c) {
		this.current=c;
	}
	/** 
	 * This function manages all undo functionality based on the other parameters
	*/
	public void execute() {
		System.out.println(this.tag);
		if (this.tag=="add") {
			this.model.removeObject(this.current);
		}
		else if (this.tag=="move") {
			Point temp=null;
			if (this.current.type()=="Circle") {
				temp = ((Circle)this.current).getCentre().copy();
				((Circle)this.current).setCentre(this.previousMove);
			}
			else if (this.current.type()=="Rectangle") {
				temp = ((Rectangle)this.current).getCorner().copy();
				((Rectangle)(this.current)).setCorner(this.previousMove);
			}
			else if (this.current.type()=="Square") {
				temp = ((Square)this.current).getCorner().copy();
				((Square)(this.current)).setCorner(this.previousMove);
			}
			this.previousMove=temp;
		}
		this.model.update();
	}
	/** 
	 * This function manages all redo functionality based on the other parameters
	*/
	public void executeRedo() {
		System.out.println(this.tag);
		if (this.tag=="add") {
			this.model.addDrawable(this.current);
		}
		else if (this.tag=="move") {
			if (this.current.type()=="Circle") {
				((Circle)this.current).setCentre(this.previousMove);
			}
			else if (this.current.type()=="Rectangle") {
				((Rectangle)(this.current)).setCorner(this.previousMove);
			}
			else if (this.current.type()=="Square") {
				((Square)(this.current)).setCorner(this.previousMove);
			}
		}
		this.model.update();
	}
}
