package boutique.pieges;

/**
 * Deux 14 qui se ressemblaient, fusionnes au nom de « ne te repete pas ». Le delai de retour est une regle
 * commerciale ; le delai de remboursement est une obligation legale. Ils ne changent pas pour les memes raisons.
 */
public class DelaisAvant {

    // etait 14 ; passe a 30 pour l'operation « 30 jours pour changer d'avis »
    static final int DELAI_JOURS = 30;

    public int retourJours() {
        return DELAI_JOURS;
    }

    public int remboursementJours() {
        return DELAI_JOURS;
    }
}
