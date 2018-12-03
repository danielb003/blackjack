package view.factory;

import java.util.HashMap;
import java.util.Map;

public class ImageFactory {
	
	private static final String[] FILE_STRINGS = new String[]
			{ "ace_clubs.png", "ace_diamonds.png", "ace_hearts.png", "ace_spades.png", "two_clubs.png", "two_diamonds.png",
					"two_hearts.png", "two_spades.png", "three_clubs.png", "three_diamonds.png", "three_hearts.png", "three_spades.png",
					"four_clubs.png", "four_diamonds.png", "four_hearts.png", "four_spades.png", "five_clubs.png", "five_diamonds.png",
					"five_hearts.png", "five_spades.png", "six_clubs.png", "six_diamonds.png", "six_hearts.png", "six_spades.png",
					"seven_clubs.png", "seven_diamonds.png", "seven_hearts.png", "seven_spades.png", "eight_clubs.png", "eight_diamonds.png",
					"eight_hearts.png", "eight_spades.png", "nine_clubs.png", "nine_diamonds.png", "nine_hearts.png", "nine_spades.png",
					"ten_clubs.png", "ten_diamonds.png", "ten_hearts.png", "ten_spades.png", "jack_clubs.png", "jack_diamonds.png",
					"jack_hearts.png", "jack_spades.png", "queen_clubs.png", "queen_diamonds.png", "queen_hearts.png", "queen_spades.png",
					"king_clubs.png", "king_diamonds.png", "king_hearts.png", "king_spades.png", "back_red_card.jpg"};
	
	private static Map<Integer, String> imageMap;
	
	public void createImageMap() {
		
		imageMap = new HashMap<Integer, String>();
		
		for(int i=0; i<FILE_STRINGS.length; i++) {
			imageMap.put((i), FILE_STRINGS[i]);
		}
	}

}
