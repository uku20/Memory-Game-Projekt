import java.util.InputMismatchException;
import java.util.Scanner;

public class Mängujuht {
    private Scanner scan = new Scanner(System.in);
    private int sisendMõõde; //Kasutaja sisestusega seotud laua suurus
    private int xKoordinaat; //Sisestatud rea nr
    private int yKoordinaat; //Sisestatud veeru nr

    public int getSisendMõõde() {
        return sisendMõõde;
    }

    //Kasutusjuhise väljastamiseks
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

    //Kasutajalt mängulaua mõõtme küsimiseks
    public void alusta() {
        boolean mõõdeValitud = false;
        //Kordan tegevust seni, kuni mõõde on valitud
        do {
            System.out.println("\nVali mängulaua mõõde: ");
            try {
                //Kui sisestati täisarv, siis salvestan selle
                int sisend = scan.nextInt();
                //Kontrollin sisendi tingimusi
                if (sisend == 4 || sisend == 6) {
                    sisendMõõde = sisend;
                    mõõdeValitud = true;
                } else //Sisestati vale arv
                    System.out.println("See mõõde ei sobi. Proovi uuesti");
                scan.nextLine();
            } catch (InputMismatchException e) { //Sisestati midagi muud peale arvu
                System.out.println("See mõõde ei sobi. Proovi uuesti");
                scan.nextLine();
            }
        }
        while (!mõõdeValitud);
    }

    //Kasutajalt elemendi asukoha küsimiseks
    public void küsiKaarti() {
        //Esimesele käigule eelnev juhend
        if (xKoordinaat == 0)
            System.out.println("""
                    Sisesta kaardi asukoht kujul "rida[tühik]veerg".\s
                    Näiteks "1 1" on vasakul üleval nurgas olev kaart.\s """);
        System.out.println("Vali kaart: ");
        try {
            //Kui sisestati täisarvud, siis väärtustan isendiväljad
            xKoordinaat = Integer.parseInt(scan.next());
            yKoordinaat = Integer.parseInt(scan.next());
        } catch (NumberFormatException e) { //Sisestati midagi muud
            System.out.println("Viga, sisesta uuesti");
            küsiKaarti();
        }
    }
    //Indeksi saamiseks
    public int getxKoordinaat() {
        return xKoordinaat-1;
    }
    public int getYkoordinaat() {
        return yKoordinaat-1;
    }

    //Lõpptulemuse väljastamiseks
    public void lõpuRaport(int skoor) {
        System.out.println();
        System.out.println("Palju õnne! Oled leidnud kõik paarid.\n" +
                "Lõpetasid mängu " + skoor + " punktiga.");
    }
}
