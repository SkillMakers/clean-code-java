package boutique.solid.i;

/** Avant : tout moyen de paiement doit savoir tout faire. */
public interface MoyenPaiementAvant {
    void payer(int centimes);

    void rembourser(int centimes);

    void payerEnTroisFois(int centimes);
}
