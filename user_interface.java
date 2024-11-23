import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class user_interface extends Application {
    static Label turnLabel;
    static Button[][] buttonArray;
    static Scene sceneVar;
    static TicTacToe game;

    private void buttonClick(String id) {
        Button button = (Button) sceneVar.lookup("#" + id);
        int convertedID = Integer.parseInt(id);
        if (!game.win && game.turn(convertedID / 10, convertedID % 10)) {
            if (game.user == 1) {
                button.setText("X");
                turnLabel.setText("PLAYER 1 (X) TURN");
            } else if (game.user == -1) {
                button.setText("O");
                turnLabel.setText("PLAYER 2 (O) TURN");
            }

            if (game.win) {
                if (game.user == 1) {
                    turnLabel.setText("PLAYER 1 (X) WON!");
                } else if (game.user == -1) {
                    turnLabel.setText("PLAYER 2 (O) WON!");
                }
            }
        }
    }

    public user_interface() {
        buttonArray = new Button[5][5];
        for (int i = 0; i < 5; i++) {
            Button[] buttonRow = new Button[5];
            for (int j = 0; j < 5; j++) {
                buttonRow[j] = new Button();
                Button buttonVar = buttonRow[j];
                buttonVar.setMinSize(60, 60);
                buttonVar.setId(Integer.toString(10 * i + j));
                buttonVar.setOnAction(evt -> buttonClick(buttonVar.getId()));
            }
            buttonArray[i] = buttonRow;
        }

        game = new TicTacToe();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        turnLabel = new Label();

        VBox screen = new VBox(5);

        HBox buttonColumns = new HBox();
        for (int i = 0; i < 5; i++) {
            VBox buttonRows = new VBox();
            for (Button btn : buttonArray[i]) {
                buttonRows.getChildren().add(btn);
            }
            buttonColumns.getChildren().add(buttonRows);
        }

        screen.getChildren().addAll(buttonColumns, turnLabel);
        StackPane layout = new StackPane();
        layout.getChildren().add(screen);

        primaryStage.setTitle("TicTacToe");
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(600);

        sceneVar = new Scene(layout);
        primaryStage.setScene(sceneVar);
        primaryStage.show();
    }
}
