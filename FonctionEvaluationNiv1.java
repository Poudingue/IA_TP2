public class FonctionEvaluationNiv1 implements FonctionEvaluation{
    /**
     * Fonction qui donne une valeur à la grille, pour le joueur,
     * de telle façon que la valeur soit égale à MAX si joueur gagne,
     * égale à 0 s'il y a match nul et égale à MIN si joueur perd.
     * @param grille : la grille de puissance 4.
     * @param joueur : le joueur qui joue le coup.
     * @return la valeur donnée à la grille, pour le joueur.
     */
    public double evaluation(Grille grille, int joueur){
        /*
        for(int i=0; i<grille.NB_COLONNES; i++){
            // if(i)
            if(grille.coupGagnant(joueur, i)){
                return FonctionEvaluation.MAX;
            }else if(grille.coupGagnant(Grille.joueurSuivant(joueur), i)){
                return FonctionEvaluation.MIN;
            }
        }
        */
        //Si on a pas de victoire, on se rabat sur la fonction d'évaluation par défaut
        return (new FonctionEvaluationProf()).evaluation(grille, joueur);
    }
}
