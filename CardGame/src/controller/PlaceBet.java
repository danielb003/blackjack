package controller;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.CardPanel;
import view.Toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

public class PlaceBet implements ActionListener {

	private GameEngine gameEngine;
	private CardPanel cardPanel;
	private Toolbar toolbar;
	private boolean valid = false;
	private Collection<Player> allPlayers = new ArrayList<Player>();

	public PlaceBet(GameEngine gameEngine, CardPanel cardPanel, Toolbar toolbar) {

		this.gameEngine = gameEngine;
		this.cardPanel = cardPanel;
		this.toolbar = toolbar;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		while (!valid) {

			String inputCheck = cardPanel.getBetInput();

			if (inputCheck != "") {

				try {

					int betAmount = Integer.parseInt(cardPanel.getBetInput());

					if (betAmount <= 0) {
						JOptionPane.showMessageDialog(null, "Bet must be more than 0", "Bet Error",
								JOptionPane.INFORMATION_MESSAGE);
						break;
					} else {
						valid = true;

						// get all players from the game engine
						allPlayers = gameEngine.getAllPlayers();

						if (!allPlayers.isEmpty()) {
							// loop through until player matches the combo box player
							for (Player currentPlayer : allPlayers) {
								if (currentPlayer.getPlayerName() == toolbar.getPlayerSelect().toString()
										&& currentPlayer.getPoints() >= betAmount) {
									// place bet, enable deal button and disable bet button
									gameEngine.placeBet(currentPlayer, betAmount);
									currentPlayer.setBetCollected(true);
									cardPanel.setDeal(true);
									cardPanel.setBet(false);
									cardPanel.setBetAmount(betAmount);
								} else if (currentPlayer.getPlayerName() == toolbar.getPlayerSelect().toString()
										&& currentPlayer.getPoints() < betAmount) {
									JOptionPane.showMessageDialog(null, "Not enough points to place bet", "Bet Error",
											JOptionPane.INFORMATION_MESSAGE);
									continue;
								}

							}

						} else {
							JOptionPane.showMessageDialog(null, "No Player Selected", "Bet Error",
									JOptionPane.INFORMATION_MESSAGE);
						}

					}

				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Enter an integer only", "Input Error",
							JOptionPane.INFORMATION_MESSAGE);
					break;
				}

			} else {
				JOptionPane.showMessageDialog(null, "No input detected", "Input Error", JOptionPane.INFORMATION_MESSAGE);
				break;
			}

		}

		valid = false;

	}

}
