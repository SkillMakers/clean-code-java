package boutique.solid.i;

/** N'implemente que ce qu'il sait faire : plus de methode qui leve une exception. */
public class Virement implements Paiement, Remboursable {

    @Override
    public void payer(int centimes) {
        System.out.println("virement de " + centimes + " centimes");
    }

    @Override
    public void rembourser(int centimes) {
        System.out.println("virement retour de " + centimes + " centimes");
    }
}
