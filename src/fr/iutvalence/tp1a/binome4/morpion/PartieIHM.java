package fr.iutvalence.tp1a.binome4.morpion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ImageIcon;

public class PartieIHM {
    /** Plateau de la partie. */
	protected final Plateau m_plateau;
    /** Joueurs. */
    protected final Joueur[] m_joueurs;
    /** Gestionnaire de score */
    protected final Score m_score;
    /** Indice du joueur courant. */
    protected int m_tour;
    /** Compteur du nombre de tour. */
    protected int      m_nbTours;
    
    public PartieIHM(final Joueur joueur1, final Joueur joueur2, final Score gestionnaireScore) {
    	m_joueurs = new Joueur[] { joueur1, joueur2 };
    	m_score = gestionnaireScore;
    	m_plateau = new Plateau();
    	m_tour = 0;
    	m_nbTours = 0;
    }


    public boolean estPlein() {
        return m_nbTours >= 9;
    }
    

    /** Retourne vrai si il y a une victoire, faux sinon. */
    public boolean victoire() {
    	return m_plateau.victoire();
    }
    
    public Pion joueurCourant() {
    	return m_joueurs[m_tour].pion(); 
    }
    
}
