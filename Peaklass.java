import java.util.Arrays;

public class Peaklass {

    public static void main(String[] args) {
        Mängujuht mängujuht = new Mängujuht();
        mängujuht.juhataSisse();
        mängujuht.alusta();

        int lauaSuurus = mängujuht.getSisendMõõde();
        Mänguväli väli = new Mänguväli(lauaSuurus);
        String[][] kaardid = väli.getMängulaud();
        String[][] kate = väli.genereeriKate();
        int mituPaari = (int) (Math.pow(väli.getLauaSuurus(), 2) / 2);
        int õigeid = 0;
        int skoor = 0;

        while (õigeid!=mituPaari) {
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
                        skoor += lauaSuurus * 2;
                        System.out.println("Leidsid paari! Sinu skoor: " + skoor);
                    } else {
                        System.out.println();
                        Mänguväli.väljastaMängulaud(kate);
                        kate[x][y] = "#";
                        kate[x2][y2] = "#";
                        if (skoor > 0)
                            skoor -= 1;
                        System.out.println("\nPaari ei leitud. Sinu skoor: " + skoor);
                        ootaSekundeid(1);
                    }
                    ootaSekundeid(1);
                }else {
                    kate[x][y] = "#";
                    System.out.println("See kaart ei sobi... Proovi uuesti.");
                }
            }else {
                System.out.println("See kaart ei sobi... Proovi uuesti.");
            }
        }
        System.out.println();
        Mänguväli.väljastaMängulaud(kate);
        ootaSekundeid(2);
        mängujuht.lõpuRaport(skoor);
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
