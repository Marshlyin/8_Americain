package variante;

import modele.Carte;
import modele.Effet;
import modele.Talon;
import modele.Variante;

public class ManEtResta extends Variante {

	public void genererEffetCarte(Carte c) {
		
		Effet e;
		
		switch(c.getNumero())
		{
		case Dix: e = new Effet(1); break;
		case Sept: e = new Effet(4); break;
		case Valet : e = new Effet(5); break;
		case As : e = new Effet(2,2); break;
		case Deux: e = new Effet(2,2); break;
		case Huit: e = new Effet(3); break;
		default: e = new Effet(0); break;
		
		}
		
		c.setEffet(e);
	}

	public void posable(Carte c)
	{
		Carte carteSup = Talon.getInstance().getCarteSuperieure();
		boolean normalPosable = (c.getNumero().equals(carteSup.getNumero()) || c.getSymbole().equals(carteSup.getSymbole()));
		switch(c.getNumero())
		{
		case Huit: c.setPosable(true); break;
		default : c.setPosable(normalPosable);
		}
	}
	
	//public void derniereCartePosee() si la derniere carte posee est un AS
	//le joueur suivant peut poser son AS s'il en a un sinon piocher deux cartes
	//puis FIN de Manche avec comptage de points

}
