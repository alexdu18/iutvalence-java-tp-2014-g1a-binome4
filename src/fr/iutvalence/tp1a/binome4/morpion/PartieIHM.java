package fr.iutvalence.tp1a.binome4.morpion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class PartieIHM {

    /** Plateau de la partie. */
    private final Plateau m_plateau;
    /** Joueurs. */
    private final Joueur[] m_joueurs;
    /** Gestionnaire de score */
    private final Score m_score;
    /** Indice du joueur courant. */
    private int m_tour;
    /** Déclaration du tableau du jeu. */
    private final Pion[][] m_tableau;
    /** Compteur du nombre de tour. */
    private       int      m_nbTours;
    /** IHM. */
    private final Console m_console;

    public PartieIHM(final Joueur joueur1, final Joueur joueur2, final Score gestionnaireScore) {
	m_joueurs = new Joueur[] { joueur1, joueur2 };
	m_score = gestionnaireScore;
	m_plateau = new Plateau();
	m_console = new Console(); // a changer par l'ihm
	m_tour = 0;
	m_tableau = new Pion[3][3];
        for (int i = 0; i < 3; i++) {
            Arrays.fill(m_tableau[i], Pion.LIBRE);
        }
        m_nbTours = 0;
    }

    /** Gére une partie. */
    public void jouer() {
	m_console.afficherPlateau(m_plateau.toString());
	while (!m_plateau.victoire() && !m_plateau.estPlein()) {
	    m_console.afficherDebutTour(m_joueurs[m_tour]);
	    int[] saisie;
	    while (true) {
		saisie = m_console.saisirCoordonnees();
		if (m_plateau.estLibre(saisie[0], saisie[1])) {
		    break;
		}
		m_console.afficherErreurSaisie();
	    }
	    m_plateau.poserPion(saisie[0], saisie[1], m_joueurs[m_tour].pion());
	    m_console.afficherPlateau(m_plateau.toString());
	    m_tour = (m_tour + 1) % 2;
	}
	if (m_plateau.victoire()) {
	    final Joueur vainqueur = m_joueurs[(m_tour + 1) % 2];
	    m_console.afficherVainqueur(vainqueur);
	    m_score.gagne(vainqueur.nom());
	} else {
	    m_console.afficherMatchNul();
	    m_score.nul();
	}
	m_console.afficherStatistiques(m_joueurs, m_score);
    }

    public boolean estPlein() {
        return m_nbTours >= 9;
    }
    
    public boolean estLibre(final int ligne, final int colonne) {
        return m_tableau[ligne][colonne] == Pion.LIBRE;
    }

    /** Pose le pion, change le joueur, et incremente le compteur de tour. */
    public void poserPion(final int ligne, final int colonne, final Pion pion) {
        m_tableau[ligne][colonne] = pion;
        m_nbTours++;
    }

    /** Retourne vrai si il y a une victoire, faux sinon. */
    public boolean victoire() {
        return ((m_tableau[0][0] == m_tableau[0][1]) && (m_tableau[0][0] == m_tableau[0][2]) && (m_tableau[0][0] != Pion.LIBRE)) || (((m_tableau[1][0] == m_tableau[1][1]) && (m_tableau[1][0] == m_tableau[1][2])) && (m_tableau[1][0] != Pion.LIBRE)) || (((m_tableau[2][0] == m_tableau[2][1]) && (m_tableau[2][0] == m_tableau[2][2])) && (m_tableau[2][0] != Pion.LIBRE)) || (((m_tableau[0][0] == m_tableau[1][0]) && (m_tableau[0][0] == m_tableau[2][0])) && (m_tableau[0][0] != Pion.LIBRE)) || (((m_tableau[0][1] == m_tableau[1][1]) && (m_tableau[0][1] == m_tableau[2][1])) && (m_tableau[0][1] != Pion.LIBRE)) || (((m_tableau[0][2] == m_tableau[1][2]) && (m_tableau[0][2] == m_tableau[2][2])) && (m_tableau[0][2] != Pion.LIBRE)) || (((m_tableau[0][0] == m_tableau[1][1]) && (m_tableau[0][0] == m_tableau[2][2])) && (m_tableau[0][0] != Pion.LIBRE)) || (((m_tableau[0][2] == m_tableau[1][1]) && (m_tableau[0][2] == m_tableau[2][0])) && (m_tableau[0][2] != Pion.LIBRE));
    }
    
   

}
