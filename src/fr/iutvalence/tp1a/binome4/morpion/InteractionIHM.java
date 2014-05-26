package fr.iutvalence.tp1a.binome4.morpion;

public interface InteractionIHM {
    void placerPion(int ligne, int colonne, Pion joueur);
    void victoire(Pion joueur);
    void nul();
}
