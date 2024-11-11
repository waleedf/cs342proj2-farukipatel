import java.util.ArrayList;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.InputStream;

public class JavaFXTemplate extends Application {

	private static Stage primaryStage; // Main stage for scene transitions

	/**
	 * Current Progress:
	 * - Implemented core game phases using GameState enum (START_SCREEN, BETTING,
	 * DEALING, PLAYER_DECISION, DEALER_REVEAL, RESULT, RESET).
	 * - Added PauseTransition to provide smooth transitions between game phases.
	 * - Each phase method is outlined with TODOs for integrating backend logic.
	 * - Currently using placeholders for core game logic interactions (e.g.,
	 * dealing cards, player decisions, evaluating hands).
	 * - Integrated backend classes (Player, Dealer, Deck, ThreeCardLogic) into
	 * controller methods to manage bets, dealing cards, player decisions, and
	 * result evaluation.
	 * - Added UI elements including Menu Bar and Player Turn Indicator to
	 * betting.fxml.
	 * Last Change: Completed showResultsAndReset and finished the game flow.
	 */

	private enum GameState {
		START_SCREEN, BETTING, DEALING, PLAYER_DECISION, DEALER_REVEAL, RESULT, RESET
	}

	private GameState gameState; // Current state of the game

	@FXML
	private Button exitButton;
	@FXML
	private Button resetButton;

	@FXML
	private Button playButton1;
	@FXML
	private Button foldButton1;
	@FXML
	private Button playButton2;
	@FXML
	private Button foldButton2;
	@FXML
	private Label playerTurnIndicator;
	@FXML
	private Label player1WinningsLabel;
	@FXML
	private Label player2WinningsLabel;
	@FXML
	private TextField player1AnteBet;
	@FXML
	private TextField player1PairPlusBet;
	@FXML
	private TextField player2AnteBet;
	@FXML
	private TextField player2PairPlusBet;
	@FXML
	private ImageView dealerCardOne, dealerCardTwo, dealerCardThree, playerCard1One, playerCard1Two, playerCard1Three, playerCard2One, playerCard2Two, playerCard2Three;
	private String cardPath = "/cards/";

	@FXML
	private Label dealerQualificationLabel;

	@FXML
	private Label player1Card1;
	@FXML
	private Label player1Card2;
	@FXML
	private Label player1Card3;

	@FXML
	private Label player2Card1;
	@FXML
	private Label player2Card2;
	@FXML
	private Label player2Card3;

	@FXML
	private Label dealerCard1;
	@FXML
	private Label dealerCard2;
	@FXML
	private Label dealerCard3;
	@FXML
	private Label statusBox;

	private Player player1 = new Player();
	private Player player2 = new Player();

	private Dealer dealer = new Dealer();
	private boolean player1DecisionMade = false;
	private boolean player2DecisionMade = false;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		JavaFXTemplate.primaryStage = primaryStage; // Assign to static variable
		gameState = GameState.START_SCREEN; // Set initial game state

		loadStartScreen();
	}

	// Load Start Screen (Phase 1)
	private void loadStartScreen() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/start.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root, 700, 700);

		primaryStage.setTitle("Three-Card Poker - Start Screen");
		primaryStage.setScene(scene);
		primaryStage.show();
		System.out.println("Game started. Showing Start Screen."); // Debug message
	}

	// Event handler to start the game
	public void startGame(ActionEvent event) {
		try {
			gameState = GameState.BETTING; // Transition to Betting Phase
			loadBettingScreen();
			System.out.println("Transitioned to BETTING phase."); // Debug message
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Load Betting Screen (Phase 2)
	private void loadBettingScreen() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("betting.fxml"));
		Parent bettingRoot = loader.load();
		Scene bettingScene = new Scene(bettingRoot, 700, 700);
		primaryStage.setScene(bettingScene);
		primaryStage.show();
		System.out.println("Loaded Betting Screen. Players are placing bets."); // Debug message
	}

	// Event handler to exit the game
	public void exitGame(ActionEvent event) {
		System.out.println("Exiting game."); // Debug message
		primaryStage.close();
	}

	// Event handler for Fresh Start
	public void freshStart(ActionEvent event) {
		try {
			gameState = GameState.BETTING;
			loadBettingScreen();
			// statusBox.setText("Game Status: Game reset. Place your bets to start over.");

			System.out.println("Game reset. Starting fresh at BETTING phase."); // Debug message
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Event handler to apply new look
	public void applyNewLook(ActionEvent event) {
		// TODO: Implement New Look functionality by changing CSS styling
		System.out.println("New Look option selected. TODO: Implement new UI styling.");
		// statusBox.setText("Game Status: New Look applied.");

	}

	// Event handler for showing player information
	public void showPlayerInfo(ActionEvent event) {
		System.out.println("Player 1 Information: ");
		System.out.println("Ante Bet: " + player1.getAnteBet() + ", Pair Plus Bet: " + player1.getPairPlusBet()
				+ ", Total Winnings: " + player1.getTotalWinnings());

		System.out.println("Player 2 Information: ");
		System.out.println("Ante Bet: " + player2.getAnteBet() + ", Pair Plus Bet: " + player2.getPairPlusBet()
				+ ", Total Winnings: " + player2.getTotalWinnings());

		System.out.println("Player Info button pressed. TODO: Display player information.");
		// statusBox.setText("Game Status: Player information displayed in console.");

	}

	// Event handler for showing options
	public void showOptions(ActionEvent event) {
		// TODO: Implement options display functionality
		System.out.println("Options button pressed. TODO: Display game options.");
		// statusBox.setText("Game Status: Game options displayed.");
	}

	// Confirm Bets (Phase 2)
	@FXML
	public void confirmBets(ActionEvent event) {
		try {
			int player1Ante = Integer.parseInt(player1AnteBet.getText());
			int player1PairPlus = Integer.parseInt(player1PairPlusBet.getText());
			int player2Ante = Integer.parseInt(player2AnteBet.getText());
			int player2PairPlus = Integer.parseInt(player2PairPlusBet.getText());

			if (player1Ante >= 5 && player1Ante <= 25 && player1PairPlus >= 5 && player1PairPlus <= 25) {
				player1.setAnteBet(player1Ante);
				player1.setPairPlusBet(player1PairPlus);
				System.out
						.println("Player 1 bets confirmed: Ante - " + player1Ante + ", Pair Plus - " + player1PairPlus);
			} else {
				System.out.println("Player 1 entered invalid bets. Please enter values between $5 and $25.");
				statusBox.setText(
						"Game Status: Invalid bets entered by Player 1. Please enter values between $5 and $25.");
				return;
			}

			if (player2Ante >= 5 && player2Ante <= 25 && player2PairPlus >= 5 && player2PairPlus <= 25) {
				player2.setAnteBet(player2Ante);
				player2.setPairPlusBet(player2PairPlus);
				System.out
						.println("Player 2 bets confirmed: Ante - " + player2Ante + ", Pair Plus - " + player2PairPlus);
			} else {
				System.out.println("Player 2 entered invalid bets. Please enter values between $5 and $25.");
				statusBox.setText(
						"Game Status: Invalid bets entered by Player 2. Please enter values between $5 and $25.");
				return;
			}

			gameState = GameState.DEALING;
			dealCardsWithPause();
			System.out.println("Transitioning to DEALING phase.");

		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Bet");
			alert.setHeaderText(null);
			alert.setContentText("Invalid bet entered. Please enter a value between $5 and $25.");
			alert.showAndWait();
			System.out.println("Invalid bet input detected. Prompting user to re-enter."); // Debug message
		}
	}

	// Update Player Turn Indicator
	private void updatePlayerTurnIndicator() {
		if (gameState == GameState.PLAYER_DECISION) {
			if (!player1DecisionMade) {
				playerTurnIndicator.setText("Current Turn: Player One");
			} else if (!player2DecisionMade) {
				playerTurnIndicator.setText("Current Turn: Player Two");
			}
		} else if (gameState == GameState.DEALER_REVEAL) {
			playerTurnIndicator.setText("Dealer's Turn");
		}
	}

	// Deal Cards (Phase 3) with Pause Transition
	private void dealCardsWithPause() {
		PauseTransition pause = new PauseTransition(Duration.seconds(1));
		pause.setOnFinished(event -> {
			dealCards();
			updatePlayerTurnIndicator();
			System.out.println("Cards dealt to players and dealer.");
			// Display cards dealt for debugging purposes
			System.out.println("Player 1 Hand: " + player1.getHand());
			System.out.println("Player 2 Hand: " + player2.getHand());
			System.out.println("Dealer Hand: " + dealer.getDealersHand()); // Debug message
		});
		pause.play();
	}

	private void updateCardImages(ArrayList<Card> hand, ImageView... imageViews) {
		for(int i = 0; i < hand.size(); ++i) {
			String filename = cardPath + hand.get(i).getImageFileName();
			InputStream imageStream = getClass().getResourceAsStream(filename);
			if(imageStream == null) {
				System.out.println("Image not found: " + filename);
				continue;
			}
			imageViews[i].setImage(new Image(imageStream));
		}
	}

	private void dealCards() {
		if (gameState == GameState.DEALING) {
			// Dealing cards to players and dealer using Deck and Dealer classes
			ArrayList<Card> player1Hand = dealer.dealHand();
			player1.setHand(player1Hand);
			ArrayList<Card> player2Hand = dealer.dealHand();
			player2.setHand(player2Hand);
			dealer.dealDealersHand();

			// Update card labels in UI
			updateCardImages(player1Hand, playerCard1One, playerCard1Two, playerCard1Three);
			updateCardImages(player2Hand, playerCard2One, playerCard2Two, playerCard2Three);
			updateCardImages(dealer.getDealersHand(), dealerCardOne, dealerCardTwo, dealerCardThree);

			// Additional debug messages
			System.out.println("Player 1 Hand dealt: " + player1.getHand());
			System.out.println("Player 2 Hand dealt: " + player2.getHand());
			System.out.println("Dealer Hand dealt: " + dealer.getDealersHand());

			gameState = GameState.PLAYER_DECISION;
			promptPlayerDecisionWithPause();
		}
	}

	// Prompt Player Decision (Phase 4) with Pause Transition
	private void promptPlayerDecisionWithPause() {
		PauseTransition pause = new PauseTransition(Duration.seconds(1));
		pause.setOnFinished(event -> promptPlayerDecision());
		pause.play();
		System.out.println("Prompting player decisions with a pause."); // Debug message
	}

	private void promptPlayerDecision() {
		if (gameState == GameState.PLAYER_DECISION) {
			// Enable Play/Fold buttons for players, manage turn-based decisions
			playButton1.setDisable(false);
			foldButton1.setDisable(false);
			playerTurnIndicator.setText("Current Turn: Player One");
			statusBox.setText("Game Status: Cards dealt. Player One's turn to decide.");

			System.out.println("Player One's turn. Waiting for decision."); // Debug message
		}
	}

	// Handle Player Decision - Play or Fold
	@FXML
	public void playerDecision(ActionEvent event) {
		Button sourceButton = (Button) event.getSource();
		boolean play = sourceButton.getText().contains("Play");

		if (gameState == GameState.PLAYER_DECISION) {
			handlePlayerDecision(player1, sourceButton, playButton1, foldButton1, play);
			player1DecisionMade = true;
		} else if (sourceButton == playButton2 || sourceButton == foldButton2) {
			handlePlayerDecision(player2, sourceButton, playButton2, foldButton2, play);
			player2DecisionMade = true;
		}

		// Proceed if both players have made decisions
		if (player1DecisionMade && player2DecisionMade) {
			gameState = GameState.DEALER_REVEAL;
			System.out.println("Both players have made their decisions. Transitioning to DEALER_REVEAL phase.");
			revealDealerHandWithPause();
		}
	}

	// Reveal Dealer Hand (Phase 5) with Pause Transition
	private void revealDealerHandWithPause() {
		PauseTransition pause = new PauseTransition(Duration.seconds(1));
		pause.setOnFinished(event -> {
			revealDealerHand();
			evaluateHandsWithPause();
			System.out.println("Revealing dealer's hand."); // Debug message
		});
		pause.play();
	}

	private void revealDealerHand() {
		if (gameState == GameState.DEALER_REVEAL) {
			// Reveal dealer's cards
			dealer.dealDealersHand(); // Assuming a dealCards() method in Dealer to deal cards to itself

			// Check if dealer qualifies
			boolean dealerQualifies = ThreeCardLogic.dealerQualifies(dealer.getDealersHand());
			if (dealerQualifies) {
				dealerQualificationLabel.setText("Dealer Qualifies!");
				System.out.println("Dealer qualifies with: " + dealer.getDealersHand());
			} else {
				dealerQualificationLabel.setText("Dealer does not qualify.");
				System.out.println("Dealer does not qualify. Players win their Ante bets.");
				player1.updateTotalWinnings(player1.getAnteBet());
				player2.updateTotalWinnings(player2.getAnteBet());
			}

			// Proceed to evaluating hands
			gameState = GameState.RESULT;
			evaluateHandsWithPause();
		}
	}

	private void evaluateHands() {
		if (gameState == GameState.RESULT) {
			// Compare player hands with the dealer's hand
			int player1Result = ThreeCardLogic.compareHands(player1.getHand(), dealer.getDealersHand());
			int player2Result = ThreeCardLogic.compareHands(player2.getHand(), dealer.getDealersHand());

			// Determine winnings for player 1
			if (player1Result > 0) {
				// Player 1 wins - calculate total winnings (Ante + Pair Plus + Play)
				int winnings = player1.getAnteBet() + player1.getPairPlusBet() + player1.getPlayBet();
				player1.updateTotalWinnings(winnings);
				System.out.println("Player 1 wins! Total Winnings: $" + winnings);
			} else if (player1Result < 0) {
				// Player 1 loses - no additional winnings
				System.out.println("Player 1 loses.");
			} else {
				// Tie - Player 1 gets their Ante and Play bets back
				player1.updateTotalWinnings(player1.getAnteBet() + player1.getPlayBet());
				System.out.println("Player 1 ties.");
			}

			// Determine winnings for player 2
			if (player2Result > 0) {
				// Player 2 wins - calculate total winnings (Ante + Pair Plus + Play)
				int winnings = player2.getAnteBet() + player2.getPairPlusBet() + player2.getPlayBet();
				player2.updateTotalWinnings(winnings);
				System.out.println("Player 2 wins! Total Winnings: $" + winnings);
			} else if (player2Result < 0) {
				// Player 2 loses - no additional winnings
				System.out.println("Player 2 loses.");
			} else {
				// Tie - Player 2 gets their Ante and Play bets back
				player2.updateTotalWinnings(player2.getAnteBet() + player2.getPlayBet());
				System.out.println("Player 2 ties.");
			}

			// Update game state to RESET for next round
			gameState = GameState.RESET;
			System.out.println("Transitioning to RESET phase.");
			showResultsAndReset();
		}
	}

	// Evaluate Hands and Calculate Results (Phase 6) with Pause Transition
	private void evaluateHandsWithPause() {
		PauseTransition pause = new PauseTransition(Duration.seconds(2));
		pause.setOnFinished(event -> evaluateHands());
		pause.play();
		System.out.println("Evaluating hands with a pause."); // Debug message
	}

	private void handlePlayerDecision(Player player, Button sourceButton, Button playButton, Button foldButton,
			boolean play) {
		if (sourceButton == playButton || sourceButton == foldButton) {
			if (play) {
				player.setPlayBet(player.getAnteBet());
			} else {
				player.resetBets();
			}
		}

		int player1Result = ThreeCardLogic.compareHands(player1.getHand(), dealer.getDealersHand());
		int player2Result = ThreeCardLogic.compareHands(player2.getHand(), dealer.getDealersHand());

		// Determine winnings for player 1
		if (player1Result > 0) {
			player1.updateTotalWinnings(player1.getAnteBet() + player1.getPairPlusBet() + player1.getPlayBet());
			System.out.println("Player 1 wins!");
		} else if (player1Result < 0) {
			System.out.println("Player 1 loses.");
		} else {
			System.out.println("Player 1 ties.");
		}

		// Determine winnings for player 2
		if (player2Result > 0) {
			player2.updateTotalWinnings(player2.getAnteBet() + player2.getPairPlusBet() + player2.getPlayBet());
			System.out.println("Player 2 wins!");
		} else if (player2Result < 0) {
			System.out.println("Player 2 loses.");
		} else {
			System.out.println("Player 2 ties.");
		}
		gameState = GameState.RESET;
		System.out.println("Transitioning to RESET phase.");

	}

	private void showResultsAndReset() {
		if (gameState == GameState.RESET) {
			player1WinningsLabel.setText("Total Winnings: $" + player1.getTotalWinnings());
			player2WinningsLabel.setText("Total Winnings: $" + player2.getTotalWinnings());
			System.out.println("Game over. Results have been displayed. Ready for reset.");

			// Make reset and exit buttons visible for user to take action
			resetButton.setVisible(true);
			resetButton.setOnAction(event -> resetGame());

			exitButton.setVisible(true);
			exitButton.setOnAction(event -> exitGame(event));
		}
	}

	private void resetGame() {
		try {
			// Reset game state to BETTING
			gameState = GameState.BETTING;

			// Create new instances for player objects to reset all their attributes
			player1 = new Player();
			player2 = new Player();
			dealer = new Dealer();

			player1DecisionMade = false;
			player2DecisionMade = false;

			// Reset UI elements
			player1AnteBet.clear();
			player1PairPlusBet.clear();
			player2AnteBet.clear();
			player2PairPlusBet.clear();

			playButton1.setDisable(true);
			foldButton1.setDisable(true);
			playButton2.setDisable(true);
			foldButton2.setDisable(true);
			playerTurnIndicator.setText("Current Turn: Waiting...");

			// Load Betting Screen to start over
			loadBettingScreen();
			System.out.println("Game reset. Transitioning to BETTING phase.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}