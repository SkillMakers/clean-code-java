package boutique.solid;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import boutique.solid.i.CarteBancaire;
import boutique.solid.i.PaiementFractionne;
import boutique.solid.i.Virement;
import boutique.solid.i.VirementAvant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InterfacesTest {

    @Test
    @DisplayName("Avant : le virement implemente payerEnTroisFois... en levant une exception")
    void interfaceTropGrosse() {
        // GIVEN
        VirementAvant virement = new VirementAvant();
        // WHEN / THEN
        assertThrows(UnsupportedOperationException.class, () -> virement.payerEnTroisFois(300_00));
    }

    @Test
    @DisplayName("Apres : seul ce qui sait payer en trois fois est un PaiementFractionne")
    void petitesInterfaces() {
        // GIVEN
        Object virement = new Virement();
        Object carte = new CarteBancaire();
        // WHEN / THEN
        assertFalse(virement instanceof PaiementFractionne);
        assertTrue(carte instanceof PaiementFractionne);
    }

    @Test
    @DisplayName("En trois fois, 100,00 euros font 33,34 + 33,33 + 33,33 : pas un centime perdu")
    void troisEcheancesExactes() {
        // GIVEN
        int total = 100_00;
        // WHEN
        int[] echeances = CarteBancaire.echeances(total);
        // THEN
        assertArrayEquals(new int[] {33_34, 33_33, 33_33}, echeances);
    }
}
