package boutique.solid.i;

/** Seulement pour les moyens qui savent payer en plusieurs fois. */
public interface PaiementFractionne {
    void payerEnTroisFois(int centimes);
}
