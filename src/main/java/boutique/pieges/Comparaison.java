package boutique.pieges;

import boutique.avant.Calc;
import boutique.propre.Ligne;
import boutique.propre.Livraison;
import java.util.List;

/**
 * Test de caracterisation (« golden master ») : l'ancien code et le nouveau sur les memes paniers, avec et
 * sans code promo, a domicile et en magasin ; on compte les prix differents.
 */
public final class Comparaison {

    /** Un calcul de prix a comparer a {@link Calc#calc}. */
    public interface Calcul {
        int total(List<Ligne> lignes, String codePromo, Livraison livraison);
    }

    /** Le resultat : nombre de cas, de prix differents, plus grand ecart et premier exemple. */
    public record Resultat(int cas, int differents, int ecartMaxCentimes, String premierExemple) {
    }

    private Comparaison() {
    }

    public static Resultat comparer(Calcul nouveau, List<List<Ligne>> paniers) {
        int cas = 0;
        int differents = 0;
        int ecartMax = 0;
        String exemple = null;
        for (List<Ligne> panier : paniers) {
            for (String code : new String[] {null, "BIENVENUE10"}) {
                for (Livraison livraison : Livraison.values()) {
                    cas++;
                    boolean retrait = livraison == Livraison.RETRAIT_MAGASIN;
                    int attendu = Calc.calc(Paniers.versTableau(panier), code, retrait);
                    int obtenu = nouveau.total(panier, code, livraison);
                    if (attendu != obtenu) {
                        differents++;
                        ecartMax = Math.max(ecartMax, Math.abs(attendu - obtenu));
                        if (exemple == null) {
                            exemple = panier + " code=" + code + " " + livraison
                                    + " : avant " + attendu + ", apres " + obtenu;
                        }
                    }
                }
            }
        }
        return new Resultat(cas, differents, ecartMax, exemple);
    }
}
