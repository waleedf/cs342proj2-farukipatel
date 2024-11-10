import java.util.ArrayList;

public class Dealer {
    private Deck theDeck;
    private ArrayList<Card> dealersHand;

    // Constructor to initialize a new deck and dealer's hand
    public Dealer() {
        this.theDeck = new Deck();
        this.dealersHand = new ArrayList<>();
    }

    // Method to deal a hand of 3 cards
    // Method to deal a hand of 3 cards
    public ArrayList<Card> dealHand() {
        // Check if deck has fewer than 34 cards and reshuffle if necessary
        if (theDeck.size() < 34) {
            theDeck.newDeck(); // Reshuffle with a new set of 52 cards
            System.out.println("[INFO] Deck reshuffled with a new set of cards.");
        }

        return theDeck.dealHand(); // Deal 3 cards from the deck
    }

    // Method for dealer to deal its own hand
    public void dealDealersHand() {
        if (theDeck.size() < 34) {
            theDeck.newDeck();
        }
        this.dealersHand = theDeck.dealHand();
    }

    // Getter to access the dealer's hand
    public ArrayList<Card> getDealersHand() {
        return dealersHand;
    }
}
