import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mänguväli {

    public static String[][] algne(int lauaSuurus){
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
        //Teen tähemärkide massiivi, kust saab võtta nii palju märke, kui vaja
        //Praegu maksimaalne laua suurus mis töötaks, oleks 6x6
        String[] tähemärgid = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
                                "O", "P", "Q", "R"};
        List<String> kasutatavadTähemärgid = new ArrayList<>();
        for (int i = 0; i < mituMärki; i++) {
            kasutatavadTähemärgid.add(tähemärgid[i]);
        }
        //Loon algse tabeli, kus kõik elemendid, on "#"
        String[][] valmisTabel = new String[lauaSuurus][lauaSuurus];
        for (int i = 0; i < lauaSuurus; i++) {
            for (int j = 0; j < lauaSuurus; j++) {
                valmisTabel[i][j] = "#";
            }
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
            valmisTabel[x1][y1] = tähemärk;
            valmisTabel[x2][y2] = tähemärk;
            vabadIndeksid.remove(arv1);
            if(arv1<arv2){
                vabadIndeksid.remove(arv2 - 1);
            }else{
                vabadIndeksid.remove(arv2);
            }
            kasutatavadTähemärgid.remove(arv3);
        }
        return valmisTabel;
    }

    public static void main(String[] args) {
        String[][] tabel = algne(4);
        for (int i = 0; i < tabel.length; i++) {
            System.out.println(Arrays.toString(tabel[i]));
        }
    }
}
