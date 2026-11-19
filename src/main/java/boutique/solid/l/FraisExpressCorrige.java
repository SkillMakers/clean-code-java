package boutique.solid.l;

import java.util.List;

/** Ne prend que des Expediable : passer un e-book ne compile plus. */
public class FraisExpressCorrige {

    public int frais(List<? extends Expediable> colis) {
        return FraisExpress.pourGrammes(colis.stream().mapToInt(Expediable::poidsGrammes).sum());
    }
}
