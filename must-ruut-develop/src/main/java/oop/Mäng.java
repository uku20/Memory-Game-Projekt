package oop;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Mäng extends Application {
    private MängulaudII mängulaud;
    private Avaleht avaleht;
    private static boolean vaheta = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mängulaud = new MängulaudII(4);
        double aknaSuurus = 700;

        Scene stseen1 = new Scene(mängulaud.looSisu());
        primaryStage.setTitle("Memoriin");
        primaryStage.setScene(stseen1);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
