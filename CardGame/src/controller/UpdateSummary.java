package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.Summary;
import view.Toolbar;

public class UpdateSummary implements ActionListener {

	private GameEngine gameEngine;
	private Summary summary;
	private Toolbar toolbar;
	private Collection<Player> allPlayers = new ArrayList<Player>();
	private Player selectedPlayer;

	public UpdateSummary(GameEngine gameEngine, Summary summary, Toolbar toolbar) {

		this.gameEngine = gameEngine;
		this.summary = summary;
		this.toolbar = toolbar;
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// get all players from the game engine
		allPlayers = gameEngine.getAllPlayers();

		// loop through until player matches the combo box player
		for (Player updatedPlayer : allPlayers) {
			if (updatedPlayer.getPlayerName() == toolbar.getPlayerSelect().toString()) {
				System.out.println(updatedPlayer.getPlayerId());
				// update the summary panel
				summary.setPlayerId(updatedPlayer);
				summary.setPlayerName(updatedPlayer);
				summary.setPlayerPoints(updatedPlayer);
			}
		}

	}

	public Player getSelectedPlayer() { return selectedPlayer; }

}
