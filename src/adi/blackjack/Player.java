package adi.blackjack;

import java.util.ArrayList;
import java.util.List;

/**
 * The Player class provides the implementation for the player in a game of
 * Blackjack. The dealer has a list of hands that he is playing currently and a
 * number of chips to play with.
 * 
 * @author Aditya
 * @version 1.0
 */
public class Player {

	// Player constants
	private float numChips;

	// Player hand
	private List<Hand> playerHand;

	//Create a new player
	public Player(int numChips) {
		this.numChips = numChips;
	}

	// Get remaning chips in the player's account
	public float getNumChips() {
		return this.numChips;
	}

	// Set the number of chips in player's account
	public void setNumChips(float numChips) {
		this.numChips = numChips;
	}

	// Return list of the hands that the player has currently
	public final List<Hand> getPlayerHand() {
		return playerHand;
	}

	// Split an existing hand
	public void split(Hand currentHand) {
		Hand splitHand = new Hand();
		splitHand.addCard(currentHand.getCard());
		playerHand.add(splitHand);
		currentHand.setHandSplit(true);
		splitHand.setHandSplit(true);
	}

	// Begins a new hand
	public void newHand() {
		Hand firstHand = new Hand();
		this.playerHand = new ArrayList<Hand>();
		this.playerHand.add(firstHand);
	}
}
