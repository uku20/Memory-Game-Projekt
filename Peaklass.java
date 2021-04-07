import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

public class Peaklass {

    public static void main(String[] args) {
        /*Mänguväli väli = new Mänguväli(4);
        String[][] kaardid = väli.getMängulaud();
        for (int i = 0; i < kaardid.length; i++) {
            System.out.println(Arrays.toString(kaardid[i]));
        }
        System.out.println();
        String[][] kate = väli.genereeriKate();
        for (int i = 0; i < kate.length; i++) {
            System.out.println(Arrays.toString(kate[i]));
        }*/
        int mituPaari = 8;//lauasuurs**2 / 2
        int õigeid = 0;
        Mänguväli väli = new Mänguväli(4);
        String[][] kaardid = väli.getMängulaud();
        String[][] kate = väli.genereeriKate();

        Mängujuht mängujuht = new Mängujuht();

        //mängujuht.alusta();
        Mänguväli.väljastaMängulaud(kaardid);

        while (õigeid!=mituPaari) {
            System.out.println();
            Mänguväli.väljastaMängulaud(kate);
            System.out.println();
            mängujuht.küsiKaarti();
            int x = mängujuht.getxKoordinaat();
            int y = mängujuht.getYkoordinaat();
            String täht1 = Mänguväli.valiKaks(kate, kaardid, x, y);
            mängujuht.küsiKaarti();
            int x2 = mängujuht.getxKoordinaat();
            int y2 = mängujuht.getYkoordinaat();
            String täht2 = Mänguväli.valiKaks(kate, kaardid, x2, y2);
            if(täht1.equals(täht2)){
                kate[x][y] = kaardid[x][y];
                kate[x2][y2] = kaardid[x2][y2];
                System.out.println("õige");
                õigeid += 1;
            }else{
                kate[x][y] = "#";
                kate[x2][y2] = "#";
                System.out.println("vale");
            }
        }

        //mängujuht.lõpeta();
    }
}
