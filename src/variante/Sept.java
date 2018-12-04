package variante;

import modele.Carte;
import modele.Effet;
import modele.Partie;
import modele.Talon;
import modele.Variante;
import modele.Carte.Numero;
import modele.Carte.Symbole;

/**
 * La variante d'après wikipedia
10 : oblige à rejouer
7 : le joueur suivant passe son tour/rejouer si on joue à deux joueurs
Valet : changement de sens/rejouer si on joue à deux joueurs
As : fait piocher 2 cartes au joueur suivant, à moins de poser un autre As qui s'ajoute pour le joueur suivant
8 : comme en version minimale
Dame de trèfle : fait prendre 3 cartes au joueur suivant sans aucun recourt
*/
public class Sept extends Variante{

	@Override
	public void genererEffetCarte(Carte c) {
		Effet e;
		int nbJoueurs = Partie.getInstance().getNombreJoueur();
		
		switch(c.getNumero())
		{
		case Valet: {
			if (nbJoueurs <= 2)
				e = new Effet(1);
			else
				e = new Effet(5); 
			break;
		}
		case Dix : e = new Effet(1); break;
		case Sept: e = new Effet(4); break;
		case As : e = new Effet(2,2); break;
		case Huit: e = new Effet(3); break;
		case Dame : {
			if (c.getSymbole().equals(Symbole.Trèfle)){
				e = new Effet(2,3);
			}
		}
		default: e = new Effet(0); break;
		
		}
		
		c.setEffet(e);
		
	}

	public void posable(Carte c) {
		Carte carteSup = Talon.getInstance().getCarteSuperieure();
		boolean normalPosable = (c.getNumero().equals(carteSup.getNumero()) || c.getSymbole().equals(carteSup.getSymbole()));
		
		
		if(carteSup.getNumero().equals(Numero.As) && Partie.getInstance().getJoueurActuel().isaPioche()==false){
			c.setPosable(c.getNumero().equals(carteSup.getNumero()));
		} else {
		switch(c.getNumero())
		{
		case Huit: c.setPosable(true); break;
		default : c.setPosable(normalPosable);
		}
	}
	}

}
