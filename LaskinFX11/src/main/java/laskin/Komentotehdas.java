import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import laskin.*;

public class Komentotehdas {

    TextField tuloskentta;
    TextField syotekentta;
    Button nollaa;
    Button undo;
    Sovelluslogiikka sovellus;

    public Komentotehdas(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
    }

    public Komento hae(String operaatio) {
        if (operaatio.equals("summa")) {
            return new Summa(tuloskentta, syotekentta,  nollaa, undo, sovellus);
        } else if (operaatio.equals("erotus")) {
            return new Erotus(tuloskentta, syotekentta,  nollaa, undo, sovellus);
        } else if (operaatio.equals("nollaa")) {
            return new Nollaa(tuloskentta, syotekentta,  nollaa, undo, sovellus);
        }
        return null;
    }
}
