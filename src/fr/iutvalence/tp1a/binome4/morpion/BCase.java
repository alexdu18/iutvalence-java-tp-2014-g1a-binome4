package fr.iutvalence.tp1a.binome4.morpion;

import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class BCase extends JButton {
	/**
	 * 
	 */
	private int ligne;
	private int colonne;
	private Pion pion;

	public BCase(int numL, int numC, ActionListener abonnement) {
		this.ligne = numL;
		this.colonne = numC;
		pion = Pion.LIBRE;
		this.addActionListener(abonnement);
	}
	
	public int obtenirLigne() {
		return ligne;
	}

	public int obtenirColonne() {
		return colonne;
	}
	
	public Pion obtenirPion(){
		return pion;
	}
	
	public void poserPion(int tour) {
		if(tour%2 == 1){
			this.setIcon(new ImageIcon("img/cercle.png"));
			pion = Pion.JOUEUR2;
		}else{
			this.setIcon(new ImageIcon("img/croix.png"));
			pion = Pion.JOUEUR1;
		}
	}
	
	public boolean victoire() {
        return ((m_tableau[0][0] == m_tableau[0][1]) && (m_tableau[0][0] == m_tableau[0][2]) && (m_tableau[0][0] != Pion.LIBRE)) || (((m_tableau[1][0] == m_tableau[1][1]) && (m_tableau[1][0] == m_tableau[1][2])) && (m_tableau[1][0] != Pion.LIBRE)) || (((m_tableau[2][0] == m_tableau[2][1]) && (m_tableau[2][0] == m_tableau[2][2])) && (m_tableau[2][0] != Pion.LIBRE)) || (((m_tableau[0][0] == m_tableau[1][0]) && (m_tableau[0][0] == m_tableau[2][0])) && (m_tableau[0][0] != Pion.LIBRE)) || (((m_tableau[0][1] == m_tableau[1][1]) && (m_tableau[0][1] == m_tableau[2][1])) && (m_tableau[0][1] != Pion.LIBRE)) || (((m_tableau[0][2] == m_tableau[1][2]) && (m_tableau[0][2] == m_tableau[2][2])) && (m_tableau[0][2] != Pion.LIBRE)) || (((m_tableau[0][0] == m_tableau[1][1]) && (m_tableau[0][0] == m_tableau[2][2])) && (m_tableau[0][0] != Pion.LIBRE)) || (((m_tableau[0][2] == m_tableau[1][1]) && (m_tableau[0][2] == m_tableau[2][0])) && (m_tableau[0][2] != Pion.LIBRE));
    }
}
