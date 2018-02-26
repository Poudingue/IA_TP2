/*
 * TP 3 : Puissance 4
 *
 * @author Thomas CUSSON
 */

/**
 * Joueur qui choisit en avec la fonction d'évaluation
 *
 */

import java.util.Stack;

public class JoueurEvalSimple implements Joueur{
	/**
	 * Fonction qui joue directement dans la case considérée comme la meilleure par la fonction d'évaluation fournie
	 * @param grille : la grille de puissance 4.
	 * @param joueur : le joueur qui doit jouer le coup.
	 * @return l'indice de la colonne dans laquelle poser le pion
	 * ainsi que la valeur associée é la nouvelle grille.
	 */
	public Resultat coup(Grille grille, int joueur){
		FonctionEvaluation evaluateur = new FonctionEvaluationProf();
		int better_column = -1;
		double better_result = FonctionEvaluation.MIN;
		Stack<Integer> to_try = grille.generateurCoups();
		while(!to_try.empty()){
			int current_try = to_try.pop();
			// En cas de victoire possible, on joue directement ça
			if(grille.coupGagnant(joueur, current_try)){
			    return new Resultat(current_try, FonctionEvaluation.MAX);
			}
			Grille grille_temp = new Grille(grille);
			grille_temp.joueEn(joueur, current_try);
			double eval = evaluateur.evaluation(grille_temp, joueur);
			if(eval>better_result){
				better_result = eval;
				better_column = current_try;
			}
		}
		return new Resultat(better_column, better_result);
	}

	public String print(){
		return "JoueurEvalSimple";
	}
}
