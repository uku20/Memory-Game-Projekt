package oop;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Locale;

public class Avaleht extends Application{
    private int lauaSuurus;


    public void start(Stage primaryStage) throws Exception{
        final ToggleGroup group = new ToggleGroup();
        BorderPane avaleht = new BorderPane();
        RadioButton suurus = new RadioButton("lihtsam (4x4)");
        RadioButton suurus2 = new RadioButton("raskem (6x6)");
        suurus.setToggleGroup(group);
        suurus.setSelected(true);
        suurus2.setToggleGroup(group);
        Text nupuTekst = new Text("Vali tase: ");
        Button nupp = new Button("Alusta");
        Text text = new Text("Sisesta oma nimi: ");
        TextField textField = new TextField("");
        textField.setAlignment(Pos.CENTER);
        nupp.setOnMouseClicked(event -> {
            String tekst = textField.getText();
            try {
                boolean kasSobib = nupuVajutus(tekst);
                if (kasSobib){
                    if (suurus.isSelected()){
                        lauaSuurus = 4;
                    }
                    else{
                        lauaSuurus = 6;
                    }
                    primaryStage.close();
                    MängulaudII mängulaudII = new MängulaudII(lauaSuurus);
                    Scene scene = new Scene(mängulaudII.looSisu());
                    Stage stage = new Stage();
                    stage.setTitle("Memoriin");
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (TühiTekstErind tühiTekstErind) {
                text.setText("Tühi tekst, sisesta nimi: ");
            } catch (ViganeSisendErind viganeSisendErind) {
                text.setText("Vigane sisend, proovi uuesti: ");
            }
        });
        VBox vBox1 = new VBox(nupuTekst, suurus, suurus2, text, textField, nupp);
        vBox1.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox1, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public boolean nupuVajutus(String tekst) throws TühiTekstErind, ViganeSisendErind {
        boolean kasEdasi = false;
        String tähed = "qwertyuiopüõasdfghjklöäzxcvbnm";
        if (tekst.equals("")){
            throw new TühiTekstErind("Tühi tekst");
        }else{
            for (int i = 0; i < tekst.length(); i++) {
                if (!tähed.contains(Character.toString(tekst.charAt(i)).toLowerCase(Locale.ROOT))){
                    throw new ViganeSisendErind("Vigane sisend");
                }
                if (i==tekst.length()-1){
                    kasEdasi=true;
                }
            }
            if (kasEdasi){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        launch(args);
    }
    public int getLauaSuurus() {
        return lauaSuurus;
    }
}
