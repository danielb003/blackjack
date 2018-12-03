package model;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;

import model.interfaces.GameEngine;
import view.AppFrame;
import view.GameEngineCallbackGUIImpl;
import view.interfaces.GameEngineCallback;
import view.interfaces.GameEngineCallbackGUI;
/* the above used to be model.interfaces.GameEngineCallback
but i changed it, check with tutor */
import model.interfaces.Player;
import model.interfaces.PlayingCard;

public class GameEngineImpl implements GameEngine {

	private ArrayList<Player> gamePlayer = new ArrayList<Player>();
	private ArrayList<GameEngineCallback> gameEngineCallbacks = new ArrayList<GameEngineCallback>();
	private ArrayList<GameEngineCallbackGUI> gameEngineCallbackGUIs = new ArrayList<GameEngineCallbackGUI>();
	private Deque<String> testDeck = new ArrayDeque<String>();
	private Deque<PlayingCard> deck = new ArrayDeque<PlayingCard>();
	private PlayingCard playingCard = null, newCard = null;
	private AppFrame appFrame;
	private GameEngineCallbackGUIImpl gameEngineCallbackGUI;
	private int bustCounter;

	// Constructor to build a deck at the start of the game
	@SuppressWarnings({ "static-access" })
	public GameEngineImpl() {
		for (int i = 0; i < playingCard.DECK_SIZE; i++) {
			playingCard = new PlayingCardImpl();

			// if card doesn't already exist in the deck, add to the deck
			if (!testDeck.contains(playingCard.toString())) {
				// Original deck debugger
				// System.out.println((i + 1) + " - " + playingCard);
				testDeck.push(playingCard.toString());
				deck.push(playingCard);
			} else {
				i--;
			}
		}
	}

	@Override
	public void dealPlayer(Player player, int delay) {
				
		bustCounter = 0;

		// loop until player has busted
		while (bustCounter <= BUST_LEVEL) {
			// create the timing variables
			final long PERIOD = delay * 1000;
			long startTime = System.currentTimeMillis();

			// deal a new card to the player
			newCard = new PlayingCardImpl();
			newCard = deck.pop();
			testDeck.pop();

			bustCounter += newCard.getScore();

			// if the new card busts the player runs this condition
			if (bustCounter > BUST_LEVEL) {
				bustCounter -= newCard.getScore();

				// log the busted card
				for (GameEngineCallback gecb : gameEngineCallbacks) {
					gecb.bustCard(player, newCard, this);
				}
				
				for (GameEngineCallbackGUI gecgui : gameEngineCallbackGUIs) {
					gecgui.bustCard(getAppFrame(), newCard);
				}

				// log the results
				for (GameEngineCallback gecb : gameEngineCallbacks) {
					gecb.result(player, bustCounter, this);
				}

				player.setResult(bustCounter);

				// delay until delay PERIOD has been used
				clockHelper(startTime, PERIOD);
				
				for (GameEngineCallbackGUI gecgui : gameEngineCallbackGUIs) {
					gecgui.result(getAppFrame());
				}

				break;
			} else {
				for (GameEngineCallback gecb : gameEngineCallbacks) {
					gecb.nextCard(player, newCard, this);
				}
				
				for (GameEngineCallbackGUI gecgui : gameEngineCallbackGUIs) {
					gecgui.nextCard(getAppFrame(), newCard);
				}
			}

			// delay until delay PERIOD has been used
			clockHelper(startTime, PERIOD);
			
		}
		
	}

	@Override
	public void dealHouse(int delay) {
		bustCounter = 0;

		// loop until the house has busted
		while (bustCounter <= BUST_LEVEL) {
			// create the timing variables
			final long PERIOD = delay * 1000;
			long startTime = System.currentTimeMillis();

			// deal a new card to the house
			newCard = new PlayingCardImpl();
			newCard = deck.pop();
			testDeck.pop();

			bustCounter += newCard.getScore();

			// if the new card busts the house runs this condition
			if (bustCounter > BUST_LEVEL) {
				bustCounter -= newCard.getScore();

				// log the busted card
				for (GameEngineCallback gecb : gameEngineCallbacks) {
					gecb.houseBustCard(newCard, this);
				}
				
				for (GameEngineCallbackGUI gecgui : gameEngineCallbackGUIs) {
					gecgui.bustCard(getAppFrame(), newCard);
				}

				// settle the bets and adjust final points
				changeGameState(bustCounter);

				// log the house result
				for (GameEngineCallback gecb : gameEngineCallbacks) {
					gecb.houseResult(bustCounter, this);
				}

				// delay until delay PERIOD has been used
				clockHelper(startTime, PERIOD);
				
				for (GameEngineCallbackGUI gecgui : gameEngineCallbackGUIs) {
					gecgui.result(getAppFrame());
				}
				
				for (Player player : gamePlayer) {
					player.resetBet();
				}

				break;

			} else {
				for (GameEngineCallback gecb : gameEngineCallbacks) {
					gecb.nextHouseCard(newCard, this);
				}
				
				for (GameEngineCallbackGUI gecgui : gameEngineCallbackGUIs) {
					gecgui.nextCard(getAppFrame(), newCard);
				}
			}
			
			// delay until delay PERIOD has been used
			clockHelper(startTime, PERIOD);
			
		}
		
	}

	@Override
	public void addPlayer(Player player) {
		gamePlayer.add(player);
	}

	@Override
	public Player getPlayer(String id) {
		// loop through all the players and return player which matches the method
		// parameter, else return null
		for (Player player : gamePlayer) {
			if (id == player.getPlayerId()) {
				return player;
			}
		}
		return null;
	}

	@Override
	public boolean removePlayer(Player player) {
		return gamePlayer.remove(player);
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		this.gameEngineCallbacks.add(gameEngineCallback);
		this.gameEngineCallbackGUI = new GameEngineCallbackGUIImpl();
		this.gameEngineCallbackGUIs.add(gameEngineCallbackGUI);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		// if game engine callback exists remove it
		if (gameEngineCallbacks.contains(gameEngineCallback)) {
			gameEngineCallbacks.remove(gameEngineCallback);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Collection<Player> getAllPlayers() {
		// loop through players and return them in a collection
		for (Player player : gamePlayer) {
			player.toString();
		}

		if (gamePlayer != null) {
			return gamePlayer;
		} else {
			return null;
		}
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		// if checked method returns true/false this method will do the same
		if (player.placeBet(bet)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Deque<PlayingCard> getShuffledDeck() {
		// loop through the deck and return them in a collection
		for (PlayingCard card : deck) {
			card.toString();
		}
		return deck;
	}

	// custom method to settle the bets
	private void changeGameState(int houseResult) {
		// loop through the players and apply new points
		for (Player player : gamePlayer) {

			if (houseResult < player.getResult()) {
				// win
				int newPoints = player.getPoints() + player.getBet() * 2;
				player.setPoints(newPoints);
			} else if (houseResult > player.getResult()) {
				// loss
				int newPoints = player.getPoints() - player.getBet();
				player.setPoints(newPoints);
			}
		}
	}

	// custom method to count time differences
	private void clockHelper(long startTime, long PERIOD) {
		// loop through while until PERIOD has elapsed
		long endTime = System.currentTimeMillis();

		while (endTime - startTime < PERIOD) {
			endTime = System.currentTimeMillis();
		}
	}
	
	private AppFrame getAppFrame() { return this.appFrame; }
	
	public void setAppFrame(AppFrame appFrame) {
		this.appFrame = appFrame;
	}

}
