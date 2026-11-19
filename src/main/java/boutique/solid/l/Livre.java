package boutique.solid.l;

/** Un livre papier : il part dans un colis. */
public record Livre(String nom, int poidsGrammes) implements Expediable {
}
