package modele;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * <b>Représente les joueurs virtuels (controlés par le programme) formant les adversaires du joueur humain</b>
 * <p>
 * Un Joueur Virtuel est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un nom choisi aléatoirement parmis une liste de nom prédéfini</li>
 * <li>Un HashSet de nom déja utilisé afin de ne pas reutiliser deux fois le même nom pour les joueurs
 * <li>Un comportement caractérisant son dégré "d'intelligence" dans le choix des cartes</li>
 * </ul>
 * 
 * @see Joueur
 * @see Strategie
 * 
 * 
 */
public class JoueurVirtuel extends Joueur implements Strategie{
	
	
	/**
     * </b>Nom est un type énuméré "type-safe".</b> 
     * Il énumère les noms prédéfinis des IA (des fruits en l'occurence) 
     * <p>
     *
     * 
     * @see JoueurVirtuel#JoueurVirtuel()
     */
	private enum Nom {
		Banane,
		Pomme,
		Cerise,
		Abricot,
		Peche,
		Ananas,
		Tomate,
		Orange;	
	}
	
	/**
	 * Le niveau "d'intelligence du joueur virtuel"
	 * @see JoueurVirtuel#jouer()
	 */
	private int comportement;
	
	/**
	 * Une Collection qui ne peut pas contenir de doublon : On évite ainsi de reutiliser deux fois le même nom
	 * @see JoueurVirtuel#JoueurVirtuel()
	 */
	public static HashSet<Nom> nomsUtilises;
	
	

	public int getComportement() {
		return comportement;
	}

	public void setComportement(int comp) {
		comportement = comp;
	}
	
	/**
	 * Le constructeur du joueur virtuel
	 * Il instancie la main, crée une collection de cartes dans sa main et attribue un nom unique parmi l'enumeration
	 * 
	 * 
	 */

	public JoueurVirtuel() {
		
		this.main = new Paquet();
		this.main.cartes = new LinkedList<Carte>();
		int rand;

		// Si aucun joueur virtuel n'a encore été instancié, la collection non plus
		if (JoueurVirtuel.nomsUtilises == null)
		{
			JoueurVirtuel.nomsUtilises = new HashSet<Nom>();
		}

		if (JoueurVirtuel.nomsUtilises.isEmpty())
		{
			rand = (int) (Math.random()*Nom.values().length);
			JoueurVirtuel.nomsUtilises.add(Nom.values()[rand]);
		}
		else
		{
			do
			{
				rand = (int) (Math.random()*Nom.values().length);

			} while (JoueurVirtuel.nomsUtilises.contains(Nom.values()[rand]));
			JoueurVirtuel.nomsUtilises.add(Nom.values()[rand]);
		}

		this.nom = (Nom.values()[rand]).toString();
	}
	
	
	/**
	 * En fonction du comportement, execute la stratégie de jeu 
	 * et affiche le nombre de carte restante dans la main du joueur
	 * 
	 * @see JoueurVirtuel#executerOffensif()
	 * @see JoueurVirtuel#executerRandom()
	 * 
	 */
	public void jouer() {
		System.out.println("C'est au tour de " +this.nom +" de jouer ! ");
		switch (comportement) {
		case 0 : this.executerRandom(); break;
		case 1 : this.executerOffensif(); break;
		default : this.executerRandom(); break;
		}
		System.out.println("Il lui reste [" + this.main.cartes.size() + "] cartes !");
	}
	
	/**
	 * Même principe que pour @see JoueurHumain#bonnePioche()
	 * 
	 * @see JoueurVirtuel#executerOffensif()
	 * @see JoueurVirtuel#executerRandom()
	 */
	private void bonnePiocheIA() {
		if(this.peutJouer()) {
			ArrayList<Carte> carteJouable =  this.obtenirCartesJouables();

			this.attendre();
			int carteRand = (int) (Math.random()*carteJouable.size());
			Carte c = carteJouable.get(carteRand);
			System.out.println(this.nom + " joue " + c.toString());
			this.poserCarte(this.main.cartes.get(this.main.cartes.indexOf(c)));	
		}
		else {System.out.println(this.nom +" ne peut pas jouer...");
		}
	}
	
	/**
	 * Dans la collection des cartes jouables, récupère celles qui ont un effet 
	 * @see Carte#possedeUnEffet()
	 * @see Joueur#obtenirCartesJouables()
	 * 
	 * @see JoueurVirtuel#executerOffensif()
	 * @param carteJouable la collection de carte jouable récupérée par obtenirCartesJouable()
	 * @return une collection de carte A Effets
	 * 
	 */
	private ArrayList<Carte> obtenirCartesAEffets(ArrayList<Carte> carteJouable) {
		ArrayList<Carte> carteAEffets = new ArrayList<Carte>();
		ListIterator<Carte> it = this.obtenirCartesJouables().listIterator();
		while (it.hasNext()) {
		 Carte c = it.next();
		 if(c.possedeUnEffet()) {
			 carteAEffets.add(c);
		 }		 
		}
		return carteAEffets;
		
	}


	/** Stratégie **/
	
	/**
	 * Cette Stratégie choisi au hasard une carte à jouer parmis la collection de carte jouable et la joue
	 * sinon le joueur pioche, et si bonne pioche, il joue la carte
	 * 
	 *  @see Joueur#obtenirCartesJouables()
	 *  @see Joueur#poserCarte(Carte)
	 */

	public void executerRandom() {

		if (this.peutJouer()) {
			ArrayList<Carte> carteJouable = this.obtenirCartesJouables();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int carteRand = (int) (Math.random()*carteJouable.size());
			Carte c = carteJouable.get(carteRand);
			System.out.println(this.nom + " joue " + c.toString());
			this.poserCarte(this.main.cartes.get(this.main.cartes.indexOf(c)));
		}
		else {
			System.out.println(this.nom +" pioche !");
			this.piocher();
			this.bonnePiocheIA();
		}

	}
	
	/**
	 * Cette stratégie choisi parmis la liste des cartes jouables les cartes a effet et joue au hasard une de ces cartes
	 * si il ne possède plus de carte à effet, joue les cartes sans effets
	 * 
	 * @see JoueurVirtuel#obtenirCartesAEffets(ArrayList)
	 * 
	 */

	public void executerOffensif() {
		if (this.peutJouer()) {
			ArrayList<Carte> carteJouable = this.obtenirCartesJouables();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<Carte> carteAEffet = this.obtenirCartesAEffets(carteJouable); //utilise ses effets en premier
			if (!carteAEffet.isEmpty()) {
				int carteRand = (int) (Math.random()*carteAEffet.size());
				Carte c = carteAEffet.get(carteRand);
				System.out.println(this.nom + " joue " + c.toString());
				this.poserCarte(this.main.cartes.get(this.main.cartes.indexOf(c)));
			} else { 															// quand il n'a plus de carte a effet, utilise les cartes basiques 
				int carteRand = (int) (Math.random()*carteJouable.size());
				Carte c = carteJouable.get(carteRand);
				System.out.println(this.nom + " joue " + c.toString());
				this.poserCarte(this.main.cartes.get(this.main.cartes.indexOf(c)));} 
			}
		else {
			System.out.println(this.nom +" pioche !"); //sinon, pioche
			this.piocher();
			this.bonnePiocheIA();
		}

	
	}


	
	










}