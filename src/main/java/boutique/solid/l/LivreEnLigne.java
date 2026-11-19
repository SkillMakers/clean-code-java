package boutique.solid.l;

/** Apres : un e-book est un produit, pas un colis. Il n'a pas de methode poidsGrammes a trahir. */
public record LivreEnLigne(String nom, String lienDeTelechargement) {
}
