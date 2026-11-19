package boutique.solid.s;

/** Ou l'on enregistre une commande. Change quand la base change. */
public interface DepotCommandes {
    void enregistrer(String email, int totalCentimes);
}
