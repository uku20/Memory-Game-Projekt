package oop;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Kaart extends Node {
    private static final Color seljaVärv = Color.web("lightgrey");

    private Rectangle kaardiSelg;
    private Rectangle kaardiPilt;
    private Color pildiVärv;
    private boolean kasValitud;

    public Kaart(Rectangle kaardiPilt) {
        this.kaardiPilt = kaardiPilt;
        pildiVärv = (Color) kaardiPilt.getFill();
        int suurus = 3;
        kaardiSelg = new Rectangle(suurus, suurus, seljaVärv);
        kaardiSelg.setOnMouseClicked(event -> kuvaKaart());
        kasValitud = false;
    }

    public void kuvaKaart() {
        if (kaardiSelg.getFill() == seljaVärv) {
            kaardiSelg.setFill(pildiVärv);
        } else {
            kaardiSelg.setFill(seljaVärv);
        }
    }

    public Rectangle getKaardiPilt() {
        return kaardiPilt;
    }

    public Rectangle getKaardiSelg() {
        return kaardiSelg;
    }

}
