import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXTemplate extends Application {

	private static Stage primaryStage; // Make primaryStage static to ensure it’s accessible in static contexts

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
		System.out.println("Begin Game button pressed");
	}

	public void play(ActionEvent event) {
		System.out.println("Play button pressed");
	}

	public void fold(ActionEvent event) {
		System.out.println("Fold button pressed");
	}

	public void exitGame(ActionEvent event) {
		System.out.println("Exit option selected");
	}

	public void freshStart(ActionEvent event) {
		System.out.println("Fresh Start option selected");
	}

	public void applyNewLook(ActionEvent event) {
		System.out.println("New Look option selected");
	}
}
