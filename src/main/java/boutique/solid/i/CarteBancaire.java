package boutique.solid.i;

/** La carte sait tout faire : elle implemente les trois interfaces. */
public class CarteBancaire implements Paiement, Remboursable, PaiementFractionne {

    @Override
    public void payer(int centimes) {
        System.out.println("carte : " + centimes + " centimes");
    }

    @Override
    public void rembourser(int centimes) {
        System.out.println("carte, remboursement : " + centimes + " centimes");
    }

    @Override
    public void payerEnTroisFois(int centimes) {
        System.out.println("carte, 3 fois : " + java.util.Arrays.toString(echeances(centimes)));
    }

    /** Trois echeances qui font exactement le total : le reste de la division va sur la premiere. */
    public static int[] echeances(int centimes) {
        int base = centimes / 3;
        return new int[] {centimes - 2 * base, base, base};
    }
}
