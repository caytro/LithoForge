# ADR 0001 - Version de Java

## Contexte

LithoForge est un projet d'apprentissage visant à développer
un générateur de lithophanies conteneurisé.

Nous souhaitons utiliser une version Java moderne avec un support
long terme.

## Décision

Nous utilisons Java 21 LTS.

## Raisons

- version LTS ;
- support des fonctionnalités Java modernes ;
- compatibilité avec les frameworks actuels ;
- disponibilité dans les images Docker.

## Conséquences

Le projet nécessite un JDK 21 minimum.