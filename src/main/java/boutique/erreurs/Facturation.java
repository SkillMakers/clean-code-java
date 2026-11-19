package boutique.erreurs;

/** Trouve l'adresse de facturation d'un client : version avec null, puis version avec Optional. */
public class Facturation {

    private final ClientsAvant clientsAvant = new ClientsAvant();
    private final Clients clients = new Clients();

    public String destinataireAvant(String numeroClient) {
        Client client = clientsAvant.trouver(numeroClient);
        return client.email();
    }

    public String destinataire(String numeroClient) {
        return clients.trouver(numeroClient)
                .map(Client::email)
                .orElseThrow(() -> new ClientInconnu(numeroClient));
    }
}
