import java.util.Scanner;
import java.time.Duration;
import java.time.Instant;

public class Mängujuht {
    private Scanner scan = new Scanner(System.in);
    private int sisendMõõde;
    private int xKoordinaat;
    private int yKoordinaat;

    public void alusta() {
        System.out.println("Vali mängulaua mõõde");
        sisendMõõde = scan.nextInt();
    }

    public void küsiKaarti() {
        System.out.println("Vali kaart (kujul \"rea nr, veeru nr\"): ");
        String[] sisend = scan.nextLine().split(",");
        for (String s: sisend) s.strip();
        try {
            xKoordinaat = Integer.parseInt(sisend[0]);
            yKoordinaat = Integer.parseInt(sisend[1]);
        } catch (NumberFormatException e) {
            System.out.println("Viga, sisesta uuesti");
            küsiKaarti();
        }

    }

    public int getxKoordinaat() {
        return xKoordinaat-1;
    }

    public int getYkoordinaat() {
        return yKoordinaat-1;
    }

    public void lõpuRaport() {
        //tegevus;
    }

}
