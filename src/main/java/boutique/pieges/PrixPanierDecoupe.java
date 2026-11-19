package boutique.pieges;

import boutique.propre.Ligne;
import boutique.propre.Livraison;
import java.util.List;

/**
 * Le meme calcul que boutique.corrige.PrixPanier, decoupe « une ligne par methode ». Chaque methode est
 * minuscule, et il faut sauter entre douze methodes pour suivre un calcul en trois etapes.
 */
public class PrixPanierDecoupe {

    public int total(List<Ligne> lignes, String codePromo, Livraison livraison) {
        return ajouterFraisSiBesoin(remiserSiBesoin(sommer(lignes), codePromo), livraison);
    }

    private int sommer(List<Ligne> lignes) {
        return lignes.stream().mapToInt(this::ttcDeLaLigne).sum();
    }

    private int ttcDeLaLigne(Ligne ligne) {
        return arrondir(multiplierParTaux(ligne.totalHtCentimes()));
    }

    private int multiplierParTaux(int montant) {
        return montant * taux();
    }

    private int taux() {
        return 120;
    }

    private int arrondir(int montantFoisCent) {
        return (montantFoisCent + 50) / 100;
    }

    private int remiserSiBesoin(int montant, String codePromo) {
        return codeValide(codePromo) ? remiser(montant) : montant;
    }

    private boolean codeValide(String codePromo) {
        return "BIENVENUE10".equals(codePromo);
    }

    private int remiser(int montant) {
        return montant * 90 / 100;
    }

    private int ajouterFraisSiBesoin(int montant, Livraison livraison) {
        return fraisDus(montant, livraison) ? montant + frais() : montant;
    }

    private boolean fraisDus(int montant, Livraison livraison) {
        return livraison == Livraison.DOMICILE && montant < 50_00;
    }

    private int frais() {
        return 4_90;
    }
}
