package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import model.interfaces.GameEngine;

public class StatusBar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel status;

	public StatusBar(GameEngine gameEngine, AppFrame appFrame) {

		status = new JLabel("  Idle", SwingConstants.LEFT);
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		status.setBorder(border);
		setLayout(new GridLayout(1, 1));
		add(status);
	
	}
	
	public void setStatusDealing() {
		this.status.setText("  Dealing");
	}

	public JLabel getStatus() {
		return status;
	}

	public void setStatus(JLabel status) {
		this.status = status;
	}

}
