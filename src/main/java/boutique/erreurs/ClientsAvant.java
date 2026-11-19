package boutique.erreurs;

import java.util.Map;

/** Renvoie null quand le client n'existe pas : l'erreur eclate plus loin, la ou on ne la cherche pas. */
public class ClientsAvant {

    private final Map<String, Client> parNumero = Map.of("C-1", new Client("Lea", "lea@exemple.fr"));

    public Client trouver(String numero) {
        return parNumero.get(numero);
    }
}
