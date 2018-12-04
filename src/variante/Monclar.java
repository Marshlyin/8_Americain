package variante;

import modele.Carte;
import modele.Effet;
import modele.Partie;
import modele.Talon;
import modele.Variante;
import modele.Carte.Numero;

/**
 * La variante d'après wikipedia :
 * 
    10 : oblige à rejouer
    7 : le joueur suivant passe son tour
    Valet : change le sens du jeu
    9 : fait piocher une carte au joueur suivant sans recours
    As : fait piocher 3 cartes au joueur suivant, à moins de poser un 8 ou un autre As qui s'ajoute pour le joueur suivant
    8 : permet de changer de couleur et arrête les attaques

 * @author Flavien
 *
 */
public class Monclar extends Variante {

	public void genererEffetCarte(Carte c) {
		Effet e;

		switch(c.getNumero()){
		case Dix : e = new Effet(1); break;
		case Sept : e = new Effet(4); break;
		case Valet : e = new Effet(5); break;
		case Neuf : e = new Effet(2,1); break;
		case As : e = new Effet(2,3); break;
		case Huit : e = new Effet(3); break; // TODO this.contrer
		default : e = new Effet(0); break;

		}

		c.setEffet(e);
	}

	public void posable(Carte c) {
		Carte carteSup = Talon.getInstance().getCarteSuperieure();
		boolean normalPosable = (c.getNumero().equals(carteSup.getNumero()) || c.getSymbole().equals(carteSup.getSymbole()));
	
		if(carteSup.getNumero().equals(Numero.As) && !Partie.getInstance().getJoueurActuel().isaPioche()) {
			c.setPosable(c.getNumero().equals(carteSup.getNumero()));
		} else {
		switch(c.getNumero())
		{
		case Huit: 
			if(carteSup.getNumero().equals(Numero.As)){
				c.setPosable(true);}
			break;
			default : c.setPosable(normalPosable);
		}
	}
	}

}
