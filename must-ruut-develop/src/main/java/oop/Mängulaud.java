package oop;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Mängulaud extends Application {

    public static int VAHED = 8;
    private TextField ekraan;
    private int lauaSuurus;

    @Override
    public void start(Stage primaryStage) throws Exception {
        lauaSuurus = 4;
        ekraan = new TextField();
        ekraan.setAlignment(Pos.CENTER_LEFT);
        BorderPane erinupud = looErinupud();

        HBox tavanupud = new HBox(VAHED);
        for (int i = 0; i < lauaSuurus; i++) {
            tavanupud.getChildren().addAll(
                    looNupuVeerg(lauaSuurus, i + 1)
            );
        }

        VBox mäng = new VBox(VAHED, tavanupud, erinupud, ekraan);
        mäng.setPadding(new Insets(10));
        Scene stseen = new Scene(mäng);
        primaryStage.setScene(stseen);
        primaryStage.setResizable(true);
        primaryStage.show();

    }

    private BorderPane looErinupud() {
        BorderPane piiripaan = new BorderPane();

        Button backspace = new Button("Lõpeta");
        backspace.setTextFill(Color.RED);
        backspace.setOnMouseClicked(event -> {
            //Siia tuleb lõpeta programm käsud
        });
        Button c = new Button("Parim skoor");
        c.setTextFill(Color.RED);
        c.setOnMouseClicked(event -> {
            //See võiks lugeda parima skoori
        });
        HBox hbox = new HBox(VAHED, backspace, c);
        piiripaan.setLeft(hbox);
        return piiripaan;
    }


    private void reageeri(int x, int y, Button nupp){
        ekraan.setText(ekraan.getText() + x + " " + y);
        String märk = nupp.getText();
        if (märk.equals("#")){
            nupp.setText("A");
        }
        else{
            nupp.setText("#");
        }
    }
    private VBox looNupuVeerg(int mitu, int mitmes){
        VBox nupuVeerg = new VBox(VAHED);
        for (int i = 0; i < mitu; i++) {
            int x = i + 1;
            Button nupp = new Button("#");
            nupp.setMinWidth(40);
            nupp.setMinHeight(25);
            nupp.setTextFill(Color.BLACK);
            nupp.setOnMouseClicked(event -> reageeri(x, mitmes, nupp));
            nupuVeerg.getChildren().add(nupp);
        }
        return nupuVeerg;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
