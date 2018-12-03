package controller;

import model.interfaces.GameEngine;
import view.DialogHelper;
import view.Toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.SimplePlayer;

public class AddPlayer implements ActionListener {

	private GameEngine gameEngine;
	private Toolbar toolbar;

	public AddPlayer(GameEngine gameEngine, Toolbar toolbar) {

		this.gameEngine = gameEngine;
		this.toolbar = toolbar;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// game accepts a maximum of 6 players
		if (toolbar.getPlayerCount() < 6) {
			DialogHelper dialogHelper = new DialogHelper(toolbar.getPlayerCount() + 1);

			// add new player if dialog helper is valid
			if (!dialogHelper.getValidation()) {
				SimplePlayer newPlayer = dialogHelper.getPlayer();
				gameEngine.addPlayer(newPlayer);
				toolbar.addNameDropdown(newPlayer);

				toolbar.increasePlayerCount();
			} else {
				JOptionPane.showMessageDialog(null, "Add player cancelled", "Operation Cancelled",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} else {
			JOptionPane.showMessageDialog(null, "Max players (6) reached. New player can't be added.", "Add Player Error",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

}
