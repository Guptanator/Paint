package ca.utoronto.utm.tabPanel;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import ca.utoronto.utm.paint.PaintModel;
import javafx.scene.Scene;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class CanvasPopup extends JFrame {
	
	private JButton ok = new JButton("OK");
	private JButton cancel = new JButton("CANCEL");
	private JPanel panel;
	
	public CanvasPopup() {
		this.setVisible(true);
		this.setSize(300, 300);;
		panel = new JPanel();
		panel.add(ok);
		panel.add(cancel);
		this.add(panel);
		
	}

	GraphicsDevice gdevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	int width = gdevice.getDisplayMode().getWidth();
	int height = gdevice.getDisplayMode().getHeight();
	
}
