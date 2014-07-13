package adi.blackjack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

/**
 * The Dealer class provides the implementation for the dealer in a game of
 * Blackjack. The dealer has a tray from which he draws his cards from. He also
 * has a hand which holds the cards dealt to him. The shoe tray is implemented
 * as a stack.
 * 
 * @author Aditya
 * @version 1.0
 */
public class Dealer {
	// The number of decks used to create the shoe.
	int numDecks;
	private Stack<Card> shoeTray;

	// Dealer hand
	private Hand dealerHand;

	// Create a new dealer object
	public Dealer(int numDecks) {
		this.shoeTray = new Stack<Card>();
		this.numDecks = numDecks;
		prepareShoeTray();
	}

	// Prepare shoe
	public void prepareShoeTray() {
		for (int i = 0; i < numDecks; i++) {
			for (int j = 0; j < Card.suitGroups.length; j++) {
				for (int k = 0; k < Card.faceValues.length; k++) {
					this.shoeTray.push(new Card(Card.suitGroups[j],
							Card.faceValues[k]));
				}
			}
		}
		shuffleCards(); // Shuffle Cards
	}

	// Shuffle cards
	public void shuffleCards() {
		Collections.shuffle(this.shoeTray);
	}

	// Deal card
	public Card dealCard() {
		try {
			return this.shoeTray.pop();
		} catch (EmptyStackException e) {
			prepareShoeTray();
			return this.shoeTray.pop();
		}
	}

	// Dealer takes a card
	public void takeCard(Card card) {
		dealerHand.addCard(card);
	}

	// Show the top card of the dealer hand
	public String showTopCard() {
		return this.dealerHand.showTopCard();
	}

	// Returns the dealer hand object
	public final Hand getDealerHand() {
		return dealerHand;
	}

	// Begins a new hand
	public void newHand() {
		this.dealerHand = new Hand();
	}

	// Dealer plays out his hand. Continues to deal until the value of his hand
	// is 17 or greater
	public void playoutHand() {
		while (dealerHand.valueOfCards() < 17)
			dealerHand.addCard(this.shoeTray.pop());
	}

	// Testing only - Uses 1 Deck
//	public Dealer(boolean Val) throws FileNotFoundException {
//		this.shoeTray = new Stack<Card>();
//		Scanner inputReader = new Scanner(new File("test.data"));
//		while (inputReader.hasNext()) {
//			String[] fields = inputReader.nextLine().split("[|]");
//			this.shoeTray.push(new Card(fields[1], fields[0]));
//		}
//		inputReader.close();
//	}
}
