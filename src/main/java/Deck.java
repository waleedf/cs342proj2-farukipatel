import java.util.ArrayList;
import java.util.Collections;

public class Deck extends ArrayList<Card> {

    // Constructor to initialize a new deck with 52 cards
    public Deck() {
        newDeck();
    }

    // Method to create and shuffle a new deck of 52 cards
    public void newDeck() {
        this.clear(); // Clear any existing cards
        char[] suits = {'C', 'D', 'H', 'S'};
        for (char suit : suits) {
            for (int value = 2; value <= 14; value++) {
                this.add(new Card(suit, value));
            }
        }
        Collections.shuffle(this); // Shuffle the deck
    }

    // Method to deal a hand of 3 cards
    public ArrayList<Card> dealHand() {
        // Check if there are fewer than 34 cards left, reshuffle if needed
        if (this.size() < 34) {
            newDeck();
        }
        
        ArrayList<Card> hand = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (!this.isEmpty()) {
                hand.add(this.remove(0));
            }
        }
        return hand;
    }
}
