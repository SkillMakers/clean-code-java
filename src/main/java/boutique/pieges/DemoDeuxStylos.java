package boutique.pieges;

import boutique.avant.Calc;
import boutique.propre.Ligne;
import boutique.propre.Livraison;
import java.util.List;

/** Le plus petit panier qui fait mentir le premier jet : deux articles a 1,02 euro HT, retrait en magasin. */
public class DemoDeuxStylos {

    public static void main(String[] args) {
        List<Ligne> panier = List.of(new Ligne("Stylo", 1_02, 1), new Ligne("Gomme", 1_02, 1));
        System.out.println("avant (Calc)          : " + Calc.calc(Paniers.versTableau(panier), null, true));
        System.out.println("premier jet propre    : " + new boutique.propre.PrixPanier().total(panier, null, Livraison.RETRAIT_MAGASIN));
        System.out.println("corrige               : " + new boutique.corrige.PrixPanier().total(panier, null, Livraison.RETRAIT_MAGASIN));
    }
}
