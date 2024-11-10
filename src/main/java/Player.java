import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private int anteBet;
    private int playBet;
    private int pairPlusBet;
    private int totalWinnings;

    // Constructor to initialize player data
    public Player() {
        this.hand = new ArrayList<>();
        this.anteBet = 0;
        this.playBet = 0;
        this.pairPlusBet = 0;
        this.totalWinnings = 0;
    }

    // Setters for bets
    public void setAnteBet(int anteBet) {
        if (anteBet >= 5 && anteBet <= 25) {
            this.anteBet = anteBet;
        } else {
            throw new IllegalArgumentException("Ante bet must be between $5 and $25.");
        }
    }

    public void setPlayBet(int playBet) {
        if (playBet == this.anteBet) {
            this.playBet = playBet;
        } else {
            throw new IllegalArgumentException("Play bet must be equal to the ante bet.");
        }
    }

    public void setPairPlusBet(int pairPlusBet) {
        if (pairPlusBet >= 5 && pairPlusBet <= 25) {
            this.pairPlusBet = pairPlusBet;
        } else {
            throw new IllegalArgumentException("Pair Plus bet must be between $5 and $25.");
        }
    }

    // Method to set the player's hand
    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    // Method to get the player's hand
    public ArrayList<Card> getHand() {
        return hand;
    }

    // Method to update total winnings
    public void updateTotalWinnings(int amount) {
        this.totalWinnings += amount;
    }

    // Method to get total winnings
    public int getTotalWinnings() {
        return totalWinnings;
    }

    // Method to reset bets (for a new round)
    public void resetBets() {
        this.anteBet = 0;
        this.playBet = 0;
        this.pairPlusBet = 0;
    }

    // Getters for bets
    public int getAnteBet() {
        return anteBet;
    }

    public int getPlayBet() {
        return playBet;
    }

    public int getPairPlusBet() {
        return pairPlusBet;
    }
}
