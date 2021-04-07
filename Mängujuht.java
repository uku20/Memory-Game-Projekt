import java.util.Scanner;
import java.time.Duration;
import java.time.Instant;

public class Mängujuht {
    private Scanner scan = new Scanner(System.in);
    private int sisendMõõde;
    private int xKoordinaat;
    private int ykoordinaat;

    public void alusta() {
        System.out.println("Vali mängulaua mõõde");
        sisendMõõde = scan.nextInt();
    }

    public void küsiKaarti() {
        System.out.println("Vali x koordinaat");
        xKoordinaat = scan.nextInt();
        System.out.println("Vali y koordinaat");
        ykoordinaat = scan.nextInt();
    }

    public int getxKoordinaat() {
        return xKoordinaat;
    }

    public int getYkoordinaat() {
        return ykoordinaat;
    }

    public void lõpuRaport() {
        //tegevus;
    }

}
