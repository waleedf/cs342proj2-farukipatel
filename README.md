# Three Card Poker Game

This project is a JavaFX-based implementation of a **Three Card Poker** game. The application includes functionality for dealing cards, placing bets, and determining the outcome of a round, which involves both the dealer and player. Below is an overview of the project's features and how you can run it.

## Features

- **Graphical User Interface**: Built using JavaFX, featuring FXML for layout management.
- **Game Flow**:
  - Players can set an Ante and Pair Plus bet to start the game.
  - The dealer and player are each dealt three cards.
  - The dealer must qualify with a queen or higher to proceed.
  - Players can choose to **Play** or **Fold** after being dealt cards.
  - Hands are compared, and winnings or losses are calculated based on Three Card Poker rules.
- **Card Deck**: A fully functional deck, shuffle mechanics, and dealing cards to both dealer and player.
- **Interactive Buttons**: Player actions like **Play** and **Fold** are handled through buttons in the UI.
- **Animations**: Card dealing and game transitions are accompanied by pause animations for improved UX.

## Installation and Setup

1. **Requirements**:
   - Java JDK 8 or higher.
   - JavaFX library (typically included with JDK 8, but may require separate installation for later versions).

2. **Clone the Repository**:
   ```sh
   git clone https://github.com/yourusername/three-card-poker.git
   cd three-card-poker
   ```

3. **Compile and Run**:
   Use your preferred Java IDE (such as IntelliJ IDEA or Eclipse), or compile using the command line:
   ```sh
   javac JavaFXTemplate.java
   java JavaFXTemplate
   ```

## How to Play

- **Step 1**: Set an Ante Bet and optionally a Pair Plus Bet. Bets must be between **$5 and $25**.
- **Step 2**: Click **Begin Game** to start the game.
- **Step 3**: The dealer will deal three cards to both the player and themselves.
- **Step 4**: You can choose to **Play** (continue) or **Fold** (give up your bets).
- **Step 5**: If the dealer qualifies (with a Queen high or better), hands are compared to determine the winner.

## Key Classes

- **JavaFXTemplate**: Main application class that handles the UI and game flow.
- **Dealer**: Represents the dealer and contains logic to deal cards and manage the dealer's hand.
- **Player**: Represents the player and includes methods for placing bets and managing winnings.
- **Deck**: Represents a deck of cards, with methods for shuffling and drawing cards.
- **ThreeCardLogic**: Provides static methods for evaluating hands, determining the winner, and other game logic.

## Tests

The project includes unit tests using JUnit to ensure that game logic works as expected. Here are some key test cases:

- **Deck Shuffling and Card Drawing**: Ensures that cards are shuffled properly and drawn from the deck.
- **Bet Validation**: Tests that the ante and pair plus bets are within allowed limits.
- **Player Actions**: Tests for handling player actions, including **Play**, **Fold**, and win/loss scenarios.
- **Dealer Behavior**: Ensures that the dealer qualifies appropriately and manages card drawing until hitting the threshold.

## Future Enhancements

- **More Betting Options**: Introduce side bets or increase the betting range.
- **Multiplayer Support**: Allow multiple players to join a single game session.
- **Improved Graphics**: Upgrade card designs and table graphics for a more immersive experience.

