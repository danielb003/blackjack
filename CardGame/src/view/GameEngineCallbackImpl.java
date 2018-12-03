package view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton/Partial example implementation of GameEngineCallback showing Java
 * logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback {
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	ConsoleHandler handler = new ConsoleHandler();
	Collection<Player> gamePlayer = new ArrayList<Player>();

	public GameEngineCallbackImpl() {
		// FINE shows dealing output, INFO only shows result
		logger.setLevel(Level.FINE);
		handler.setLevel(Level.FINE);
		logger.addHandler(handler);
		logger.setUseParentHandlers(false);
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		logger.log(Level.FINE, String.format("Card Dealt to %s .. %s", player.getPlayerName(), card.toString()));
	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		logger.log(Level.INFO, String.format("%s, final result = %s\n", player.getPlayerName(), result));
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		logger.log(Level.FINE,
				String.format("Card Dealt to %s .. %s ... YOU BUSTED!", player.getPlayerName(), card.toString()));
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		logger.log(Level.FINE, String.format("Card Dealt to the House .. %s", card.toString()));
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		logger.log(Level.FINE, String.format("Card Dealt to the House .. %s ... HOUSE BUSTED!", card.toString()));
	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		logger.log(Level.INFO, String.format("The House, final result = %s\n", result));

		// get all the players and store them in a new collection
		gamePlayer = engine.getAllPlayers();

		// string to use for the logging
		String playerResults = "";
		// loop through all the players in the new collection
		// and convert their value into a string. Add newline.
		for (Player p : gamePlayer) {
			String part = String.valueOf(p);
			part += "\n";
			playerResults += part;
		}

		logger.log(Level.INFO, String.format("Final player results\n%s\n", playerResults));
	}

}
