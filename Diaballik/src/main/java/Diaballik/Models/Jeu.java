package Diaballik.Models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Diaballik.Controllers.TerrainUtils;
import Diaballik.IA.Coup_Gagnant;
import Diaballik.IA.Random_IA;
import Diaballik.Models.ConfigJeu.Mode;
import Diaballik.Patterns.Observable;

public class Jeu extends Observable {
    public Terrain tr;
    public Joueur joueur1;
    public Joueur joueur2;
    public Joueur joueurCourant;
    public boolean gameOver = false;
    Piece from = null;
    Piece to = null;
    private ArrayList<Position> listeMarque = new ArrayList<Position>();
    private ArrayList<Position> listePositionsPossibles = new ArrayList<Position>();
    private boolean IA;
    Random_IA I;
    ConfigJeu config;

    public Jeu() {
        tr = new Terrain();
        tr.Create();

    }

    public void configurer(ConfigJeu c) {
        config = c;
    }

    public void start() {
        // Joueur 1 (Là le joueur 1 joue forcement les blancs, mais on pourra modifier
        // dans les
        // parametres ci-dessous)

        if (config.getMode() == Mode.ordinateur) {
            IA = true;
            joueur1 = new Joueur(TypeJoueur.Joueur1, PieceType.White, 2, 1, config.getName1());
            joueur2 = new Joueur(TypeJoueur.IA, PieceType.Black, 2, 1, config.getName3());
            // TODO : case config.getIALevel()
            I = new Random_IA(joueur2.couleur, tr);
        } else {
            IA = false;
            joueur1 = new Joueur(TypeJoueur.Joueur1, PieceType.White, 2, 1, config.getName1());
            joueur2 = new Joueur(TypeJoueur.Joueur2, PieceType.Black, 2, 1, config.getName2());
        }

        if (config.getP1First() == true)
            joueurCourant = joueur1;
        else
            joueurCourant = joueur2;

        tr._jeuParent = this;
        metAJour();
        // IA joue en premier
        if (IA && !config.getP1First()) {
            I.IA();
            FinTour();
        }

    }

    public Jeu(Terrain terrain) {
        tr = terrain;

    }

    public void FinTour() {
        if (gameOver)
            return;
        joueurCourant.nbMove = 2;
        joueurCourant.passeDispo = 1;
        if (joueurCourant == joueur1) {
            joueurCourant = joueur2;
            if (IA) {
                I.IA();
                FinTour();
            }
        } else {
            joueurCourant = joueur1;
        }
        metAJour();
        RetirerMarque();
    }

    private boolean PieceAuJoueur(Piece select) {
        if (select.Type == PieceType.White && joueurCourant == joueur1)
            return true;
        else if (select.Type == PieceType.Black && joueurCourant == joueur2 && !IA)
            return true;
        else
            return false;
    }

    public void SelectionPiece(int l, int c) {
        if ((0 > l || l > 6 || 0 > c || c > 6) || gameOver)
            return;
        Piece select = tr._terrain[l][c];
        // première sélection d'une piece qui nous appartient si aucune sélection
        // précedemment
        if (from == null && PieceAuJoueur(select)) {
            from = select;
            if ((!select.HasBall))
                SelectionDeplacement(l, c);
            else {
                SelectionPasse(from);
            }
        } else if (from != null) {
            to = select;
            if ((from.HasBall) && PieceAuJoueur(select)) {
                if (from != to)
                    Passe();
                else {
                    RetirerMarque();
                    from = to = null;
                }
            } else {
                Deplacement();
            }
        }
        // mauvaise selection, reinitialisation de from et to
        else {
            RetirerMarque();
            from = to = null;
        }
        if (joueurCourant.nbMove == 0 && joueurCourant.passeDispo == 0)
            FinTour();
        metAJour();

    }

    public void SelectionPieceIA(int l, int c) {
        Piece select = tr._terrain[l][c];
        if (from == null) {
            from = select;
            if ((!select.HasBall))
                SelectionDeplacement(l, c);
            else {
                SelectionPasse(from);
            }
        } else if (from != null) {
            to = select;
            if ((from.HasBall)) {
                if (from != to)
                    Passe();
                else {
                    RetirerMarque();
                    from = to = null;
                }
            } else {
                Deplacement();
            }
        }
        // mauvaise selection, reinitialisation de from et to
        else {
            RetirerMarque();
            from = to = null;
        }
        if (joueurCourant.nbMove == 0 && joueurCourant.passeDispo == 0)
            FinTour();
        metAJour();

    }

    public void SelectionDeplacement(int l, int c) {
        tr._terrain[l][c].SelectionDeplacement = true;
        // getPiecePos(select).SelectionDeplacement = true;
        // Piece select = tr._terrain[l][c];
        // System.out.printf("Selection déplacement : Piece position (%d,%d)\n",
        // select.Position.l, select.Position.c);
        listeMarque.add(new Position(l, c));
        listePositionsPossibles = from.PossiblePositions(joueurCourant.nbMove);
        for (Position pos : listePositionsPossibles) {
            int li = pos.l;
            int co = pos.c;
            tr._terrain[li][co].PossibleDeplacement = true;
            listeMarque.add(pos);
        }

    }

    public void SelectionPasse(Piece select) {
        getPiecePos(select).SelectionPasse = true;
        listeMarque.add(select.Position);
        if (joueurCourant.passeDispo == 1) {
            ArrayList<Position> ar = from.passesPossibles();
            for (Position pos : ar) {
                int l = pos.l;
                int c = pos.c;
                tr._terrain[l][c].PossiblePasse = true;
                listeMarque.add(pos);
            }
        }
    }

    public void Passe() {
        if (joueurCourant.passeDispo == 1) {
            if (TerrainUtils.passeWrapper2(from, to) == true) {
                joueurCourant.passeDispo = 0;
                gameOver = victoire() != PieceType.Empty;
            }
        }
        RetirerMarque();
        from = to = null;
    }

    public void Deplacement() {
        if (listePositionsPossibles.contains(to.Position)) {
            ArrayList<Position> diag = from.getDiagonals();
            int temp = joueurCourant.nbMove;
            if (diag.contains(to.Position)) {
                // Si c'est un mouvement en diagonale, on prend deux coups
                joueurCourant.nbMove -= 2;
            } else {
                // Sinon 1 seul ou 2 selon si on a avancé d'une ou de deux cases
                joueurCourant.nbMove -= Math.abs((from.Position.l + from.Position.c) - (to.Position.l + to.Position.c));
            }
            if (joueurCourant.nbMove >= 0) {
                from.move(to.Position.l, to.Position.c);
                gameOver = antijeu2();
            } else {
                joueurCourant.nbMove = temp;
            }
        }
        listePositionsPossibles.clear();
        RetirerMarque();
        from = to = null;
    }

    public void RetirerMarque() {

        Iterator<Position> itr = listeMarque.iterator();
        while (itr.hasNext()) {
            Position pos = (Position) itr.next();
            int l = pos.l;
            int c = pos.c;
            tr._terrain[l][c].PossiblePasse = false;
            tr._terrain[l][c].PossibleDeplacement = false;
            tr._terrain[l][c].SelectionPasse = false;
            tr._terrain[l][c].SelectionDeplacement = false;
            itr.remove();
        }
    }

    private Piece getPiecePos(Piece piece) {
        return tr._terrain[piece.Position.l][piece.Position.c];
    }

    // retourne vrai si il y a antijeu
    public boolean antijeu() {
        boolean antijeu = true;
        for (int i = 1; i < tr.taille() - 1; i++) {
            antijeu = true;
            for (int j = 0; j < tr.taille(); j++) {
                antijeu = antijeu && (tr.getTerrain()[i][j].Type == PieceType.White);
            }
        }
        if (antijeu) {
            System.out.println("Les blancs ont fait un antijeu");
            return true;
        }
        antijeu = true;
        for (int i = 1; i < tr.taille() - 1; i++) {
            antijeu = true;
            for (int j = 0; j < tr.taille(); j++) {
                antijeu = antijeu && (tr.getTerrain()[i][j].Type == PieceType.Black);
            }
        }
        if (antijeu) {
            System.out.println("Les noirs ont fait un antijeu");
            return true;
        }
        return false;
    }

    public boolean antijeu2() {
        int cpt = 0;
        int nbAdverseColonne = 0;
        PieceType actuel = joueurCourant.couleur;
        PieceType adverse = (actuel == PieceType.Black) ? PieceType.White : PieceType.Black;
        int l = -1, c = -1;

        if (adverse == PieceType.Black) {
            for (int colonne = 0; colonne < tr.taille(); colonne++) {
                nbAdverseColonne = 0;
                // verifie s'il y a un pion adverse dans la premiere colonne
                if (colonne == 0) {
                    for (int ligne = 0; ligne < tr.taille(); ligne++) {
                        if (tr.getTerrain()[ligne][colonne].Type == adverse) {
                            nbAdverseColonne++;
                            l = ligne;
                            c = colonne;
                            if ((l + 1 < tr.taille()) && tr.getTerrain()[l + 1][c].Type == actuel)
                                cpt++;
                        }
                        if (nbAdverseColonne > 1)
                            return false;

                    }
                    if (nbAdverseColonne == 0)
                        return false;

                } else {
                    // verifie dans les cases adjacente à droite la presence de pions adverses
                    int ligne = l;
                    if (ligne - 1 >= 0 && tr.getTerrain()[ligne - 1][colonne].Type == adverse) {
                        nbAdverseColonne++;
                        l = ligne - 1;
                        c = colonne;
                        if (l + 1 < tr.taille() && tr.getTerrain()[l + 1][c].Type == actuel)
                            cpt++;
                    } else if (tr.getTerrain()[ligne][colonne].Type == adverse) {
                        nbAdverseColonne++;
                        l = ligne;
                        c = colonne;
                        if (l + 1 < tr.taille() && tr.getTerrain()[l + 1][c].Type == actuel)
                            cpt++;
                    } else if (ligne + 1 < 7 && tr.getTerrain()[ligne + 1][colonne].Type == adverse) {
                        nbAdverseColonne++;
                        l = ligne + 1;
                        c = colonne;
                        if (l + 1 < tr.taille() && tr.getTerrain()[l + 1][c].Type == actuel)
                            cpt++;
                    }
                    if (nbAdverseColonne != 1)
                        return false;

                }

            }
            if (cpt >= 3) {
                System.out.println("Les noirs ont fait un antijeu");
                return true;
            } else
                return false;

        }
        // adverse == PieceType.White
        else {
            for (int colonne = 0; colonne < tr.taille(); colonne++) {
                nbAdverseColonne = 0;
                // verifie s'il y a un pion adverse dans la premiere colonne
                if (colonne == 0) {
                    for (int ligne = 0; ligne < tr.taille(); ligne++) {
                        if (tr.getTerrain()[ligne][colonne].Type == adverse) {
                            nbAdverseColonne++;
                            l = ligne;
                            c = colonne;
                            if ((l - 1 >= 0) && tr.getTerrain()[l - 1][c].Type == actuel) {
                                cpt++;
                            }
                        }
                        if (nbAdverseColonne > 1)
                            return false;

                    }
                    if (nbAdverseColonne == 0)
                        return false;

                } else {
                    // verifie dans les cases adjacente à droite la presence de pions adverses
                    int ligne = l;
                    if (ligne - 1 >= 0 && tr.getTerrain()[ligne - 1][colonne].Type == adverse) {
                        nbAdverseColonne++;
                        l = ligne - 1;
                        c = colonne;
                        if ((l - 1 >= 0) && tr.getTerrain()[l - 1][c].Type == actuel) {
                            cpt++;
                        }
                    } else if (tr.getTerrain()[ligne][colonne].Type == adverse) {
                        nbAdverseColonne++;
                        l = ligne;
                        c = colonne;
                        if ((l - 1 >= 0) && tr.getTerrain()[l - 1][c].Type == actuel) {
                            cpt++;
                        }
                    } else if (ligne + 1 < 7 && tr.getTerrain()[ligne + 1][colonne].Type == adverse) {
                        nbAdverseColonne++;
                        l = ligne + 1;
                        c = colonne;
                        if ((l - 1 >= 0) && tr.getTerrain()[l - 1][c].Type == actuel) {
                            cpt++;
                        }
                    }
                    if (nbAdverseColonne != 1)
                        return false;
                }
            }
            if (cpt >= 3) {
                System.out.println("Les blancs ont fait un antijeu");
                return true;
            } else {
                return false;
            }
        }
    }

    // retourne le type de piece qui a gagnée
    public PieceType victoire() {
        for (int i = 0; i < tr.taille(); i++) {
            if ((tr.getTerrain()[0][i].Type == PieceType.White) && (tr.getTerrain()[0][i].HasBall)) {
                System.out.println("Les blancs ont gagné !");
                return PieceType.White;
            }
            if ((tr.getTerrain()[tr.taille() - 1][i].Type == PieceType.Black)
                    && (tr.getTerrain()[tr.taille() - 1][i].HasBall)) {
                System.out.println("Les noirs ont gagné !");
                return PieceType.Black;
            }
        }
        return PieceType.Empty;
    }

    // mode textuelle

    public Piece getPiece(PieceType t) {
        Scanner sc = new Scanner(System.in);
        String ligne;
        String[] ligne_split;
        int pieceL;
        int pieceC;
        Piece res = tr.getTerrain()[0][0];
        boolean done = false;
        while (!done) {
            System.out.println("Entrez les coordonnées de la pièce " + t + " : l c ");
            ligne = sc.nextLine();
            ligne_split = ligne.split(" ");
            pieceL = Integer.parseInt(ligne_split[0]);
            pieceC = Integer.parseInt(ligne_split[1]);
            if ((pieceL > tr.taille()) || (pieceC > tr.taille())) {
                sc.close();
                throw new IllegalAccessError("Erreur dans getPiece : l ou c > taille");
            }
            res = tr.getTerrain()[pieceL][pieceC];
            if ((res.Type == t) && (!res.HasBall)) {
                done = true;
            } else {
                if (res.HasBall) {
                    System.out.println("Erreur : Vous ne pouvez pas bouger la piece qui a la balle");
                }
                if (res.Type != t) {
                    System.out.println("Erreur : Veuillez choisir une piece de type " + t);
                }
            }
        }

        // Je sais pas pourquoi, j'ai pas envie de savoir pourquoi, je ne veux même plus
        // chercher à savoir pourquoi,
        // mais pour une raison que j'ignore, si on ferme le scanner ici alors plus rien
        // ne marche
        // Du coup, juste laissez-le ouvert et osef du warning
        // sc.close();
        return res;
    }

    // fin de tour
    public void endTurn() {
        joueurCourant.nbMove = 2;
        joueurCourant.passeDispo = 1;
        if (joueurCourant.n == TypeJoueur.Joueur1) {
            joueurCourant = joueur2;
        } else {
            joueurCourant = joueur1; // a modifier pour joueur contre l'ia
        }
        move();
    }

    // Ici encore on utilise l'entrée standard. A modifier plus tard pour l'ihm et
    // l'ia
    public void move() {
        if (joueurCourant.n == TypeJoueur.IA) {
            I.IA();
        } else {
            // victoire
            if (gameOver) {
                System.exit(0); // la j'ai mis system.exit mais on changera si necessaire
            }
            Scanner sc = new Scanner(System.in);
            char choix;
            Piece from;
            Piece to;
            // tant qu'il y a un truc à faire
            while (!gameOver) {
                if ((joueurCourant.nbMove == 0 && joueurCourant.passeDispo == 0)) {
                    // end turn
                    endTurn();
                }
                tr.PrintTerrain();
                System.out.println("tour des " + joueurCourant.couleur);
                System.out.println("Nombre de mouvements restants : " + joueurCourant.nbMove);
                System.out.println("Nombre de passes restantes : " + joueurCourant.passeDispo);
                System.out.println(
                        "entrez 'p' pour faire une passe, 'm' pour faire un mouvement, ou 'q' pour passer votre tour");
                choix = sc.nextLine().charAt(0);
                switch (choix) {
                    case 'p':
                        // passe
                        if (joueurCourant.passeDispo == 1) {
                            from = tr.getPieceWithBall(joueurCourant.couleur);
                            System.out.println("Les passes possibles sont : " + from.passesPossibles());
                            to = getPiece(joueurCourant.couleur);
                            TerrainUtils.passeWrapper(from, to);
                            joueurCourant.passeDispo = 0;
                            gameOver = victoire() != PieceType.Empty;
                        } else {
                            System.out.println("Vous ne pourrez faire qu'une seule passe");
                        }
                        break;
                    case 'm':
                        // mouvement
                        from = getPiece(joueurCourant.couleur);
                        ArrayList<Position> ar = from.PossiblePositions(joueurCourant.nbMove);
                        System.out.println("Positions possibles : " + ar);
                        to = getPiece(PieceType.Empty);
                        // On verifie si c est bien un mouvement legal

                        if (ar.contains(to.Position)) {
                            ArrayList<Position> diag = from.getDiagonals();
                            int temp = joueurCourant.nbMove;
                            if (diag.contains(to.Position)) {
                                // Si c'est un mouvement en diagonale, on prend deux coups
                                joueurCourant.nbMove -= 2;
                            } else {
                                // Sinon 1 seul ou 2 selon si on a avancé d'une ou de deux cases
                                joueurCourant.nbMove -= Math
                                        .abs((from.Position.l + from.Position.c) - (to.Position.l + to.Position.c));
                            }
                            if (joueurCourant.nbMove >= 0) {
                                from.move(to.Position.l, to.Position.c);
                            } else {
                                System.out.println("Vous n'avez plus de mouvements");
                                joueurCourant.nbMove = temp;
                            }
                        } else {
                            throw new IllegalAccessError("Mouvement illegal");
                        }
                        gameOver = antijeu();
                        break;
                    case 'q':
                        // end turn
                        endTurn();
                        break;
                    default:
                        System.out.println("Choix invalide");
                        break;
                }
            }
        }
    }

    private void test_Random_IA_P(Terrain tr) {
        Random_IA A = new Random_IA(PieceType.Black, tr);
        PieceType tour = PieceType.White;
        Boolean victoire = false;
        int nbMove = 2; // on a droit à deux mouvements max
        int passe_faite = 1; // une passe
        Scanner sc = new Scanner(System.in);
        char choix;
        Piece from;
        Piece to;
        while (!victoire) {
            System.out.println(" Tour = " + tour);
            if (tour == PieceType.White) {
                // tant qu'il y a un truc à faire
                while (nbMove > 0 || passe_faite > 0) {
                    tr.PrintTerrain();
                    System.out.println("tour des " + tour);
                    System.out.println("Nombre de mouvements restants : " + nbMove);
                    System.out.println("Nombre de passes restantes : " + passe_faite);
                    System.out.println(
                            "entrez 'p' pour faire une passe, 'm' pour faire un mouvement, ou 'q' pour passer votre tour");

                    String s = sc.nextLine();

                    if (s.length() == 0) {
                        System.out.println("Choix invalide");
                        continue;
                    }
                    choix = s.charAt(0);
                    switch (choix) {
                        case 'p':
                            // passe
                            if (passe_faite == 1) {
                                from = tr.getPieceWithBall(tour);
                                System.out.println("Les passes possibles sont : " + from.passesPossibles());
                                to = getPiece(tour);
                                TerrainUtils.passeWrapper(from, to);
                                passe_faite = 0;
                                // victoire?
                                if (to.Position.l == 0) {
                                    System.out.println("Les blanc ont gagnés !");
                                    victoire = true;
                                    // System.exit(0); // La j'ai fais un system.exit mais on changera plus tard si
                                    // il le faut
                                }
                            } else {
                                System.out.println("Vous ne pourrez faire qu'une seule passe");
                            }
                            break;
                        case 'm':
                            // mouvement
                            System.out.println("nb moves : " + nbMove);
                            if (nbMove == 0) {
                                System.out.println("Vous n'avez plus de mouvements");
                                nbMove = 0;
                                continue;
                            }
                            from = getPiece(tour);
                            ArrayList<Position> ar = from.PossiblePositions(nbMove);
                            System.out.println("Positions possibles : " + ar);
                            to = getPiece(PieceType.Empty);
                            // On verifie si c est bien un mouvement legal
                            if (ar.contains(to.Position)) {
                                ArrayList<Position> diag = from.getDiagonals();
                                if (diag.contains(to.Position)) {
                                    // Si c'est un mouvement en diagonale, on prend deux coups
                                    nbMove -= 2;
                                } else {
                                    // Sinon 1 seul ou deux selon si on a avancé d'une ou deux cases
                                    nbMove -= Math
                                            .abs((from.Position.l + from.Position.c) - (to.Position.l + to.Position.c));
                                }
                                if (nbMove >= 0) {
                                    from.move(to.Position.l, to.Position.c);
                                } else {
                                    System.out.println("Vous n'avez plus de mouvements");
                                    nbMove = 0;
                                }
                            } else {
                                throw new IllegalAccessError("Mouvement illegal");
                            }
                            break;

                        case 'q':
                            // end turn
                            nbMove = 0;
                            passe_faite = 0;
                            break;
                        default:
                            System.out.println("Choix invalide");
                            break;
                    }
                }
                tour = PieceType.Black;
            } else {
                A.IA();
                victoire = A.Victoire_IA;
                tour = PieceType.White;
                nbMove = 2;
                passe_faite = 1;
            }
        }
        sc.close();
    }

    private void test_Random_IA_IA(Terrain tr) {
        Random_IA A = new Random_IA(PieceType.Black, tr);
        Random_IA B = new Random_IA(PieceType.White, tr);
        PieceType tour = PieceType.White;
        Boolean victoire = false;
        while (!victoire) {
            tr.PrintTerrain();
            System.out.println(" Tour = " + tour);
            if (tour == PieceType.White) {
                B.IA();
                victoire = B.Victoire_IA;
                tour = PieceType.Black;
            } else {
                A.IA();
                victoire = A.Victoire_IA;
                tour = PieceType.White;
            }
        }
        if (A.Victoire_IA) {
            System.out.println("Victoire IA A");
        } else {
            System.out.println("Victoire IA B");
        }
    }

    /**
     * @author Thomas
     * @param p Pièce que possède la balle
     * @return null Si aucune position trouvé
     * @return res Liste des positions possible
     */
    public ArrayList<Position> winningMove(Piece p) {
        ArrayList<Position> list = p.passesPossibles();
        ArrayList<Position> res = new ArrayList<Position>();
        if (list.size() == 0) {
            return null;
        } else {
            if (p.Type == PieceType.Black) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).l == 6) {
                        res.add(list.get(i));
                    }
                }
            } else if (p.Type == PieceType.White) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).l == 0) {
                        res.add(list.get(i));
                    }
                }
            }
            if (res.size() == 0) {
                return null;
            }
            return res;
        }
    }

    private void test_Coup_Gagnant_IA_P(Terrain tr) {
        Coup_Gagnant A = new Coup_Gagnant(PieceType.Black, tr);
        PieceType tour = PieceType.White;
        Boolean victoire = false;
        int nbMove = 2; // on a droit à deux mouvements max
        int passe_faite = 1; // une passe
        Scanner sc = new Scanner(System.in);
        char choix;
        Piece from;
        Piece to;
        while (!victoire) {
            System.out.println(" Tour = " + tour);
            if (tour == PieceType.White) {
                // tant qu'il y a un truc à faire
                while (nbMove > 0 || passe_faite > 0) {
                    tr.PrintTerrain();
                    System.out.println("tour des " + tour);
                    System.out.println("Nombre de mouvements restants : " + nbMove);
                    System.out.println("Nombre de passes restantes : " + passe_faite);
                    System.out.println(
                            "entrez 'p' pour faire une passe, 'm' pour faire un mouvement, ou 'q' pour passer votre tour");

                    String s = sc.nextLine();

                    if (s.length() == 0) {
                        System.out.println("Choix invalide");
                        continue;
                    }
                    choix = s.charAt(0);
                    switch (choix) {
                        case 'p':
                            // passe
                            if (passe_faite == 1) {
                                from = tr.getPieceWithBall(tour);
                                System.out.println("Les passes possibles sont : " + from.passesPossibles());
                                to = getPiece(tour);
                                TerrainUtils.passeWrapper(from, to);
                                passe_faite = 0;
                                // victoire?
                                if (to.Position.l == 0) {
                                    System.out.println("Les noirs ont blanc !");
                                    victoire = true;
                                    // System.exit(0); // La j'ai fais un system.exit mais on changera plus tard si
                                    // il le faut
                                }
                            } else {
                                System.out.println("Vous ne pourrez faire qu'une seule passe");
                            }
                            break;
                        case 'm':
                            // mouvement
                            System.out.println("nb moves : " + nbMove);
                            if (nbMove == 0) {
                                System.out.println("Vous n'avez plus de mouvements");
                                nbMove = 0;
                                continue;
                            }
                            from = getPiece(tour);
                            ArrayList<Position> ar = from.PossiblePositions(nbMove);
                            System.out.println("Positions possibles : " + ar);
                            to = getPiece(PieceType.Empty);
                            // On verifie si c est bien un mouvement legal
                            if (ar.contains(to.Position)) {
                                ArrayList<Position> diag = from.getDiagonals();
                                if (diag.contains(to.Position)) {
                                    // Si c'est un mouvement en diagonale, on prend deux coups
                                    nbMove -= 2;
                                } else {
                                    // Sinon 1 seul ou deux selon si on a avancé d'une ou deux cases
                                    nbMove -= Math
                                            .abs((from.Position.l + from.Position.c) - (to.Position.l + to.Position.c));
                                }
                                if (nbMove >= 0) {
                                    from.move(to.Position.l, to.Position.c);
                                } else {
                                    System.out.println("Vous n'avez plus de mouvements");
                                    nbMove = 0;
                                }
                            } else {
                                throw new IllegalAccessError("Mouvement illegal");
                            }
                            break;

                        case 'q':
                            // end turn
                            nbMove = 0;
                            passe_faite = 0;
                            break;
                        default:
                            System.out.println("Choix invalide");
                            break;
                    }
                }
                tour = PieceType.Black;
            } else {
                A.IA();
                victoire = A.Victoire_IA;
                tour = PieceType.White;
                nbMove = 2;
                passe_faite = 1;
            }
        }
        sc.close();
    }

    public static void main(String[] args) {
        Jeu j = new Jeu();
        // j.test_Random_IA_IA(j.tr);
        // j.test_Random_IA_P(j.tr);
        j.test_Coup_Gagnant_IA_P(j.tr);
        // j.move();
    }

}
