package Diaballik.Models;

import java.util.ArrayList;

import javax.swing.*;

import Diaballik.Diaballik;
import Diaballik.Controllers.TerrainUtils;

public class Piece extends JButton implements IPiece {
    public PieceType Type = PieceType.Empty;
    public Position Position;
    public boolean HasBall = false;
    public Terrain Parent;

    public Piece(PieceType type, boolean hasBall, int l, int c, Terrain parent) {
        this.Type = type;
        this.Position = new Position(l, c);
        this.HasBall = hasBall;
        this.Parent = parent;
    }

    @Override
    public void addBall() {
        if (this.HasBall) {
            throw new IllegalStateException("Le joueur ne peut avoir qu'une seule balle.");
        }
        this.HasBall = true;
    }

    @Override
    public void removeBall() {
        if (!this.HasBall) {
            throw new IllegalStateException("le joueur n'a pas déjà de balle.");
        }
        this.HasBall = false;
    }

    @Override
    public void move(int l, int c) {
        this.Position = new Position(l,c);
        TerrainUtils.Swap(this, Parent.getTerrain()[l][c]);
    }

    @Override
    public Piece Clone() {
        return new Piece(this.Type, this.HasBall, this.Position.l, this.Position.c, this.Parent);
    }

    public String toString() {
        return "[" + this.Type + "," + this.HasBall + "," + this.Position + "]";
    }

    //@Override
    public ArrayList<Position> PossiblePositions() {
        int j = this.Position.l;
        int i = this.Position.c;
        int index = i;
        int compteur = 0;
        ArrayList<Position> result = new ArrayList<Position>();
        /*
        while((index<Parent.taille()) && (compteur !=2) && (!Parent.isOccupied(new Position(this.Position.l,this.Position.c + compteur +1)))){
            result.add(new Position(this.Position.l,this.Position.c + compteur +1));
            index++;
            compteur++;
        }

        index = i;
        compteur = 0;

        while((index>0) && (compteur !=2) && (!Parent.isOccupied(new Position(this.Position.l,this.Position.c + compteur -2)))){
            result.add(new Position(this.Position.l,this.Position.c + compteur -2));
            index--;
            compteur++;
        }

        index = j;
        compteur = 0;

        while((index<Parent.taille()) && (compteur !=2) && (!Parent.isOccupied(new Position(this.Position.l+compteur+1,this.Position.c)))){
            result.add(new Position(this.Position.l+compteur+1,this.Position.c));
            index++;
            compteur++;
        }

        index = j;
        compteur = 0;

        while((index>0) && (compteur !=2) && (!Parent.isOccupied(new Position(this.Position.l+compteur-2,this.Position.c)))){
            result.add(new Position(this.Position.l + compteur -2,this.Position.c));
            index++;
            compteur++;
        }
        */
        if(!this.Parent.isOccupied(new Position(this.Position.l-1, this.Position.c)) && ((this.Position.l-1)>=0)){
            result.add(new Position(this.Position.l-1, this.Position.c));
            System.out.println("here : "+this.Parent.isOccupied(new Position(this.Position.l-1, this.Position.c)));
            if(!this.Parent.isOccupied(new Position(this.Position.l-2, this.Position.c)) && ((this.Position.l-2)>=0)){
                result.add(new Position(this.Position.l-2, this.Position.c));
            }
        }
        if(!this.Parent.isOccupied(new Position(this.Position.l+1, this.Position.c)) && ((this.Position.l+1)<this.Parent.taille())){
            result.add(new Position(this.Position.l+1, this.Position.c));
            if(!this.Parent.isOccupied(new Position(this.Position.l+2, this.Position.c)) && ((this.Position.l+2)<this.Parent.taille())){
                result.add(new Position(this.Position.l+2, this.Position.c));
            }
        }
        if(!this.Parent.isOccupied(new Position(this.Position.l, this.Position.c+1)) && ((this.Position.c+1)<this.Parent.taille())){
            result.add(new Position(this.Position.l, this.Position.c+1));
            if(!this.Parent.isOccupied(new Position(this.Position.l, this.Position.c+2)) && ((this.Position.c+2)<this.Parent.taille())){
                result.add(new Position(this.Position.l, this.Position.c+2));
            }
        }
        if(!this.Parent.isOccupied(new Position(this.Position.l, this.Position.c-1)) && ((this.Position.c-1)>=0)){
            result.add(new Position(this.Position.l, this.Position.c-1));
            if(!this.Parent.isOccupied(new Position(this.Position.l, this.Position.c-2)) && ((this.Position.c-2)>=0)){
                result.add(new Position(this.Position.l, this.Position.c-2));
            }
        }
        if(!this.Parent.isOccupied(new Position(this.Position.l-1, this.Position.c-1)) && ((this.Position.c-1)>=0 &&(this.Position.l-1)>=0)){
            result.add(new Position(this.Position.l-1, this.Position.c-1));
        }
        if(!this.Parent.isOccupied(new Position(this.Position.l+1, this.Position.c+1)) && ((this.Position.c+1)<this.Parent.taille() &&(this.Position.l+1)<this.Parent.taille())){
            result.add(new Position(this.Position.l+1, this.Position.c+1));
        }
        if(!this.Parent.isOccupied(new Position(this.Position.l+1, this.Position.c-1)) && ((this.Position.c-1)>=0 &&(this.Position.l+1)<this.Parent.taille())){
            result.add(new Position(this.Position.l+1, this.Position.c-1));
        }
        if(!this.Parent.isOccupied(new Position(this.Position.l-1, this.Position.c+1)) && ((this.Position.c+1)<this.Parent.taille() &&(this.Position.l-1)>=0)){
            result.add(new Position(this.Position.l-1, this.Position.c+1));
        }
        return result;
    }
}