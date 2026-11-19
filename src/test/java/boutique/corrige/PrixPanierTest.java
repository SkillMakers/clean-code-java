package boutique.corrige;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import boutique.propre.Ligne;
import boutique.propre.Livraison;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** Les quatre exemples du premier jet, plus un prix qui n'est pas rond (la ou le premier jet se trompe). */
class PrixPanierTest {

    private final PrixPanier prix = new PrixPanier();

    @Test
    @DisplayName("Un livre a 19,90 HT livre a domicile : 28,78")
    void unLivreADomicile() {
        // GIVEN
        List<Ligne> panier = List.of(new Ligne("Livre", 19_90, 1));
        // WHEN
        int total = prix.total(panier, null, Livraison.DOMICILE);
        // THEN
        assertEquals(28_78, total);
    }

    @Test
    @DisplayName("70,00 HT avec BIENVENUE10 : 75,60, livraison offerte")
    void codePromoEtLivraisonOfferte() {
        // GIVEN
        List<Ligne> panier = List.of(new Ligne("Lampe", 45_00, 1), new Ligne("Bougie", 12_50, 2));
        // WHEN
        int total = prix.total(panier, "BIENVENUE10", Livraison.DOMICILE);
        // THEN
        assertEquals(75_60, total);
    }

    @Test
    @DisplayName("Retrait en magasin : 23,88, sans frais")
    void retraitEnMagasin() {
        // GIVEN
        List<Ligne> panier = List.of(new Ligne("Livre", 19_90, 1));
        // WHEN
        int total = prix.total(panier, null, Livraison.RETRAIT_MAGASIN);
        // THEN
        assertEquals(23_88, total);
    }

    @Test
    @DisplayName("Une quantite nulle est refusee")
    void quantiteNulleRefusee() {
        // GIVEN
        int quantite = 0;
        // WHEN / THEN
        assertThrows(IllegalArgumentException.class, () -> new Ligne("Livre", 19_90, quantite));
    }

    @Test
    @DisplayName("Deux lignes a 1,02 HT : 1,22 + 1,22 = 2,44 (arrondi par ligne), pas 2,45 (arrondi sur 2,04 x 1,2)")
    void arrondiParLigne() {
        // GIVEN
        List<Ligne> panier = List.of(new Ligne("Stylo", 1_02, 1), new Ligne("Gomme", 1_02, 1));
        // WHEN
        int total = prix.total(panier, null, Livraison.RETRAIT_MAGASIN);
        // THEN
        assertEquals(2_44, total);
    }
}
