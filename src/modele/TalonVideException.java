package modele;

/**
 * <b>TalonVideException est la classe repr�sentant une exception du jeu Huit Am�ricain.</b>
 * <p>
 * Cette exception est lev� lorsque la pioche est vide mais que Talon 
 * ne comporte qu'une seule carte.
 * <p>
 * Ce cas arrive tr�s rarement. Seulement lorsque l'utilisateur d�cide de ne piocher que des cartes, et de n'en
 * jouer aucune.
 * </p>
 * 
 * @see JoueurHumain
 * @see Pioche#distribuer(int)
 * @see Talon#vider()
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public class TalonVideException extends Exception{
	
	/**
	 * Tout objet h�ritant de la classe Exception doivent �tre s�rialisable.
	 * Un entier long a �t� g�n�r� automatiquement.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur TalonVideException.
	 * <p>
     * A la construction d'une exception TalonVideException. Un message d'�rreur
     * est affich� dans la console.
     * 
     * </p>
	 */
	public TalonVideException()
	{
		System.out.println("Il n'y a pas assez de cartes pour que Talon puisse remplir la pioche !");
	}
	
}
