package boutique.propre;

/** Une ligne du panier : un produit, son prix hors taxe en centimes, une quantite. */
public record Ligne(String produit, int prixHtCentimes, int quantite) {

    public Ligne {
        if (quantite <= 0) {
            throw new IllegalArgumentException("quantite doit etre positive : " + quantite);
        }
    }

    public int totalHtCentimes() {
        return prixHtCentimes * quantite;
    }
}
