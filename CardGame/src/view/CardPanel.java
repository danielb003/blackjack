package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import controller.DealPlayer;
import controller.PlaceBet;
import controller.UpdateCardPanel;
import model.interfaces.GameEngine;
import view.factory.ImageFactory;

public class CardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField betInput;
	private JButton bet;
	private JButton deal;
	private JLabel betAmount, betTotal, betLabel, houseActiveLabel, picLabel;
	private boolean newRound = true, firstDeal = true;
	private int betTotalInt = 0;
	private BufferedImage cardImage;
	private Border blackBorder = BorderFactory.createLineBorder(Color.BLACK, 1);
	private Border blueBorder = BorderFactory.createLineBorder(Color.BLUE, 1);
	private ImageFactory imgFactory;

	public CardPanel(GameEngine gameEngine, AppFrame appFrame) {
		
		// set layout
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		// instantiate image factory and create image array
		imgFactory = new ImageFactory();
		imgFactory.createImageMap();
		// create starting deck images
		try {
			cardImage = ImageIO.read(new File("img/back_red_card.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		picLabel = new JLabel(new ImageIcon(cardImage));
		picLabel.setMaximumSize(new Dimension(161, 245));
		// set the border of this component
		picLabel.setBorder(blackBorder);
		picLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


		// create components
		houseActiveLabel = new JLabel("HOUSE ACTIVE");
		houseActiveLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		houseActiveLabel.setForeground(Color.blue);
		houseActiveLabel.setVisible(false);
		betAmount = new JLabel("Bet: - ");
		betAmount.setAlignmentX(Component.CENTER_ALIGNMENT);
		betAmount.setForeground(Color.darkGray);
		betTotal = new JLabel("Total: 0");
		betTotal.setAlignmentX(Component.CENTER_ALIGNMENT);
		betLabel = new JLabel("Place Bet");
		betLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		betInput = new JTextField(10);
		betInput.setMaximumSize(new Dimension(100, 20));
		betInput.setAlignmentX(Component.CENTER_ALIGNMENT);
		bet = new JButton("Bet");
		bet.setAlignmentX(Component.CENTER_ALIGNMENT);
		bet.setEnabled(false);
		deal = new JButton("Deal");
		deal.setAlignmentX(Component.CENTER_ALIGNMENT);
		deal.setEnabled(false);

		add(Box.createVerticalStrut(6));
		add(houseActiveLabel);
		add(Box.createVerticalStrut(6));
		add(picLabel);
		add(Box.createVerticalStrut(6));
		add(betAmount);
		add(betTotal);
		add(Box.createVerticalStrut(10));
		add(betLabel);
		add(Box.createVerticalStrut(2));
		add(betInput);
		add(Box.createVerticalStrut(2));
		add(bet);
		add(Box.createVerticalStrut(2));
		add(deal);
		add(Box.createVerticalStrut(4));

		// listener to update the card panel
		appFrame.toolbar.playerSelect.addActionListener(new UpdateCardPanel(gameEngine, this, appFrame.toolbar));
		
		// add action listeners
		bet.addActionListener(new PlaceBet(gameEngine, this, appFrame.toolbar));
		deal.addActionListener(new DealPlayer(gameEngine, this, appFrame.toolbar));

	}

	public String getBetInput() { return betInput.getText(); }

	public void setBetInput() {
		this.betInput.setText("");
	}
	
	public void setDeal(boolean state) {
		deal.setEnabled(state);
	}
	
	public void setBet(boolean state) {
		bet.setEnabled(state);
	}

	public void setBetAmount(int betAmount) {
		this.betAmount.setText("Bet: " + betAmount);
	}
	
	public void resetBetAmount() {
		this.betAmount.setText("Bet: -");
	}
	
	public boolean getNewRound() { return this.newRound; }
	
	public void setNewRound(boolean newRound) {
		this.newRound = newRound;
	}
	
	public boolean getFirstDeal() { return this.firstDeal; }
	
	public void setFirstDeal(boolean firstDeal) {
		this.firstDeal = firstDeal;
	}
	
	public void addBetTotal(int betTotal) {
		this.betTotalInt += betTotal;
		this.betTotal.setText("Total: " + this.betTotalInt);
	}
	
	public void setBetTotal(int betTotal) {
		this.betTotalInt = betTotal;
		this.betTotal.setText("Total: " + this.betTotalInt);
	}
	
	public void setHouseActiveLabel(boolean visibility) {
		this.houseActiveLabel.setVisible(visibility);
	}

	public void setBetTotalColorBlack() {
		this.betTotal.setForeground(Color.darkGray);
		this.picLabel.setBorder(blackBorder);
	}
	
	public void setBetTotalColorBlue() {
		this.betTotal.setForeground(Color.blue);
		this.picLabel.setBorder(blueBorder);
	}
	
	public void changeCardImage(String nextCardFileString) {
		
		try {
			cardImage = ImageIO.read(new File("img/" + nextCardFileString.toLowerCase()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.picLabel.setIcon(new ImageIcon(cardImage));
	
	}
	
}
