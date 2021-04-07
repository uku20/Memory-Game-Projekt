import java.util.Scanner;

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
        if (xKoordinaat == 0)
            System.out.println("Vali kaart (kujul \"rida[tühik]veerg\", näiteks \"1 1\"): ");
        else {
            System.out.println("Vali kaart: ");
        }
        try {
            xKoordinaat = Integer.parseInt(scan.next());
            yKoordinaat = Integer.parseInt(scan.next());
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

    public void võiduRaport(int skoor) {
        System.out.println();
        System.out.println("Palju õnne! Oled leidnud kõik paarid.\n" +
                "Lõpetasid mängu " + skoor + " punktiga.");
    }
}
