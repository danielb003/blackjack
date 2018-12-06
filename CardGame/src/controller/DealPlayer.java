package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.CardPanel;
import view.Toolbar;

public class DealPlayer extends Thread implements ActionListener {
	
	private GameEngine gameEngine;
	private CardPanel cardPanel;
	private Toolbar toolbar;
	private Collection<Player> allPlayers = new ArrayList<Player>();
	private int playersLeft;

	public DealPlayer(GameEngine gameEngine, CardPanel cardPanel, Toolbar toolbar) {
		
		this.gameEngine = gameEngine;
		this.cardPanel = cardPanel;
		this.toolbar = toolbar;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		new Thread() {
			public void run() {

				// count the amount of players
				toolbar.getPlayerCount();
		
				// get all players from the game engine
				allPlayers = gameEngine.getAllPlayers();
				
				// loop through until player matches the combo box player
				for (Player currentPlayer : allPlayers) {
					if (currentPlayer.getPlayerName() == toolbar.getPlayerSelect().toString()) {
						if(cardPanel.getFirstDeal()) {
							playersLeft = toolbar.getPlayerCount() - 1;
							cardPanel.setFirstDeal(false);
						}
						
						if(playersLeft == 0) {
							gameEngine.dealPlayer(currentPlayer, 1);
							currentPlayer.setBetCollected(false);
							cardPanel.setDeal(false);
							cardPanel.resetBetAmount();
							
							JOptionPane.showMessageDialog(null, "House dealing...", "Deal Notification",
									JOptionPane.INFORMATION_MESSAGE);
							
							cardPanel.setHouseActiveLabel(true);
							cardPanel.setBetTotalColorBlue();
							cardPanel.setBetTotal(0);
							gameEngine.dealHouse(1);
							cardPanel.setNewRound(true);
							cardPanel.setFirstDeal(true);
							
							for(Player player : allPlayers) {
								player.setPlayerDealt(false);
								player.setResult(0);
							}
							
						} else if(playersLeft > 0) {
							gameEngine.dealPlayer(currentPlayer, 1);
							currentPlayer.setPlayerDealt(true);
							currentPlayer.setBetCollected(false);
							cardPanel.setDeal(false);
							cardPanel.resetBetAmount();
							playersLeft--;
						}
						
					}
					
				}
				
			}
			
		}.start();
		
	}

}
