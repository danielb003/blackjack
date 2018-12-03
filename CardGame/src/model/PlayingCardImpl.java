package model;

import java.util.Random;

import model.interfaces.PlayingCard;

public class PlayingCardImpl implements PlayingCard {

	Random random = new Random();
	private Suit suit;
	private Value value;
	private int score;

	public PlayingCardImpl() {
		setCard();
	}

	@Override
	public Suit getSuit() { return suit; }

	@Override
	public Value getValue() { return value; }

	@Override
	public int getScore() { return score; }

	@Override
	public String toString() {
		return "Suit: " + this.getSuit() + ", Value: " + this.getValue() + ", Score: " + this.getScore();
	}

	@Override
	public boolean equals(PlayingCard card) {
		// TODO Auto-generated method stub
		return false;
	}

	private void setCard() {
		int suitHigh = 3, valueHigh = 12, low = 0, i = 0, j = 0, selectedSuit, selectedValue;

		selectedSuit = random.nextInt(suitHigh + 1 - low) + low;

		for (Suit s : Suit.values()) {
			if (selectedSuit == i) {
				suit = s;
			}
			i++;
		}

		selectedValue = random.nextInt(valueHigh + 1 - low) + low;

		for (Value v : Value.values()) {
			if (selectedValue == j) {
				value = v;
				break;
			}
			j++;
		}

		for (int k = 0; k <= selectedValue; k++) {
			if (k == 0) {
				score = 1;
			} else if (k < 10 && k > 0) {
				score = k + 1;
			} else if (k >= 11) {
				score = 10;
			}
		}
	}
}
