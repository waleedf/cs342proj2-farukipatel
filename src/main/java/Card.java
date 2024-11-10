public class Card {
    private char suit; // Represents the suit of the card: 'C', 'D', 'H', 'S'
    private int value; // Represents the value of the card (2-14)

    // Constructor
    public Card(char suit, int value) {
        if (suit != 'C' && suit != 'D' && suit != 'H' && suit != 'S') {
            throw new IllegalArgumentException("Invalid suit. Must be 'C', 'D', 'H', or 'S'");
        }
        if (value < 2 || value > 14) {
            throw new IllegalArgumentException("Invalid value. Must be between 2 and 14");
        }
        this.suit = suit;
        this.value = value;
    }

    // Getters
    public char getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public String getImageFileName() {
        String suitName;
        switch(suit) {
            case 'C':
                suitName = "clubs";
                break;
            case 'D':
                suitName = "diamonds";
                break;
            case 'H':
                suitName = "hearts";
                break;
            case 'S':
                suitName = "spades";
                break;
            default: throw new IllegalStateException("Unexpected value: " + suit);
        }

        String valueName;
        switch (value) {
            case 11: valueName = "Jack"; break;
            case 12: valueName = "Queen"; break;
            case 13: valueName = "King"; break;
            case 14: valueName = "Ace"; break;
            default: valueName = String.valueOf(value); break;
        }
        return valueName + "_of_" + suitName + ".png";
    }


    // Utility method to display card details
    @Override
    public String toString() {
        String valueName;
        switch (value) {
            case 11: valueName = "Jack"; break;
            case 12: valueName = "Queen"; break;
            case 13: valueName = "King"; break;
            case 14: valueName = "Ace"; break;
            default: valueName = String.valueOf(value); break;
        }
        return valueName + " of " + suit;
    }
}
