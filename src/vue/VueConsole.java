package vue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ListIterator;

import classe.event.CarteChangedEvent;
import classe.event.ChoixCouleurChangedEvent;
import classe.event.JoueurChangedEvent;
import classe.event.PartieChangedEvent;
import classe.eventListener.CarteListener;
import classe.eventListener.ChoixCouleurListener;
import classe.eventListener.JoueurListener;
import classe.eventListener.PartieListener;
import modele.Joueur;
import modele.JoueurHumain;
import modele.Partie;

/**
 * <b>VueConsole est la classe représentant la vue console concurrente à l'interface graphique.</b>
 * <p>
 * La vue console est caractérisée par les informations suivantes :
 * <ul>
 * <li>Des chaînes de caractères statiques : "Jouer", "Quitter".</li>
 * <li>Une référence sur la Partie.</li>
 * <li>Un état qui représente l'état de la partie.</li>
 * <li>Un joueur qui est le joueur actuel de la partie.</li>
 * </ul>
 * 
 * <p>
 * NB: Cette vue concurrente n'a pas pu être spécifié complètement et n'est pas fonctionnel à 100%.
 * Elle n'est donc pas utilisée.
 * </p>
 * 
 * @see Partie
 * @see Joueur
 * @see Plateau
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public class VueConsole implements Runnable, PartieListener, ChoixCouleurListener, JoueurListener, CarteListener {
	
	/**
	 * Une chaîne de caractère spécifique pour indiquer "Jouer".
	 * Elle n'est pas modifiable.
	 */
	public static String JOUER = "Jouer";
	
	/**
	 * Une chaîne de caractère spécifique pour indiquer "Quitter".
	 * Elle n'est pas modifiable.
	 */
	public static String QUITTER = "Quitter";
	
	/**
	 * Une chaîne de caractère spécifique pour indiquer une flèche de saisie.
	 * Elle n'est pas modifiable.
	 */
	public static String PROMPT = ">";
	
	/**
	 * Une référence sur Partie pour pouvoir l'observer.
	 */
	private Partie partie;
	
	/**
	 * L'état actuel de la partie.
	 * Selon l'état, la saisie de l'utilisateur sera redirigée vers les fonctionnalités
	 * présentes;
	 */
	private int etat;
	
	/**
	 * Le joueur jouant actuellement.
	 */
	private Joueur joueurActuel;
	
	/**
	 * Constructeur VueConsole
	 * 
	 *  <p>
     * A la construction d'un objet VueConsole. La console s'ajoute en tant qu'observateur
     * de la partie et démarre dans un Thread concurrent à l'interface graphique.
     * </p>
     * 
	 */
	public VueConsole() {
		this.partie = Partie.getInstance();
		this.partie.ajouterPartieListener(this);
		
		Thread t = new Thread(this);
		t.start();
	}
	
	/**
	 * La console attend une saisie continuellement jusqu'à que l'utilisateur quitte l'application.
	 * Selon l'état de la partie et la saisie de l'utilisateur, la console appellera les méthodes
	 * correspondant à la saisie.
	 */
	public void run() {
		
		String saisie = null;
		boolean quitter = false;

		
		do {
			saisie = this.lireChaine();
			
			if(saisie != null) {
				switch(this.etat) {
				case 1 : break;
				case 2 : choisirCartes(saisie);
				default : System.out.println("Saisie incorrecte ..."); break;
				}
			}
			
		} while(quitter == false);
		
		System.exit(0);
	}
	
	/**
	 * Lorsque c'est le tour du joueur humain et qu'il n'a pas encore joué, 
	 * une saisie est demandée entre "Piocher" et "Passer".
	 * 
	 * @param saisie
	 * 				La saisie de l'utilisateur.
	 */
	private void choisirCartes(String saisie) {
		if(!this.joueurActuel.getAJoue()) {
			if(!this.joueurActuel.isaPioche()) {
				switch(saisie) {
				case "Piocher" : break;
				}
			}
			else {
				switch(saisie) {
				case "Passer" : break;
				}
			}
		}
		
	}
	
	/**
	 * Méthode demandant à l'utilisateur de saisir une chaîne de caractère dans la console.
	 * La saisie est enregistrée dans un BufferedReader afin de pouvoir traiter la saisie.
	 * 
	 * @return la saisie de l'utilisateur.
	 * @return en cas d'erreur, null.
	 */
	private String lireChaine(){
		BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
		String resultat = null;
		
		try{
			System.out.print(VueConsole.PROMPT);
			resultat = br.readLine();
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		return resultat;
	}

	/**
	 * Lorsque l'état de la partie change, la console actualise son
	 * état afin de permettre à l'utilisateur d'utiliser les fonctionnalités
	 * correspondantes à l'état actuel de la partie. Le joueur actuel est aussi
	 * actualisé.
	 * 
	 * Etat = 0 : Aucune partie en cours
	 * Etat = 1 : Partie en cours
	 */
	public void PartieChanged(PartieChangedEvent event) {
		if(event.estPartieEnCours()) {
			this.etat = 1;
			
			ListIterator<Joueur> it = event.getJoueurs().listIterator();
			while(it.hasNext()) {
				Joueur j = (Joueur) it.next();
				j.ajouterJoueurListener(this);
			}
			
		}
		else {
			this.etat = 0;
		}
		
	}
	
	/**
	 * Lorsque c'est le tour d'un nouveau joueur, on teste si le joueur
	 * actuel est humain. Si oui, l'état de la console passe en mode 2, sinon
	 * mode 1.
	 * Ce dernier peut ensuite "Jouer".
	 * 
	 * @see VueConsole#choisirCartes(String)
	 */
	public void joueurChanged(JoueurChangedEvent event) {
		if(event.getSource() instanceof JoueurHumain) {
			this.joueurActuel = (Joueur) event.getSource();
			this.etat = 2;
		}
		else {
			this.etat = 1;
		}
		
	}

	/**
	 * Cette méthode devrait afficher le choix des couleurs lors
	 * de l'événement "changement de couleur" dans la console.
	 * Elle n'a pas été implémentée dans le cadre de ce projet.
	 * Cette méthode n'est pas utilisée.
	 */
	public void choixCouleurChanged(ChoixCouleurChangedEvent event) {
		// TODO Auto-generated method stub
	}

	/**
	 * Cette méthode devrait actualiser les mains des joueurs.
	 * Elle n'a pas été implémentée dans le cadre de ce projet.
	 * Cette méthode n'est pas utilisée.
	 */
	public void carteChanged(CarteChangedEvent event) {
		// TODO Auto-generated method stub
	}
	
}
