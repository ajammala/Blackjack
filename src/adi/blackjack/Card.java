package adi.blackjack;

/**
 * The Card class provides the implementation for the card object in a game of
 * Blackjack. Each card has a suit group and a face value. The class provides
 * constants to define the limits on the card suits and the values. It also
 * provides methods to instantiate a card object, get the value of a card object
 * for the black jack game and finally a method to return the string value of a
 * card.
 * 
 * @author Adi
 * @version 1.0
 */
public class Card {

	// Define constants for the Card class
	public static final int DIAMOND = 0;
	public static final int CLUB = 1;
	public static final int HEART = 2;
	public static final int SPADE = 3;

	public static final String[] suitGroups = { "diamond", "club", "heart",
	"spade" };
	public static final String[] faceValues = { "Ace", "2", "3", "4", "5", "6",
		"7", "8", "9", "10", "Jack", "Queen", "King" };

	private String suitGroup;
	private String faceValue;

	// Constructor for Card
	public Card(String suitGroup, String faceValue) {
		this.suitGroup = suitGroup;
		this.faceValue = faceValue;
	}

	// Get the suit group for the card
	public String getSuitGroup() {
		return suitGroup;
	}

	// Get the face value for the card
	public String getFaceValue() {
		return faceValue;
	}

	// Get card value for black jack game
	public int getCardValue() {
		int result = 0;
		switch (this.faceValue) {
		case "Ace":
			result = 1;
			break;
		case "2":
			result = 2;
			break;
		case "3":
			result = 3;
			break;
		case "4":
			result = 4;
			break;
		case "5":
			result = 5;
			break;
		case "6":
			result = 6;
			break;
		case "7":
			result = 7;
			break;
		case "8":
			result = 8;
			break;
		case "9":
			result = 9;
			break;
		case "10":
		case "Jack":
		case "Queen":
		case "King":
			result = 10;
			break;
		}
		return result;
	}

	// Compare the value of a card to another card
	public boolean equalValueToCard(Card card) {
		if (this.getFaceValue().equalsIgnoreCase(card.getFaceValue()))
			return true;
		else
			return false;
	}

	// Provide a string representation of the card
	public String toString() {
		return (this.faceValue + " " + this.suitGroup);
	}
}
