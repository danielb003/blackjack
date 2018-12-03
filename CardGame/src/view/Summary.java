package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.UpdateSummary;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class Summary extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel title, id, name, points;

	public Summary(GameEngine gameEngine, AppFrame appFrame) {

		setLayout(new GridLayout(14, 1, 0, 0));
		setPreferredSize(new Dimension(150, 40));

		// create labels
		title = new JLabel("Summary");
		id = new JLabel("ID: 0");
		name = new JLabel("Name: Player");
		points = new JLabel("Points: 0");

		// align labels
		title.setHorizontalAlignment(JLabel.CENTER);
		id.setHorizontalAlignment(JLabel.CENTER);
		name.setHorizontalAlignment(JLabel.CENTER);
		points.setHorizontalAlignment(JLabel.CENTER);

		// create font set it to title
		Font font = new Font("Al Bayan", Font.BOLD, 15);
		title.setFont(font);

		add(title);
		add(id);
		add(name);
		add(points);

		// listener to update the summary panel
		appFrame.toolbar.playerSelect.addActionListener(new UpdateSummary(gameEngine, this, appFrame.toolbar));

	}

	public void setPlayerId(Player player) {
		this.id.setText("ID: " + player.getPlayerId());
	}

	public void setPlayerName(Player player) {
		this.name.setText("Name: " + player.getPlayerName());
	}

	public void setPlayerPoints(Player player) {
		if (player.getBet() == 0) {
			String updatedPoints = Integer.toString(player.getPoints());
			this.points.setText("Points: " + updatedPoints);
		} else if (player.getBet() > 0) {
			String updatedPoints = Integer.toString(player.getPoints() - player.getBet());
			this.points.setText("Points: " + updatedPoints);
		}
	}

	public void updatePlayerPoints(int betUpdate) {
		this.points.setText("Points: " + betUpdate);
	}

}
