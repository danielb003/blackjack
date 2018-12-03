package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.CardPanel;
import view.Toolbar;

public class UpdateCardPanel implements ActionListener {

	private GameEngine gameEngine;
	private CardPanel cardPanel;
	private Toolbar toolbar;
	private Collection<Player> allPlayers = new ArrayList<Player>();

	public UpdateCardPanel(GameEngine gameEngine, CardPanel cardPanel, Toolbar toolbar) {

		this.gameEngine = gameEngine;
		this.cardPanel = cardPanel;
		this.toolbar = toolbar;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// get all players from the game engine
		allPlayers = gameEngine.getAllPlayers();

		// loop through until player matches the combo box player
		for (Player updatedPlayer : allPlayers) {
			if (updatedPlayer.getPlayerName() == toolbar.getPlayerSelect().toString()) {
				// update the card panel
				if(cardPanel.getNewRound()) {
					cardPanel.setHouseActiveLabel(false);
					cardPanel.setBetTotalColorBlack();
				}
				
				if (updatedPlayer.getBet() == 0 && !updatedPlayer.getBetCollected()) {
					cardPanel.setBet(true);
					cardPanel.setDeal(false);
					cardPanel.setBetTotal(updatedPlayer.getResult());
					cardPanel.setNewRound(false);
				} else if(updatedPlayer.getBet() > 0 && updatedPlayer.getPlayerDealt()) {
					cardPanel.setBet(false);
					cardPanel.setDeal(false);
					cardPanel.setBetTotal(updatedPlayer.getResult());
				} else if (updatedPlayer.getBet() > 0 && !updatedPlayer.getPlayerDealt()) {
					cardPanel.setDeal(true);
					cardPanel.setBet(false);
					cardPanel.setBetTotal(updatedPlayer.getResult());
				}

				cardPanel.setBetInput();
				cardPanel.setBetAmount(updatedPlayer.getBet());
			
			}
			
		}

	}

}
