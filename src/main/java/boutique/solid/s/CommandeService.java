package boutique.solid.s;

import boutique.propre.Ligne;
import boutique.propre.Livraison;
import java.util.List;

/**
 * Ne fait plus qu'enchainer : le calcul, l'enregistrement et la notification vivent ailleurs. Les trois
 * dependances arrivent par le constructeur (inversion des dependances) : un test passe des faux.
 * En production : new CommandeService(new boutique.corrige.PrixPanier()::total, depot, notification).
 */
public class CommandeService {

    private final CalculPrix prix;
    private final DepotCommandes depot;
    private final Notification notification;

    public CommandeService(CalculPrix prix, DepotCommandes depot, Notification notification) {
        this.prix = prix;
        this.depot = depot;
        this.notification = notification;
    }

    public int valider(String email, List<Ligne> lignes, String codePromo, Livraison livraison) {
        int total = prix.total(lignes, codePromo, livraison);
        depot.enregistrer(email, total);
        notification.commandeValidee(email, total);
        return total;
    }
}
