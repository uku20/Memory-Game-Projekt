import java.util.InputMismatchException;
import java.util.Scanner;

public class Mängujuht {
    private Scanner scan = new Scanner(System.in);
    private int sisendMõõde;
    private int xKoordinaat;
    private int yKoordinaat;

    public int getSisendMõõde() {
        return sisendMõõde;
    }

    public void juhataSisse() {
        Peaklass.ootaSekundeid(1);
        System.out.println(
                """
                        Tere tulemast! 
                                                
                        Oled asunud mängima memoriini - pead mängulaualt üles leidma kõik ühesuguste sümbolite paarid.\s
                        Igal käigul küsin sinult nende kahe kaardi rea ja veeru numbrit, mida soovid ümber pöörata.\s
                        Pakkumisega tuleb püsida mängulaua mõõtmetes. Sinu eelnevad sisestused jäävad küll ekraanile,\s
                        ent mänguilu mõttes jäta enda vaatevälja ainult vahetult eelnev käik. Iga üles leitud paar annab \s
                        punkte vastavalt valemile (kaartide arv reas * 2). Iga vale paar võtab ühe punkti maha.\s
                        """);
        System.out.println(
                """
                        Alustame mängulaua mõõtmetest. Et mäng oleks piisavalt huvitav, aga mitte liiga pikk,\s
                        saad valida mõõtmete 4 ja 6 vahel. See tähendab, sisestades ühe neist täisarvudest,\s
                        genereeritakse kas 4x4 või 6x6 suuruses mängulaud.""");
    }

    public void alusta() {
        boolean mõõdeValitud = false;
        do {
            System.out.println("\nVali mängulaua mõõde: ");
            try {
                int sisend = scan.nextInt();
                if (sisend == 4 || sisend == 6) {
                    sisendMõõde = sisend;
                    mõõdeValitud = true;
                } else
                    System.out.println("See mõõde ei sobi. Proovi uuesti");
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("See mõõde ei sobi. Proovi uuesti");
                scan.nextLine();
            }
        }
        while (!mõõdeValitud);
    }

    public void küsiKaarti() {
        if (xKoordinaat == 0)
            System.out.println("""
                    Sisesta kaardi asukoht kujul "rida[tühik]veerg".\s
                    Näiteks "1 1" on vasakul üleval nurgas olev kaart.\s """);
        System.out.println("Vali kaart: ");
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

    public void lõpuRaport(int skoor) {
        System.out.println();
        System.out.println("Palju õnne! Oled leidnud kõik paarid.\n" +
                "Lõpetasid mängu " + skoor + " punktiga.");
    }
}
