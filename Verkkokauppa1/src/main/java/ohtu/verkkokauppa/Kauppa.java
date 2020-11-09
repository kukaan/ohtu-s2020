package ohtu.verkkokauppa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Kauppa {

    @Autowired
    private VarastoInterface varastoInterface;
    @Autowired
    private PankkiInterface pankkiInterface;

    private Ostoskori ostoskori;
    @Autowired
    private ViitegeneraattoriInterface viitegeneraattoriInterface;

    private String kaupanTili;

    public Kauppa() {
        kaupanTili = "33333-44455";
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = varastoInterface.haeTuote(id);
        varastoInterface.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (varastoInterface.saldo(id)>0) {
            Tuote t = varastoInterface.haeTuote(id);
            ostoskori.lisaa(t);
            varastoInterface.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = viitegeneraattoriInterface.uusi();
        int summa = ostoskori.hinta();
        
        return pankkiInterface.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
