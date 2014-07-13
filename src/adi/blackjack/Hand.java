package adi.blackjack;

import java.util.Iterator;
import java.util.Stack;

/**
 * The Hand class provides the implementation for a hand in a game of Blackjack.
 * The class contains methods to instantiate a new class, to add a card to the
 * hand, remove a card from it and methods to display the top card and the
 * entire stack of cards. It also provides methods for implementing options
 * offered to the player such as Hit, Stand, Surrender, Split and Double down.
 * 
 * @author Adi
 * @version 1.0
 */
class Hand {
	// Define variables for the class
	private Stack<Card> cards;

	private boolean handFinished;
	private boolean handSurrendered;
	private boolean handStood;
	private boolean handDoubledDown;
	private boolean handSplit;

	// Constructor for Hand
	public Hand() {
		this.cards = new Stack<Card>();

		this.handFinished = false;
		this.handSurrendered = false;
		this.handStood = false;
		this.handDoubledDown = false;
		this.handSplit = false;
	}

	// Add a card to the hand
	public void addCard(Card card) {
		this.cards.push(card);
	}

	// Remove a card to the hand
	public Card getCard() {
		return this.cards.pop();
	}

	// Show only the top card
	public String showTopCard() {
		Card topCard = this.cards.peek();
		return (topCard.toString() + "\n");
	}

	// Show all cards in the hand
	public String showAllCards() {
		StringBuilder result = new StringBuilder();
		for (Iterator<Card> i = cards.iterator(); i.hasNext();) {
			Card myCard = i.next();
			result.append(myCard.toString());
			result.append("\n");
		}
		result.append("Value of cards: ");
		result.append(valueOfCards());
		result.append("\n");
		return result.toString();
	}

	// Value of cards in the hand
	public int valueOfCards() {
		int value = 0; // The value computed for the hand.
		boolean ace = false; // This will be set to true if the hand has an Ace
		for (Iterator<Card> i = this.cards.iterator(); i.hasNext();) {
			Card myCard = i.next();
			if (myCard.getCardValue() == 1) {
				ace = true; // There is at least one ace.
			}
			value = value + myCard.getCardValue();
		}
		if (ace == true && value + 10 <= 21) {
			value = value + 10;
		}
		return value;
	}

	// Method to implement a 'hit'
	public void hit(Card card) {
		if (!canHit())
			return;
		cards.push(card);
		if (isHandBust())
			handFinished = true;
	}

	/* Method to check if the 'hit' method can be called
	* 1. Cannot hit if a hand is done.
	* 2. Cannot hit if player doubles down and has more than 3 cards
	*/
	public boolean canHit() {
		if (handFinished)
			return false;
		if (handDoubledDown)
			return cards.size() < 3;
		return true;
	}

	// Method to implement a 'stand'
	public void stand() {
		if (!canStand())
			return;
		handStood = true;
		handFinished = true;
	}

	/* Method to check if the 'stand' method can be called
	* 1. Cannot stand if a player doubles down and has less than 3 cards
	*/
	public boolean canStand() {
		if (handDoubledDown && cards.size() < 3)
			return false;
		return true;
	}

	// Method to implement a 'double down'
	public void doubleDown(float balChips, float betChips) {
		if (!canDoubleDown(balChips, betChips))
			return;
		handDoubledDown = true;
	}

	/* Method to check if the 'double down' method can be called
	* 1. Hand cannot be finished
	* 2. Hand cannot already be doubled down
	* 3. Hand cannot be split
	* 4. The player has enough chips to double down
	*/
	public boolean canDoubleDown(float balChips, float betChips) {
		return (!handFinished && !handDoubledDown && (cards.size() == 2)
				&& !handSplit && (balChips > (2 * betChips)));
	}

	// Method to implement a 'surrender'
	public void surrender() {
		if (!canSurrender())
			return;
		handFinished = true;
		handSurrendered = true;
	}

	/* Method to check if the 'surrender' method can be called
	* 1. Hand cannot be done
	* 2. Hand cannot be doubled down
	* 3. Hand cannot be split
	* 4. The player has only 2 cards so far
	*/
	public boolean canSurrender() {
		return (!handFinished && !handDoubledDown && !isBlackJack()
				&& cards.size() == 2 && !handSplit);
	}

	/* Method to check if a hand can be split
	* 1. Hand has not been already split
	* 2. The player has only 2 cards
	* 3. The cards have same value
	* 4. The user has enough chips to bet for a new hand
	* 5. Player cannot already have doubled down
	*/
	public boolean canSplit(float balChips, float betChips) {
		if (handSplit)
			return false;
		else
			return (!handDoubledDown && (cards.size() == 2)
					&& cards.get(0).equalValueToCard(cards.get(1)) && (balChips > (2 * betChips)));
	}

	// Method to check for a 'Blackjack' or a Natural
	public boolean isBlackJack() {
		return (valueOfCards() == 21 && cards.size() == 2); // 2 cards drawn and
		// value=21
	}

	// Method to check if a hand is 'bust'
	public boolean isHandBust() {
		return valueOfCards() > 21;
	}

	// Method to check if a hand is finished
	public boolean isHandFinished() {
		return handFinished;
	}

	// Method to check is a hand is surrendered
	public boolean isHandSurrendered() {
		return handSurrendered;
	}

	// Method to check is a hand is 'standing'
	public boolean isHandStood() {
		return handStood;
	}

	// Method to check is a hand is 'doubled down'
	public boolean isHandDoubledDown() {
		return handDoubledDown;
	}

	// Method to check is a hand is 'split'
	public boolean isHandSplit() {
		return handSplit;
	}

	// Method to set the value for handSplit
	public void setHandSplit(boolean handSplit) {
		this.handSplit = handSplit;
	}
}