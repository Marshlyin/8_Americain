package modele;

/**
 * <b>Carte est la classe repr�sentant une carte du jeu Huit Am�ricain.</b>
 * <p>
 * Une carte de jeu est caract�ris� par les informations suivantes :
 * <ul>
 * <li>Un symbole attribu� entre Carreau, Coeur, Pique, Tr�fle.</li>
 * <li>Un num�ro attribu� entre le nombre Deux et Dix ainsi que Valet, Dame, Roi, As 
 * et la carte temporaire Temp.</li>
 * <li>Des points attribu�s, qui permettent de calculer le score d'un joueur en fin de manche.</li>
 * <li>Un effet attribu� selon la variante choisie.  </li>
 * <li>Un booleen posable qui d�finit la possibilit� de poser ou non une carte sur le Talon en 
 * fonction de la variante.</li>
 * </ul>
 * <p>
 * De plus, une carte est souvent contenue dans un Paquet.
 * 
 * @see Paquet
 * @see Variante
 * @see Effet
 * @see Talon
 * 
 * @author Vincent HBT
 * @version 1.0
 */
public class Carte {
	
	/**
     * <b>Symbole est un type �num�r� "type-safe".</b> 
     * <p>
     * Il �num�re les quatres couleurs d'un jeu de cartes classique :
     * <ul>
     * <li>Carreau</li>
     * <li>Coeur</li>
     * <li>Pique</li>
     * <li>Tr�fle</li>
     * </ul>
     * La valeur d'un symbole d'une carte ne change pas.
     * 
     * @see Carte#getSymbole()
     * @see Carte#Carte(Numero, Symbole)
     */
	public enum Symbole {
		Carreau, 
		Coeur, 
		Pique, 
		Tr�fle;
	}
	
	/**
     * <b>Numero est un type �num�r� "type-safe".</b>
     * <p>
     * Il �num�re tous les num�ros d'un jeu de cartes classique :
	 * <ul>
	 * <li>Deux</li>
	 * <li>Trois</li>
	 * <li>Quatre</li>
	 * <li>Cinq</li>
	 * <li>Six</li>
	 * <li>Sept</li>
	 * <li>Huit</li>
	 * <li>Neuf</li>
	 * <li>Dix</li>
	 * <li>Valet</li>
	 * <li>Dame</li>
	 * <li>Roi</li>
	 * <li>As</li>
	 * <li>Temp</li>
	 * </ul>
     * <p>
     * Avec une valeur sp�ciale la carte "temporaire" Temp qui r�pr�sente la carte de
     * couleur suite � un changement de couleur.
     * </p>
     * La valeur d'un numero d'une carte ne change pas.
     * L'�num�ration poss�de un constructeur interne qui permet d'associer � un num�ro
     * une valeur de points.
     * 
     * @see Numero#Numero(int)
     * @see Effet#changerCouleur()
     * @see Carte#getNumero()
     * @see Carte#Carte(Numero, Symbole)
     */
	public enum Numero {
		Temp(0), 
		Deux(20),
		Trois(3),
		Quatre(4),
		Cinq(5),
		Six(6),
		Sept(20), 
		Huit(50), 
		Neuf(9), 
		Dix(20), 
		Valet(20), 
		Dame(10),
		Roi(10), 
		As(50);
		
		/**
         * Les points d'un num�ro : d�finis dans l'�num�ration Numero.
         * 
         * @see Numero
         * @see Numero#Numero(int)
         */
		protected int point;
		
		/**
         * Constructeur Numero.
         * <p>
         * A la construction d'un objet Numero, les points sont directement
         * affect� � l'attribut point de Numero.
         * </p>
         * 
         * @param point
         *            Le nombre de points attribu�.
         * 
         * @see Numero#point
         */
		Numero(int point) {
			this.point = point;
		}
	}
	
	
	/**
     * Les points de la carte. Ils sont attribu�s directement depuis l'�num�ration
     * Numero dans le constructeur de Carte. Ils ne sont pas modifiables.
     * 
     * @see Carte#getPoint()
     * @see Carte#Carte(Numero, Symbole)
     */
	private int point;
	
	/**
     * Le symbole de la carte. Il est attribu� directement depuis l'�num�ration
     * Symbole dans le constructeur de Carte. Il n'est pas modifiable.
     * 
     * @see Carte#getSymbole()
     * @see Carte#Carte(Numero, Symbole)
     */
	private Symbole symbole;
	
	/**
     * Le num�ro de la carte. Il est attribu� directement depuis l'�num�ration
     * Numero dans le constructeur de Carte. Il n'est pas modifiable.
     * 
     * @see Carte#getNumero()
     * @see Carte#Carte(Numero, Symbole)
     */
	private Numero numero;
	
	/**
     * Ce bool�en indique si la carte peut �tre pos�e sur le talon. 
     * Il est accessible et modifiable tout le long de la partie.
     * Ce sont les effets et la variante qui vont interagir avec ce dernier.
     * 
     * @see Carte#isPosable()
     * @see Carte#setPosable(boolean)
     * @see Effet
     * @see Variante
     */
	private boolean posable;
	
	/**
     * L'effet de la carte. Il est attribu� directement selon la variante lors
     * de l'initialisation de la Pioche.
     * 
     * @see Pioche#initialiser()
     * @see Carte#getEffet()
     * @see Carte#setEffet(Effet)
     * @see Variante
     */
	private Effet effet;
	

	/**
     * Constructeur Carte.
     * <p>
     * A la construction d'un objet Carte, le symbole et le num�ro de carte sont
     * initialis�s par les param�tres donn�s en argument. Les points sont aussi
     * initialis�s via l'attribut point du num�ro.
     * </p>
     * 
     * @param numero
     *            Le num�ro de le carte.
     * @param symbole
     *            Le symbole de la carte.
     * 
     * @see Carte#numero
     * @see Carte#point
     * @see Carte#symbole
     */
	public Carte(Numero numero, Symbole symbole){
		this.numero = numero;
		this.point = numero.point;
		this.symbole = symbole;
	}
	
	/**
     * Retourne un bool�en indiquant si la carte poss�de un effet.
     * <p>
     * A l'initialisation de la pioche, l'effet de la carte est initialis�.
     * Le num�ro 0 est attribu� pour les cartes sans effet.
     * Cette m�thode teste si le num�ro de l'effet est �gal � 0 ou non.
     * </p>
     * 
     * @return vrai si la carte a un effet, faux sinon
     * 
     * @see Carte#effet
     * @see Pioche#initialiser()
     */
	public boolean possedeUnEffet() {
		if (this.effet.getNumEffet() != 0)
			return true;
		else
			return false;
	}
	
	/**
     * Retourne le bool�en posable de la carte apr�s actualisation.
     * La variante d�finit si la carte peut �tre pos�e sur le talon.
     * On appelle la m�thode {@link Variante#posable(Carte)} qui actualise
     * le bool�en posable de la carte.
     * 
     * @return le bool�en posable.
     * 
     * @see Variante#posable(Carte)
     * @see Carte#posable
     */
	public boolean peutEtrePoseeSurTalon() {
		Partie.getInstance().getVariante().posable(this);
		return this.isPosable();
	}
	
	/**
	 * Active l'effet de carte.
	 * Cette m�thode appelle la m�thode action() de l'effet.
	 * 
	 * @see Effet#action()
	 */
	public void activerEffet() {
		this.effet.action();
	}
	
	/**
	 * Retourne le symbole de la carte.
	 * @return Le symbole correspondant sous la forme d'un symbole.
	 */
	public Symbole getSymbole() {
		return symbole;
	}
	
	/**
	 * Retourne l'effet de la carte.
	 * @return L'effet correspondant � la carte sous la forme d'un effet.
	 */
	public Effet getEffet() {
		return effet;
	}
	
	/**
	 * Met � jour l'effet de la carte.
	 * @param effet
	 * 				Le nouveau effet de la carte.
	 */
	public void setEffet(Effet effet) {
		this.effet = effet;
	}
	
	/**
	 * Retourne le num�ro de la carte.
	 * @return Le num�ro correspondant sous la forme d'un num�ro.
	 */
	public Numero getNumero() {
		return numero;
	}
	
	/**
	 * Retourne les points de la carte.
	 * @return Les points de la cartes sous la forme d'un entier.
	 */
	public int getPoint() {
		return point;
	}
	/**
	 * Retourne le bool�en posable de la carte.
	 * @return Le bool�en posable dans l'�tat o� il est lors de l'appel
	 * 			de cette m�thode.
	 */
	public boolean isPosable() {
		return this.posable;
	}
	
	/**
	 * Met � jour le bool�en posable de la carte.
	 * @param posable
	 * 					Le nouveau bool�en indiquant si la carte
	 * 					peut �tre pos�e sur le talon.
	 */
	public void setPosable(boolean posable) {
		this.posable = posable;
	}

	/**
	 * Retourne le num�ro et le symbole de la carte. 
	 * Dans le cas de la carte "Temporaire", on indique 
	 * la couleur � jouer.
	 * 
	 * @return la couleur/le symbole de la carte � jouer sous forme de String ou
	 * 			le num�ro et le symbole de la carte sous forme de String.
	 */
	public String toString(){
		StringBuffer sb = new StringBuffer();
		
		if (this.getNumero() == Numero.Temp) {
			sb.append("Veuillez jouer une carte de ");
			sb.append(this.getSymbole());
		} 
		else {
			sb.append(this.getNumero());
			sb.append(" de ");
			sb.append(this.getSymbole());
		}
		
		return sb.toString();
	}
}




