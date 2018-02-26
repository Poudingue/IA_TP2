/*
 * TP 3  : Puissance 4
 *
 * @author Tassadit BOUADI.
 */

/**
 * Joueur qui demande de choisir une colonne de la grille dans laquelle jouer.
 *
 */
public class JoueurHumain implements Joueur{

	/**
	 * Fonction qui indique dans quelle colonne de la grille jouer,
	 * en demandant au joueur d'entrer un indice valide.
	 * @param grille : la grille de puissance 4.
	 * @param joueur : le joueur qui doit jouer le coup.
	 * @return l'indice de la colonne dans laquelle poser le pion
	 * ainsi que la valeur associéàèe éàè la nouvelle grille.
	 */
	public Resultat coup(Grille grille, int joueur){
		System.out.println("Entrez un numéro de colonne valide");
		int col = Lecture.lireInt();//On ne gère pas le fait de ne pas entrer d'int

		while(!grille.peutJouerEn(col)){
			System.out.println("\tVous ne pouvez pas jouer dans la colonne " + col + "!");
			System.out.println("Entrez un numéro de colonne valide");
			col = Lecture.lireInt();
		}

		return new Resultat(col, 0);
	}

	public String print(){
		return "humain";
	}

}
