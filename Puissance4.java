/*
 * TP 3 : Puissance 4
 *
 * @author Tassadit BOUADI.
 */

/**
 * Programme principal du jeu du puissance 4.
 *genera
 */
public class Puissance4 {
	//Mettre à true pour jouer contre l'IA
	static boolean humain = false;

	public static void main(String[] args) {
		//création des joueurs
		//Peut aussi être initialisé négaMax, JoueurMinMaxBis, JoueurAleatoire
		Joueur joueur1 = humain?new JoueurHumain():new JoueurEvalSimple();
		Joueur joueur2 = new JoueurMinMax();
		//nb de parties pour faire des statistiques sur le taux de réussite.
		int echantillon = 10000;
		//Stats de win/lose
		int j1=0;
		int j2=0;
		int nul=0;
		//On fait toutes les parties
		for(int i=1; i<=echantillon; i++){
			boolean essai_pair = i%2==0;
			//Les joueurs alternent, pour statistiquement annuler l'avantage de commencer en premier
			int result = essai_pair?jouer(joueur1, joueur2):jouer(joueur2, joueur1);
			// int result = jouer(joueur1, joueur2);
			switch (result) {
				case 1:
					if(essai_pair){j1++;}else{j2++;}
					// j1++;
					break;
				case 2:
					if(essai_pair){j2++;}else{j1++;}
					// j2++;
					break;
				case 0:
					nul++;
					break;
				default:
					System.out.println("Bad retour pour jouer");
					System.exit(0);
					break;
			}
			if(i%100==0){
				System.out.println("Partie numéro "+i+" :");
				System.out.println("Joueur 1 : "+j1);
				System.out.println("Joueur 2 : "+j2);
				System.out.println("Aucun    : "+nul);
			}
		}
		//affichage de fin
		System.out.println("Résumé des "+echantillon+" parties :");
		System.out.println("Joueur 1 : "+j1+" parties gagnées");
		System.out.println("Joueur 2 : "+j2+" parties gagnées");
		System.out.println(nul+" matches nuls");
	}

	/**
	 * Fonction qui effectue la boucle de jeu.
	 *
	 * @param joueur1 : le premier joueur.
	 * @param joueur2 : le second joueur.
	 */
	public static int jouer(Joueur joueur1, Joueur joueur2){
		Resultat res;
		int coup;
		Grille grille = new Grille();

		Joueur joueurCour = joueur1;
		int numJoueur = Grille.JOUEUR1; //le joueur 1 commence à jouer

		int vainqueur = 0;//match nul
		boolean jeuFini = false;

		//boucle de jeu
		while(!jeuFini && grille.getNbCoups() < grille.NB_LIGNES*grille.NB_COLONNES){
			joueurCour=numJoueur==Grille.JOUEUR1?joueur1:joueur2;
			res = joueurCour.coup(grille, numJoueur);
			if(grille.coupGagnant(numJoueur, res.getColonne())){
				vainqueur=numJoueur;jeuFini=true;
			}
			grille.joueEn(numJoueur, res.getColonne());
			numJoueur=Grille.joueurSuivant(numJoueur);
			//affichage de la grille
			if(humain){System.out.println(grille);}
		}//fin boucle de jeu

		//affichage du vainqueur"
		switch(vainqueur){
			case Grille.JOUEUR1:
				if(humain){System.out.println("Victoire du "+joueur1.print());}
				return 1;
			case Grille.JOUEUR2:
				if(humain){System.out.println("Victoire du "+joueur2.print());}
				return 2;
			default:
				if(humain){System.out.println("Match nul");}
				return 0;
		}
	}
}
