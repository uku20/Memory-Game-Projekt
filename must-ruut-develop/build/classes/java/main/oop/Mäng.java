package oop;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Mäng extends Application {
    private MängulaudII mängulaud;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mängulaud = new MängulaudII(4);

        BorderPane põhiPaneel = new BorderPane();
        põhiPaneel.setCenter(mängulaud.getKuvar());

        Scene stseen1 = new Scene(põhiPaneel, 700, 700, Color.SNOW);
        primaryStage.setTitle("Memoriin");
        primaryStage.setScene(stseen1);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
