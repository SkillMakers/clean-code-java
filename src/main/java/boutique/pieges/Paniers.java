package boutique.pieges;

import boutique.propre.Ligne;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** Paniers aleatoires mais reproductibles (graine fixe) : 1 a 6 lignes, prix HT de 1,00 a 99,99 euros, 1 a 5 pieces. */
public final class Paniers {

    private Paniers() {
    }

    public static List<List<Ligne>> generer(int nombre, long graine) {
        Random hasard = new Random(graine);
        List<List<Ligne>> paniers = new ArrayList<>();
        for (int n = 0; n < nombre; n++) {
            List<Ligne> lignes = new ArrayList<>();
            int nbLignes = 1 + hasard.nextInt(6);
            for (int i = 0; i < nbLignes; i++) {
                lignes.add(new Ligne("P" + i, 100 + hasard.nextInt(9_900), 1 + hasard.nextInt(5)));
            }
            paniers.add(lignes);
        }
        return paniers;
    }

    /** Le meme panier au format attendu par boutique.avant.Calc. */
    public static int[][] versTableau(List<Ligne> lignes) {
        return lignes.stream().map(l -> new int[] {l.prixHtCentimes(), l.quantite()}).toArray(int[][]::new);
    }
}
