
package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }
    
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("negatiivinen kapasitteetti");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("negatiivinen kasvatuskoko");//heitin vaan jotain :D
        }
        this.ljono = new int[kapasiteetti];
        Arrays.fill(ljono, 0);
        this.alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % ljono.length == 0) {
                int[] taulukkoNew = new int[alkioidenLkm + kasvatuskoko];
                kopioiTaulukko(ljono, taulukkoNew);
                ljono = taulukkoNew;
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        int loytyneenIndeksi = -1;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                loytyneenIndeksi = i; //siis luku löytyy tuosta kohdasta :D
                ljono[loytyneenIndeksi] = 0;
                break;
            }
        }
        if (loytyneenIndeksi != -1) {
            for (int j = loytyneenIndeksi; j < alkioidenLkm - 1; j++) {
                ljono[j] = ljono[j+1];
            }
            alkioidenLkm--;
            return true;
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        StringBuilder tuotos = new StringBuilder("{");
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos.append(ljono[i]).append(", ");
        }
        if (alkioidenLkm > 0) {
            tuotos.append(ljono[alkioidenLkm - 1]);
        }
        tuotos.append("}");
        return tuotos.toString();
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = new IntJoukko();
        for (int i = 0; i < a.alkioidenLkm; i++) {
            yhdiste.lisaa(a.ljono[i]);
        }
        for (int i = 0; i < b.alkioidenLkm; i++) {
            yhdiste.lisaa(b.ljono[i]);
        }
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = new IntJoukko();
        for (int i = 0; i < a.alkioidenLkm; i++) {
            for (int j = 0; j < b.alkioidenLkm; j++) {
                if (a.ljono[i] == b.ljono[j]) {
                    leikkaus.lisaa(b.ljono[j]);
                }
            }
        }
        return leikkaus;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko erotus = new IntJoukko();
        for (int i = 0; i < a.alkioidenLkm; i++) {
            erotus.lisaa(a.ljono[i]);
        }
        for (int i = 0; i < b.alkioidenLkm; i++) {
            erotus.poista(b.ljono[i]);
        }
 
        return erotus;
    }
        
}
