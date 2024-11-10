import java.util.ArrayList;
import java.util.Collections;

public class ThreeCardLogic {

    // Method to determine if the dealer qualifies (must have Queen high or better)
    public static boolean dealerQualifies(ArrayList<Card> dealerHand) {
        Collections.sort(dealerHand, (c1, c2) -> Integer.compare(c2.getValue(), c1.getValue()));
        // The dealer qualifies if they have at least a Queen high card (value 12 or
        // greater)
        return dealerHand.get(0).getValue() >= 12;
    }

    // Method to compare hands
    // Returns 1 if dealer wins, 2 if player wins, 0 if tie
    public static int compareHands(ArrayList<Card> dealerHand, ArrayList<Card> playerHand) {
        Collections.sort(dealerHand, (c1, c2) -> Integer.compare(c2.getValue(), c1.getValue()));
        Collections.sort(playerHand, (c1, c2) -> Integer.compare(c2.getValue(), c1.getValue()));

        for (int i = 0; i < 3; i++) {
            if (playerHand.get(i).getValue() > dealerHand.get(i).getValue()) {
                return 2; // Player wins
            } else if (dealerHand.get(i).getValue() > playerHand.get(i).getValue()) {
                return 1; // Dealer wins
            }
        }
        return 0; // Tie
    }

    // Method to evaluate Pair Plus winnings
    // Returns the payout based on the type of hand
    public static int evalPPWinnings(ArrayList<Card> hand, int pairPlusBet) {
        Collections.sort(hand, (c1, c2) -> Integer.compare(c2.getValue(), c1.getValue()));

        boolean isFlush = hand.get(0).getSuit() == hand.get(1).getSuit()
                && hand.get(1).getSuit() == hand.get(2).getSuit();
        boolean isStraight = hand.get(0).getValue() == hand.get(1).getValue() + 1
                && hand.get(1).getValue() == hand.get(2).getValue() + 1;
        boolean isThreeOfAKind = hand.get(0).getValue() == hand.get(1).getValue()
                && hand.get(1).getValue() == hand.get(2).getValue();

        if (isFlush && isStraight) {
            return pairPlusBet * 40; // Straight Flush
        } else if (isThreeOfAKind) {
            return pairPlusBet * 30; // Three of a Kind
        } else if (isStraight) {
            return pairPlusBet * 6; // Straight
        } else if (isFlush) {
            return pairPlusBet * 3; // Flush
        } else if (hand.get(0).getValue() == hand.get(1).getValue()
                || hand.get(1).getValue() == hand.get(2).getValue()) {
            return pairPlusBet * 1; // Pair
        }

        return 0; // No winning hand
    }
}
