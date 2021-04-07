import java.util.Scanner;
import java.time.Duration;
import java.time.Instant;

public class Mängujuht {
    private Scanner scan = new Scanner(System.in);
    private int sisendMõõde;

    public void alusta() {
        System.out.println("Vali mängulaua mõõde");
        sisendMõõde = scan.nextInt();
    }

    public void küsiKaarti() {
        //tegevus;
    }

    public void lõpuRaport() {
        //tegevus;
    }

}
