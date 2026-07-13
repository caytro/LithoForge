# Tutoriel 01 – Les bases du langage Java

> Ce document n'est pas un cours de Java. Il rassemble les notions découvertes au fil du développement de **LithoForge**, avec des exemples issus du projet.

---

# 1. Les variables en Java

Java distingue trois catégories principales de variables.

## 1.1 Variables locales

Une variable locale est déclarée dans une méthode.

```java
void foo() {
    int width = 10;
}
```

Elle n'existe que pendant l'exécution de la méthode.

---

## 1.2 Paramètres

Les paramètres sont également des variables locales.

```java
void foo(int width) {
    ...
}
```

Ils sont créés lors de l'appel de la méthode.

---

## 1.3 Champs (attributs)

Les champs sont déclarés dans une classe.

```java
public class GrayImage {

    private final int width;

}
```

Chaque instance de la classe possède ses propres champs.

---

# 2. Résolution des noms

Lorsque le compilateur rencontre un identifiant comme :

```java
width
```

il le recherche dans l'ordre suivant :

1. Variables locales
2. Paramètres
3. Champs de l'objet (`this.width`)
4. Champs `static`

Le premier trouvé masque les suivants.

## Exemple

```java
class Demo {

    private int width = 100;

    void foo() {

        int width = 20;

        System.out.println(width);
        System.out.println(this.width);
    }
}
```

Affiche :

```
20
100
```

La variable locale masque le champ.

---

# 3. Le mot-clé `this`

`this` désigne l'objet courant.

Dans :

```java
this.width
```

on accède explicitement au champ de l'objet.

La plupart du temps, `this` est implicite.

```java
return width;
```

est équivalent à

```java
return this.width;
```

tant qu'aucune variable locale ou paramètre ne porte le même nom.

Lorsqu'un paramètre porte le même nom que le champ :

```java
public GrayImage(int width) {
    this.width = width;
}
```

* `this.width` désigne le champ de l'objet ;
* `width` désigne le paramètre.

---

# 4. Les méthodes `static`

Une méthode `static` appartient à la classe et non à une instance.

Exemple :

```java
private static void validate(int[][] pixels)
```

Une méthode `static` :

* ne possède pas de `this` ;
* ne peut accéder directement qu'à ses paramètres, ses variables locales et les membres `static` de la classe.

Elle ne peut pas accéder directement aux champs d'instance.

---

# 5. Le mot-clé `final`

Pour un champ :

```java
private final int width;
```

la valeur ne pourra être affectée qu'une seule fois.

Pour une référence :

```java
private final int[][] pixels;
```

`final` signifie que la référence ne pourra plus être remplacée.

En revanche, le contenu du tableau reste modifiable :

```java
pixels[0][0] = 255;
```

`final` ne garantit donc pas, à lui seul, l'immuabilité.

---

# 6. Immutabilité

Une classe immuable ne change jamais d'état après sa construction.

Pour construire une classe immuable :

* tous les champs sont `private` ;
* tous les champs sont `final` ;
* aucune méthode de modification (`set...`) n'est exposée ;
* les objets mutables reçus en paramètre sont copiés.

Dans `GrayImage`, cela se traduit par une copie défensive du tableau de pixels.

---

# 7. Copie défensive

Le constructeur ne doit jamais conserver directement la référence du tableau fourni.

Mauvaise pratique :

```java
this.pixels = pixels;
```

Bonne pratique :

```java
this.pixels = defensiveCopy(pixels);
```

Ainsi, toute modification du tableau d'origine est sans effet sur l'objet `GrayImage`.

---

# 8. Pourquoi `validate()` est `static` ?

La méthode :

```java
private static void validate(int[][] pixels)
```

ne dépend pas de l'objet en cours de construction.

Elle ne fait qu'analyser ses paramètres.

La déclarer `static` exprime clairement qu'elle n'utilise pas l'état de l'objet.

---

# 9. TDD (Test Driven Development)

Pour LithoForge, nous suivons le cycle suivant :

1. Écrire un test.
2. Vérifier qu'il échoue (Rouge).
3. Écrire le minimum de code nécessaire.
4. Vérifier que le test passe (Vert).
5. Refactoriser sans modifier le comportement.

L'objectif est de construire progressivement une API simple et robuste.

---

# Ce que j'ai appris aujourd'hui

* Java recherche les variables dans un ordre bien défini (locale → paramètre → champ → membre `static`).
* `this` est souvent implicite.
* Une méthode `static` ne possède pas de `this`.
* `final` ne rend pas un objet immuable.
* Une copie défensive est indispensable lorsqu'un objet immuable reçoit un objet mutable.
* Une bonne API cherche à rendre les états invalides impossibles à représenter.
* Les tests décrivent le comportement attendu avant l'implémentation.
