package boutique.solid.l;

/** Version avant : tout produit a un poids. */
public class Produit {

    private final String nom;
    private final int poidsGrammes;

    public Produit(String nom, int poidsGrammes) {
        this.nom = nom;
        this.poidsGrammes = poidsGrammes;
    }

    public String nom() {
        return nom;
    }

    public int poidsGrammes() {
        return poidsGrammes;
    }
}
