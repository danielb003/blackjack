package model;

import model.interfaces.Player;

public class SimplePlayer implements Player {

	private String playerID, playerName;
	private int initialPoints, playerBet, result;
	private boolean playerDealt = false, betCollected = false;

	public SimplePlayer(String playerID, String playerName, int initialPoints) {
		this.playerID = playerID;
		this.playerName = playerName;
		this.initialPoints = initialPoints;
	}

	@Override
	public String getPlayerName() { return playerName; }

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public int getPoints() { return initialPoints; }

	@Override
	public void setPoints(int points) {
		this.initialPoints = points;
	}

	@Override
	public String getPlayerId() { return playerID; }

	@Override
	public boolean placeBet(int bet) {
		if (bet <= initialPoints && bet > 0) {
			playerBet = bet;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getBet() { return playerBet; }

	@Override
	public void resetBet() {
		this.playerBet = 0;
	}

	@Override
	public int getResult() { return result; }

	@Override
	public void setResult(int result) {
		this.result = result;
	}
	
	@Override
	public boolean getPlayerDealt() { return playerDealt; }
	
	@Override
	public void setPlayerDealt(boolean playerDealt) {
		this.playerDealt = playerDealt;
	}
	
	@Override
	public boolean getBetCollected() { return betCollected; }
	
	@Override
	public void setBetCollected(boolean betCollected) {
		this.betCollected = betCollected;
	}
	
	@Override
	public String toString() {
		return this.getPlayerName();
	}

}
