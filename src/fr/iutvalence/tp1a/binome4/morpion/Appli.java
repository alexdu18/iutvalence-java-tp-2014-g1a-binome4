package fr.iutvalence.tp1a.binome4.morpion;

import java.util.Scanner;

import javax.swing.SwingUtilities;

/**
 * Application.
 * 
 * @author Prinsac & Culty
 * @version 1.1
 */
@SuppressWarnings("HardCodedStringLiteral")
public final class Appli {
	/** Application générale. */
	public static void main(final String... args) {
		final Scanner reader = new Scanner(System.in, "UTF-8");
		boolean choixInterface = false;
		String consoleOuGraph = "";
		while (!choixInterface) {
			System.out.println("Pour jouer en console tapez 'c', pour jouer en graphique tapez 'g'.");
			consoleOuGraph = reader.nextLine();
			if((consoleOuGraph.equalsIgnoreCase("c")) || (consoleOuGraph.equalsIgnoreCase("g"))){
				choixInterface = true;
			}
		}
		if (consoleOuGraph.equalsIgnoreCase("c")) {
			System.out.println("Entrez le pseudo du joueur 1 (X) :");
			final String pseudoJ1S = reader.nextLine();
			System.out.println("Entrez le pseudo du joueur 2 (O) :");
			final String pseudoJ2S = reader.nextLine();
			final Joueur joueur1 = new Joueur(pseudoJ1S, Pion.JOUEUR1);
			final Joueur joueur2 = new Joueur(pseudoJ2S, Pion.JOUEUR2);
			final Score gestionnaireScore = new Score(joueur1, joueur2);
			boolean rejouer = true;
			while (rejouer) {
				final Partie partie = new Partie(joueur1, joueur2,
						gestionnaireScore);
				partie.jouer();
				System.out.println("Voulez-vous rejouer ?");
				System.out.println("1 pour rejouer, 0 pour arrêter.");
				rejouer = reader.nextInt() == 1;
			}
		} else if (consoleOuGraph.equalsIgnoreCase("g")) {
			SwingUtilities.invokeLater(new Fenetre());
		}
	}

}