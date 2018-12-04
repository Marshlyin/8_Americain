package modele;

/**
 * <b>TalonVideException est la classe représentant une exception du jeu Huit Américain.</b>
 * <p>
 * Cette exception est levé lorsque la pioche est vide mais que Talon 
 * ne comporte qu'une seule carte.
 * <p>
 * Ce cas arrive très rarement. Seulement lorsque l'utilisateur décide de ne piocher que des cartes, et de n'en
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
	 * Tout objet héritant de la classe Exception doivent être sérialisable.
	 * Un entier long a été généré automatiquement.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur TalonVideException.
	 * <p>
     * A la construction d'une exception TalonVideException. Un message d'érreur
     * est affiché dans la console.
     * 
     * </p>
	 */
	public TalonVideException()
	{
		System.out.println("Il n'y a pas assez de cartes pour que Talon puisse remplir la pioche !");
	}
	
}
