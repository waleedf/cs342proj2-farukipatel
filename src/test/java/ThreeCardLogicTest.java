import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

class ThreeCardLogicTest {

    @Test
    void testDealerQualifies() {
        ArrayList<Card> dealerHand = new ArrayList<>(Arrays.asList(
                new Card('H', 12), // Queen
                new Card('S', 10),
                new Card('C', 7)
        ));
        assertTrue(ThreeCardLogic.dealerQualifies(dealerHand));
    }

    @Test
    void testDealerDoesNotQualify() {
        ArrayList<Card> dealerHand = new ArrayList<>(Arrays.asList(
                new Card('H', 11), // Jack
                new Card('S', 9),
                new Card('C', 8)
        ));
        assertFalse(ThreeCardLogic.dealerQualifies(dealerHand));
    }

    @Test
    void testPlayerWinsAgainstDealer() {
        ArrayList<Card> dealerHand = new ArrayList<>(Arrays.asList(
                new Card('H', 10),
                new Card('S', 9),
                new Card('C', 7)
        ));
        ArrayList<Card> playerHand = new ArrayList<>(Arrays.asList(
                new Card('H', 12), // Queen
                new Card('S', 11), // Jack
                new Card('C', 10)
        ));
        assertEquals(2, ThreeCardLogic.compareHands(dealerHand, playerHand));
    }

    @Test
    void testDealerWinsAgainstPlayer() {
        ArrayList<Card> dealerHand = new ArrayList<>(Arrays.asList(
                new Card('H', 13), // King
                new Card('S', 10),
                new Card('C', 9)
        ));
        ArrayList<Card> playerHand = new ArrayList<>(Arrays.asList(
                new Card('H', 12), // Queen
                new Card('S', 11), // Jack
                new Card('C', 8)
        ));
        assertEquals(1, ThreeCardLogic.compareHands(dealerHand, playerHand));
    }

    @Test
    void testTieBetweenDealerAndPlayer() {
        ArrayList<Card> dealerHand = new ArrayList<>(Arrays.asList(
                new Card('H', 14), // Ace
                new Card('S', 11), // Jack
                new Card('C', 10)
        ));
        ArrayList<Card> playerHand = new ArrayList<>(Arrays.asList(
                new Card('H', 14), // Ace
                new Card('S', 11), // Jack
                new Card('C', 10)
        ));
        assertEquals(0, ThreeCardLogic.compareHands(dealerHand, playerHand));
    }

    @Test
    void testPairPlusStraightFlush() {
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(
                new Card('H', 14), // Ace
                new Card('H', 13), // King
                new Card('H', 12)  // Queen
        ));
        assertEquals(40, ThreeCardLogic.evalPPWinnings(hand, 1));
    }

    @Test
    void testPairPlusThreeOfAKind() {
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(
                new Card('H', 10),
                new Card('D', 10),
                new Card('C', 10)
        ));
        assertEquals(30, ThreeCardLogic.evalPPWinnings(hand, 1));
    }

    @Test
    void testPairPlusStraight() {
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(
                new Card('H', 10),
                new Card('D', 9),
                new Card('C', 8)
        ));
        assertEquals(6, ThreeCardLogic.evalPPWinnings(hand, 1));
    }

    @Test
    void testDealerQualifiesWithLowestQueenHigh() {
        ArrayList<Card> dealerHand = new ArrayList<>(Arrays.asList(
                new Card('H', 12), // Queen
                new Card('C', 10),
                new Card('S', 5)
        ));
        assertTrue(ThreeCardLogic.dealerQualifies(dealerHand));
    }

    @Test
    void testPairPlusFlush() {
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(
                new Card('H', 10),
                new Card('H', 8),
                new Card('H', 7)
        ));
        assertEquals(3, ThreeCardLogic.evalPPWinnings(hand, 1));
    }

    @Test
    void testPairPlusPair() {
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(
                new Card('H', 10),
                new Card('D', 10),
                new Card('C', 8)
        ));
        assertEquals(1, ThreeCardLogic.evalPPWinnings(hand, 1));
    }
    @Test
    void testCompareHandsTieDifferentSuits() {
        ArrayList<Card> dealerHand = new ArrayList<>(Arrays.asList(
                new Card('H', 14), // Ace
                new Card('S', 13), // King
                new Card('D', 11)  // Jack
        ));
        ArrayList<Card> playerHand = new ArrayList<>(Arrays.asList(
                new Card('C', 14), // Ace
                new Card('D', 13), // King
                new Card('H', 11)  // Jack
        ));
        assertEquals(0, ThreeCardLogic.compareHands(dealerHand, playerHand));
    }

    @Test
    void testPairPlusNoWinningHand() {
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(
                new Card('H', 10),
                new Card('D', 9),
                new Card('C', 6)
        ));
        assertEquals(0, ThreeCardLogic.evalPPWinnings(hand, 1));
    }
    @Test
    void testPairPlusStraightNonSequentialSuits() {
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(
                new Card('H', 9),
                new Card('D', 8),
                new Card('S', 7)
        ));
        assertEquals(6, ThreeCardLogic.evalPPWinnings(hand, 1)); // 6x for a straight
    }

    @Test
    void testPairPlusBoundaryStraightFlush() {
        ArrayList<Card> hand = new ArrayList<>(Arrays.asList(
                new Card('H', 5),
                new Card('H', 4),
                new Card('H', 3)
        ));
        assertEquals(40, ThreeCardLogic.evalPPWinnings(hand, 1));
    }

    @Test
    void testCompareHandsPlayerWinsHighCard() {
        ArrayList<Card> dealerHand = new ArrayList<>(Arrays.asList(
                new Card('S', 10),
                new Card('D', 8),
                new Card('H', 6)
        ));
        ArrayList<Card> playerHand = new ArrayList<>(Arrays.asList(
                new Card('C', 11),
                new Card('D', 7),
                new Card('S', 5)
        ));
        assertEquals(2, ThreeCardLogic.compareHands(dealerHand, playerHand));
    }

    @Test
    void testCompareHandsDealerWinsHighCard() {
        ArrayList<Card> dealerHand = new ArrayList<>(Arrays.asList(
                new Card('S', 13),
                new Card('D', 8),
                new Card('H', 6)
        ));
        ArrayList<Card> playerHand = new ArrayList<>(Arrays.asList(
                new Card('C', 12),
                new Card('D', 7),
                new Card('S', 5)
        ));
        assertEquals(1, ThreeCardLogic.compareHands(dealerHand, playerHand));
    }

    @Test
    void testCompareHandsBoundaryTie() {
        ArrayList<Card> dealerHand = new ArrayList<>(Arrays.asList(
                new Card('S', 14), // Ace
                new Card('D', 11), // Jack
                new Card('H', 10)
        ));
        ArrayList<Card> playerHand = new ArrayList<>(Arrays.asList(
                new Card('C', 14), // Ace
                new Card('D', 11), // Jack
                new Card('H', 10)
        ));
        assertEquals(0, ThreeCardLogic.compareHands(dealerHand, playerHand));
    }

    @Test
    void testDealerQualificationBoundary() {
        ArrayList<Card> dealerHand = new ArrayList<>(Arrays.asList(
                new Card('C', 12), // Queen
                new Card('D', 10),
                new Card('S', 7)
        ));
        assertTrue(ThreeCardLogic.dealerQualifies(dealerHand));
    }

    @Test
    void testDealerDoesNotQualifyBoundary() {
        ArrayList<Card> dealerHand = new ArrayList<>(Arrays.asList(
                new Card('C', 11), // Jack
                new Card('D', 10),
                new Card('S', 7)
        ));
        assertFalse(ThreeCardLogic.dealerQualifies(dealerHand));
    }
}


