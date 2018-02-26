pour lancer :

make
java -cp .:libraries/* Puissance4

Par défaut, le programme lance 10.000 parties, pour faire des statistiques sur les probabilités de gagner de telle IA contre telle autre. Avec un nombre d'itérations plus important sur MinMax, cela peut prendre plus de temps de lancer autant de parties.

Les fonctions sont généralement commentées pour expliquer le code

Au début du fichier Puissance4.java, une valeur booléenne human permet de prendre le contrôle du joueur 1
Sinon, il s'agit du joueur Eval Simple, qui joue aux endroits où il peut gagner, et qui sinon joue au meilleur endroit selon la fonction d'évaluation (celle fournie)

Le joueur MinMax est réglé par défaut à 2 itérations, il est possible de changer cette valeur.
Le joueur MinMaxBis est utile pour faire s'affronter 2 joueurs MinMax avec un nb d'itérations différent

Les joueurs négaMax et Aléatoire sont également disponibles pour tests
(négamax est pas totalement correctement implémenté, je pense)

Exemples de résultats obtenus :
Aléatoire  contre MinMax 2 itérations :
    24 à 9.971, 5 matches nuls
Aléatoire  contre MinMax 2 itérations :
    31 à 9.967, 2 matches nuls
Aléatoire  contre MinMax 2 itérations :
    12 à 9.987, 1 match nul

EvalSimple contre MinMax 2 itérations :
    487 à 9.408, 105 matches nuls
EvalSimple contre MinMax 3 itérations :
    803 à 9.168, 29 matches nuls
EvalSimple contre MinMax 4 itérations :
    299 à 9.626, 75 matches nuls

MinMax 2 contre MinMax 2 :
    4.005 à 3.885, 2110 matches nuls -> à niveau équivalent, beaucoup plus de matches nuls
MinMax 2 contre MinMax 3 :
    4.819 à 4.205,  976 matches nuls
MinMax 2 contre MinMax 4 :
    3.152 à 4.932, 1916 matches nuls
