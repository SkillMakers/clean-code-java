# Clean code et principes SOLID en Java : exemples et pièges

Code de la vidéo **[« Clean code et principes SOLID en Java : exemples et pièges »](https://youtu.be/VdOheLt0XZs)** de la chaîne
[IT Skills Maker](https://www.youtube.com/@ItSkillsMaker), playlist
[Java pour développeurs](https://www.youtube.com/playlist?list=PLFmYDsjERZrU).

Le fil rouge : le calcul du prix d'un panier de la boutique en ligne, rendu propre étape par étape, puis les
cinq principes SOLID sur le même code, en **Java 25**.

| Étape | Paquet | Ce qu'on montre |
|---|---|---|
| Le code de départ | [`boutique.avant`](src/main/java/boutique/avant/) | `calc(int[][] l, String c, boolean b)` : il marche, c'est la référence |
| Nommer, une fonction = une chose | [`boutique.propre`](src/main/java/boutique/propre/) | `PrixPanier`, `Ligne`, `Livraison` : le premier jet propre, qui passe ses 4 tests… et calcule faux |
| La version fidèle | [`boutique.corrige`](src/main/java/boutique/corrige/) | la TVA arrondie ligne par ligne, comme l'original |
| Erreurs | [`boutique.erreurs`](src/main/java/boutique/erreurs/) | `null` et sa NullPointerException loin de la cause, puis `Optional` et `ClientInconnu` |
| S | [`boutique.solid.s`](src/main/java/boutique/solid/s/) | un service qui calcule, enregistre et envoie le mail, puis trois dépendances injectées |
| L | [`boutique.solid.l`](src/main/java/boutique/solid/l/) | un e-book sous-classe de `Produit` fait planter les frais express ; avec `Expediable`, ça ne compile plus |
| I | [`boutique.solid.i`](src/main/java/boutique/solid/i/) | le virement forcé d'implémenter le paiement en trois fois |
| D | [`boutique.solid.d`](src/main/java/boutique/solid/d/) | la promo du vendredi : avec `LocalDate.now()`, son test ne passe qu'un jour sur sept ; avec une `Clock`, sept sur sept |
| Quand la règle dessert | [`boutique.pieges`](src/main/java/boutique/pieges/) | deux délais de 14 jours fusionnés (le remboursement devient illégal), le calcul découpé en 12 méthodes, et le **golden master** |

**Le piège** : sur 10 000 paniers aléatoires (graine 42), avec et sans code promo, à domicile et en magasin,
le premier jet propre donne un autre prix que l'ancien code dans **9 976 calculs sur 40 000** (jusqu'à
2 centimes). Ses tests écrits à la main utilisaient des prix ronds. La version corrigée : 0.

## Lancer

Avec Java 25 et Maven : `sh executer.sh`. Ou sans rien installer, avec Docker :

```
docker run --rm -v "$PWD:/w" -w /w maven:3.9-eclipse-temurin-25 sh executer.sh
```

Le script lance les 32 tests JUnit (compilation avec `-Xlint:all -Werror`), les démos, le test qui lit
l'horloge du système (lancé à part : tag `depend-du-jour`), et la compilation de l'e-book qui doit échouer.
Les sorties réelles (Temurin 25.0.4.1, un jeudi) sont dans [`preuves/`](preuves/).
