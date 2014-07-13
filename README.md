# Blackjack #
## Overview ##
Each game of a blackjack has one player and one dealer.
#### Player ####
The player plays against the dealer/the house. ***To win the game, the player has to get the value of his hand as close as possible to 21 without going over.***

#### Dealer ####
The dealer acts for the house and distributes the cards. The dealer has to continue to hit until his hand value is 17 or over. ***To win the game, the dealer has to get a higher value hand than the player.***

#### Shoe ####
The blackjack dealer shuffles the cards. Once the cards are shuffled, the dealer will place the shuffled cards in a box known as the **"shoe"**.

#### Hand ####
The cards currently being held by a player is a hand.


## Game ##
The dealer is in-charge of creating a shoe from the specified number of decks and dealing the cards to the player and to himself. Once the cards are dealt, the player has options to surrender, hit, double down, stand and split, depending on the state of the game. 

* ***Player can split a hand only once*** - when the original pair of cards have been dealt to the player.
* ***Player can double-down only once in the game*** - when the original pair of cards have been dealt to the player.
* ***Player can surrender only once in the game*** - when the original pair of cards have been dealt to the player.

## Rules ##
#### Surrender ####
* The current hand is not finished.
* The player has not doubled down already on current hand.
* The player has not split his hand.
* The player has only 2 cards so far.

#### Double Down ####
* The current hand is not finished.
* The player has not doubled down already on current hand.
* The player has not split his hand.
* The player has enough chips to double down.

#### Hit ####
* The current hand is not finished.
* The player cannot hit if he has already doubled down and has more than 3 cards.

#### Stand ####
* The player cannot stand if he had already doubled down and has less than 3 cards.

#### Split ####
* The player has not split his hand.
* The player has only 2 cards and the cards have same value.
* The user has enough chips to bet for a new hand.
* The player has not doubled down already on current hand.


## Winning ##
1. If a user surrenders and the dealer does not have a blackjack (Ace and a ten-value card), then the user loses half the original bet amount.
2. If both the player and the dealer bust (hand value goes over 21), the dealer wins.
3. If the player's hand is less than the dealer's hand and both hands are under 21, the dealer wins.
4. If the player's hand is more than the dealer's hand and both hands are under 21, the player wins. If a player has a blackjack, he wins 3/2 times the original bet.
5. If player and the dealer have the same value in hand, nobody wins or loses. The match is a tie. *The only exception is when the user has a blackjack and the dealer does not. In such cases, the player wins 3/2 times the original bet.*