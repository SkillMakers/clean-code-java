package boutique.solid;

import static org.junit.jupiter.api.Assertions.assertEquals;

import boutique.corrige.PrixPanier;
import boutique.propre.Ligne;
import boutique.propre.Livraison;
import boutique.solid.s.CommandeService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** Plus de base ni de serveur de mail : deux faux suffisent pour tester l'enchainement. */
class CommandeServiceTest {

    @Test
    @DisplayName("Valider une commande l'enregistre et previent le client avec le bon total")
    void valider() {
        // GIVEN
        List<String> enregistrees = new ArrayList<>();
        List<String> notifiees = new ArrayList<>();
        CommandeService service = new CommandeService(
                new PrixPanier()::total,
                (email, total) -> enregistrees.add(email + "=" + total),
                (email, total) -> notifiees.add(email + "=" + total));
        // WHEN
        int total = service.valider("lea@exemple.fr", List.of(new Ligne("Livre", 19_90, 1)), null,
                Livraison.DOMICILE);
        // THEN
        assertEquals(28_78, total);
        assertEquals(List.of("lea@exemple.fr=2878"), enregistrees);
        assertEquals(List.of("lea@exemple.fr=2878"), notifiees);
    }

    @Test
    @DisplayName("Un retrait en magasin ne paie pas de frais de livraison")
    void retraitEnMagasin() {
        // GIVEN
        List<String> enregistrees = new ArrayList<>();
        CommandeService service = new CommandeService(
                new PrixPanier()::total,
                (email, total) -> enregistrees.add(email + "=" + total),
                (email, total) -> { });
        // WHEN
        int total = service.valider("lea@exemple.fr", List.of(new Ligne("Livre", 19_90, 1)), null,
                Livraison.RETRAIT_MAGASIN);
        // THEN
        assertEquals(23_88, total);
        assertEquals(List.of("lea@exemple.fr=2388"), enregistrees);
    }
}
