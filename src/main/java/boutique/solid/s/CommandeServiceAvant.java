package boutique.solid.s;

import boutique.avant.Calc;
import java.util.ArrayList;
import java.util.List;

/**
 * Calcule, enregistre et previent le client : trois metiers dans une classe. La compta, l'equipe base de
 * donnees et le marketing viennent tous la modifier, et tester le total oblige a fournir une base et un mail.
 */
public class CommandeServiceAvant {

    final List<String> base = new ArrayList<>();
    final List<String> mailsEnvoyes = new ArrayList<>();

    public int valider(String email, int[][] lignes, String codePromo) {
        int total = Calc.calc(lignes, codePromo, false);
        base.add("INSERT INTO commande(email, total) VALUES ('" + email + "', " + total + ")");
        mailsEnvoyes.add("A " + email + String.format(" : merci pour votre commande de %d,%02d euros", total / 100, total % 100));
        return total;
    }
}
