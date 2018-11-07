package ca.utoronto.utm.tabPanel;

import ca.utoronto.utm.paint.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class tabButtonHandler implements EventHandler<ActionEvent> {
	private TabPanelParent tpp;
	private ShapeChooserPanel shapePanel;
	
	
	
	public tabButtonHandler(TabPanelParent parent, View currentView) {
		this.tpp=parent;
		shapePanel = new ShapeChooserPanel(currentView);
		
	}
	
	@Override
	public void handle(ActionEvent event) {
		TabChooserButton source = (TabChooserButton)(event.getSource());
		this.tpp.clearAllButtons(source);
		String type = source.getType();
		if (type=="shape") {
			if (source.isSelected()) {
				this.tpp.updateCurrentTab(shapePanel);
			}
			else {
				this.tpp.removeChild(shapePanel);
			}
		}
		if (type=="color") {
			//this.tpp.updateCurrentTab(null);
					
		}
		if (type=="thick") {
			//this.tpp.updateCurrentTab(null);
		}
	}
}
