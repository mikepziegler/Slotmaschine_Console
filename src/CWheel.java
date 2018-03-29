import java.util.*;

public class CWheel {


    private Random rdm;

    private String[] symbolname = {
            "Tomate", "Banane", "Traube",
            "Herz", "Kachel", "Ass",
            "Kreuz", "Geld", "7"
    };


    public CWheel() {

        rdm = new Random();

    }

    public int rollWheel() {

        int i = Math.abs(rdm.nextInt() % 9);

        int symbol = i;

        return symbol;
    }

    public String getSymbolname(int nr) {
        return symbolname[nr];
    }
}
