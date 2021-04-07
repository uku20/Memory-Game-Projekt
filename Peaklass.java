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
        Mänguväli väli = new Mänguväli(4);
        String[][] kaardid = väli.getMängulaud();
        String[][] kate = väli.genereeriKate();

        Mängujuht mängujuht = new Mängujuht();

        mängujuht.alusta();

        while (kate != kaardid) {
            //mängujuht.küsiKaarti(kaardid);
        }

        //mängujuht.lõpeta();
    }
}
