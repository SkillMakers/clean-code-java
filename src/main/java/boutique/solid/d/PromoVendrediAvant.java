package boutique.solid.d;

import java.time.DayOfWeek;
import java.time.LocalDate;

/** La promo du vendredi, qui lit l'horloge du systeme : son test ne passe qu'un jour sur sept. */
public class PromoVendrediAvant {

    public boolean active() {
        return LocalDate.now().getDayOfWeek() == DayOfWeek.FRIDAY;
    }
}
