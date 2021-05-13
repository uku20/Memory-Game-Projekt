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

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class Avaleht extends Application{
    private int lauaSuurus;


    public void start(Stage primaryStage) throws Exception{
        //Loon nupude jaoks togglegrupi ja loon nupud
        final ToggleGroup group = new ToggleGroup();
        RadioButton suurus = new RadioButton("lihtsam (4x4)");
        RadioButton suurus2 = new RadioButton("raskem (6x6)");
        //Panen nupud gruppi ja määran default valiku
        suurus.setToggleGroup(group);
        suurus.setSelected(true);
        suurus2.setToggleGroup(group);
        //Teen informatiivse teksti, nupu ja textfieldi, kuhu saab oma nime kirjutada
        Text nupuTekst = new Text("Vali tase: ");
        Button nupp = new Button("Alusta");
        Text text = new Text("Sisesta oma nimi: ");
        TextField textField = new TextField("");
        textField.setAlignment(Pos.CENTER);
        //Nupule vajutusel käivitub see osa
        nupp.setOnMouseClicked(event -> {
            //Loeme, mis kirjas on
            String tekst = textField.getText();
            try {
                //Kontrollin üle, kas sõne on korras
                boolean kasSobib = nupuVajutus(tekst);
                if (kasSobib){
                    //Määran laua suuruse
                    if (suurus.isSelected()){
                        lauaSuurus = 4;
                    }
                    else{
                        lauaSuurus = 6;
                    }
                    //Sulgen esimese akna
                    primaryStage.close();
                    //Tegelen failiga
                    failiTöö(tekst);
                    //Loon teise akna
                    MängulaudII mängulaud = new MängulaudII(lauaSuurus);
                    Scene scene = new Scene(mängulaud.looSisu());
                    Stage stage = new Stage();
                    stage.setTitle("Memoriin");
                    stage.setScene(scene);
                    stage.show();
                }
            }
            //Teavitan kasutajat, kui sõne on tühi
            catch (TühiTekstErind tühiTekstErind) {
                text.setText("Tühi tekst, sisesta nimi: ");
            }
            //Teavitan kasutajat, kui sõne oli vigane
            catch (ViganeSisendErind viganeSisendErind) {
                text.setText("Vigane sisend, proovi uuesti: ");
            }catch (Exception e){
                //Kui failiga peaks probleeme esinema
                System.out.println("Failiga oli probleeme");
            }
        });
        //Loon akna, milles on eelnevalt nimetatud komponendid
        VBox vBox1 = new VBox(nupuTekst, suurus, suurus2, text, textField, nupp);
        vBox1.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox1, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public boolean nupuVajutus(String tekst) throws TühiTekstErind, ViganeSisendErind {
        //Kontrollin, et sõnes oleksid ainult tähed
        boolean kasEdasi = false;
        String tähed = "qwertyuiopüõasdfghjklöäzxcvbnm";
        if (tekst.equals("")){
            //Kui sõne on tühi, on viga
            throw new TühiTekstErind("Tühi tekst");
        }else{
            //Kui sõnes on vale sümbol, on viga
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
    public static void failiTöö(String nimi) throws Exception{
        //Panen faili kirja, millal oli esimene kord, kui sellise nimega mängija mängu mängis
        List<String> nimed = new ArrayList<>();
        File mängud = new File("EsimesedMänguKorrad.txt");
        //Võtan kuupäeva ilma ajata
        Date currentDate = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MMM/yyyy");
        String dateOnly = dateFormat.format(currentDate);
        //Kontrollin, kas fail juba eksisteerib
        boolean kasOnOlemas = mängud.exists();
        if(!kasOnOlemas){
            //Kui faili ei eksisteeri, panen nime ja aja kirja
            try(PrintWriter out = new PrintWriter(new FileWriter(mängud, true))){
                out.write(nimi + " " + dateOnly + "\n");
            }
        }
        else{
            //Kui fail eksiteerib, loen faili läbi, et kontrollida, kas see nimi on juba failis
            try(Scanner sc = new Scanner(mängud, StandardCharsets.UTF_8)){
                while (sc.hasNextLine()){
                    String rida = sc.nextLine();
                    String[] jupid = rida.split(" ");
                    String vananimi = jupid[0];
                    nimed.add(vananimi);
                }
            }
            //Kui nime ei ole failis, pane selle sinna
            if (!nimed.contains(nimi)){
                try(PrintWriter out = new PrintWriter(new FileWriter(mängud, true))){
                    out.write(nimi + " " + dateOnly + "\n");
                }
            }
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

    public int getLauaSuurus() {
        return lauaSuurus;
    }
}
