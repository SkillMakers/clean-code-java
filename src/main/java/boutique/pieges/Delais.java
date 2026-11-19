package boutique.pieges;

/** Deux constantes, deux raisons de changer : modifier le retour ne touche plus au remboursement. */
public class Delais {

    /** Choix commercial de la boutique (la loi impose au moins 14 jours). */
    static final int RETOUR_JOURS = 30;

    /** Obligation legale : rembourser au plus tard 14 jours apres la retractation. */
    static final int REMBOURSEMENT_MAX_JOURS = 14;

    public int retourJours() {
        return RETOUR_JOURS;
    }

    public int remboursementJours() {
        return REMBOURSEMENT_MAX_JOURS;
    }
}
