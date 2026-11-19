package boutique.solid.i;

/** Apres : ce que tout moyen de paiement sait faire. */
public interface Paiement {
    void payer(int centimes);
}
