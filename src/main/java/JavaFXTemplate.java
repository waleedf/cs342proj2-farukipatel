import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import java.util.ArrayList;

public class JavaFXTemplate extends Application {

	private static Stage primaryStage; // Make primaryStage static to ensure it’s accessible in static contexts

	private VBox gameplayPhase;
	@FXML
	private Label playerTurnIndicator, player1AnteBetLabel, player1PairPlusBetLabel, player1PlayBetLabel, player1WinningsLabel;

	@FXML
	private ImageView dealerCard1, dealerCard2, dealerCard3, playerCard1, playerCard2, playerCard3;

	@FXML
	private TextField anteBetField, pairPlusBetField;

	@FXML
	private Label currentBetAmountLabel, gameInfo;

    private Dealer dealer;
    private Player player1;

	private String cardPath = "/cards/";

	public void initialize() {
		dealer = new Dealer();
		player1 = new Player();
	}
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		JavaFXTemplate.primaryStage = primaryStage; // Assign to static variable

		// Load the start screen FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource("start.fxml"));
		Parent root = loader.load();

		// Set the scene with the loaded FXML layout
		Scene scene = new Scene(root, 700, 700);
		primaryStage.setTitle("Three-Card Poker - Start Screen");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void startGame(ActionEvent event) {
		try {
			// Load betting.fxml
			FXMLLoader loader = new FXMLLoader(getClass().getResource("betting.fxml"));
			Parent bettingRoot = loader.load();

			// Set the scene to betting.fxml
			Scene bettingScene = new Scene(bettingRoot, 700, 700);
			primaryStage.setScene(bettingScene); // Use the static variable primaryStage
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showPlayerInfo(ActionEvent event) {
		System.out.println("Player Info button pressed");
	}

	public void showOptions(ActionEvent event) {
		System.out.println("Options button pressed");
	}

	public void beginGame(ActionEvent event) {
		try {
			System.out.println("Begin Game button pressed");
			int anteBet = Integer.parseInt(anteBetField.getText());
			int pairPlusBet = Integer.parseInt(pairPlusBetField.getText());
			player1.setAnteBet(anteBet);
			player1.setPairPlusBet(pairPlusBet);
			dealer.dealDealersHand();
			player1.setHand(dealer.dealHand());
			updateUI();
			updateCardImages(player1.getHand(), playerCard1, playerCard2, playerCard3);
			updateCardImages(dealer.getDealersHand(), dealerCard1, dealerCard2, dealerCard3);
		} catch (Exception e) {
			gameInfo.setText("Error: " + e.getMessage());
		}
	}

	private void updateCardImages(ArrayList<Card> hand, ImageView... imageViews) {
		for(int i = 0; i < hand.size(); ++i) {
			String filename = hand.get(i).getImageFileName();
			imageViews[i].setImage(new Image(getClass().getResourceAsStream(cardPath + filename)));
		}
	}

	

	private void updateUI() {
        player1AnteBetLabel.setText("Ante: $" + player1.getAnteBet());
        player1PairPlusBetLabel.setText("Pair Plus: $" + player1.getPairPlusBet());
        player1PlayBetLabel.setText("Play: $" + player1.getPlayBet());
        player1WinningsLabel.setText("Total Winnings: $" + player1.getTotalWinnings());
    }

	public void play(ActionEvent event) {
		System.out.println("Play button pressed");
		player1.setPlayBet(player1.getAnteBet());
		
	}

	public void fold(ActionEvent event) {
		System.out.println("Fold button pressed");
		gameInfo.setText("You fold! Dealer wins!");
		resetBets();
	}

	private void resetBets() {
		player1.resetBets();
		updateUI();
	}

	public void exitGame(ActionEvent event) {
		System.out.println("Exit option selected");
	}

	public void freshStart(ActionEvent event) {
		System.out.println("Fresh Start option selected");
		dealer = new Dealer();
		player1 = new Player();
		gameplayPhase.setVisible(false);
		updateUI();
	}

	public void applyNewLook(ActionEvent event) {
		System.out.println("New Look option selected");
	}
}
