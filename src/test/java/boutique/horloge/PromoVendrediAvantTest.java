package boutique.horloge;

import static org.junit.jupiter.api.Assertions.assertTrue;

import boutique.solid.d.PromoVendrediAvant;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Le test qu'on ecrit un vendredi, et qui casse les six autres jours. Exclu du build normal (tag
 * « depend-du-jour ») ; executer.sh le lance a part pour montrer le resultat du jour.
 */
@Tag("depend-du-jour")
class PromoVendrediAvantTest {

    @Test
    @DisplayName("La promo du vendredi est active")
    void promoActive() {
        // GIVEN
        PromoVendrediAvant promo = new PromoVendrediAvant();
        // WHEN
        boolean active = promo.active();
        // THEN
        assertTrue(active);
    }
}
