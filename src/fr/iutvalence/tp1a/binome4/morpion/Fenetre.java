package fr.iutvalence.tp1a.binome4.morpion;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Fenetre implements Runnable {
    private PartieIHM partieIHM;
    private JTextField jtf1;
    private JTextField jtf2;

    private JButton buton;

    private JFrame demande;
    private JFrame tabMorpion;

    @Override
    public void run() {
	demanderJoueurs();
	jeu();
    }

    private void demanderJoueurs() {
	demande = new JFrame("Morpion");
	demande.setSize(600, 600);
	// demande.setDefaultCloseOperation(JFrame.D);
	demande.setLocationRelativeTo(null);
	JPanel container = new JPanel();
	container.setBackground(Color.white);
	container.setLayout(new BorderLayout());
	JPanel top = new JPanel();
	jtf1 = new JTextField("Joueur1");
	jtf1.setPreferredSize(new Dimension(100, 25));
	jtf1.setForeground(Color.BLUE);
	JLabel label1 = new JLabel("Pseudo joueur 1");
	top.add(label1);
	top.add(jtf1);
	jtf2 = new JTextField("Joueur2");
	jtf2.setPreferredSize(new Dimension(100, 25));
	jtf2.setForeground(Color.BLUE);
	JButton valide = new JButton("Valider");
	valide.addActionListener(new BoutonListener());
	top.add(new JLabel("Pseudo joueur 2"));
	top.add(jtf2);
	top.add(valide);
	container.add(top, BorderLayout.NORTH);
	demande.setContentPane(container);
	demande.setVisible(true);
	demande.pack();
    }

    private void jeu() {
	tabMorpion = new JFrame("Morpion");
	tabMorpion.setSize(600, 600);
	// demande.setDefaultCloseOperation(JFrame.D);
	tabMorpion.setLocationRelativeTo(null);
	JPanel container = new JPanel();
	container.setBackground(Color.white);
	JPanel top = new JPanel();

	tabMorpion.setLayout(new GridLayout(3, 3));

	for (int numeroDeLigne = 1; numeroDeLigne < 4; numeroDeLigne++) {
	    for (int numeroDeColonne = 1; numeroDeColonne < 4; numeroDeColonne++) {
		JButton bouton = new Bouton(numeroDeLigne, numeroDeColonne);
		tabMorpion.add(bouton);
	    }
	}

	tabMorpion.setVisible(false);

    }

    private class Bouton extends JButton {
	private int ligne;
	private int colonne;
	private Pion pion;

	Bouton(int numLigne, int numColonne) {
	    ligne = numLigne;
	    colonne = numColonne;
	    addActionListener(new BListener());
	}

	class BListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
		if (partieIHM.estLibre(ligne, colonne)){
		    partieIHM.poserPion(ligne,colonne,m_joueurs[m_tour].pion());
		    
		}
	    }
	}
    }

    class BoutonListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
	    final Joueur joueur1 = new Joueur(jtf1.getText(), Pion.JOUEUR1);
	    final Joueur joueur2 = new Joueur(jtf2.getText(), Pion.JOUEUR2);
	    final Score gestionnaireScore = new Score(joueur1, joueur2);
	    partieIHM = new PartieIHM(joueur1, joueur2, gestionnaireScore);
	    demande.setVisible(false);
	    tabMorpion.setVisible(true);

	}
    }
}
