package modele;

/**
 * <b>Carte est la classe représentant une carte du jeu Huit Américain.</b>
 * <p>
 * Une carte de jeu est caractérisé par les informations suivantes :
 * <ul>
 * <li>Un symbole attribué entre Carreau, Coeur, Pique, Trèfle.</li>
 * <li>Un numéro attribué entre le nombre Deux et Dix ainsi que Valet, Dame, Roi, As 
 * et la carte temporaire Temp.</li>
 * <li>Des points attribués, qui permettent de calculer le score d'un joueur en fin de manche.</li>
 * <li>Un effet attribué selon la variante choisie.  </li>
 * <li>Un booleen posable qui définit la possibilité de poser ou non une carte sur le Talon en 
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
     * <b>Symbole est un type énuméré "type-safe".</b> 
     * <p>
     * Il énumère les quatres couleurs d'un jeu de cartes classique :
     * <ul>
     * <li>Carreau</li>
     * <li>Coeur</li>
     * <li>Pique</li>
     * <li>Trèfle</li>
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
		Trèfle;
	}
	
	/**
     * <b>Numero est un type énuméré "type-safe".</b>
     * <p>
     * Il énumère tous les numéros d'un jeu de cartes classique :
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
     * Avec une valeur spéciale la carte "temporaire" Temp qui réprésente la carte de
     * couleur suite à un changement de couleur.
     * </p>
     * La valeur d'un numero d'une carte ne change pas.
     * L'énumération possède un constructeur interne qui permet d'associer à un numéro
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
         * Les points d'un numéro : définis dans l'énumération Numero.
         * 
         * @see Numero
         * @see Numero#Numero(int)
         */
		protected int point;
		
		/**
         * Constructeur Numero.
         * <p>
         * A la construction d'un objet Numero, les points sont directement
         * affecté à l'attribut point de Numero.
         * </p>
         * 
         * @param point
         *            Le nombre de points attribué.
         * 
         * @see Numero#point
         */
		Numero(int point) {
			this.point = point;
		}
	}
	
	
	/**
     * Les points de la carte. Ils sont attribués directement depuis l'énumération
     * Numero dans le constructeur de Carte. Ils ne sont pas modifiables.
     * 
     * @see Carte#getPoint()
     * @see Carte#Carte(Numero, Symbole)
     */
	private int point;
	
	/**
     * Le symbole de la carte. Il est attribué directement depuis l'énumération
     * Symbole dans le constructeur de Carte. Il n'est pas modifiable.
     * 
     * @see Carte#getSymbole()
     * @see Carte#Carte(Numero, Symbole)
     */
	private Symbole symbole;
	
	/**
     * Le numéro de la carte. Il est attribué directement depuis l'énumération
     * Numero dans le constructeur de Carte. Il n'est pas modifiable.
     * 
     * @see Carte#getNumero()
     * @see Carte#Carte(Numero, Symbole)
     */
	private Numero numero;
	
	/**
     * Ce booléen indique si la carte peut être posée sur le talon. 
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
     * L'effet de la carte. Il est attribué directement selon la variante lors
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
     * A la construction d'un objet Carte, le symbole et le numéro de carte sont
     * initialisés par les paramètres donnés en argument. Les points sont aussi
     * initialisés via l'attribut point du numéro.
     * </p>
     * 
     * @param numero
     *            Le numéro de le carte.
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
     * Retourne un booléen indiquant si la carte possède un effet.
     * <p>
     * A l'initialisation de la pioche, l'effet de la carte est initialisé.
     * Le numéro 0 est attribué pour les cartes sans effet.
     * Cette méthode teste si le numéro de l'effet est égal à 0 ou non.
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
     * Retourne le booléen posable de la carte après actualisation.
     * La variante définit si la carte peut être posée sur le talon.
     * On appelle la méthode {@link Variante#posable(Carte)} qui actualise
     * le booléen posable de la carte.
     * 
     * @return le booléen posable.
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
	 * Cette méthode appelle la méthode action() de l'effet.
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
	 * @return L'effet correspondant à la carte sous la forme d'un effet.
	 */
	public Effet getEffet() {
		return effet;
	}
	
	/**
	 * Met à jour l'effet de la carte.
	 * @param effet
	 * 				Le nouveau effet de la carte.
	 */
	public void setEffet(Effet effet) {
		this.effet = effet;
	}
	
	/**
	 * Retourne le numéro de la carte.
	 * @return Le numéro correspondant sous la forme d'un numéro.
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
	 * Retourne le booléen posable de la carte.
	 * @return Le booléen posable dans l'état où il est lors de l'appel
	 * 			de cette méthode.
	 */
	public boolean isPosable() {
		return this.posable;
	}
	
	/**
	 * Met à jour le booléen posable de la carte.
	 * @param posable
	 * 					Le nouveau booléen indiquant si la carte
	 * 					peut être posée sur le talon.
	 */
	public void setPosable(boolean posable) {
		this.posable = posable;
	}

	/**
	 * Retourne le numéro et le symbole de la carte. 
	 * Dans le cas de la carte "Temporaire", on indique 
	 * la couleur à jouer.
	 * 
	 * @return la couleur/le symbole de la carte à jouer sous forme de String ou
	 * 			le numéro et le symbole de la carte sous forme de String.
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




