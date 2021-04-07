import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mänguväli {
    private String[][] mängulaud; //määran siin mängulaua juba isendiväljana
    private int lauaSuurus; //Praegu maksimaalne laua suurus mis töötaks, oleks 6x6
    private String[] tähemärgid = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R"};

    public Mänguväli(int lauaSuurus) {
        this.lauaSuurus = lauaSuurus;
        mängulaud = new String[lauaSuurus][lauaSuurus];
    }

    public String[][] getMängulaud() {
        täidaLaud();
        return mängulaud;
    }

    // Loob "katte" ehk lauasuuruse tabeli, kus iga element on sümbol '#'
    public String[][] genereeriKate() {
        String[][] kate = new String[lauaSuurus][lauaSuurus];
        for (int i = 0; i < lauaSuurus; i++) {
            for (int j = 0; j < lauaSuurus; j++) {
                kate[i][j] = "#";
            }
        }
        return kate;
    }

    public void täidaLaud(){
        int mituMärki = (lauaSuurus * lauaSuurus)/2;
        //teen listi, kuhu panen kõik massiivi asukohtade indeksite paarid.
        List<int[]> vabadIndeksid = new ArrayList<>();
        for (int i = 0; i < lauaSuurus; i++) {
            for (int j = 0; j < lauaSuurus; j++) {
                int[] indeksipaar = new int[2];
                indeksipaar[0] = i;
                indeksipaar[1] = j;
                vabadIndeksid.add(indeksipaar);
            }
        }

        List<String> kasutatavadTähemärgid = new ArrayList<>();
        for (int i = 0; i < mituMärki; i++) {
            kasutatavadTähemärgid.add(tähemärgid[i]);
        }

        //Valin Random abiga suvalised kaks positsiooni tabelis ja ühe tähemärgi,
        //siis panen tähemärgid nendele positsioonidele ja eemaldan vastavad asjad listidest
        //ja jätkan senikaua, kuni tabel on valmis.
        while(vabadIndeksid.size()!=0){
            int arv1 = (int) (Math.round(Math.random() * (1 - (vabadIndeksid.size())) + vabadIndeksid.size())-1);
            int arv2 = (int) (Math.round(Math.random() * (1 - (vabadIndeksid.size())) + vabadIndeksid.size())-1);
            while(arv1==arv2){
                arv2 = (int) (Math.round(Math.random() * (1 - (vabadIndeksid.size())) + vabadIndeksid.size())-1);
            }
            int[] positsioon1 = vabadIndeksid.get(arv1);
            int x1 = positsioon1[0];
            int y1 = positsioon1[1];
            int[] positsioon2 = vabadIndeksid.get(arv2);
            int x2 = positsioon2[0];
            int y2 = positsioon2[1];
            int arv3 = (int) (Math.round(Math.random() * (1 - kasutatavadTähemärgid.size()) + kasutatavadTähemärgid.size())-1);
            String tähemärk = kasutatavadTähemärgid.get(arv3);
            mängulaud[x1][y1] = tähemärk;
            mängulaud[x2][y2] = tähemärk;
            vabadIndeksid.remove(arv1);
            if(arv1<arv2){
                vabadIndeksid.remove(arv2 - 1);
            }else{
                vabadIndeksid.remove(arv2);
            }
            kasutatavadTähemärgid.remove(arv3);
        }
    }
    public static void väljastaMängulaud(String[][] mängulaud){
        for (int i = 0; i < mängulaud.length; i++) {
            System.out.println(Arrays.toString(mängulaud[i]));
        }
    }
    public static String valiKaks(String[][] tühi, String[][] algne, int x, int y){
        tühi[x][y] = algne[x][y];
        return algne[x][y];
    }

}
