package boutique.solid.l;

/** Un e-book n'a pas de poids : la sous-classe refuse ce que la classe mere promet. */
public class LivreNumerique extends Produit {

    public LivreNumerique(String nom) {
        super(nom, 0);
    }

    @Override
    public int poidsGrammes() {
        throw new UnsupportedOperationException("un livre numerique n'a pas de poids");
    }
}
