/*
 * TP 3 : Puissance 4
 *
 * @author Thomas CUSSON
 */

/**
 * Joueur choisissant le meilleur minmax selon un nombre donné d'itérations
 *
 */

 import java.util.Stack;
public class JoueurMinMax implements Joueur{
    int nb_iterations = 2;//>0. Il semblerait qu'en mettre plus de 2 est inutile, à moins que ça soit juste mal implémenté

    /**
     * Fonction qui indique dans quelle colonne de la grille jouer,
     * en suivant MinMax
     * @param grille : la grille de puissance 4.
     * @param joueur : le joueur qui doit jouer le coup.
     * @return l'indice de la colonne dans laquelle poser le pion
     * ainsi que la valeur associée é la nouvelle grille.
     */
    public Resultat coup(Grille grille, int joueur){
        FonctionEvaluation evaluateur = new FonctionEvaluationProf();
        return meilleur(true, grille, nb_iterations, joueur, FonctionEvaluation.MIN, FonctionEvaluation.MAX, evaluateur);
    }

    /**
     * Fonction récursive rendant les meilleurs coupsà jouer
     * @param max : true si on cherche à maximiser, false sinon
     * @param grille : la grille de puissance 4.
     * @param it : le numéro d'itération. 0 = feuille de l'arbre
     * @param joueur : le joueur qui doit jouer le coup.
     * @param alpha : alpha pour réduction alpha
     * @param beta : beta pour réduction beta
     * @param evaluateur : la fonction d'heuristique d'évaluation
     * @return l'indice de la colonne dans laquelle poser le pion
     * ainsi que la valeur associée é la nouvelle grille.
     */
    private Resultat meilleur(boolean max, Grille grille, int it, int joueur, double alpha, double beta, FonctionEvaluation evaluateur){
        Stack<Integer> to_try = grille.generateurCoups();
        //On part de la première valeur qu'on trouve
        double eval = evaluateur.evaluation(grille, joueur);
        int column = -1;//-1 invalide pour retour erreur
        if(it==0){//On est sur une feuille, on évalue.
            return new Resultat(-1,eval);
        }

        //Coupure alpha beta sur la valeur de ce node
        if((max&&eval>beta)
        ||(!max&&eval<alpha)){
            return new Resultat(-1, eval);
        }
        while(!to_try.empty()){//On vide peu à peu la liste des coups à essayer
            int current_try = to_try.pop();
            Grille grille_temp = new Grille(grille);

            // En cas de victoire possible, on joue directement ça quand on cherche à maximiser
            if(max&&grille.coupGagnant(joueur, current_try)){
                return new Resultat(current_try, FonctionEvaluation.MAX);
            }
            // En cas de défaite quand on cherche à minimiser, on renvoie directement le MIN
            if(!max&&grille.coupGagnant(Grille.joueurSuivant(joueur),current_try)){
                return new Resultat(current_try, FonctionEvaluation.MIN);
            }
            //Dans le cas où on est en mode min, c'est l'autre joueur qui joue
            grille_temp.joueEn(max?joueur:Grille.joueurSuivant(joueur), current_try);
            //L'évaluation se fait toujours du point de vue de notre joueur
            double current_eval = meilleur(!max, grille_temp, it-1, joueur, alpha, beta, evaluateur).getValeur();

            if((max&&current_eval>eval)//Dans max, on cherche à maximiser eval
            ||(!max&&current_eval<eval)//Dans min à le minimiser
            ||column==-1){//Si on n'a pas encore défini la colonne, on met la première valeur, peu importe son score
                eval=current_eval;
                column=current_try;
            }
            //coupure beta et coupure alpha
            if((max&&eval>beta)||(!max&&eval<alpha)){
                eval=max?beta:alpha;
                column=current_try;
                break;
            }
            //Coupures alpha et beta pour les prochaines itérations
            if (max&&eval>alpha){alpha=eval;}
            if(!max&&eval<beta ){beta =eval;}
        }
        return new Resultat(column, eval);
    }

    /*
    * rend la string correspondant au joueur
    */
    public String print(){
        return "Joueur MinMax";
    }
}
