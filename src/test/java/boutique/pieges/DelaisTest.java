package boutique.pieges;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** La duplication accidentelle : deux 14 qui n'avaient en commun que leur valeur. */
class DelaisTest {

    @Test
    @DisplayName("Avant : allonger le delai de retour a 30 jours allonge aussi le remboursement")
    void fusionDeplaceLeRemboursement() {
        // GIVEN
        DelaisAvant delais = new DelaisAvant();
        // WHEN
        int remboursement = delais.remboursementJours();
        // THEN
        assertEquals(30, remboursement);
    }

    @Test
    @DisplayName("Apres : retour a 30 jours, remboursement toujours a 14 jours au plus")
    void deuxConstantes() {
        // GIVEN
        Delais delais = new Delais();
        // WHEN
        int retour = delais.retourJours();
        int remboursement = delais.remboursementJours();
        // THEN
        assertEquals(30, retour);
        assertTrue(remboursement <= 14);
    }
}
