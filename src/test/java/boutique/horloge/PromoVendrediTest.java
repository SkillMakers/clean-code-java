package boutique.horloge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import boutique.solid.d.PromoVendredi;
import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

/** Avec une horloge fixe, le test donne le meme resultat tous les jours de la semaine. */
class PromoVendrediTest {

    @ParameterizedTest(name = "{0}")
    @EnumSource(DayOfWeek.class)
    @DisplayName("La promo n'est active que le vendredi, quel que soit le jour ou le test tourne")
    void seulementLeVendredi(DayOfWeek jour) {
        // GIVEN : le lundi 16 novembre 2026, decale jusqu'au jour teste
        ZoneId paris = ZoneId.of("Europe/Paris");
        LocalDate date = LocalDate.of(2026, 11, 16).plusDays(jour.getValue() - 1L);
        Clock horloge = Clock.fixed(date.atStartOfDay(paris).toInstant(), paris);
        // WHEN
        boolean active = new PromoVendredi(horloge).active();
        // THEN
        assertEquals(jour == DayOfWeek.FRIDAY, active);
    }
}
