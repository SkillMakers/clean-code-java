#!/bin/sh
# Compile, teste et execute tout ; sorties dans ../preuves/.
# Lance dans le conteneur maven:3.9-eclipse-temurin-25 (voir le README).
# -Xlint:all -Werror dans le pom : un seul avertissement fait echouer la compilation.
set -eu
cd "$(dirname "$0")"
P=preuves
mkdir -p "$P"

{
  echo "\$ java -version"; java -version 2>&1
  echo "\$ mvn -version | head -1"; mvn -version | head -1
  echo "\$ date"; TZ=Europe/Paris date "+%A %d/%m/%Y %H:%M (Europe/Paris)"
} > "$P/versions.txt"

mvn -q -B clean test > "$P/tests.txt" 2>&1 || { cat "$P/tests.txt"; exit 1; }
grep -h "Tests run" target/surefire-reports/*.txt >> "$P/tests.txt"

for d in pieges.DemoGoldenMaster pieges.DemoDeuxStylos erreurs.DemoErreurs solid.l.DemoLiskov; do
  nom=$(echo "$d" | sed 's/.*\.//')
  echo "\$ java boutique.$d" > "$P/$nom.txt"
  java -cp target/classes "boutique.$d" >> "$P/$nom.txt"
done

# Le test qui lit l'horloge du systeme : lance a part, le jour de l'execution
{
  echo "\$ date"; TZ=Europe/Paris date "+%A %d/%m/%Y"
  echo "\$ mvn test -Dgroups=depend-du-jour -Dgroupes.exclus=aucun -Dtest=PromoVendrediAvantTest"
  # sans -q : la ligne « Tests run » doit sortir meme un vendredi, quand le test passe
  TZ=Europe/Paris mvn -B test -Dgroups=depend-du-jour -Dgroupes.exclus=aucun -Dtest=PromoVendrediAvantTest \
    > "$P/.promo.log" 2>&1 || true
  grep -E "Tests run: 1,|expected:" "$P/.promo.log" | head -4
} > "$P/promo-vendredi-avant.txt"
rm -f "$P/.promo.log"
grep -q "Tests run: 1," "$P/promo-vendredi-avant.txt" \
  || { echo "Le test du vendredi n'a pas tourne"; cat "$P/promo-vendredi-avant.txt"; exit 1; }

# Liskov apres : passer un e-book au calcul corrige ne compile plus
T=$(mktemp -d)
cp -r src/main/java/. "$T/"
cat > "$T/boutique/solid/l/EssaiEbook.java" <<'EOF'
package boutique.solid.l;

import java.util.List;

class EssaiEbook {
    int frais() {
        return new FraisExpressCorrige().frais(List.of(new LivreEnLigne("Roman en e-book", "https://exemple.fr/roman")));
    }
}
EOF
{
  echo "\$ (EssaiEbook : un LivreEnLigne passe a FraisExpressCorrige)"
  echo "\$ javac boutique/solid/l/*.java"
  (cd "$T" && javac -d "$T/out" boutique/solid/l/*.java 2>&1) || echo "(compilation refusee, code $?)"
} > "$P/liskov-ebook-compilation.txt"
rm -rf "$T"
grep -q "incompatible types" "$P/liskov-ebook-compilation.txt" \
  || { echo "Erreur de compilation attendue absente"; cat "$P/liskov-ebook-compilation.txt"; exit 1; }

# Lignes et methodes : l'ancien calcul, le propre, le decoupe a l'extreme
{
  wc -l src/main/java/boutique/avant/Calc.java src/main/java/boutique/corrige/PrixPanier.java \
        src/main/java/boutique/pieges/PrixPanierDecoupe.java
  for f in src/main/java/boutique/avant/Calc.java src/main/java/boutique/corrige/PrixPanier.java \
           src/main/java/boutique/pieges/PrixPanierDecoupe.java; do
    echo "$f : $(grep -cE '^    (public|private)( static)? [a-zA-Z<>]+ [a-zA-Z]+\(' "$f") methodes"
  done
} > "$P/lignes.txt"

echo "OK : preuves dans $P"
