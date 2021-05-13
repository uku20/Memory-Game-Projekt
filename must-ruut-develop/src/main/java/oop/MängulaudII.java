package oop;


import javafx.animation.FillTransition;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;


public class MängulaudII  {
    private static final double VAHE = 7;
    private int klikke = 2;
    private Kaart valitud = null;
    private int lauaSuurus;
    private int paare;
    private int leitud;
    private int skoor;

    private final String[] värviValik = {
            "aquamarine", "blueviolet", "chartreuse", "coral", "darkblue", "darkred",
            "green", "magenta", "olive", "orange", "paleturquoise", "pink",
            "red", "sienna", "tan", "thistle", "yellow", "yellowgreen"};
    private Map<String, Integer> lisamisi;
    String[] valitudVärvid;


    public MängulaudII(int lauaSuurus) {
        this.lauaSuurus = lauaSuurus;
        paare = (lauaSuurus*lauaSuurus) / 2;
        leitud = 0;
        skoor = 0;
        // Konstruktoris luua ka uus map, kuhu hiljem salvestada
        // suvaliselt valitud värvide kasutamiste arv kaartide loomisel
        lisamisi = new HashMap<>(paare);
        valiVärvid();
        // Hilisemaks kasutamiseks ka valitud värvid järjendina
        valitudVärvid = lisamisi.keySet().toArray(new String[0]);
    }

    public Parent looSisu() {
        GridPane juur = new GridPane();
        juur.setPrefSize(700,700);
        juur.setBackground(new Background(new BackgroundFill(Color.SNOW, CornerRadii.EMPTY, Insets.EMPTY)));

        List<Kaart> kaardid = new ArrayList<>();
        for (int i = 0; i < paare; i++) {
            kaardid.add(new Kaart(valitudVärvid[i]));
            kaardid.add(new Kaart(valitudVärvid[i]));
        }
        Collections.shuffle(kaardid);
        for (int i = 0; i < kaardid.size(); i++) {
            Kaart kaart = kaardid.get(i);
            int xKoordinaat = (kaart.getSUURUS() * (i%lauaSuurus));
            int yKoordinaat = (kaart.getSUURUS() * (i/lauaSuurus));
            kaart.setTranslateX(xKoordinaat);
            kaart.setTranslateY(yKoordinaat);
            juur.getChildren().add(kaart);

        }
        // Vahede tekitamine ruudustikku, ei toimi kahjuks
        //juur.setHgap(VAHE);
        //juur.setVgap(VAHE);

        return juur;
    }

    private void valiVärvid() {
        // Valib värvivalikust suvaliselt nii palju värve, kui on vaja luua lauale erinevaid paare
        // Esialgu väärtustame iga värvi väärtusena arvu 0 ehk veel pole neid värve kaartide loomisel kasutatud
        for (int i = 0; i < paare; i++) {
            while (true) {
                int genArv = new Random().nextInt(värviValik.length);
                String genVärv = värviValik[genArv];
                if (!(lisamisi.containsKey(genVärv))) {
                    lisamisi.put(genVärv, 0);
                    break;
                }
            }
        }
    }

    public class Kaart extends StackPane {
        private final int SUURUS = 100;
        private Rectangle kaardiPind;
        private Color vaikeVärv;
        private Color kaardiVärv;
        private boolean animeerib;
        private Duration aeg = Duration.millis(6000);


        public Kaart(String värv) {
            this.vaikeVärv = Color.web("lightgrey");
            this.kaardiPind = new Rectangle(SUURUS, SUURUS);
            kaardiPind.setFill(vaikeVärv);
            this.kaardiVärv = Color.web(värv);

            getChildren().addAll(kaardiPind);

            if (leitud < paare) {
                setOnMouseClicked(this::reageeriKlikile);
            } else {
                Stage lava3 = new Stage();
                lava3.setScene(new Lõpp(50, aeg).lõpuStseen());
                lava3.show();
            }
            sulge();
        }

        public void reageeriKlikile(MouseEvent me) {
            if (onKuvatud() || klikke == 0)
                return;
            klikke--;
            if (valitud == null) {
                valitud = this;
                ava(() -> {});
            } else {
                ava(() -> {
                    if (!onSamaVärvi(valitud)) {
                        skoor -= 1;
                        System.out.println("Paari ei leitud. Skoor: " + skoor);
                        valitud.sulge();
                        this.sulge();
                    } else {
                        leitud++;
                        skoor += lauaSuurus * 2;
                        System.out.println("Leiti paar! Skoor: " + skoor);
                    }
                    valitud = null;
                    klikke = 2;
                });
            }
        }

        private boolean onSamaVärvi(Kaart teineKaart) {
            return kaardiPind.getFill() == teineKaart.kaardiPind.getFill();
        }

        private boolean onKuvatud() {
            return kaardiPind.getFill() == kaardiVärv;
        }

        public void ava(Runnable action) {
            FillTransition ft = new FillTransition(Duration.seconds(0.5),
                    kaardiPind, vaikeVärv, kaardiVärv);
            ft.setOnFinished(e -> action.run());
            ft.play();
        }

        public void sulge() {
            FillTransition ft = new FillTransition(Duration.seconds(0.5),
                    kaardiPind, kaardiVärv, vaikeVärv);
            ft.play();
        }

        public int getSUURUS() {
            return SUURUS;
        }
    }
}
