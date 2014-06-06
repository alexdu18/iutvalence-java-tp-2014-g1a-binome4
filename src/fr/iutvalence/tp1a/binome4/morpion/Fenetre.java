package fr.iutvalence.tp1a.binome4.morpion;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.WindowConstants;

public class Fenetre extends JFrame implements Runnable, ActionListener {
	/**
	 * 
	 */
	private JTextField jtf1;
	private JTextField jtf2;
	private JLabel lab1;
	private JLabel lab2;

	private JButton valide;

	private JDialog fenetreFinTour;
	private JFrame demande;
	private JFrame tabMorpion;
	private int tour;
	private PartieIHM model;
	private JButton rejouer;
	private JButton quitter;

	private Joueur joueur1;
	private Joueur joueur2;
	private Score gestionnaireScore;
	private JTextArea texte;
	private String text;

	@Override
	public void run() {
		demanderJoueurs();
		jeu();
	}

	private void demanderJoueurs() {

		/** Fenetre globale */
		demande = new JFrame("Morpion");
		demande.setSize(500, 500);
		demande.setResizable(false);
		demande.setLocationRelativeTo(null);
		demande.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tour = 0;

		/** Conteneur global */
		JPanel container = new JPanel();
		container.setBackground(Color.white);

		/** Police */
		Font police = new Font("Arial", Font.BOLD, 16);

		/** Champ 1 */
		jtf1 = new JTextField("Joueur1");
		jtf1.setPreferredSize(new Dimension(230, 40));
		jtf1.setFont(police);
		jtf1.setForeground(Color.BLUE);
		lab1 = new JLabel("Pseudo joueur 1");
		lab1.setFont(police);
		container.add(lab1);
		container.add(jtf1);

		/** Champ 2 */
		jtf2 = new JTextField("Joueur2");
		jtf2.setPreferredSize(new Dimension(230, 40));
		jtf2.setFont(police);
		jtf2.setForeground(Color.BLUE);
		lab2 = new JLabel("Pseudo joueur 2");
		lab2.setFont(police);
		container.add(lab2);
		container.add(jtf2);

		/** Bouton de validation */
		valide = new JButton("Valider");
		valide.setFont(police);
		valide.addActionListener(this);
		container.add(valide);

		/** Traitement divers */
		demande.setContentPane(container);
		demande.setVisible(true);
	}

	private void fenetreFinTour() {

		/** Fenetre globale */
		fenetreFinTour = new JDialog(tabMorpion, "Morpion", true);
		fenetreFinTour.setSize(800, 200);
		fenetreFinTour.setResizable(false);
		fenetreFinTour.setLocationRelativeTo(null);
		fenetreFinTour.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		/** Conteneur global */
		JPanel container = new JPanel();
		container.setBackground(Color.white);

		/** Police */
		Font police = new Font("Arial", Font.BOLD, 16);

		if (model.victoire() == true) {
			Joueur vainqueur = model.m_joueurs[(tour + 1) % 2];
			model.m_score.gagne(vainqueur.nom());
			text = vainqueur.nom() + " gagne ! \n\n"
					+model.m_joueurs[0].nom()+" = "+model.m_score.getScore(model.m_joueurs[0].nom())+"\n"
					+model.m_joueurs[1].nom()+" = "+model.m_score.getScore(model.m_joueurs[1].nom())+"\n"
					+"Nul = "+model.m_score.getNul();
		} else {
			model.m_score.nul();
			text = "Match nul ! \n\n"
					+model.m_joueurs[0].nom()+" = "+model.m_score.getScore(model.m_joueurs[0].nom())+"\n"
					+model.m_joueurs[1].nom()+" = "+model.m_score.getScore(model.m_joueurs[1].nom())+"\n"
					+"Nul = "+model.m_score.getNul();
		}
		texte = new JTextArea(text);
		texte.setFont(police);
		this.fenetreFinTour.add(texte);

		JPanel barreBas = new JPanel();
		rejouer = new JButton("Rejouer");
		rejouer.addActionListener(this);
		this.rejouer.setFont(police);
		quitter = new JButton("Quitter");
		quitter.addActionListener(this);
		this.quitter.setFont(police);
		barreBas.add(rejouer);
		barreBas.add(quitter);
		this.fenetreFinTour.add(barreBas, BorderLayout.SOUTH);
		fenetreFinTour.setVisible(true);
		
	}

	private void jeu() {

		/** Fenetre globale */
		tabMorpion = new JFrame("Morpion");
		tabMorpion.setSize(500, 500);
		// tabMorpion.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		tabMorpion.setLocationRelativeTo(null);

		/** Conteneur global */
		JPanel container = new JPanel();
		container.setBackground(Color.white);

		/** Grille de jeu */
		tabMorpion.setLayout(new GridLayout(3, 3));

		for (int numeroDeLigne = 1; numeroDeLigne < 4; numeroDeLigne++) {
			for (int numeroDeColonne = 1; numeroDeColonne < 4; numeroDeColonne++) {
				tabMorpion.add(new BCase(numeroDeLigne, numeroDeColonne, this));
			}
		}

		/** Traitement divers */
		tabMorpion.setVisible(false);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == valide) {
			joueur1 = new Joueur(jtf1.getText(), Pion.JOUEUR1);
			joueur2 = new Joueur(jtf2.getText(), Pion.JOUEUR2);
			gestionnaireScore = new Score(joueur1, joueur2);
			model = new PartieIHM(joueur1, joueur2, gestionnaireScore);
			demande.setVisible(false);
			tabMorpion.setVisible(true);
		} else if (e.getSource() == rejouer) {
			fenetreFinTour.dispose();
			tabMorpion.dispose();
			
			//Mettre en place la solution pour refaire une partie
			
		} else if (e.getSource() == quitter) {
			fenetreFinTour.dispose();
			tabMorpion.dispose();
		} else {
			BCase bcase = (BCase) e.getSource();
			if (bcase.obtenirPion() == Pion.LIBRE) {
				bcase.poserPion((tour)%2,model);
				tour++;
				if (model.victoire() == true || tour == 9) {
					fenetreFinTour();
				}

			}
		}
	}

}