package boutique.solid.d;

import java.time.Clock;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Depend d'une abstraction, java.time.Clock, recue par le constructeur.
 * En production : {@link #heureDeParis()} ; en test : Clock.fixed(...).
 */
public class PromoVendredi {

    private final Clock horloge;

    public PromoVendredi(Clock horloge) {
        this.horloge = horloge;
    }

    /**
     * L'horloge du systeme, mais a l'heure de la boutique : un serveur regle en UTC verrait encore jeudi
     * le vendredi entre minuit et une heure a Paris (deux heures en ete).
     */
    public static PromoVendredi heureDeParis() {
        return new PromoVendredi(Clock.system(ZoneId.of("Europe/Paris")));
    }

    public boolean active() {
        return LocalDate.now(horloge).getDayOfWeek() == DayOfWeek.FRIDAY;
    }
}
