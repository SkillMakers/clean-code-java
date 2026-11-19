package boutique.propre;

import java.util.List;

/**
 * Premier jet de la version propre de {@link boutique.avant.Calc} : noms, constantes, petites methodes.
 * Elle passe les tests ecrits a la main, et pourtant elle ne calcule pas toujours le meme prix :
 * voir {@code DemoGoldenMaster} et {@link boutique.corrige.PrixPanier}.
 */
public class PrixPanier {

    static final int TVA_POURCENT = 20;
    static final String CODE_BIENVENUE = "BIENVENUE10";
    static final int REMISE_BIENVENUE_POURCENT = 10;
    static final int SEUIL_LIVRAISON_OFFERTE = 50_00;
    static final int FRAIS_LIVRAISON = 4_90;

    public int total(List<Ligne> lignes, String codePromo, Livraison livraison) {
        int totalTtc = ajouterTva(totalHt(lignes));
        int apresRemise = appliquerRemise(totalTtc, codePromo);
        return ajouterFraisDeLivraison(apresRemise, livraison);
    }

    private int totalHt(List<Ligne> lignes) {
        return lignes.stream().mapToInt(Ligne::totalHtCentimes).sum();
    }

    private int ajouterTva(int montantHt) {
        // arrondi au centime le plus proche (un montant HT x 1,2 ne tombe jamais pile sur un demi-centime)
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
