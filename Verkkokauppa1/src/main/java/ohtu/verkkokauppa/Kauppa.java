package ohtu.verkkokauppa;

public class Kauppa {

    private VarastoInterface varastoInterface;
    private PankkiInterface pankkiInterface;
    private Ostoskori ostoskori;
    private ViitegeneraattoriInterface viitegeneraattoriInterface;
    private String kaupanTili;

    public Kauppa(VarastoInterface varastoInterface, PankkiInterface pankkiInterface, ViitegeneraattoriInterface viitegeneraattoriInterface) {
        this.varastoInterface = varastoInterface;
        this.pankkiInterface = pankkiInterface;
        this.viitegeneraattoriInterface = viitegeneraattoriInterface;
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
