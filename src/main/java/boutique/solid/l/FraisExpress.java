package boutique.solid.l;

import java.util.List;

/** 9,90 euros plus 1 euro par kilo entame. Ecrit pour des Produit, il ne connait pas les sous-classes. */
public class FraisExpress {

    public int frais(List<Produit> produits) {
        return pourGrammes(produits.stream().mapToInt(Produit::poidsGrammes).sum());
    }

    /** Le tarif lui-meme, partage avec FraisExpressCorrige : un seul endroit a changer. */
    static int pourGrammes(int grammes) {
        int kilosEntames = (grammes + 999) / 1000;
        return 9_90 + kilosEntames * 1_00;
    }
}
