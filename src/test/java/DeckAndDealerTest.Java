import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class DeckAndDealerTest {

    @Test
    void testNewDeckSize() {
        Deck deck = new Deck();
        assertEquals(52, deck.size());
    }

    @Test
    void testDeckShuffle() {
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();
        deck2.newDeck(); // New shuffle
        assertNotEquals(deck1, deck2); // Shuffling changes order
    }

    @Test
    void testDealHandSize() {
        Deck deck = new Deck();
        assertEquals(3, deck.dealHand().size());
    }

    @Test
    void testDeckSizeAfterDeal() {
        Deck deck = new Deck();
        deck.dealHand();
        assertEquals(49, deck.size());
    }

    @Test
    void testDeckReshufflesCorrectly() {
        Deck deck = new Deck();
        for (int i = 0; i < 17; i++) {
            deck.dealHand();
        }
        assertTrue(deck.size() >= 34); // Reshuffle logic prevents deck from depleting
    }

    @Test
    void testDealerInitializesCorrectly() {
        Dealer dealer = new Dealer();
        assertNotNull(dealer);
    }

    @Test
    void testDealerDealsValidHand() {
        Dealer dealer = new Dealer();
        dealer.dealDealersHand();
        assertEquals(3, dealer.getDealersHand().size());
    }

    @Test
    void testDealerReshuffle() {
        Dealer dealer = new Dealer();
        for (int i = 0; i < 17; i++) {
            dealer.dealDealersHand();
        }
        assertEquals(3, dealer.getDealersHand().size());
    }

    @Test
    void testDealerHandIsUnique() {
        Dealer dealer = new Dealer();
        dealer.dealDealersHand();
        ArrayList<Card> hand = dealer.getDealersHand();
        assertEquals(3, hand.stream().distinct().count());
    }

    @Test
    void testDeckFullCycle() {
        Deck deck = new Deck();
        int totalDealt = 0;
        while (deck.size() >= 3) {
            deck.dealHand();
            totalDealt += 3;
        }
        assertEquals(52, totalDealt);
    }
}