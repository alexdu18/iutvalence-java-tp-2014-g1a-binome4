package fr.iutvalence.tp1a.binome4.morpion;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Fenetre implements Runnable, InteractionIHM {
    private PartieIHM model;
    private JTextField jtf1;
    private JTextField jtf2;
    private JLabel lab1;
    private JLabel lab2;

    private JButton valide;
    private JButton buton;

    private JFrame demande;
    private JFrame tabMorpion;

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
	valide.addActionListener(new BValide());
	container.add(valide);

	/** Traitement divers */
	demande.setContentPane(container);
	demande.setVisible(true);
    }

    private void jeu() {
	/** Fenetre globale */
	tabMorpion = new JFrame("Morpion");
	tabMorpion.setSize(500, 500);
	tabMorpion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	tabMorpion.setLocationRelativeTo(null);

	/** Conteneur global */
	JPanel container = new JPanel();
	container.setBackground(Color.white);

	/** Conteneur haut */
	JPanel top = new JPanel();

	/** Grille de jeu */
	tabMorpion.setLayout(new GridLayout(3, 3));

	for (int numeroDeLigne = 1; numeroDeLigne < 4; numeroDeLigne++) {
	    for (int numeroDeColonne = 1; numeroDeColonne < 4; numeroDeColonne++) {
		JButton bouton = new BCase(numeroDeLigne,numeroDeColonne, model,this);
		//bouton.addActionListener(new BCase(numeroDeLigne,numeroDeColonne,joueur.pion()));
		tabMorpion.add(bouton);
	    }
	}

	/** Traitement divers */
	tabMorpion.setVisible(false);
    }

    public class BCase extends JButton implements ActionListener {
	private int ligne;
	private int colonne;
	private InteractionIHM ihm;
	private PartieIHM model;
	
	public BCase(int numL, int numC, PartieIHM model, InteractionIHM ihm){
	    this.ligne = numL;
	    this.colonne = numC;
	    this.ihm = ihm;
	}
	
	public void actionPerformed(ActionEvent e) {
	    model.poserPion(ligne, colonne);
	    ihm.placerPion(ligne, colonne, model.joueurCourant());
	    // XXX Victoire et nul
	}
    }

    class BValide implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    final Joueur joueur1 = new Joueur(jtf1.getText(), Pion.JOUEUR1);
	    final Joueur joueur2 = new Joueur(jtf2.getText(), Pion.JOUEUR2);
	    final Score gestionnaireScore = new Score(joueur1, joueur2);
	    model = new PartieIHM(joueur1, joueur2, gestionnaireScore);
	    demande.setVisible(false);
	    tabMorpion.setVisible(true);
	}
    }

}