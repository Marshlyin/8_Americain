package variante;

import modele.Carte;
import modele.Effet;
import modele.Partie;
import modele.Talon;
import modele.Variante;
import modele.Carte.Numero;
/**
 * 
 * La variante d'après wikipedia : 
 * 
    7 : fait piocher 2 cartes au joueur suivant, à moins de poser un autre 7, lequel ajoute 2 cartes à piocher au joueur d'après, soit 4 cartes, et ainsi de suite. Si le gagnant termine par un 7, le joueur suivant pioche quand même 2 cartes à moins qu'il possède lui-même un 7 ainsi de suite.
    8 : oblige à rejouer.
    10: change le sens.
    valet : carte universelle, elle peut être posée sur n'importe quelle couleur ; elle permet également de choisir sa couleur.
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
