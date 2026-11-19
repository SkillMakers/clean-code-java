package boutique.avant;

/**
 * Le calcul du prix d'un panier tel qu'on le trouve souvent : il marche, mais il faut le relire trois fois.
 * C'est le point de depart de la video ; le comportement de cette methode est la reference.
 */
public class Calc {

    public static int calc(int[][] l, String c, boolean b) {
        int t = 0;
        for (int i = 0; i < l.length; i++) {
            if (l[i][1] <= 0) {
                throw new IllegalArgumentException("quantite");
            }
            int x = l[i][0] * l[i][1];
            t = t + (x * 6 + 2) / 5;
        }
        if (c != null && c.equals("BIENVENUE10")) {
            t = t * 90 / 100;
        }
        // livraison offerte des 60 euros
        if (!b && t < 5000) {
            t = t + 490;
        }
        return t;
    }
}
