package fr.iutvalence.tp1a.binome4.morpion;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Fenetre extends JFrame implements Runnable, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6526373500966340956L;
	private JTextField jtf1;
	private JTextField jtf2;
	private JLabel lab1;
	private JLabel lab2;

	private JButton valide;

	private JFrame demande;
	private Window tabMorpion;
	private int tour;
	private PartieIHM model;

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
			final Joueur joueur1 = new Joueur(jtf1.getText(), Pion.JOUEUR1);
			final Joueur joueur2 = new Joueur(jtf2.getText(), Pion.JOUEUR2);
			final Score gestionnaireScore = new Score(joueur1, joueur2);
			model = new PartieIHM(joueur1, joueur2, gestionnaireScore);
			demande.setVisible(false);
			tabMorpion.setVisible(true);
		} else {
			BCase bcase = (BCase) e.getSource();
			if (bcase.obtenirPion() == null) {
				bcase.poserPion(tour);
				tour++;
				
			}
		}
	}

}