/*
 * TP 3  : Puissance 4
 *
 * @author Tassadit BOUADI.
 * @second author Thomas CUSSON
 */

import java.util.Random;
import java.util.Stack;
import java.util.Collections;

public class Grille{
	public  static final int NB_LIGNES   = 6;
	public  static final int NB_COLONNES = 7;
	public  static final int JOUEUR1     = -1;
	public  static final int JOUEUR2     = 1;
	private static final Random RANDOM   = new Random();

	private int[][] _grille; //la grille représentant le jeu
	private int[] _libre;    //la 1ère colonne libre de chaque ligne
	private int _nbCoups;    //le nombre de coups joués


	/**
	 * Constructeur.
	 */
	public Grille(){
		_grille = new int[NB_LIGNES][NB_COLONNES];
		_libre = new int[NB_COLONNES];
		_nbCoups = 0;
	}


	/**
	 * Constructeur par recopie.
	 * @param g : le grille à recopier.
	 */
	public Grille(Grille g){
		Grille tmp = g.copie();
		_grille = tmp._grille;
		_libre = tmp._libre;
		_nbCoups = tmp._nbCoups;
	}


	/**
	 * Fonction de copie d'une grille.
	 * @return la copie de la grille courante.
	 */
	public Grille copie(){
		Grille g = new Grille();

		for(int i=0; i<NB_LIGNES; i++){
			for(int j=0; j<NB_COLONNES; j++){
				g._grille[i][j] = _grille[i][j];
			}
		}
		for(int j=0; j<NB_COLONNES; j++){
			g._libre[j] = _libre[j];
		}
		g._nbCoups = _nbCoups;

		return g;
	}



	/**
	 * Fonction qui donne la valeur de la case (l,c).
	 * @param l : l'indice de la ligne.
	 * @param c : l'indice de la colonne.
	 * @return la valeur de la case (l,c).
	 */
	public int getVal(int l, int c){
		return _grille[l][c];
	}


	/**
	 * Fonction qui donne l'indice de la 1éàère ligne vide de la colonne c.
	 * @param c : l'indice de la colonne.
	 * @return l'indice de la 1éàère ligne vide la colonne c.
	 */
	public int getLigneLibre(int c){
		return _libre[c];
	}


	/**
	 * Fonction qui donne le nombre de coups joués.
	 * @return le nombre de coups joués.
	 */
	public int getNbCoups(){
		return _nbCoups;
	}


	/**
	 * Fonction qui indique si la grille est pleine.
	 * @return vrai si la grille est pleine.
	 */
	public boolean estPleine(){
		return _nbCoups >= (NB_LIGNES*NB_COLONNES);
	}



	/**
	 * Fonction qui donne le nombre de cases consécutives occupées par
	 * joueur dans la 1ère diagonale, à partir de la case (l,c).
	 * @param joueur : le joueur considéré.
	 * @param l : l'indice de la ligne.
	 * @param c : l'indice de la colonne.
	 * @return le nombre de cases consécutives dans la 1éàère diagonale.
	 */
	public int getNbCasesDiagonale1(int joueur, int l, int c){
		int nb = 0;
		if(_grille[l][c] != joueurSuivant(joueur)){
			nb ++;
			boolean egal = true;
			for(int k=1; (l+k)<NB_LIGNES && (c+k)<NB_COLONNES && egal; k++){
				if(_grille[l+k][c+k] == joueur){
					nb ++;
				}else{
					egal = false;
				}
			}
			egal = true;
			for(int k=1; (l-k)>=0 && (c-k)>=0 && egal; k++){
				if(_grille[l-k][c-k] == joueur){
					nb ++;
				}else{
					egal = false;
				}
			}
		}
		return nb;
	}


	/**
	 * Fonction qui donne le nombre de cases conséàècutives occupéàèes par
	 * joueur dans la 2éàème diagonale, éàè partir de la case (l,c).
	 * @param joueur : le joueur considéàèréàè.
	 * @param l : l'indice de la ligne.
	 * @param c : l'indice de la colonne.
	 * @return le nombre de cases conséàècutives dans la 2éàème diagonale.
	 */
	public int getNbCasesDiagonale2(int joueur, int l, int c){
		int nb = 0;

		if(_grille[l][c] != joueurSuivant(joueur)){
			nb ++;
			boolean egal = true;
			for(int k=1; (l+k)<NB_LIGNES && (c-k)>=0 && egal; k++){
				if(_grille[l+k][c-k] == joueur){
					nb ++;
				}else{
					egal = false;
				}
			}
			egal = true;
			for(int k=1; (l-k)>=0 && (c+k)<NB_COLONNES && egal; k++){
				if(_grille[l-k][c+k] == joueur){
					nb ++;
				}else{
					egal = false;
				}
			}
		}
		return nb;
	}


	/**
	 * Fonction qui donne le nombre de cases conséàècutives occupéàèes par
	 * joueur horizontalement, éàè partir de la case (l,c).
	 * @param joueur : le joueur considéàèréàè.
	 * @param l : l'indice de la ligne.
	 * @param c : l'indice de la colonne.
	 * @return le nombre de cases conséàècutives horizontalement.
	 */
	public int getNbCasesHorizontale(int joueur, int l, int c){
		int nb = 0;

		if(_grille[l][c] != joueurSuivant(joueur)){
			nb ++;
			boolean egal = true;
			for(int k=1; (c-k)>=0 && egal; k++){
				if(_grille[l][c-k] == joueur){
					nb ++;
				}else{
					egal = false;
				}
			}
			egal = true;
			for(int k=1; (c+k)<NB_COLONNES && egal; k++){
				if(_grille[l][c+k] == joueur){
					nb ++;
				}else{
					egal = false;
				}
			}
		}

		return nb;
	}


	/**
	 * Fonction qui donne le nombre de cases conséàècutives occupéàèes par
	 * joueur verticalement, éàè partir de la case (l,c).
	 * @param joueur : le joueur considéàèréàè.
	 * @param l : l'indice de la ligne.
	 * @param c : l'indice de la colonne.
	 * @return le nombre de cases conséàècutives verticalement.
	 */
	public int getNbCasesVerticale(int joueur, int l, int c){
		int nb = 0;

		if(_grille[l][c] != joueurSuivant(joueur)){
			nb ++;
			boolean egal = true;
			for(int k=1; (l-k)>=0 && egal; k++){
				if(_grille[l-k][c] == joueur){
					nb ++;
				}else{
					egal = false;
				}
			}
		}

		return nb;
	}



	/**
	 * Fonction qui donne les indices des colonnes dans lesquelles on peut jouer.
	 * J'y ai mélangé l'ordre dans lequel ça sort pour éviter un déterminisme rendant prédictible les fonctions se basant là-dessus
	 * @return les indices des colonnes dans lesquelles on peut jouer.
	 */
	public Stack<Integer> generateurCoups(){
		Stack<Integer> res = new Stack<Integer>();
		for(int j=0; j<NB_COLONNES; j++){
			if(_libre[j] < NB_LIGNES){
				res.push(j);
			}
		}
		Collections.shuffle(res);
		return res;
	}


	/**
	 * Fonction qui donne un indice de colonne dans laquelle on peut jouer,
	 * en en choisissant un aléàèatoirement parmi les indices possibles.
	 * @return un indice de colonne dans lequel on peut jouer.
	 */
	public int getCoupAleatoire(){
		Stack<Integer> coups = generateurCoups();
		int nb = coups.size();
		if(coups.empty()){
			return -1;
		}else{
			// On a déjà mélangé en créant les coups possibles
			return coups.peek();
		}
	}


	/**
	 * Fonction qui dit indique si on peut jouer dans la colonne c.
	 * @param c : l'indice de la colonne.
	 * @return vrai si on peut jouer dans la colonne c.
	 */
	public boolean peutJouerEn(int c){
		return ((c>=0) && (c<NB_COLONNES) && (_libre[c]<NB_LIGNES));
	}


	/**
	 * Fonction qui modifie la grille, après que joueur ait joué dans la colonne c.
	 * @param joueur : le numéàèro du joueur.
	 * @param c : l'indice de la colonne.
	 * @return vrai si joueur peut jouer dans la colonne c.
	 */
	public boolean joueEn(int joueur, int c){
		boolean res = peutJouerEn(c);

		if(res){
			_grille[_libre[c]][c] = joueur;
			_libre[c] ++;
			_nbCoups ++;
		}

		return res;
	}


	/**
	 * Fonction qui indique si le joueur gagne en jouant dans la colonne c.
	 * @param joueur : le numéro du joueur.
	 * @param c : l'indice de la colonne.
	 * @return vrai si joueur gagne en jouant dans la colonne c.
	 */
	public boolean coupGagnant(int joueur, int c){
		int l = _libre[c];

		int nbD1 = getNbCasesDiagonale1 (joueur, l, c);
		int nbD2 = getNbCasesDiagonale2 (joueur, l, c);
		int nbH  = getNbCasesHorizontale(joueur, l, c);
		int nbV  = getNbCasesVerticale  (joueur, l, c);

		return (nbD1 >= 4) || (nbD2 >= 4) || (nbH >= 4) || (nbV >= 4);
	}



	/**
	 * Fonction statique qui donne le numéàèro du joueur suivant.
	 * @param joueur : le numéàèro du joueur courant.
	 * @return le numéàèro du joueur suivant.
	 */
	public static int joueurSuivant(int joueur){
		int advers;
		switch(joueur){
			case JOUEUR1:
				advers = JOUEUR2;
				break;
			default:
				advers = JOUEUR1;
		}
		return advers;
	}


	/**
	 * Fonction pour l'affichage d'une grille.
	 * @return la chaéàène correspondant éàè la grille.
	 */
	public String toString(){
		String res = "";
		for(int i=NB_LIGNES-1; i>=0; i--){
			for(int j=0; j<NB_COLONNES; j++){
				res += (i==NB_LIGNES-1?(j==0?"┌":"┬"):(j==0?"├":"┼"))+"───";
			}
			res += (i==NB_LIGNES-1?"┐":"┤")+"\n│";
			for(int j=0; j<NB_COLONNES; j++){
				res += " "+(_grille[i][j]==JOUEUR1?"o":(_grille[i][j]==JOUEUR2?"x":" "))+" │";
			}
			res += "\n";
		}
		for(int j=0; j<NB_COLONNES; j++){
			res += (j==0?"└":"┴")+"───";
		}
		res += "┘\n";
		for(int j=0; j<NB_COLONNES; j++){
			res +=  (j<10?"  ":" ") + j + " ";
		}
		res += "\n";
		return res;
	}
}
