package boutique.pieges;

import boutique.propre.Ligne;
import java.util.List;

/** Les trois versions propres face a l'ancien code, sur 10 000 paniers (graine 42). */
public class DemoGoldenMaster {

    public static void main(String[] args) {
        List<List<Ligne>> paniers = Paniers.generer(10_000, 42);
        afficher("premier jet (propre.PrixPanier)", Comparaison.comparer(new boutique.propre.PrixPanier()::total, paniers));
        afficher("corrige (corrige.PrixPanier)", Comparaison.comparer(new boutique.corrige.PrixPanier()::total, paniers));
        afficher("decoupe (pieges.PrixPanierDecoupe)", Comparaison.comparer(new PrixPanierDecoupe()::total, paniers));
    }

    private static void afficher(String nom, Comparaison.Resultat r) {
        System.out.printf("%-36s %d cas, %d prix differents (%.1f %%), ecart max %d centime(s)%n",
                nom, r.cas(), r.differents(), 100.0 * r.differents() / r.cas(), r.ecartMaxCentimes());
        if (r.premierExemple() != null) {
            System.out.println("   1er exemple : " + r.premierExemple());
        }
    }
}
