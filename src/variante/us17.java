package variante;

import modele.Carte;
import modele.Effet;
import modele.Partie;
import modele.Talon;
import modele.Variante;
import modele.Carte.Numero;
/**
 * 
 * La variante d'apr�s wikipedia : 
 * 
    7 : fait piocher 2 cartes au joueur suivant, � moins de poser un autre 7, lequel ajoute 2 cartes � piocher au joueur d'apr�s, soit 4 cartes, et ainsi de suite. Si le gagnant termine par un 7, le joueur suivant pioche quand m�me 2 cartes � moins qu'il poss�de lui-m�me un 7 ainsi de suite.
    8 : oblige � rejouer.
    10: change le sens.
    valet : carte universelle, elle peut �tre pos�e sur n'importe quelle couleur ; elle permet �galement de choisir sa couleur.
    as : passe le tour de son voisin.

 * @author Flavien
 *
 */
public class us17 extends Variante {

	@Override
	public void genererEffetCarte(Carte c) {
		Effet e;
		
		switch(c.getNumero())
		{
		case Sept : e = new Effet(2,2); break;
		case Huit: e = new Effet(1); break;
		case Dix : e = new Effet(5); break;
		case Valet : e = new Effet(3); break;
		case As : e = new Effet(4); break;
		default: e = new Effet(0); break;
		
		}
		
		c.setEffet(e);
		
	}

	@Override
	public void posable(Carte c) {
		Carte carteSup = Talon.getInstance().getCarteSuperieure();
		boolean normalPosable = (c.getNumero().equals(carteSup.getNumero()) || c.getSymbole().equals(carteSup.getSymbole()));
		
		
		if(carteSup.getNumero().equals(Numero.As) && Partie.getInstance().getJoueurActuel().isaPioche()==false){
			c.setPosable(c.getNumero().equals(carteSup.getNumero()));
		} else {
		switch(c.getNumero())
		{
		case Valet: c.setPosable(true); break;
		default : c.setPosable(normalPosable);
		}
		}
	}

}
