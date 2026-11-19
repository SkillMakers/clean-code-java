package boutique.solid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import boutique.solid.l.FraisExpress;
import boutique.solid.l.FraisExpressCorrige;
import boutique.solid.l.Livre;
import boutique.solid.l.LivreNumerique;
import boutique.solid.l.Produit;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LiskovTest {

    @Test
    @DisplayName("Express : 1,65 kg = 2 kilos entames, 9,90 + 2,00")
    void fraisExpress() {
        // GIVEN
        List<Produit> panier = List.of(new Produit("Roman", 450), new Produit("Guide", 1200));
        // WHEN
        int frais = new FraisExpress().frais(panier);
        // THEN
        assertEquals(11_90, frais);
    }

    @Test
    @DisplayName("Avant : un e-book, sous-classe de Produit, fait planter le calcul a l'execution")
    void sousClasseQuiCasse() {
        // GIVEN
        List<Produit> panier = List.of(new Produit("Roman", 450), new LivreNumerique("Roman en e-book"));
        // WHEN / THEN
        assertThrows(UnsupportedOperationException.class, () -> new FraisExpress().frais(panier));
    }

    @Test
    @DisplayName("Apres : seuls les Expediable entrent dans le calcul, meme resultat")
    void expediable() {
        // GIVEN
        List<Livre> colis = List.of(new Livre("Roman", 450), new Livre("Guide", 1200));
        // WHEN
        int frais = new FraisExpressCorrige().frais(colis);
        // THEN
        assertEquals(11_90, frais);
    }
}
