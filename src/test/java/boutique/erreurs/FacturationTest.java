package boutique.erreurs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FacturationTest {

    private final Facturation facturation = new Facturation();

    @Test
    @DisplayName("Un client connu : son adresse de facturation")
    void clientConnu() {
        // GIVEN
        String numero = "C-1";
        // WHEN
        String email = facturation.destinataire(numero);
        // THEN
        assertEquals("lea@exemple.fr", email);
    }

    @Test
    @DisplayName("Avec null : NullPointerException, loin de la cause")
    void avecNull() {
        // GIVEN
        String numeroInconnu = "C-9";
        // WHEN
        NullPointerException e = assertThrows(NullPointerException.class,
                () -> facturation.destinataireAvant(numeroInconnu));
        // THEN
        assertTrue(e.getMessage().contains("is null"), e.getMessage());
    }

    @Test
    @DisplayName("Avec Optional : une exception qui dit quel client manque")
    void avecOptional() {
        // GIVEN
        String numeroInconnu = "C-9";
        // WHEN
        ClientInconnu e = assertThrows(ClientInconnu.class, () -> facturation.destinataire(numeroInconnu));
        // THEN
        assertEquals("client inconnu : C-9", e.getMessage());
    }
}
