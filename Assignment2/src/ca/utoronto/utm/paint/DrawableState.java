package ca.utoronto.utm.paint;

import ca.utoronto.utm.shapes.Drawable;

public class DrawableState {
	private Drawable previous;
	private Drawable current;
	private String tag;
	private PaintModel model;
	public DrawableState(String type) {
		this.tag = type;
	}
	public DrawableState(Drawable previous, Drawable current,String type) {
		this.previous=previous;
		this.current=current;
		this.tag=type;
	}
	public void setModel(PaintModel m) {
		this.model=m;
	}
	public void setPrevious(Drawable p) {
		this.previous=p;
	}
	public void setCurrent(Drawable c) {
		this.current=c;
	}
	
}
