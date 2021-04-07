import java.util.Arrays;

public class Peaklass {

    public static void main(String[] args) {
        int mituPaari = 8;//lauasuurs**2 / 2
        int õigeid = 0;
        Mänguväli väli = new Mänguväli(4);
        String[][] kaardid = väli.getMängulaud();
        String[][] kate = väli.genereeriKate();
        Mängujuht mängujuht = new Mängujuht();
        int skoor = 0;

        //mängujuht.alusta();
        Mänguväli.väljastaMängulaud(kaardid);

        while (õigeid!=mituPaari) { //koos skanneriga
            ootaSekundeid(1);
            System.out.println();
            Mänguväli.väljastaMängulaud(kate);
            System.out.println();
            mängujuht.küsiKaarti();
            int x = mängujuht.getxKoordinaat();
            int y = mängujuht.getYkoordinaat();
            if(kate[x][y].equals("#")){
                String täht1 = Mänguväli.valiKaks(kate, kaardid, x, y);
                mängujuht.küsiKaarti();
                int x2 = mängujuht.getxKoordinaat();
                int y2 = mängujuht.getYkoordinaat();
                if(kate[x2][y2].equals("#")){
                    String täht2 = Mänguväli.valiKaks(kate, kaardid, x2, y2);
                    if (täht1.equals(täht2)) {
                        kate[x][y] = kaardid[x][y];
                        kate[x2][y2] = kaardid[x2][y2];
                        õigeid += 1;
                        skoor += väli.getLauaSuurus() * 2;
                        System.out.println("Leidsid paari! ");
                    } else {
                        Mänguväli.väljastaMängulaud(kate);
                        kate[x][y] = "#";
                        kate[x2][y2] = "#";
                        skoor -= 1;
                        System.out.println("Paari ei leitud. ");
                    }
                    System.out.println("Sinu skoor: " + skoor + "\n");
                    ootaSekundeid(1);
                }else {
                    kate[x][y] = "#";
                    System.out.println("See kaart ei sobi... Proovi uuesti.");
                }
            }else {
                System.out.println("See kaart ei sobi... Proovi uuesti.");
            }
        }
        mängujuht.võiduRaport(skoor);

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
