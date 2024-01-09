# Codingweek-28

Groupe composé de MOY Kélian, THUILLIER Killian, CHRISTIEN Adrien, HOYAU Christophe

## Créer un .jar
exporter la variable d'environnement de javafx
```sh
export JAVAFX_HOME=/path/to/your/javafx/home
```

clean build gradle puis créé le jar, le lance (pour tester) puis le déplace en le nommant `App.jar` dans le dossier `dist/App.jar`
```sh
./gradlew clean build && ./gradlew shadowjar && java --module-path ${JAVAFX_HOME}/lib --add-modules=javafx.base,javafx.controls,javafx.fxml -jar build/libs/my-application-1.0.0.jar && cp build/libs/my-application-1.0.0.jar dist/App.jar

```

## Planning

### Pour Lundi 08/01

Livrable prévu: connexion, inscription et gestion des utilisateurs.

Terminer les étapes préliminaires et le modèle de donnée

### Pour Mardi 09/01

Fixer les bugs de la veille et avoir une archive JAR exécutable.

Livrable prévu: page d'acceuil, création des objets bien et service ainsi que leur gestion, page Profil.

