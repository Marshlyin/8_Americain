package modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.event.EventListenerList;

/**
 * Classe représentant le joueur humain (l'utilisateur du jeu) 
 * On retrouve les memes attributs que ceux de sa superclasse joueur
 * Beaucoup de méthode se retrouvent dépressiées à cause du passage à l'interface graphique
 * @author Flavien
 * 
 * @see Joueur
 * @see Partie
 * 
 *
 */
public class JoueurHumain extends Joueur {
		
	
	/** 
	 * redifinition de la méthode Piocher() de Joueur afin de modifier l'affichage du joueur humain
	 * @see Joueur#piocher()
	 */
	public void piocher(){
		Pioche p = Pioche.getInstance();
		LinkedList<Carte> cartesPioches;
		this.setaPioche(true);
		try {
				
			cartesPioches = Pioche.getInstance().distribuer(1+(p.getReserve()));
			
			System.out.println("Vous avez pioché : " + cartesPioches.toString());
			ListIterator<Carte> it = cartesPioches.listIterator();
			this.main.cartes.addAll(cartesPioches);
			while(it.hasNext()) {
				this.notiferCarteChanged((Carte) it.next(), true);
			}
			
		} catch (TalonVideException e) {
			System.out.println("Aucune carte ne peut être piochée, tour suivant ...");
		}
		
		p.reinitialiserReserve();
	}
	
	/**
	 * @deprecated
	 * Cette méthode était utilisée a chaque fois que l'utilisateur devait faire un choix (jouer, piocher, passer le tour)
	 * Le joueur ne pouvant passer le tour qu'après avoir pioché, l'affichage etait modifié après l'appel de la méthode pioche
	 * @see Joueur#isaPioche()
	 */
	public void afficherMenu() {
		System.out.println("Faites votre choix :\n");
		System.out.println("1 -- Jouer une carte --");
		if(!this.isaPioche()) {
			System.out.println("2 -- Piocher --");
		}
		else
		{
			System.out.println("2 -- Passer le tour --");
		}
	}
	
	/** 
	 * La méthode centrale du joueur humain
	 * Récupère les cartes jouables @see Joueur#obtenirCartesJouables()
	 * et demande au joueur de choisir une carte parmi elles
	 * 
	 * @see JoueurHumain#bonnePioche()
	 * @see JoueurHumain#choisirCartes(ArrayList)
	 * @see JoueurHumain#peutJouer()
	 * 
	 * 
	 * 
	 */
	public void jouer() {
		System.out.println("C'est à votre tour de jouer !");
		System.out.println(Talon.getInstance().toString());
		System.out.println("Vos cartes :\n" + this.main.cartes.toString());
		
		this.attendre();
		
		ArrayList<Carte> cartesJouables = new ArrayList<Carte>();
		
		if (this.peutJouer()) {
			
			cartesJouables = this.obtenirCartesJouables();
			
			
			this.afficherMenu();
			while(!this.aJoue) {
				
				Partie.PAUSE = true;
				do {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} while(Partie.PAUSE);
				
				if(Partie.CARTE_SELEC == null){
					this.piocher();
					this.bonnePioche();
				}
				else{
					this.choisirCartes(Partie.CARTE_SELEC);
				}
			}
			
		}
		else
		{
			System.out.println("Vous ne pouvez jouer aucune carte ... Pioche automatique");
			this.piocher();
			this.bonnePioche();
		}
		
	}
	
	/**
	 * @deprecated
	 * 
	 * utilisée pour la console pour faire un choix, plus utilisée dans la version graphique
	 * 
	 * exemple : " Choisissez une carte à jouer ! 
	 * 0 - As de pique"
	 * 1 - Deux de pique"
	 * ==> l'utilisateur est restreint à choisir un des chiffres affichés grace à cette méthode
	 * 
	 * 
	 * @param borneInf : le paramètre minimal pouvant être entré par l'utilisateur
	 * @param borneSup : le paramètre maximal pouvant être entré par l'utilisateur
	 * @return le choix de l'utilisateur
	 */
	private int faireUnChoix(int borneInf, int borneSup) {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(isr);
		
		int choix;
		
		do {
			try {
				choix = Integer.parseInt(in.readLine());
			
			} catch (IOException ioe) {
				ioe.printStackTrace();
				choix = borneInf - 1;
			} catch (NumberFormatException e) {
				System.out.println("Saisie incorrecte, caractères non autorisés !");
				choix = borneInf - 1;
			}
		
		} while (!isChoixCorrect(choix, borneInf, borneSup));
		
		
		
		return choix;
	}
	
	/**
	 * Permet au joueur de rejouer après avoir pioché
	 * @see JoueurHumain#obtenirCartesJouables()
	 */
	private void bonnePioche() {
	
		if(this.peutJouer()) {
			ArrayList<Carte> cartesJouables =  this.obtenirCartesJouables();
			Talon t = Talon.getInstance();
			
			this.attendre();
			
			System.out.println(t.toString());
			this.afficherMenu();
			
			while(!this.aJoue) {
				
				Partie.PAUSE = true;
				do {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				} while(Partie.PAUSE);
				
				if(Partie.CARTE_SELEC != null){
					this.choisirCartes(Partie.CARTE_SELEC);
				}
				else {
					this.aJoue = true;
				}
			}
			
		}

		
	}
	
	/**
	 * @deprecated
	 * utilisée en version console pour afficher les cartes jouables et en jouer une parmis elles 
	 * Les cartes jouables n'ayant pas la meme reference dans la main que dans la liste de carte jouables,
	 * on récupère la carte avec indexOf()
	 * 
	 * @param cartesJouables
	 * 				Les cartes jouables retrouvées avec {@link JoueurHumain#obtenirCartesJouables()}
	 */
	private void choisirCartes(ArrayList<Carte> cartesJouables) {
		
		ListIterator<Carte> it = cartesJouables.listIterator();
		int pos;
		Talon t = Talon.getInstance();
		System.out.println(t.toString());
		
		while(it.hasNext()) {
			pos = it.nextIndex();
			Carte c = (Carte) it.next();
			System.out.println(pos + " -- " + c.toString());
		}
		System.out.println("Choisissez la carte que vous voulez jouer !");
		Carte c = cartesJouables.get(this.faireUnChoix(0, cartesJouables.size()-1));
		System.out.println("Vous jouez " +this.main.cartes.get(this.main.cartes.indexOf(c)));
		this.poserCarte(this.main.cartes.get(this.main.cartes.indexOf(c)));	
	}
	
	/**
	 * Utilisé dans notre moteur de jeu actuel utilisant l'interface swing
	 * Le joueur choisi une carte de sa main. Dans le cas ou celle ci est jouable, elle est posée
	 * sinon le joueur doit en choisir une autre
	 * @param c 
	 * 			La carte selectionnée
	 */
	public void choisirCartes(Carte c) {
		if(this.obtenirCartesJouables().contains(c)) {
			System.out.println("Vous jouez " +this.main.cartes.get(this.main.cartes.indexOf(c)));
			this.poserCarte(this.main.cartes.get(this.main.cartes.indexOf(c)));	
		}
		else {
			System.out.println("Vous ne pouvez pas jouer cette carte !");
		}
	}
	
	
	/**
	 * @deprecated 
	 * utilisé dans la version console pour verifier que le choix fait dans la méthode {@link JoueurHumain#faireUnChoix(int, int)}
	 * est correct
	 * 
	 * @param choix le choix du joueur
	 * @param borneInf la borne minimale du choix à faire
	 * @param borneSup la borne maximale du choix à faire
	 * 
	 * 
	 * @return true si le choix est correct, false sinon
	 * 
	 */
	private boolean isChoixCorrect(int choix, int borneInf, int borneSup) {
		
		boolean correct = false;
		if (choix < borneInf || choix > borneSup) {
			System.out.println("Veuillez rentrer une valeur comprise entre " + borneInf + " et " + borneSup);
		}
		else
			correct = true;

		return correct;
	}
	
	/**
	 * Constructeur JoueurHumain.
	 * Crée un joueur humain avec le nom donné
	 * en paramètre.
	 * @param nom
	 * 			Nom du joueur.
	 */
	public JoueurHumain(String nom) {
		this.main = new Paquet();
		this.main.cartes = new LinkedList<Carte>();
		this.nom = nom;
		this.listeners = new EventListenerList();
	}
	

	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append(this.nom);
		
		return sb.toString();
	}

	

	
}
