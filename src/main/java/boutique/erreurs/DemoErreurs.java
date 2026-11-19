package boutique.erreurs;

/** Le meme numero de client inconnu, avec null puis avec Optional. */
public class DemoErreurs {

    public static void main(String[] args) {
        Facturation facturation = new Facturation();
        System.out.println("destinataire(C-1) = " + facturation.destinataire("C-1"));
        try {
            facturation.destinataireAvant("C-9");
        } catch (NullPointerException e) {
            System.out.println("avant : " + e);
        }
        try {
            facturation.destinataire("C-9");
        } catch (ClientInconnu e) {
            System.out.println("apres : " + e);
        }
    }
}
