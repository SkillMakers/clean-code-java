package boutique.erreurs;

import java.util.Map;
import java.util.Optional;

/** Le type de retour dit qu'un client peut manquer : l'appelant est oblige d'y penser. */
public class Clients {

    private final Map<String, Client> parNumero = Map.of("C-1", new Client("Lea", "lea@exemple.fr"));

    public Optional<Client> trouver(String numero) {
        return Optional.ofNullable(parNumero.get(numero));
    }
}
