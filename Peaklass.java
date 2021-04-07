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

        int[][] indeksid = {{1,1},{1,2},{1,3},{1,4},
                            {2,1},{2,2},{2,3},{2,4},
                            {3,1},{3,2},{3,3},{3,4},
                            {4,1},{4,2},{4,3},{4,4},{4,5},{1,1}};


        //Ilma scanneriteta vundament, saab muutujaid vabalt väärtustada ja väljundeid lisada.
        /**int mituPaari = (lauasuurus**2) / 2;
        while (õigeid!=mituPaari){
            if(x<lauasuurs && y<lauasuurus){
                String täht1 = Mänguväli.valiKaks(kate, kaardid, x, y);
                if(x2<lauasuurs && y2<lauasuurus){
                    String täht2 = Mänguväli.valiKaks(kate, kaardid, x2, y2);
                    if(!(x==x2 && y==y2)){
                        if(täht1.equals(täht2)){
                            kate[x][y] = kaardid[x][y];
                            kate[x2][y2] = kaardid[x2][y2];
                            õigeid += 1;
                        }else{
                            kate[x][y] = "#";
                            kate[x2][y2] = "#";
                        }
                    }else{
                        //midagi väljastab
                    }
                }else{
                    //midagi väljastab
                }
            }else{
                //midagi väljastab
            }
        }*/

        while (õigeid!=mituPaari) { //koos skanneriga
            System.out.println();
            Mänguväli.väljastaMängulaud(kate);
            System.out.println();
            mängujuht.küsiKaarti();
            int x = mängujuht.getxKoordinaat();
            int y = mängujuht.getYkoordinaat();
            if(x<4 && y<4){                     //lauasuurustest väiksemad
                String täht1 = Mänguväli.valiKaks(kate, kaardid, x, y);
                mängujuht.küsiKaarti();
                int x2 = mängujuht.getxKoordinaat();
                int y2 = mängujuht.getYkoordinaat();
                if(x2<4 && y2<4) {              //lauasuurustest väiksemad
                    if (!(x2 == x && y2 == y)) {
                        String täht2 = Mänguväli.valiKaks(kate, kaardid, x2, y2);
                        if (täht1.equals(täht2)) {
                            kate[x][y] = kaardid[x][y];
                            kate[x2][y2] = kaardid[x2][y2];
                            System.out.println("õige");
                            õigeid += 1;
                        } else {
                            kate[x][y] = "#";
                            kate[x2][y2] = "#";
                            System.out.println("vale");
                        }
                    }else{
                        System.out.println("võta uuesti");
                    }
                }else{
                    System.out.println("võta uuesti");
                }
            }
            else{
                System.out.println("võta uuesti");
            }

        }

        //mängujuht.lõpeta();
    }
    public static void ootaSekundeid(int n){
        try
        {
            Thread.sleep(n*1000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
}
