package boutique.propre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** Les tests ecrits a la main pour le premier jet : ils passent tous, et le premier jet est pourtant faux. */
class PrixPanierTest {

    private final PrixPanier prix = new PrixPanier();

    @Test
    @DisplayName("Un livre a 19,90 HT livre a domicile : 23,88 TTC + 4,90 de frais")
    void unLivreADomicile() {
        // GIVEN
        List<Ligne> panier = List.of(new Ligne("Livre", 19_90, 1));
        // WHEN
        int total = prix.total(panier, null, Livraison.DOMICILE);
        // THEN
        assertEquals(28_78, total);
    }

    @Test
    @DisplayName("70,00 HT avec BIENVENUE10 : 84,00 TTC, moins 10 %, livraison offerte")
    void codePromoEtLivraisonOfferte() {
        // GIVEN
        List<Ligne> panier = List.of(new Ligne("Lampe", 45_00, 1), new Ligne("Bougie", 12_50, 2));
        // WHEN
        int total = prix.total(panier, "BIENVENUE10", Livraison.DOMICILE);
        // THEN
        assertEquals(75_60, total);
    }

    @Test
    @DisplayName("Retrait en magasin : jamais de frais de livraison")
    void retraitEnMagasin() {
        // GIVEN
        List<Ligne> panier = List.of(new Ligne("Livre", 19_90, 1));
        // WHEN
        int total = prix.total(panier, null, Livraison.RETRAIT_MAGASIN);
        // THEN
        assertEquals(23_88, total);
    }

    @Test
    @DisplayName("Une quantite nulle est refusee des la creation de la ligne")
    void quantiteNulleRefusee() {
        // GIVEN
        int quantite = 0;
        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> new Ligne("Livre", 19_90, quantite));
    }
}
