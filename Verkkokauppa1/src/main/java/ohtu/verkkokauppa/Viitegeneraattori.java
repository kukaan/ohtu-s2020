package ohtu.verkkokauppa;

public class Viitegeneraattori implements ViitegeneraattoriInterface {

    private static ViitegeneraattoriInterface instanssi;

    public static ViitegeneraattoriInterface getInstance() {
        if (instanssi == null) {
            instanssi = new Viitegeneraattori();
        }

        return instanssi;
    }
    
    private int seuraava;
    
    private Viitegeneraattori(){
        seuraava = 1;    
    }
    
    @Override
    public int uusi(){
        return seuraava++;
    }
}
