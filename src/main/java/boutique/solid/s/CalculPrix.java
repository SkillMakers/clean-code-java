package boutique.solid.s;

import boutique.propre.Ligne;
import boutique.propre.Livraison;
import java.util.List;

/** Le calcul du prix, vu par CommandeService : une abstraction, pas une classe concrete. */
@FunctionalInterface
public interface CalculPrix {
    int total(List<Ligne> lignes, String codePromo, Livraison livraison);
}
