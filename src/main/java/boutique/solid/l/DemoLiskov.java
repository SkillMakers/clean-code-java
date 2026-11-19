package boutique.solid.l;

import java.util.List;

/** Le meme calcul de frais, avec un panier sans e-book puis avec un e-book. */
public class DemoLiskov {

    public static void main(String[] args) {
        FraisExpress express = new FraisExpress();
        Produit roman = new Produit("Roman broche", 450);
        Produit guide = new Produit("Guide de voyage", 1200);
        System.out.println("roman + guide : " + express.frais(List.of(roman, guide)) + " centimes");
        try {
            express.frais(List.of(roman, new LivreNumerique("Roman en e-book")));
        } catch (UnsupportedOperationException e) {
            System.out.println("roman + e-book : " + e);
        }
        FraisExpressCorrige corrige = new FraisExpressCorrige();
        System.out.println("apres, roman + guide : "
                + corrige.frais(List.of(new Livre("Roman broche", 450), new Livre("Guide de voyage", 1200)))
                + " centimes");
    }
}
