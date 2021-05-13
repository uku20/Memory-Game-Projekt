package oop;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Lõpp {
    private int lõppSkoor;
    private Duration kulunudAeg;

    public Lõpp(int lõppSkoor, Duration aeg) {
        this.lõppSkoor = lõppSkoor;
        kulunudAeg = aeg;
    }

    public Scene lõpuStseen() {
        GridPane paneel = new GridPane();
        paneel.setAlignment(Pos.CENTER);
        paneel.setVgap(25); paneel.setPadding(new Insets(25,25,25,25));

        Text õnnitlus = new Text("Palju õnne! Leidsid kõik paarid. Sinu lõppskooriks on");
        paneel.add(õnnitlus, 0, 0);

        Text skooriAla = new Text(Integer.toString(lõppSkoor));
        skooriAla.setFont(Font.font("Arial", 50));
        paneel.add(skooriAla, 0, 1);

        Label ajaInfo = new Label("Aega kulus " + kulunudAeg.toSeconds() + " sekundit");
        paneel.add(ajaInfo, 0, 2);
        Button algusesse = new Button("Algusesse");
        paneel.add(algusesse, 0, 3);

        for (Node alam : paneel.getChildren()) {
            GridPane.setHalignment(alam, HPos.CENTER);
            GridPane.setValignment(alam, VPos.CENTER);
        }

        return new Scene(paneel, 400, 300);

    }

}
