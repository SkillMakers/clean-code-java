package boutique.pieges;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import boutique.propre.Ligne;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** Le filet de securite du refactoring : l'ancien code sert de reference sur 10 000 paniers. */
class GoldenMasterTest {

    @Test
    @DisplayName("Le premier jet propre change des prix que les tests ecrits a la main ne voient pas")
    void premierJetDifferent() {
        // GIVEN : 10 000 paniers aleatoires, graine fixe
        List<List<Ligne>> paniers = Paniers.generer(10_000, 42);
        // WHEN
        Comparaison.Resultat r = Comparaison.comparer(new boutique.propre.PrixPanier()::total, paniers);
        // THEN
        assertTrue(r.differents() > 0, "le golden master doit trouver l'ecart d'arrondi");
    }

    @Test
    @DisplayName("La version corrigee calcule exactement les memes prix que l'ancien code")
    void corrigeIdentique() {
        // GIVEN : 10 000 paniers aleatoires, graine fixe
        List<List<Ligne>> paniers = Paniers.generer(10_000, 42);
        // WHEN
        Comparaison.Resultat r = Comparaison.comparer(new boutique.corrige.PrixPanier()::total, paniers);
        // THEN
        assertEquals(0, r.differents(), r.premierExemple());
    }

    @Test
    @DisplayName("La version decoupee a l'extreme est juste, elle aussi : le defaut est la lecture, pas le calcul")
    void decoupeIdentique() {
        // GIVEN : 10 000 paniers aleatoires, graine fixe
        List<List<Ligne>> paniers = Paniers.generer(10_000, 42);
        // WHEN
        Comparaison.Resultat r = Comparaison.comparer(new PrixPanierDecoupe()::total, paniers);
        // THEN
        assertEquals(0, r.differents(), r.premierExemple());
    }
}
