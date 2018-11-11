package shapes;

import javafx.scene.input.MouseEvent;

public class MoveShapeStrategy extends TransformStrategy{

	private Drawable currentShape;
	double deltaX;
	double deltaY;
	@Override
	public void handleMouse(MouseEvent e) {
		if (e.getEventType() == MouseEvent.MOUSE_PRESSED) {
			currentShape = this.findElement(e);
			if (currentShape!=null)PrepareDeltas(e);
		} else if (e.getEventType() == MouseEvent.MOUSE_DRAGGED) {
			MoveDrawable(e);
		}
	}
	public void PrepareDeltas(MouseEvent e) {
		this.deltaX = currentShape.xDifferent(e.getX());
		this.deltaY = currentShape.yDifferent(e.getY());
	}
	public void MoveDrawable(MouseEvent e) {
		if (currentShape==null) {
		}
		else if (currentShape.type()=="Circle") {
			Circle c = ((Circle)(currentShape));
			c.setCentre(new Point((int) (e.getX()+deltaX), (int) (e.getY()+deltaY)));
		}
		else if (currentShape.type()=="Rectangle") {
			Rectangle r = ((Rectangle)(currentShape));
			r.setCorner(new Point((int) (e.getX()+deltaX), (int) (e.getY()+deltaY)));
		}
		else if (currentShape.type()=="Square") {
			Square s = ((Square)(currentShape));
			s.setCorner(new Point((int) (e.getX()+deltaX), (int) (e.getY()+deltaY)));
		}
		this.model.update();
	}
}
