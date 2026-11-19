package boutique.corrige;

import boutique.propre.Ligne;
import boutique.propre.Livraison;
import java.util.List;

/**
 * Version propre ET fidele de {@link boutique.avant.Calc}. La seule difference avec le premier jet :
 * la TVA est arrondie ligne par ligne, comme dans l'original, et la methode le dit dans son nom.
 */
public class PrixPanier {

    static final int TVA_POURCENT = 20;
    static final String CODE_BIENVENUE = "BIENVENUE10";
    static final int REMISE_BIENVENUE_POURCENT = 10;
    static final int SEUIL_LIVRAISON_OFFERTE = 50_00;
    static final int FRAIS_LIVRAISON = 4_90;

    public int total(List<Ligne> lignes, String codePromo, Livraison livraison) {
        int totalTtc = totalTtcArrondiParLigne(lignes);
        int apresRemise = appliquerRemise(totalTtc, codePromo);
        return ajouterFraisDeLivraison(apresRemise, livraison);
    }

    private int totalTtcArrondiParLigne(List<Ligne> lignes) {
        return lignes.stream().mapToInt(ligne -> ajouterTva(ligne.totalHtCentimes())).sum();
    }

    private int ajouterTva(int montantHt) {
        return (montantHt * (100 + TVA_POURCENT) + 50) / 100;
    }

    private int appliquerRemise(int montant, String codePromo) {
        if (!CODE_BIENVENUE.equals(codePromo)) {
            return montant;
        }
        return montant * (100 - REMISE_BIENVENUE_POURCENT) / 100;
    }

    private int ajouterFraisDeLivraison(int montant, Livraison livraison) {
        if (livraison == Livraison.RETRAIT_MAGASIN || montant >= SEUIL_LIVRAISON_OFFERTE) {
            return montant;
        }
        return montant + FRAIS_LIVRAISON;
    }
}
