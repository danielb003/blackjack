package view;

import javax.swing.JOptionPane;

import model.SimplePlayer;

public class DialogHelper {
	
	private String name;
	private int intPoints, id;
	private boolean cancelOperation = false;
	private SimplePlayer newPlayer;

	public DialogHelper(int playerCount) {
		
		this.name = "";
		this.id = playerCount;

		// handle name input
		while (name.equals("") && !cancelOperation) {

			name = JOptionPane.showInputDialog(null, "What is your name?", "Choose Player Name",
					JOptionPane.QUESTION_MESSAGE);

			if(name == null) {
				
				name = "";
				cancelOperation = true;
			} else if (name.equals("") && name != null) {
				JOptionPane.showMessageDialog(null, "You must enter a name", "Name Entry Error",
						JOptionPane.INFORMATION_MESSAGE);
			}

		}

		// handle starting points input
		intPoints = 0;
		while (intPoints <= 0 && !cancelOperation) {

			String points = JOptionPane.showInputDialog(null, "How many buy-in points?", "Choose Buy-In Points",
					JOptionPane.QUESTION_MESSAGE);

			if (points != null) {

				try {
					intPoints = Integer.parseInt(points);

					if (intPoints <= 0) {
						JOptionPane.showMessageDialog(null, "Points must be more than 0", "Points Error",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Enter an integer only", "Input Error",
							JOptionPane.INFORMATION_MESSAGE);
				}
				
				String id = Integer.toString(this.id);
				
				newPlayer = new SimplePlayer(id, name, intPoints);

			} else {
				cancelOperation = true;
			}
		
		}
	}

	public SimplePlayer getPlayer() {
		
		return newPlayer;
	}
	
	public boolean getValidation() {
		return cancelOperation;
	}
	
}
