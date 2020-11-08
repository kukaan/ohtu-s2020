package ohtu.verkkokauppa;

public class Pankki implements PankkiInterface {

    private KirjanpitoInterface kirjanpitoInterface;

    public Pankki(KirjanpitoInterface kirjanpitoInterface) {
        this.kirjanpitoInterface = kirjanpitoInterface;
    }

    @Override
    public boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa) {
        kirjanpitoInterface.lisaaTapahtuma("tilisiirto: tililtä " + tilille + " tilille " + tilille
                + " viite " + viitenumero + " summa " + summa + "e");

        // täällä olisi koodi joka ottaa yhteyden pankin verkkorajapintaan
        return true;
    }
}
