package boutique.erreurs;

/** Levee la ou le probleme se produit, avec ce qu'il faut pour le comprendre. */
public class ClientInconnu extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ClientInconnu(String numero) {
        super("client inconnu : " + numero);
    }
}
