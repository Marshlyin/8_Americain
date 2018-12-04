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
 * <b>VueConsole est la classe repr�sentant la vue console concurrente � l'interface graphique.</b>
 * <p>
 * La vue console est caract�ris�e par les informations suivantes :
 * <ul>
 * <li>Des cha�nes de caract�res statiques : "Jouer", "Quitter".</li>
 * <li>Une r�f�rence sur la Partie.</li>
 * <li>Un �tat qui repr�sente l'�tat de la partie.</li>
 * <li>Un joueur qui est le joueur actuel de la partie.</li>
 * </ul>
 * 
 * <p>
 * NB: Cette vue concurrente n'a pas pu �tre sp�cifi� compl�tement et n'est pas fonctionnel � 100%.
 * Elle n'est donc pas utilis�e.
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
	 * Une cha�ne de caract�re sp�cifique pour indiquer "Jouer".
	 * Elle n'est pas modifiable.
	 */
	public static String JOUER = "Jouer";
	
	/**
	 * Une cha�ne de caract�re sp�cifique pour indiquer "Quitter".
	 * Elle n'est pas modifiable.
	 */
	public static String QUITTER = "Quitter";
	
	/**
	 * Une cha�ne de caract�re sp�cifique pour indiquer une fl�che de saisie.
	 * Elle n'est pas modifiable.
	 */
	public static String PROMPT = ">";
	
	/**
	 * Une r�f�rence sur Partie pour pouvoir l'observer.
	 */
	private Partie partie;
	
	/**
	 * L'�tat actuel de la partie.
	 * Selon l'�tat, la saisie de l'utilisateur sera redirig�e vers les fonctionnalit�s
	 * pr�sentes;
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
     * de la partie et d�marre dans un Thread concurrent � l'interface graphique.
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
	 * La console attend une saisie continuellement jusqu'� que l'utilisateur quitte l'application.
	 * Selon l'�tat de la partie et la saisie de l'utilisateur, la console appellera les m�thodes
	 * correspondant � la saisie.
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
	 * Lorsque c'est le tour du joueur humain et qu'il n'a pas encore jou�, 
	 * une saisie est demand�e entre "Piocher" et "Passer".
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
	 * M�thode demandant � l'utilisateur de saisir une cha�ne de caract�re dans la console.
	 * La saisie est enregistr�e dans un BufferedReader afin de pouvoir traiter la saisie.
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
	 * Lorsque l'�tat de la partie change, la console actualise son
	 * �tat afin de permettre � l'utilisateur d'utiliser les fonctionnalit�s
	 * correspondantes � l'�tat actuel de la partie. Le joueur actuel est aussi
	 * actualis�.
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
	 * actuel est humain. Si oui, l'�tat de la console passe en mode 2, sinon
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
	 * Cette m�thode devrait afficher le choix des couleurs lors
	 * de l'�v�nement "changement de couleur" dans la console.
	 * Elle n'a pas �t� impl�ment�e dans le cadre de ce projet.
	 * Cette m�thode n'est pas utilis�e.
	 */
	public void choixCouleurChanged(ChoixCouleurChangedEvent event) {
		// TODO Auto-generated method stub
	}

	/**
	 * Cette m�thode devrait actualiser les mains des joueurs.
	 * Elle n'a pas �t� impl�ment�e dans le cadre de ce projet.
	 * Cette m�thode n'est pas utilis�e.
	 */
	public void carteChanged(CarteChangedEvent event) {
		// TODO Auto-generated method stub
	}
	
}
