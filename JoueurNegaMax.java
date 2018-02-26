/*
 * TP 3 : Puissance 4
 *
 * @author Thomas CUSSON
 */

import java.util.Stack;
//Negamax est censé être équivalent à minmax, mais est sans doute mal implémenté
public class JoueurNegaMax implements Joueur{
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
            double  better_result = FonctionEvaluation.MIN;
            int     better_column = -1;
            Stack<Integer> to_try = grille.generateurCoups();
            while(!to_try.empty()){
                Grille grille_temp = new Grille(grille);
                int current_try = to_try.pop();
                grille_temp.joueEn(joueur, current_try);
                double eval = -negaMax(grille_temp, 0, Grille.joueurSuivant(joueur), evaluateur);
                if(eval>better_result){
                    better_result = eval;
                    better_column = current_try;
                }
                if(eval==FonctionEvaluation.MAX){
                    break;
                }
            }
            return new Resultat(better_column, better_result);
        }

    private double negaMax(Grille grille, int it, int joueur, FonctionEvaluation evaluateur){
        double  better_result = FonctionEvaluation.MIN;
        Stack<Integer> to_try = grille.generateurCoups();
        while(!to_try.empty()){
            Grille grille_temp = new Grille(grille);
            grille_temp.joueEn(joueur, to_try.pop());
            double eval = FonctionEvaluation.MIN;
            if(it==0){
                eval = evaluateur.evaluation(grille_temp, joueur);
            }else{
                eval = -negaMax(grille_temp, it-1, Grille.joueurSuivant(joueur), evaluateur);
            }
            if(eval==FonctionEvaluation.MAX){
                return eval;
            }
            if(eval>better_result){
                    better_result = eval;
            }
        }
        return better_result;
    }

    public String print(){
        return "Negamax";
    }
}
