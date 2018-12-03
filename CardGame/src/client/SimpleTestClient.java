package client;

import java.util.Deque;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import model.interfaces.PlayingCard;
import view.AppFrame;
import view.GameEngineCallbackImpl;

import validate.Validator;

/**
 * Simple console client for SADI assignment 1, 2018 NOTE: This code will not
 * compile until you have implemented code for the supplied interfaces!
 * 
 * You must be able to compile your code WITHOUT modifying this class.
 * Additional testing should be done by copying and adding to this class while
 * ensuring this class still works.
 * 
 * The provided Validator.jar will check if your code adheres to the specified
 * interfaces!
 * 
 * @author Daniel Bellino
 * 
 */
public class SimpleTestClient {
	private static Logger logger = Logger.getLogger("assignment1");

	public static void main(String args[]) {
		final GameEngine gameEngine = new GameEngineImpl();

		// call method in Validator.jar to test *structural* correctness
		// just passing this does not mean it actually works .. you need to test
		// yourself!
		// pass false if you want to disable logging .. (i.e. once it passes)
		// Validator.validate(false);

		// create two test players
		/*
		 * Player[] players = new Player[] { new SimplePlayer("1", "Patrick Cripps",
		 * 1000), new SimplePlayer("2", "Charlie Curnow", 500) };
		 */
		// Player[] players = new Player[10];

		// add logging callback
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());

		// Uncomment this to DEBUG your deck of cards creation
		Deque<PlayingCard> shuffledDeck = gameEngine.getShuffledDeck();
		printCards(shuffledDeck);

		// SwingUtilities.invokeLater(() -> new AppFrame(gameEngine));

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new AppFrame(gameEngine);
			}
		});

		// main loop to add players, place a bet and receive hand
		/*
		 * for (Player player : players) { gameEngine.addPlayer(player);
		 * gameEngine.placeBet(player, 100); gameEngine.dealPlayer(player, 1); }
		 */

		// all players have played so now house deals
		// GameEngineCallBack.houseResult() is called to log all players (after results
		// are calculated)
		// gameEngine.dealHouse(10);
	}

	private static void printCards(Deque<PlayingCard> deck) {
		for (PlayingCard card : deck)
			logger.log(Level.INFO, card.toString());

		System.out.println();
	}
}
