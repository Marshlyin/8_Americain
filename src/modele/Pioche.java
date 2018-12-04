package modele;

import java.util.LinkedList;

import modele.Carte.Numero;
import modele.Carte.Symbole;

/**
 * La pioche est ici un Singleton. C'est elle qui initialise toute les cartes
 * et qui les disitribue au joueurs. 
 * Elle est du type Paquet.
 * La r�serve est le nombre de carte en plus que le joueur pioche (en cas d'effet +2 par exemple)
 * @author Flavien
 *
 */
public class Pioche extends Paquet { 
	
	
	private int reserve = 0;
	
	/**
	 * Constructeur de pioche, tout est rassembl� au sein de {@link Pioche#initialiser()}
	 */
	private Pioche() {
		this.initialiser();
	}
	
	public int getReserve() {
		return reserve;
	}
	
	/**
	 * augmente la r�serve du nombre de carte n�cessaire
	 * @param supp le nomple de carte suppl�mentaire
	 */
	public void augmenterReserve(int supp) {
		this.reserve = reserve + supp;
	}
	
	/**
	 * reinitialise la r�serve 
	 * @see Joueur#piocher()
	 */
	public void reinitialiserReserve() {
		this.reserve = 0;
	}
	
	/**
	 * la m�thode utilis�e dans le constructeur pour initialiser la pioche
	 * Les cartes sont cr�es dans la double boucle for (couleur puis num�ro) puis ont leur effet attribu� 
	 * en fonction de la variante
	 */
	public void initialiser()
	{
		Variante v = Partie.getInstance().getVariante();
		this.cartes = new LinkedList<Carte>();
		int nbCartes = Partie.CARTEMIN;
		// On fait une boucle pour parcourir les num�ros dans une boucle qui parcourt les symboles
		for (int i = 0; i < Symbole.values().length; i++)
		{
			for(int j = nbCartes; j < Numero.values().length; j++)
			{
				Carte carte = new Carte(Numero.values()[j], Symbole.values()[i]);
				v.genererEffetCarte(carte);
				this.cartes.add(((Numero.values().length - nbCartes)* i + (j-nbCartes)) , carte);
			}
		}
		
		// Melanger les cartes
		this.melanger();
	}
	
	/**
	 * r�cup�re les cartes du talon et les remets dans la pioche	
	 * @param cartesTalon
	 * 					Liste des cartes du Talon.
	 */
	public void remplir(LinkedList<Carte> cartesTalon) {
		this.cartes.addAll(cartesTalon);
		this.melanger();
	}
	
	/**
	 * distribue le nombre de carte n�cessaire � chaque joueur.
	 * Si la pioche est vide, le talon est vid� et remis au sein de la pioche 
	 * 
	 * @param nbCartes le nombre de carte 
	 * @return les cartes pioch�es
	 * @throws TalonVideException
	 * 						l�ve une exception si le talon est vide.
	 */
	public LinkedList<Carte> distribuer(int nbCartes) throws TalonVideException {
		
		LinkedList<Carte> cartesPioches = new LinkedList<Carte>();
		
		for (int i = 0; i < nbCartes; i++) {
			
			if (this.cartes.isEmpty())
			{
				try {
					Talon.getInstance().vider();
				} catch (TalonVideException tve) {
					throw tve;
				}
				
				
			}
			cartesPioches.add(this.cartes.poll());
		}
		
		
		return cartesPioches;
	}
	
	/** Holder */
	
	private static class PiocheHolder
	{		
		/** Instance unique non preinitialisee */
		private final static Pioche instance = new Pioche();
	}
	
	/**
	 *  Point d'acces pour l'instance unique du singleton.
	 * @return l'instance de pioche.
	 */
	public static Pioche getInstance()
	{
		return PiocheHolder.instance;
	}
	
	public int getTaillePioche() {
		return this.cartes.size();
	}
	

	
	
		
}
