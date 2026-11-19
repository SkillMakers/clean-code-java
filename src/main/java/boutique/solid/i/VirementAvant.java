package boutique.solid.i;

/** Un virement ne se paie pas en trois fois : il est oblige de mentir a l'interface. */
public class VirementAvant implements MoyenPaiementAvant {

    @Override
    public void payer(int centimes) {
        System.out.println("virement de " + centimes + " centimes");
    }

    @Override
    public void rembourser(int centimes) {
        System.out.println("virement retour de " + centimes + " centimes");
    }

    @Override
    public void payerEnTroisFois(int centimes) {
        throw new UnsupportedOperationException("pas de paiement en trois fois par virement");
    }
}
