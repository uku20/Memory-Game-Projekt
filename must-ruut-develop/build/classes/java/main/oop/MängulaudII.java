package oop;

import javafx.scene.Group;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.*;

public class MängulaudII  {
    private static final int KAARDI_SUURUS = 100;
    private static final int VAHE = 7;
    private String[] värviValik = {
            "aquamarine", "blueviolet", "chartreuse", "coral", "darkblue", "darkred",
            "green", "magenta", "olive", "orange", "paleturquoise", "pink",
            "red", "sienna", "tan", "thistle", "yellow", "yellowgreen"};
    private Map<String, Integer> lisamisi;
    String[] valitudVärvid;

    private TilePane kaardiPaan = new TilePane();
    private Group kuvar = new Group(kaardiPaan);
    private int lauaSuurus;
    private int paare;

    public MängulaudII(int lauaSuurus) {
        this.lauaSuurus = lauaSuurus;
        paare = (lauaSuurus*lauaSuurus) / 2;
        // Konstruktoris luua ka uus map, kuhu hiljem salvestada
        // suvaliselt valitud värvide kasutamiste arv kaartide loomisel
        lisamisi = new HashMap<>(paare);
        valiVärvid();
        // Hilisemaks kasutamiseks ka valitud värvid järjendina
        valitudVärvid = lisamisi.keySet().toArray(new String[0]);
        // Määrata ruudustiku vahed ja kaartide arv reas/veerus
        kaardiPaan.setHgap(VAHE);
        kaardiPaan.setVgap(VAHE);
        kaardiPaan.setPrefColumns(lauaSuurus);
        kaardiPaan.setPrefRows(lauaSuurus);
        // Luua kaardid
        looKaardid();

    }

    public static int getKaardiSuurus() {
        return KAARDI_SUURUS;
    }

    public Group getKuvar() {
        return kuvar;
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

    private void looKaardid() {
        // Lisada paanile iga rea ja veeru kohta uus kaart
        kaardiPaan.getChildren().clear();
        for (int i = 0; i < lauaSuurus; i++) {
            for (int j = 0; j < lauaSuurus; j++) {
                kaardiPaan.getChildren().add(looKaart().getKaardiSelg());
            }
        }
    }

    private Kaart looKaart() {
        Kaart kaart;
        while (true) {
            // Juba valitud värvide seast genereerida üks suvaline
            int genIndeks = new Random().nextInt(valitudVärvid.length);
            String genVärv = valitudVärvid[genIndeks];
            // Leida lisaks, mitu korda on seda värvi kaarte juba loodud
            int lisatud = lisamisi.get(genVärv);
            if (lisatud < 2) {
                // Seda värvi kaarte on laual kas 0 või 1, seega võib ühe juurde luua
                Rectangle kaardiPilt = new Rectangle(KAARDI_SUURUS, KAARDI_SUURUS, Color.web(genVärv));
                kaart = new Kaart(kaardiPilt);
                // Suurendada värvi kasutamiste arvu mapis ja katkestada suvalise värvi genereerimine
                lisamisi.put(genVärv, lisatud + 1);
                break;
            }
        }
        return kaart;
    }

}
