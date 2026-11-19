package boutique.solid.s;

/** Comment on previent le client. Change quand le marketing change le message ou le canal. */
public interface Notification {
    void commandeValidee(String email, int totalCentimes);
}
